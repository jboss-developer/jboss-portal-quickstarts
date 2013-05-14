/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
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

package org.gatein.security.oauth.portlet.google;

import java.io.IOException;

import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import org.gatein.api.oauth.OAuthProvider;
import org.gatein.security.oauth.portlet.AbstractSocialPortlet;
import org.gatein.security.oauth.portlet.OAuthPortletFilter;

/**
 * Wrapper against some operation call to Google+ backend. It provides especially error handling functionality
 *
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public abstract class GooglePortletRequest<T> {

    private final RenderRequest request;
    private final RenderResponse response;
    private final PortletContext portletContext;
    private final OAuthProvider oauthProvider;
    private final String requiredScope;

    public GooglePortletRequest(RenderRequest request, RenderResponse response, PortletContext portletContext,
            OAuthProvider oauthPr, String requiredScope) {
        this.request = request;
        this.response = response;
        this.portletContext = portletContext;
        this.oauthProvider = oauthPr;
        this.requiredScope = requiredScope;
    }

    protected abstract T invokeRequest() throws IOException;

    public T executeRequest() throws PortletException, IOException {
        String jspErrorPage;

        try {
            return invokeRequest();
        } catch (GoogleJsonResponseException googleEx) {
            String message = oauthProvider.getFriendlyName() + " access token is invalid or scope is insufficient.";
            if (requiredScope != null) {
                message = message + "You will need scope: " + requiredScope + "<br>";
                request.setAttribute(AbstractSocialPortlet.PARAM_CUSTOM_SCOPE, requiredScope);
            }
            request.setAttribute(OAuthPortletFilter.ATTRIBUTE_ERROR_MESSAGE, message);
            request.setAttribute(OAuthPortletFilter.ATTRIBUTE_OAUTH_PROVIDER, oauthProvider);
            System.err.println("Received error from google. Type: " + googleEx.getClass() + ", message: "
                    + googleEx.getMessage());
            jspErrorPage = "/jsp/error/token.jsp";
        } catch (IOException ioe) {
            String errorMessage = "Received IO error from google. Type: " + ioe.getClass() + ", message: " + ioe.getMessage();
            System.err.println(errorMessage);
            request.setAttribute(OAuthPortletFilter.ATTRIBUTE_ERROR_MESSAGE, errorMessage);
            jspErrorPage = "/jsp/error/error.jsp";
        }

        PortletRequestDispatcher prd = portletContext.getRequestDispatcher(jspErrorPage);
        prd.include(request, response);
        return null;
    }

}
