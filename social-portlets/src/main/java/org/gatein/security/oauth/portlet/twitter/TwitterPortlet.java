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
