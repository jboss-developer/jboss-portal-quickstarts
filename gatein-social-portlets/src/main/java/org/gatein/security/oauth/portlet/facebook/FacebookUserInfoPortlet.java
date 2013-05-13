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

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.PortletRequestDispatcher;

import com.restfb.types.User;
import org.gatein.api.oauth.AccessToken;
import org.gatein.api.oauth.OAuthProvider;
import org.gatein.security.oauth.portlet.AbstractSocialPortlet;

/**
 * Very simple portlet for displaying basic information about logged Facebook user
 *
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public class FacebookUserInfoPortlet extends AbstractSocialPortlet {

    @Override
    protected String getOAuthProviderKey() {
        return OAuthProvider.FACEBOOK;
    }

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        AccessToken accessToken = getAccessToken();
        final FacebookClientWrapper facebookClient = new FacebookClientWrapper(request, response, getPortletContext(), getOAuthProvider(), accessToken.getAccessToken());

        User me = facebookClient.getMeWithDetails();

        if (me != null) {
            request.setAttribute("facebookUserInfo", me);
            PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/facebook/userinfo.jsp");
            prd.include(request, response);
        }
    }
}
