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
package org.jboss.as.quickstarts.jsfrf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 * {@link HelloBean} is the JSF backing bean for the application, holding the input data to be re-displayed.
 */
@ManagedBean(name = "helloBean")
@SessionScoped
public class HelloBean implements Serializable {

    private static final long serialVersionUID = -6239437588285327644L;

    /**
     * Static list of greetings. Contains {@code "Hello"} and {@code "Hi"}.
     */
    private static final List<SelectItem> GREETINGS;

    static {
        List<SelectItem> l = new ArrayList<SelectItem>(2);
        l.add(new SelectItem("Hello"));
        l.add(new SelectItem("Hi"));
        GREETINGS = Collections.unmodifiableList(l);
    }

    /**
     * Stores the greeting phrase which will be used to greet the application user.
     */
    private String greeting;

    /**
     * Stores the name which will be used to greet the application user.
     */
    private String name;

    /**
     * Initializes {@link #name} with the value {@code "World"} and {@link #greeting} with the value {@code "Hello"}
     */
    @PostConstruct
    public void postContruct() {
        this.name = "World";
        this.greeting = "Hello";
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
    }

    /**
     * Returns {@link #greeting}.
     * 
     * @return {@link #greeting}
     */
    public String getGreeting() {
        return greeting;
    }

    /**
     * Set {@link #greeting}.
     * 
     * @param greeting
     */
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    /**
     * Returns {@link #GREETINGS}.
     * 
     * @return {@link #GREETINGS}
     */
    public List<SelectItem> getGreetings() {
        return GREETINGS;
    }


    /**
     * Resets {@link #name} to the default value {@code "World"} and {@link #greeting} with the default value {@code "Hello"}.
     * 
     * @param ae ignored
     */
    public void reset(ActionEvent ae) {
        this.name = "World";
        this.greeting = "Hello";
    }

}
