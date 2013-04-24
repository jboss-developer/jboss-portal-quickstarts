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

package org.jboss.portletbridge.example.cdi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * @author <a href="http://community.jboss.org/people/kenfinni">Ken Finnigan</a>
 */
@Named
@ApplicationScoped
public class IssueManager implements Serializable {

    private static final long serialVersionUID = -6306424542612684236L;

    transient List<Issue> issues;

    transient int lastUsedId = 0;

    @PostConstruct
    public void loadIssues() {
        issues = new ArrayList<Issue>();

        issues.add(new Issue(++lastUsedId, "New Issue", "Here is a new issue that is super dooper important!", IssueStatus.NEW));
        issues.add(new Issue(++lastUsedId, "Open Issue", "This issue is now open and ready for fixing!", IssueStatus.OPEN));
        issues.add(new Issue(++lastUsedId, "Second Open Issue",
                "Oh dear, this issue has been around for a while.  Need to look at fixing it for the next release!",
                IssueStatus.OPEN));
        issues.add(new Issue(++lastUsedId, "Closed Issue", "This issue was closed.  Woohoo!", IssueStatus.CLOSED));
    }

    public List<Issue> getAll() {
        return issues;
    }

    public void createIssue(Issue issue) {
        issues.add(issue);
    }

    public void createIssue(String title, String description) {
        issues.add(new Issue(++lastUsedId, title, description, IssueStatus.NEW));
    }

    public int nextIssueId() {
        return ++lastUsedId;
    }

    public void deleteIssue(Issue issue) {
        boolean result = issues.remove(issue);
        if (!result) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Delete Failed",
                            "Issue has already been deleted from database"));
        }
    }
}
