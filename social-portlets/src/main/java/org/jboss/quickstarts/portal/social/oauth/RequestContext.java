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

package org.jboss.quickstarts.portal.social.oauth;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;

import org.gatein.api.oauth.AccessToken;
import org.gatein.api.oauth.OAuthProvider;

/**
 * Request scoped object holding all needed OAuth info. It's initialized by {@link OAuthPortletFilter} and then used by portlets
 *
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
@RequestScoped
public class RequestContext {

    private Map<String, OAuthContext> contextMap;

    public void saveOAuthInfo(OAuthProvider oauthProvider, AccessToken accessToken) {
        if (contextMap == null) {
            contextMap = new HashMap<String, OAuthContext>();
        }
        contextMap.put(oauthProvider.getKey(), new OAuthContext(oauthProvider, accessToken));
    }

    public OAuthProvider getOAuthProvider(String oauthProviderKey) {
        OAuthContext ctx = getOAuthContext(oauthProviderKey);
        return ctx != null ? ctx.provider : null;
    }

    public AccessToken getAccessToken(String oauthProviderKey) {
        OAuthContext ctx = getOAuthContext(oauthProviderKey);
        return ctx != null ? ctx.accessToken : null;
    }

    private OAuthContext getOAuthContext(String oauthProviderKey) {
        if (contextMap == null) {
            return null;
        } else {
            return contextMap.get(oauthProviderKey);
        }
    }

    private static class OAuthContext {

        private final OAuthProvider provider;
        private final AccessToken accessToken;

        private OAuthContext(OAuthProvider provider, AccessToken accessToken) {
            this.provider = provider;
            this.accessToken = accessToken;
        }
    }
}
