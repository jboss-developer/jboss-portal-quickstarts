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

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.PortletRequestDispatcher;

import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.PeopleFeed;
import org.gatein.api.oauth.AccessToken;
import org.gatein.api.oauth.OAuthProvider;
import org.gatein.security.oauth.portlet.AbstractSocialPortlet;

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
        PaginationState pgState = (PaginationState)session.getAttribute(ATTR_PAGINATION_CONTEXT);
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

        PeopleFeed peopleFeed = new GooglePortletRequest<PeopleFeed>(request, response, getPortletContext(), getOAuthProvider(), REQUIRED_SCOPE) {

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
