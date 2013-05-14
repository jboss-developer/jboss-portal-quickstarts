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

import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import org.gatein.api.oauth.AccessToken;
import org.gatein.api.oauth.OAuthProvider;
import org.gatein.security.oauth.portlet.AbstractSocialPortlet;

/**
 * Very simple portlet for displaying basic information about logged Google user
 *
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public class GoogleUserInfoPortlet extends AbstractSocialPortlet {

    public static final String GOOGLE_USER_INFO = "googleUserInfo";
    private static String REQUIRED_SCOPE = "https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";

    @Override
    protected String getOAuthProviderKey() {
        return OAuthProvider.GOOGLE;
    }

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        AccessToken accessToken = getAccessToken();
        final Oauth2 service = getOAuthProvider().getAuthorizedSocialApiObject(accessToken, Oauth2.class);

        Userinfo userInfo = new GooglePortletRequest<Userinfo>(request, response, getPortletContext(), getOAuthProvider(),
                REQUIRED_SCOPE) {

            @Override
            protected Userinfo invokeRequest() throws IOException {
                return service.userinfo().v2().me().get().execute();
            }

        }.executeRequest();

        if (userInfo != null) {
            request.setAttribute(GOOGLE_USER_INFO, userInfo);
        }

        PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/google/userinfo.jsp");
        prd.include(request, response);
    }
}
