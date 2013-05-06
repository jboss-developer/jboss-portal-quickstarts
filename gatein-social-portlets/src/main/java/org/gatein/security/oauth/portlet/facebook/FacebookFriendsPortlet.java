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
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.restfb.types.NamedFacebookType;
import org.gatein.api.oauth.AccessToken;
import org.gatein.api.oauth.OAuthProvider;
import org.gatein.security.oauth.portlet.AbstractSocialPortlet;

/**
 * Show friends of logged user on Facebook and displays their pictures. It has support for pagination. It has possibility to show
 * last statuses from FB wall of friends or from the FB wall of logged user
 *
 * <p>This portlet is read-only</p>
 *
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public class FacebookFriendsPortlet extends AbstractSocialPortlet {

    private static final String ATTR_FRIENDS_COUNT = "friendsCount";
    private static final String PARAM_PAGE = "_page";
    private static final String PARAM_PERSON_ID = "_personID";
    public static final String PARAM_USER_FILTER = "_userFilter";

    private static final int ITEMS_PER_PAGE = 10;

    public static final String ACTION_USER_FILTER = "_actionUserFilter";
    private static final String BUTTON_TRIGGER_FILTER = "triggerFilter";
    private static final String BUTTON_CANCEL_FILTER = "cancelFilter";

    @ProcessAction(name = ACTION_USER_FILTER)
    public void actionTriggerFilter(ActionRequest aReq, ActionResponse aResp) throws IOException {
        if (aReq.getParameter(BUTTON_TRIGGER_FILTER) != null) {

            // User pressed 'Submit filter'
            getParameterAndSaveItToSession(PARAM_USER_FILTER, aReq, aReq.getPortletSession());
        } else {

            // User pressed 'Cancel filter'
            aReq.getPortletSession().removeAttribute(PARAM_USER_FILTER);
        }
    }

    @Override
    protected String getOAuthProviderKey() {
        return OAuthProvider.FACEBOOK;
    }

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        PortletSession session = request.getPortletSession();
        FacebookBean fb = new FacebookBean();

        AccessToken accessToken = getAccessToken();
        FacebookClientWrapper facebookClient = new FacebookClientWrapper(request, response, getPortletContext(), getOAuthProvider(), accessToken.getAccessToken());

        // Obtain info about "me" including picture and render them
        UserWithPicture me = facebookClient.getMe();
        if (me == null) {
            return;
        }
        FacebookUserBean fbMe = new FacebookUserBean(me);

        String friendId = request.getParameter(PARAM_PERSON_ID);
        if (friendId != null) {
            FacebookUserBean ffb = facebookClient.getStatusesOfPerson(friendId, me.getId(), accessToken);
            if (ffb != null) {
                request.setAttribute("fbFriend", ffb);
            }
        }

        String filter = (String)session.getAttribute(PARAM_USER_FILTER);
        List<String> idsOfFriendsToDisplay;

        if (filter != null) {
            idsOfFriendsToDisplay = facebookClient.getIdsOfFilteredFriends(filter);
        } else {
            idsOfFriendsToDisplay = getIdsOfPaginatedFriends(request, response, session, facebookClient);
            int friendsCount = getFriendsCount(session, facebookClient);
            fbMe.setFriendsNumber(friendsCount);
            fb.setCurrentPageNumber(getCurrentPageNumber(request, session));
            fb.setFriendPaginatorUrls(getPaginatorUrls(friendsCount,response));
        }

        List<FacebookUserBean> fbFriends = facebookClient.getFriends(idsOfFriendsToDisplay);

        request.setAttribute("fbBean", fb);
        request.setAttribute("fbMe", fbMe);
        request.setAttribute("fbFriends", fbFriends);
        PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/facebook/friends.jsp");
        prd.include(request, response);
    }

    private int getFriendsCount(PortletSession session, FacebookClientWrapper facebookClient) throws PortletException, IOException {
        Integer friendsCount = (Integer)session.getAttribute(ATTR_FRIENDS_COUNT);
        if (friendsCount == null) {
            friendsCount = facebookClient.getFriendsCount();
            session.setAttribute(ATTR_FRIENDS_COUNT, friendsCount);
        }
        return friendsCount;
    }

    private int getCurrentPageNumber(RenderRequest request, PortletSession session){
        Integer currentPage;
        if (request.getParameter(PARAM_PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(PARAM_PAGE));
            session.setAttribute(PARAM_PAGE, currentPage);
        } else {
            currentPage = (Integer)session.getAttribute(PARAM_PAGE);
        }
        if (currentPage == null) {
            currentPage = 1;
        }
        return currentPage;
    }

    private List<String> getPaginatorUrls(int friendsCount, RenderResponse response){

        Integer pageCount = ((friendsCount-1) / ITEMS_PER_PAGE) + 1;

        List<String> urls = new ArrayList<String>();

        for (int i=1 ; i<=pageCount ; i++) {
            PortletURL url = response.createRenderURL();
            url.setParameter(PARAM_PAGE,  String.valueOf(i));

            urls.add(url.toString());
        }

        return urls;
    }

    private List<String> getIdsOfPaginatedFriends(RenderRequest request, RenderResponse response, PortletSession session,
                                                  FacebookClientWrapper facebookClient) throws PortletException, IOException {
        // Count total number of friends
        Integer friendsCount = getFriendsCount(session, facebookClient);

        // Obtain number of current page
        Integer currentPage = getCurrentPageNumber(request, session);

        Integer indexStart = (currentPage - 1) * ITEMS_PER_PAGE;
        List<NamedFacebookType> friendsToDisplay = facebookClient.getPageOfFriends(indexStart, ITEMS_PER_PAGE);
        getPaginatorUrls(friendsCount, response);

        // Collect IDS of friends to display
        List<String> ids = new ArrayList<String>();
        for (NamedFacebookType current : friendsToDisplay) {
            ids.add(current.getId());
        }

        return ids;
    }

}
