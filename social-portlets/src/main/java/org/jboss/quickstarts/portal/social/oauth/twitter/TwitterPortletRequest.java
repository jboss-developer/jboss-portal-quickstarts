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

package org.jboss.quickstarts.portal.social.oauth.twitter;

import java.io.IOException;

import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.gatein.api.oauth.OAuthProvider;
import org.jboss.quickstarts.portal.social.oauth.OAuthPortletFilter;

import twitter4j.TwitterException;

/**
 * Wrapper against some operation call to Twitter backend. It provides especially error handling functionality
 *
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public abstract class TwitterPortletRequest<T> {

    private final RenderRequest request;
    private final RenderResponse response;
    private final PortletContext portletContext;
    private final OAuthProvider oauthProvider;

    public TwitterPortletRequest(RenderRequest request, RenderResponse response, PortletContext portletContext,
            OAuthProvider oauthProvider) {
        this.request = request;
        this.response = response;
        this.portletContext = portletContext;
        this.oauthProvider = oauthProvider;
    }

    protected abstract T invokeRequest() throws TwitterException;

    public T executeRequest() throws IOException, PortletException {
        try {
            return invokeRequest();
        } catch (TwitterException te) {
            String jspErrorPage;
            if (te.getStatusCode() == 401) {
                request.setAttribute(OAuthPortletFilter.ATTRIBUTE_ERROR_MESSAGE, "Twitter access token is invalid.");
                request.setAttribute(OAuthPortletFilter.ATTRIBUTE_OAUTH_PROVIDER, oauthProvider);
                jspErrorPage = "/jsp/error/token.jsp";
            } else {
                request.setAttribute(OAuthPortletFilter.ATTRIBUTE_ERROR_MESSAGE, getErrorMessage(te));
                jspErrorPage = "/jsp/error/error.jsp";
            }

            System.err.println(getErrorMessage(te));
            PortletRequestDispatcher prd = portletContext.getRequestDispatcher(jspErrorPage);
            prd.include(request, response);
        }

        return null;
    }

    private String getErrorMessage(TwitterException te) {
        String errorMessage = "Twitter error occured. StatusCode: " + te.getStatusCode() + ", Details: " + te.getMessage();
        if (te.getCause() != null) {
            errorMessage = errorMessage + ", Cause: " + te.getCause();
        }
        return errorMessage;
    }
}
