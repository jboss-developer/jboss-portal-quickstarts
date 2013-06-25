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
        final FacebookClientWrapper facebookClient = new FacebookClientWrapper(request, response, getPortletContext(),
                getOAuthProvider(), accessToken.getAccessToken());

        User me = facebookClient.getMeWithDetails();

        if (me != null) {
            request.setAttribute("facebookUserInfo", me);
            PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/facebook/userinfo.jsp");
            prd.include(request, response);
        }
    }
}
