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

package org.gatein.quickstart.cdi.scope;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author <a href="http://community.jboss.org/people/kenfinni">Ken Finnigan</a>
 */
public abstract class JSPAbstractPortlet extends GenericPortlet {

    public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        request.setAttribute("portletName", getPortletName());
        String reset = request.getParameter("reset");
        if ((null != reset || "true".equals(reset)) || null == getBean().getText()) {
            PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/welcome.jsp");
            prd.include(request, response);
        } else {
            PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/hello.jsp");
            if (null != getResourceBean() && "ResourceBean".equals(getResourceBean().getText())) {
                getResourceBean().setText("ResourceBean Render");
            }
            request.setAttribute("bean", getBean());
            request.setAttribute("resourceBean", getResourceBean());
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
        String text = getBean().getText();
        text = text == null ? "null" : "'" + text + "'";
        if ("check".equals(resourceId)) {
            PrintWriter writer = response.getWriter();
            writer.write("bean.text is currently set to " + text +".");
        } else if ("set".equals(resourceId)) {
            PrintWriter writer = response.getWriter();
            getBean().setText("Ajax");
            writer.write("bean.text was set to " + text + ", but is now set to 'Ajax'.");
        } else if ("resource".equals(resourceId)) {
            PrintWriter writer = response.getWriter();
            AbstractBean rBean = getResourceBean();
            if (null != rBean) {
                String rbText = rBean.getText();
                rbText = rbText == null ? "null" : "'" + rbText + "'";
                rBean.setText("Ajax");
                writer.write("resourceBean.text was set to " + rbText + ", but now set to 'Ajax'.");
            } else {
                writer.write("resourceBean was null.");
            }
        }
    }

    protected abstract AbstractBean getBean();

    protected abstract AbstractBean getResourceBean();
}
