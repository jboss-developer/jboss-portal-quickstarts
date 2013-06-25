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

package org.gatein.security.oauth.portlet.facebook;

import java.io.IOException;

import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.restfb.exception.FacebookException;
import com.restfb.exception.FacebookNetworkException;
import com.restfb.exception.FacebookOAuthException;
import org.gatein.api.oauth.OAuthProvider;
import org.gatein.security.oauth.portlet.OAuthPortletFilter;

/**
 * Wrapper against some operation call to Facebook backend. It provides especially error handling functionality
 *
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
abstract class FacebookPortletRequest<T> {

    private final RenderRequest request;
    private final RenderResponse response;
    private final PortletContext portletContext;
    private final OAuthProvider oauthProvider;

    public FacebookPortletRequest(RenderRequest request, RenderResponse response, PortletContext portletContext,
            OAuthProvider oauthPr) {
        this.request = request;
        this.response = response;
        this.portletContext = portletContext;
        this.oauthProvider = oauthPr;
    }

    protected abstract T invokeRequest() throws FacebookException;

    public T executeRequest() throws IOException, PortletException {
        try {
            return invokeRequest();
        } catch (FacebookOAuthException foe) {
            String exMessage = foe.getErrorCode() + " - " + foe.getErrorType() + " - " + foe.getErrorMessage();
            System.err.println(exMessage);

            String jspPage;
            if (foe.getErrorCode() == 190 || foe.getErrorCode() >= 200 && foe.getErrorCode() <= 299) {
                // Token error occured
                request.setAttribute(OAuthPortletFilter.ATTRIBUTE_ERROR_MESSAGE, oauthProvider.getFriendlyName()
                        + " access token is invalid.");
                request.setAttribute(OAuthPortletFilter.ATTRIBUTE_OAUTH_PROVIDER, oauthProvider);
                jspPage = "/jsp/error/token.jsp";
            } else {
                request.setAttribute(OAuthPortletFilter.ATTRIBUTE_ERROR_MESSAGE,
                        "Error occured when calling Facebook operation. Details: " + exMessage);
                jspPage = "/jsp/error/error.jsp";
            }
            PortletRequestDispatcher prd = portletContext.getRequestDispatcher(jspPage);
            prd.include(request, response);
        } catch (FacebookNetworkException fne) {
            String exMessage = "Network error when connecting with Facebook: " + fne.getMessage();
            System.err.println(exMessage);
            request.setAttribute(OAuthPortletFilter.ATTRIBUTE_ERROR_MESSAGE, exMessage);
            PortletRequestDispatcher prd = portletContext.getRequestDispatcher("/jsp/error/error.jsp");
            prd.include(request, response);
        }

        return null;
    }
}
