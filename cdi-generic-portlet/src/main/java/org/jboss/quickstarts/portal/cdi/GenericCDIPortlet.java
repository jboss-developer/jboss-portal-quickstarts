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

package org.jboss.quickstarts.portal.cdi;

import javax.inject.Inject;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * A minimal Portlet using CDI.
 *
 * @author <a href="http://community.jboss.org/people/kenfinni">Ken Finnigan</a>
 */
public class GenericCDIPortlet extends GenericPortlet {
    private static final Logger log = Logger.getLogger(GenericCDIPortlet.class.getName());

    /**
     * Java EE Container injects a Request Scoped {@link DataBean} for us here.
     */
    @Inject
    private DataBean bean;

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.write("<div style=\"margin: 10px;\">\n");
        writer.write("<h1 style=\"font-size:16px;background-color:#dedfdf;padding:2px 4px;margin-bottom:2px;margin-bottom:2px;\">CDI Generic Portlet</h1>\n");
        writer.write("<p style=\"margin-left: 4px;\">Message from DataBean is: " + bean.getMessage() +"</p>\n");
        writer.write("</div>\n");
        log.info("doView() finished");
    }
}
