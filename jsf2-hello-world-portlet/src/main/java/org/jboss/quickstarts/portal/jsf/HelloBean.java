/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
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
package org.jboss.quickstarts.portal.jsf;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 * {@link HelloBean} is the JSF backing bean for the application, holding the input data to be re-displayed.
 */
@ManagedBean(name = "helloBean")
@SessionScoped
public class HelloBean implements Serializable {
    private static final Logger log = Logger.getLogger(HelloBean.class.getName());

    private static final long serialVersionUID = -6239437588285327644L;

    /**
     * Stores the name which will be used to greet the application user.
     */
    private String name;

    /**
     * Initializes {@link #name} with the value {@code "World"}.
     */
    @PostConstruct
    public void postContruct() {
        this.name = "World";
    }

    /**
     * Returns {@link #name}.
     *
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Set {@link #name}.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
        log.info("HelloBean.name set to '"+ this.name +"'.");
    }

    /**
     * Resets {@link #name} to the default value {@code "World"}.
     *
     * @param ae ignored
     */
    public void reset(ActionEvent ae) {
        this.name = "World";
        log.info("HelloBean.name reset to '"+ this.name +"'.");
    }

}
