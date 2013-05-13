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

package org.gatein.cdi;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author <a href="http://community.jboss.org/people/kenfinni">Ken Finnigan</a>
 */
public abstract class JSPAbstractPortlet extends GenericPortlet {

    public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        String reset = request.getParameter("reset");
        if ((null != reset || "true".equals(reset)) || null == getBean().getText()) {
            PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/welcome.jsp");
            prd.include(request, response);
        } else {
            PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/hello.jsp");
            request.setAttribute("bean", getBean());
            prd.include(request, response);
        }
    }

    public void processAction(ActionRequest aRequest, ActionResponse aResponse) throws PortletException, IOException {
        String text = aRequest.getParameter("text");
        getBean().setText(text);
    }

    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, IOException {
        String resourceId = request.getResourceID();
        if ("check".equals(resourceId)) {
            PrintWriter writer = response.getWriter();
            String text = getBean().getText();
            writer.write("Bean text currently set to: " + text);
        } else if ("set".equals(resourceId)) {
            PrintWriter writer = response.getWriter();
            String text = getBean().getText();
            getBean().setText("Ajax");
            writer.write("Bean text was set to: " + text + ", but now set to Ajax!");
        }
    }

    protected abstract AbstractBean getBean();
}
