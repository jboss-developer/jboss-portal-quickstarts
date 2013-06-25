/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gatein.security.oauth.portlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.ActionFilter;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.RenderFilter;

import org.gatein.api.PortalRequest;
import org.gatein.api.oauth.AccessToken;
import org.gatein.api.oauth.OAuthProvider;
import org.gatein.api.oauth.exception.OAuthApiException;
import org.gatein.api.oauth.exception.OAuthApiExceptionCode;

/**
 * Portlet filter, which is used to obtain access token for given user from portal DB and save it to CDI request scoped object.
 * of type {@link RequestContext}. So portlets can simply read access token without need to obtain it again.
 *
 * <p>
 * It also performs checks if access token is valid. In case that this user doesn't have access token or his access token is
 * invalid/expired, the filter will redirect to error screen and user needs to authenticate through OAuth workflow to obtain
 * correct access token
 * </p>
 *
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public class OAuthPortletFilter implements ActionFilter, RenderFilter {

    public static final String ATTRIBUTE_ACCESS_TOKEN = "_attrAccessToken";
    public static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
    public static final String ATTRIBUTE_OAUTH_PROVIDER = "oauthProvider";

    public static final String INIT_PARAM_ACCESS_TOKEN_VALIDATION = "accessTokenValidation";
    public static final String INIT_PARAM_OAUTH_PROVIDER_KEY = "oauthProviderKey";

    private enum AccessTokenValidation {
        SKIP, SESSION, ALWAYS
    }

    private FilterConfig filterConfig;
    private AccessTokenValidation accessTokenValidation;
    private String oauthProviderKey;

    @Inject
    private RequestContext requestContext;

    @Override
    public void init(FilterConfig filterConfig) throws PortletException {
        this.filterConfig = filterConfig;

        String accessTokenValidation = filterConfig.getInitParameter(INIT_PARAM_ACCESS_TOKEN_VALIDATION);
        if (AccessTokenValidation.ALWAYS.name().equals(accessTokenValidation)) {
            this.accessTokenValidation = AccessTokenValidation.ALWAYS;
        } else if (AccessTokenValidation.SKIP.name().equals(accessTokenValidation)) {
            this.accessTokenValidation = AccessTokenValidation.SKIP;
        } else {
            // SESSION is default validation type
            this.accessTokenValidation = AccessTokenValidation.SESSION;
        }

        this.oauthProviderKey = filterConfig.getInitParameter(INIT_PARAM_OAUTH_PROVIDER_KEY);
        if (oauthProviderKey == null) {
            throw new PortletException("Init parameter '" + INIT_PARAM_OAUTH_PROVIDER_KEY + "' needs to be provided");
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain) throws IOException,
            PortletException {
        String username = request.getRemoteUser();

        if (username == null) {
            request.setAttribute(ATTRIBUTE_ERROR_MESSAGE, "No content available for anonymous user. You need to login first.");
            PortletRequestDispatcher prd = filterConfig.getPortletContext().getRequestDispatcher("/jsp/error/error.jsp");
            prd.include(request, response);
            return;
        }

        OAuthProvider oauthProvider = getOAuthProvider();

        if (oauthProvider == null) {
            String errorMessage = "OAuth provider '" + oauthProviderKey + "' not available";
            request.setAttribute(ATTRIBUTE_ERROR_MESSAGE, errorMessage);
            PortletRequestDispatcher prd = filterConfig.getPortletContext().getRequestDispatcher("/jsp/error/error.jsp");
            prd.include(request, response);
            return;
        }

        AccessToken accessToken = loadAccessTokenOrRedirectToObtainIt(username, oauthProvider, request, response);
        if (accessToken != null) {
            accessToken = validateAccessToken(request, response, oauthProvider, accessToken);
            if (accessToken != null) {
                requestContext.saveOAuthInfo(oauthProvider, accessToken);
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void doFilter(ActionRequest request, ActionResponse response, FilterChain chain) throws IOException,
            PortletException {
        String username = request.getRemoteUser();
        OAuthProvider oauthProvider = getOAuthProvider();
        AccessToken accessToken;
        if (username != null && oauthProvider != null) {
            accessToken = oauthProvider.loadAccessToken(username);
        } else {
            accessToken = null;
        }

        if (oauthProvider != null) {
            requestContext.saveOAuthInfo(oauthProvider, accessToken);
        }

        chain.doFilter(request, response);
    }

    // Read access token from DB and display error message if it's not available
    protected AccessToken loadAccessTokenOrRedirectToObtainIt(String username, OAuthProvider oauthProvider,
            RenderRequest request, RenderResponse response) throws IOException, PortletException {
        // Try requestContext first. Otherwise obtain OAuthProvider via API
        AccessToken accessToken = requestContext.getAccessToken(oauthProviderKey);
        if (accessToken == null) {
            accessToken = oauthProvider.loadAccessToken(username);
        }

        if (accessToken == null) {
            // Will be processed by method AbstractSocialPortlet.actionRedirectToOAuthFlow
            PortletURL actionURL = response.createActionURL();
            actionURL.setParameter(ActionRequest.ACTION_NAME, AbstractSocialPortlet.ACTION_OAUTH_REDIRECT);

            request.setAttribute(ATTRIBUTE_ERROR_MESSAGE, oauthProvider.getFriendlyName()
                    + " access token not available for you.");
            request.setAttribute(ATTRIBUTE_OAUTH_PROVIDER, oauthProvider);
            PortletRequestDispatcher prd = filterConfig.getPortletContext().getRequestDispatcher("/jsp/error/token.jsp");
            prd.include(request, response);
        }

        return accessToken;
    }

    // Validate obtained access token with usage of concrete OAuthProvider and save it to session if it's valid
    protected AccessToken validateAccessToken(PortletRequest request, PortletResponse response, OAuthProvider oauthProvider,
            AccessToken accessToken) throws PortletException, IOException {
        AccessToken previousAccessToken = (AccessToken) request.getPortletSession().getAttribute(ATTRIBUTE_ACCESS_TOKEN);

        if (isValidationNeeded(accessToken, previousAccessToken)) {
            // Validate accessToken
            try {
                accessToken = getOAuthProvider().validateTokenAndUpdateScopes(accessToken);
            } catch (OAuthApiException oe) {
                String jspPage;
                if (oe.getExceptionCode() == OAuthApiExceptionCode.ACCESS_TOKEN_ERROR) {
                    request.setAttribute(ATTRIBUTE_ERROR_MESSAGE, oauthProvider.getFriendlyName() + " access token is invalid.");
                    request.setAttribute(ATTRIBUTE_OAUTH_PROVIDER, oauthProvider);
                    jspPage = "/jsp/error/token.jsp";
                } else if (oe.getExceptionCode() == OAuthApiExceptionCode.IO_ERROR) {
                    oe.printStackTrace();
                    request.setAttribute(ATTRIBUTE_ERROR_MESSAGE, "I/O error happened. See server.log for more details");
                    jspPage = "/jsp/error/error.jsp";
                } else {
                    // Some unexpected error
                    throw new PortletException(oe);
                }

                PortletRequestDispatcher prd = filterConfig.getPortletContext().getRequestDispatcher(jspPage);
                prd.include(request, response);
                return null;
            }

            if (!accessToken.equals(previousAccessToken)) {
                saveAccessToken(request, response, accessToken);
            }
        }

        return accessToken;
    }

    protected OAuthProvider getOAuthProvider() {
        // Try requestContext first. Otherwise obtain OAuthProvider via API
        OAuthProvider provider = requestContext.getOAuthProvider(oauthProviderKey);
        return provider != null ? provider : PortalRequest.getInstance().getPortal().getOAuthProvider(oauthProviderKey);
    }

    protected void saveAccessToken(PortletRequest req, PortletResponse res, AccessToken accessToken) {
        req.getPortletSession().setAttribute(ATTRIBUTE_ACCESS_TOKEN, accessToken);

        // Update existing access token in DB if it's different from the validated access token. It could be the case with
        // Google when
        // token could be refreshed.
        AccessToken existingAccessToken = getOAuthProvider().loadAccessToken(req.getRemoteUser());
        if (accessToken != null && !accessToken.equals(existingAccessToken)) {
            getOAuthProvider().saveAccessToken(req.getRemoteUser(), accessToken);
        }
    }

    private boolean isValidationNeeded(AccessToken accessToken, AccessToken previousAccessToken) {
        if (accessTokenValidation == AccessTokenValidation.ALWAYS) {
            return true;
        } else if (accessTokenValidation == AccessTokenValidation.SKIP) {
            return false;
        } else {
            return !accessToken.equals(previousAccessToken);
        }
    }
}
