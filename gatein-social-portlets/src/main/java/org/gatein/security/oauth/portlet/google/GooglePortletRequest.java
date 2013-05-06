/*
 * JBoss, a division of Red Hat
 * Copyright 2013, Red Hat Middleware, LLC, and individual
 * contributors as indicated by the @authors tag. See the
 * copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
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
            System.err.println("Received error from google. Type: " + googleEx.getClass() + ", message: " + googleEx.getMessage());
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
