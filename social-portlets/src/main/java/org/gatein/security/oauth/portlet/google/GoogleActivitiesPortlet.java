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
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Activity;
import com.google.api.services.plus.model.ActivityFeed;
import com.google.api.services.plus.model.CommentFeed;
import org.gatein.api.oauth.AccessToken;
import org.gatein.api.oauth.OAuthProvider;
import org.gatein.security.oauth.portlet.AbstractSocialPortlet;

/**
 * Simple portlet for displaying latest activities from your Google+ wall. It's read-only portlet.
 *
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public class GoogleActivitiesPortlet extends AbstractSocialPortlet {

    public static final String REQUIRED_SCOPE = "https://www.googleapis.com/auth/plus.login";

    @Override
    protected String getOAuthProviderKey() {
        return OAuthProvider.GOOGLE;
    }

    // See https://developers.google.com/+/api/latest/activities/list for details
    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        AccessToken accessToken = getAccessToken();
        final Plus service = getOAuthProvider().getAuthorizedSocialApiObject(accessToken, Plus.class);

        final Plus.Activities.List list = service.activities().list("me", "public");
        list.setMaxResults(10L);

        ActivityFeed activityFeed = new GooglePortletRequest<ActivityFeed>(request, response, getPortletContext(),
                getOAuthProvider(), REQUIRED_SCOPE) {

            @Override
            protected ActivityFeed invokeRequest() throws IOException {
                return list.execute();
            }

        }.executeRequest();

        List<GoogleActivityBean> googleActivityBeanList = new ArrayList<GoogleActivityBean>();

        if (activityFeed != null) {
            for (final Activity activity : activityFeed.getItems()) {

                GoogleActivityBean gab = new GoogleActivityBean(activity);
                CommentFeed comments = new GooglePortletRequest<CommentFeed>(request, response, getPortletContext(),
                        getOAuthProvider(), REQUIRED_SCOPE) {

                    @Override
                    protected CommentFeed invokeRequest() throws IOException {
                        return service.comments().list(activity.getId()).execute();
                    }

                }.executeRequest();

                gab.setCommentFeed(comments);

                googleActivityBeanList.add(gab);
            }

            request.setAttribute("googleActivityBeanList", googleActivityBeanList);
            PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/google/activities.jsp");
            prd.include(request, response);
        }
    }
}
