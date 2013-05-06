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

    public FacebookPortletRequest(RenderRequest request, RenderResponse response, PortletContext portletContext, OAuthProvider oauthPr) {
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
            if (foe.getErrorCode() == 190 || foe.getErrorCode() >= 200 && foe.getErrorCode() <=299) {
                // Token error occured
                request.setAttribute(OAuthPortletFilter.ATTRIBUTE_ERROR_MESSAGE, oauthProvider.getFriendlyName() + " access token is invalid.");
                request.setAttribute(OAuthPortletFilter.ATTRIBUTE_OAUTH_PROVIDER, oauthProvider);
                jspPage = "/jsp/error/token.jsp";
            } else {
                request.setAttribute(OAuthPortletFilter.ATTRIBUTE_ERROR_MESSAGE, "Error occured when calling Facebook operation. Details: " + exMessage);
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
