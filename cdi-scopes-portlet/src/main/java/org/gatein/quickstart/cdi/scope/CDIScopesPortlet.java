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

package org.gatein.quickstart.cdi.scope;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;


/**
 * A portlet demostrating the injection of beans living in portlet-specific CDI scopes {@code @PortletLifecycleScoped} and
 * {@code @PortletRedisplayScoped}. See also <a id="gatein.devguide.cdi.scope.portlets.url"
 * href="https://docs.jboss.org/author/display/GTNPORTAL36/Portlet+CDI+Scopes">Portlet CDI Scopes</a> Section of <span
 * id="compatibility.portal.projectNameShort">GateIn</span> Developer Guide.
 *
 * @author <a href="http://community.jboss.org/people/kenfinni">Ken Finnigan</a>
 * @author <a href="ppalaga@redhat.com">Peter Palaga</a>
 */
public class CDIScopesPortlet extends GenericPortlet {
    private static final Logger log = Logger.getLogger(CDIScopesPortlet.class.getName());

    /**
     * A {@code @PortletLifecycleScoped} bean.
     */
    @Inject
    private LifecycleScopedBean lifecycleScopedBean;

    /**
     * A @PortletRedisplayScoped bean.
     */
    @Inject
    private RedisplayScopedBean redisplayScopedBean;

    /**
     * {@code @PortletRedisplayScoped} beans come in here as they were left by the last invocations of
     * {@link #processAction(ActionRequest, ActionResponse)} or
     * {@link #processEvent(javax.portlet.EventRequest, javax.portlet.EventResponse)}.
     *
     * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
     */
    public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/cdi-scopes-portlet.jsp");
        request.setAttribute("lifecycleScopedBean", lifecycleScopedBean);
        request.setAttribute("redisplayScopedBean", redisplayScopedBean);
        prd.include(request, response);
    }

    /**
     * Both {@code @PortletLifecycleScoped} and {@code @PortletRedisplayScoped} beans are created anew on each
     * {@link ActionRequest} or {@link EventRequest}.
     *
     * @see javax.portlet.GenericPortlet#processAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
     */
    public void processAction(ActionRequest aRequest, ActionResponse aResponse) throws PortletException, IOException {
        String text = aRequest.getParameter("text");
        if (aRequest.getParameter("lifecycleScopedBean.setText()") != null) {
            lifecycleScopedBean.setText(text);
        } else if (aRequest.getParameter("redisplayScopedBean.setText()") != null) {
            redisplayScopedBean.setText(text);
        } else if (aRequest.getParameter("reset") != null) {
            /*
             * At this place, we do not need to do anything to reset the beans because both @PortletLifecycleScoped and
             *
             * @PortletRedisplayScoped beans are created anew on each ActionRequest.
             */
        } else {
            log.warning("Unexpected ActionRequest.");
        }
    }

    /**
     * Note that serving a {@link ResourceRequest} during {@link #serveResource(ResourceRequest, ResourceResponse)} is
     * considered separate from the regular Portlet Lifecycle, so any changes to a CDI bean within a ResourceRequest will not be
     * reflected in the instance of the bean used during an ActionRequest, EventRequest or RenderRequest, just as any changes to
     * bean state within these requests will not be reflected in the instance that a ResourceRequest sees.
     *
     * @see javax.portlet.GenericPortlet#serveResource(javax.portlet.ResourceRequest, javax.portlet.ResourceResponse)
     */
    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, IOException {
        String resourceId = request.getResourceID();
        if ("lifecycleScopedBean.getText()".equals(resourceId)) {
            response.getWriter().write("lifecycleScopedBean.text is currently set to '" + lifecycleScopedBean.getText() + "'.");
        } else if ("lifecycleScopedBean.setText()".equals(resourceId)) {
            String text = lifecycleScopedBean.getText();
            lifecycleScopedBean.setText("Ajax");
            response.getWriter().write("lifecycleScopedBean.text was set to '" + text + "', but is now set to 'Ajax'.");
        } else if ("redisplayScopedBean.getText()".equals(resourceId)) {
            response.getWriter().write("redisplayScopedBean.text is currently set to '" + redisplayScopedBean.getText() + "'.");
        } else if ("redisplayScopedBean.setText()".equals(resourceId)) {
            String text = redisplayScopedBean.getText();
            redisplayScopedBean.setText("Ajax");
            response.getWriter().write("redisplayScopedBean.text was set to '" + text + "', but is now set to 'Ajax'.");
        } else {
            response.getWriter().write("Unexpected resourceId '" + resourceId + "'.");
            log.warning("Unexpected resourceId '" + resourceId + "'.");
        }
    }

}
