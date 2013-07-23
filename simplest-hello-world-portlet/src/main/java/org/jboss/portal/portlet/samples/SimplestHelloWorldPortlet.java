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

package org.jboss.portal.portlet.samples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.portlet.GenericPortlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * The simplest posible Portlet.
 *
 * @author Peter Palaga
 */
public class SimplestHelloWorldPortlet extends GenericPortlet {
    private static final Logger log = Logger.getLogger(SimplestHelloWorldPortlet.class.getName());

    /**
     * Serves the VIEW mode. Writes "Hello World !" to the response writer.
     *
     * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
     */
    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write("<h1>Hello World !</h1>");
        writer.close();
        log.info("Hello World !");
    }
}
