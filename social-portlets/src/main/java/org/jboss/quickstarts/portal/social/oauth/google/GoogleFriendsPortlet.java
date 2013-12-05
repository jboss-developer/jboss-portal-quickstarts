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

package org.jboss.quickstarts.portal.social.oauth.google;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.PortletRequestDispatcher;

import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.PeopleFeed;

import org.gatein.api.oauth.AccessToken;
import org.gatein.api.oauth.OAuthProvider;
import org.jboss.quickstarts.portal.social.oauth.AbstractSocialPortlet;

/**
 * Simple portlet for displaying Google+ friends and their photos. It supports pagination. It's read-only portlet.
 *
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public class GoogleFriendsPortlet extends AbstractSocialPortlet {

    private static final String ATTR_PAGINATION_CONTEXT = "paginationContext";
    private static final String PARAM_PAGE = "page";
    private static final String PREV = "prev";
    private static final String NEXT = "next";
    public static final String REQUIRED_SCOPE = "https://www.googleapis.com/auth/plus.login";

    @Override
    protected String getOAuthProviderKey() {
        return OAuthProvider.GOOGLE;
    }

    // See https://developers.google.com/+/api/latest/people/list for details
    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        AccessToken accessToken = getAccessToken();
        final Plus service = getOAuthProvider().getAuthorizedSocialApiObject(accessToken, Plus.class);

        final Plus.People.List list = service.people().list("me", "visible");

        // Possible values are "alphabetical", "best"
        list.setOrderBy("alphabetical");
        list.setMaxResults(10L);

        // Try to obtain last pagination token
        PortletSession session = request.getPortletSession();
        PaginationState pgState = (PaginationState) session.getAttribute(ATTR_PAGINATION_CONTEXT);
        if (pgState == null) {
            pgState = new PaginationState();
        }

        // Try to update pgState with number of current page
        String pageParam = request.getParameter(PARAM_PAGE);
        if (pageParam != null) {
            if (PREV.equals(pageParam)) {
                pgState.decreaseCurrentPage();
            } else if (NEXT.equals(pageParam)) {
                pgState.increaseCurrentPage();
            } else {
                throw new PortletException("Illegal value of request parameter 'page'. Value was " + pageParam);
            }
        }

        list.setPageToken(pgState.getTokenOfCurrentPage());

        PeopleFeed peopleFeed = new GooglePortletRequest<PeopleFeed>(request, response, getPortletContext(),
                getOAuthProvider(), REQUIRED_SCOPE) {

            @Override
            protected PeopleFeed invokeRequest() throws IOException {
                return list.execute();
            }

        }.executeRequest();

        if (peopleFeed != null) {
            request.setAttribute("googleFriendsList", peopleFeed);
            request.setAttribute("pgState", pgState);

            // Obtain next token to session if it's available
            String nextPageToken = peopleFeed.getNextPageToken();

            // Show link for next page
            if (nextPageToken != null) {
                pgState.setTokenForPage(pgState.getCurrentPage() + 1, nextPageToken);
            }

            session.setAttribute(ATTR_PAGINATION_CONTEXT, pgState);

            PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/google/friends.jsp");
            prd.include(request, response);
        }
    }
}
