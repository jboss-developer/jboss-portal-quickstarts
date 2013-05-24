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

package org.gatein.quickstart.cdi.jsf;

import java.io.Serializable;

/**
 * A POJO representing an issue in a simple issue tracker.
 *
 * @author <a href="http://community.jboss.org/people/kenfinni">Ken Finnigan</a>
 */
public class Issue implements Serializable {

    private static final long serialVersionUID = 8358894467573180299L;

    public enum IssueStatus {
        NEW,
        OPEN,
        CLOSED
    }

    public Issue(int id, String title, String description, IssueStatus status) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    private int id;

    private String title;

    private String description;

    private IssueStatus status;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public IssueStatus getStatus() {
        return status;
    }

}
