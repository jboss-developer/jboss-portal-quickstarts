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
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookNetworkException;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.FacebookType;
import org.gatein.api.oauth.AccessToken;
import org.gatein.api.oauth.OAuthProvider;
import org.gatein.security.oauth.portlet.AbstractSocialPortlet;

/**
 * Portlet for sending status update on FB wall of logged user
 *
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public class FacebookStatusUpdatePortlet extends AbstractSocialPortlet {

    public static final String ACTION_UPDATE_STATUS = "_updateStatus";
    public static final String ACTION_BACK = "_backToForm";

    public static final String RENDER_PARAM_STATUS = "renderParamStatus";
    public static final String RENDER_PARAM_ERROR_MESSAGE = "renderParamErrorMessage";

    public static final String PARAM_MESSAGE = "message";
    public static final String PARAM_LINK = "link";
    public static final String PARAM_PICTURE = "picture";
    public static final String PARAM_NAME = "name";
    public static final String PARAM_CAPTION = "caption";
    public static final String PARAM_DESCRIPTION = "description";

    public enum Status {
        SUCCESS,
        NOT_SPECIFIED_MESSAGE_OR_LINK,
        FACEBOOK_ERROR_INSUFFICIENT_SCOPE,
        FACEBOOK_ERROR_OTHER
    }

    @Override
    protected String getOAuthProviderKey() {
        return OAuthProvider.FACEBOOK;
    }

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        PortletSession session = request.getPortletSession();

        // Refresh form values in view
        refreshParameter(PARAM_MESSAGE, request, session);
        refreshParameter(PARAM_LINK, request, session);
        refreshParameter(PARAM_PICTURE, request, session);
        refreshParameter(PARAM_NAME, request, session);
        refreshParameter(PARAM_CAPTION, request, session);
        refreshParameter(PARAM_DESCRIPTION, request, session);

        PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/facebook/statusupdate.jsp");
        prd.include(request, response);
    }


    @ProcessAction(name = ACTION_UPDATE_STATUS)
    public void actionUpdateStatus(ActionRequest aReq, ActionResponse aResp) throws IOException {
        PortletSession session = aReq.getPortletSession();

        String message = getParameterAndSaveItToSession(PARAM_MESSAGE, aReq, session);
        String link = getParameterAndSaveItToSession(PARAM_LINK, aReq, session);
        String picture = getParameterAndSaveItToSession(PARAM_PICTURE, aReq, session);
        String name = getParameterAndSaveItToSession(PARAM_NAME, aReq, session);
        String caption = getParameterAndSaveItToSession(PARAM_CAPTION, aReq, session);
        String description = getParameterAndSaveItToSession(PARAM_DESCRIPTION, aReq, session);

        if (isEmpty(message) && isEmpty(link)) {
            aResp.setRenderParameter(RENDER_PARAM_STATUS, Status.NOT_SPECIFIED_MESSAGE_OR_LINK.name());
            return;
        }

        // Obtain accessToken directly from portlet session
        AccessToken accessToken = getAccessToken();

        // This could happen if session expired
        if (accessToken == null) {
            System.err.println("accessToken not found! Maybe portlet session expired");
            return;
        }

        FacebookClient facebookClient = new DefaultFacebookClient(accessToken.getAccessToken());
        List<Parameter> params = new ArrayList<Parameter>();
        appendParam(params, PARAM_MESSAGE, message);
        appendParam(params, PARAM_LINK, link);
        appendParam(params, PARAM_PICTURE, picture);
        appendParam(params, PARAM_NAME, name);
        appendParam(params, PARAM_CAPTION, caption);
        appendParam(params, PARAM_DESCRIPTION, description);

        try {
            FacebookType publishMessageResponse = facebookClient.publish("me/feed", FacebookType.class, params.toArray(new Parameter[] {}));
            if (publishMessageResponse.getId() != null) {
                aResp.setRenderParameter(RENDER_PARAM_STATUS, Status.SUCCESS.name());
            }
        } catch (FacebookOAuthException foe) {
            String exMessage = foe.getErrorCode() + " - " + foe.getErrorType() + " - " + foe.getErrorMessage();
            System.err.println(exMessage);

            if (foe.getErrorCode() == 190 || foe.getErrorCode() >= 200 && foe.getErrorCode() <=299) {
                // Token error occured
                aResp.setRenderParameter(RENDER_PARAM_STATUS, Status.FACEBOOK_ERROR_INSUFFICIENT_SCOPE.name());
            } else {
                // Other error occured (like invalid URL of link)
                aResp.setRenderParameter(RENDER_PARAM_STATUS, Status.FACEBOOK_ERROR_OTHER.name());
                aResp.setRenderParameter(RENDER_PARAM_ERROR_MESSAGE, exMessage);
            }
        } catch (FacebookNetworkException fne) {
            String exMessage = "Network error when connecting with Facebook: " + fne.getMessage();
            System.err.println(exMessage);
            aResp.setRenderParameter(RENDER_PARAM_STATUS, Status.FACEBOOK_ERROR_OTHER.name());
            aResp.setRenderParameter(RENDER_PARAM_ERROR_MESSAGE, exMessage);
        }
    }

    @ProcessAction(name = ACTION_BACK)
    public void actionBack(ActionRequest aReq, ActionResponse aResp) throws IOException {
        aResp.removePublicRenderParameter(RENDER_PARAM_STATUS);
        aResp.removePublicRenderParameter(RENDER_PARAM_ERROR_MESSAGE);
    }

    private boolean isEmpty(String message) {
        return message == null || message.length() == 0;
    }

    private void appendParam(List<Parameter> params, String paramName, String paramValue) {
        if (paramValue != null) {
            params.add(Parameter.with(paramName, paramValue));
        }
    }

    private void refreshParameter(String paramName, RenderRequest request, PortletSession session) {
        String paramValue = getParameterAndSaveItToSession(paramName, request, session);
        request.setAttribute(paramName, paramValue);
    }
}
