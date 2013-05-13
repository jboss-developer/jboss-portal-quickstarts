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


package org.gatein.security.oauth.portlet.twitter;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.gatein.api.oauth.AccessToken;
import org.gatein.api.oauth.OAuthProvider;
import org.gatein.security.oauth.portlet.AbstractSocialPortlet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * Portlet for showing some basic informations about user from Twitter (His name, picture, number of followers, last tweets etc)
 * Portlet is read-only (doesn't support writing to Twitter)
 *
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public class TwitterPortlet extends AbstractSocialPortlet {

    @Override
    protected String getOAuthProviderKey() {
        return OAuthProvider.TWITTER;
    }

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        AccessToken accessToken = getAccessToken();
        final Twitter twitter = getOAuthProvider().getAuthorizedSocialApiObject(accessToken, Twitter.class);

        User twitterUser = new TwitterPortletRequest<User>(request, response, getPortletContext(), getOAuthProvider()) {

            @Override
            protected User invokeRequest() throws TwitterException {
                return twitter.verifyCredentials();
            }

        }.executeRequest();

        if (twitterUser != null) {
            request.setAttribute("twitterUserInfo", twitterUser);
            PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/twitter/userinfo.jsp");
            prd.include(request, response);
        }
    }
}
