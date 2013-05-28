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

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.gatein.api.cdi.context.PortletRedisplayScoped;

/**
 * A simple {@code @PortletRedisplayScoped} bean. As {@code @PortletRedisplayScoped} is passivation capable, it needs to
 * implement {@link Serializable}.
 *
 * @author <a href="http://community.jboss.org/people/kenfinni">Ken Finnigan</a>
 */
@PortletRedisplayScoped
public class RedisplayScopedBean implements Serializable {
    private static final long serialVersionUID = 4488694192474531774L;

    /**
     * Field holding a text for demo purposes.
     */
    private String text;

    /**
     * This {@code @PostConstruct} initializer will allow us to see when the bean was freshly created.
     */
    @PostConstruct
    public void init() {
        text = getClass().getSimpleName() + " initial text";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
