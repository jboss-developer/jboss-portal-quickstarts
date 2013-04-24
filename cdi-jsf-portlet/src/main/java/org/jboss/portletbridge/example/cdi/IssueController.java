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

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author <a href="http://community.jboss.org/people/kenfinni">Ken Finnigan</a>
 */
@Named
@ConversationScoped
public class IssueController implements Serializable {

    private static final long serialVersionUID = 3184963917964851211L;

    @Inject
    private IssueManager mgr;

    @Inject
    private Conversation conversation;

    private String message;

    @PostConstruct
    public void init() {
        message = "Hello and welcome to your Issues";
    }

    public void deleteIssue(Issue issue) {
        if (conversation.isTransient()) {
            conversation.begin();
        }
        mgr.deleteIssue(issue);
        message = "You just deleted Issue #" + issue.getId() + " with a title of '" + issue.getTitle() + "'";
    }

    public String getMessage() {
        return message;
    }
}
