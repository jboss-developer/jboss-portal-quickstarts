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

package org.exoplatform.sample.portal.web;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.services.listener.Event;
import org.exoplatform.services.listener.Listener;

import javax.servlet.http.HttpSessionEvent;

/**
 * Created by The eXo Platform SAS Author : Nicolas Filotto nicolas.filotto@exoplatform.com 28 sept. 2009
 */
public class SampleHttpSessionCreatedListener extends Listener<PortalContainer, HttpSessionEvent> {

    @Override
    public void onEvent(Event<PortalContainer, HttpSessionEvent> event) throws Exception {
        System.out.println("Creating a new session of the 'sample-portal'");
    }

}
