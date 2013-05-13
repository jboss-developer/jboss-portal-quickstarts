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

package org.gatein.security.oauth.portlet;

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
