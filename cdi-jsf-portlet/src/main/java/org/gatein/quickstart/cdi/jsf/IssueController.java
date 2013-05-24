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

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * A conversation scoped proxy to {@link IssueManager}. While {@link IssueManager} is {@code @ApplicationScoped},
 * {@link IssueController} is {@code @ConversationScoped}. This allows us to store some conversation specific state
 * (it is {@link #message} in our case) in {@link IssueController}.
 * <p>
 * The {@code @Named} annotation is there to be able to use {@code issueController} in Expression Language (EL) Expressions of
 * JSF templates - see {@code /src/main/webapp/templates/home.xhtml} in this project.
 *
 * @author <a href="http://community.jboss.org/people/kenfinni">Ken Finnigan</a>
 */
@Named
@ConversationScoped
public class IssueController implements Serializable {

    private static final long serialVersionUID = 3184963917964851211L;

    /**
     * Java EE Container injects a Application Scoped {@link IssueManager} for us here.
     */
    @Inject
    private IssueManager mgr;

    /**
     * Java EE Container injects a {@link Conversation} for us here.
     */
    @Inject
    private Conversation conversation;

    /**
     * A message which is changed in {@link #deleteIssue(Issue)}.
     */
    private String message;

    /**
     * Some initialization invoked just after the creation of new {@link IssueController}.
     */
    @PostConstruct
    public void init() {
        message = "Hello and welcome to your Issues";
    }

    /**
     * Performs some simple logic - in this case deleting an {@link Issue}.
     *
     * @param issue the {@link Issue} to be deleted.
     */
    public void deleteIssue(Issue issue) {
        if (conversation.isTransient()) {
            conversation.begin();
        }
        mgr.deleteIssue(issue);
        message = "You just deleted Issue #" + issue.getId() + " with a title of '" + issue.getTitle() + "'";
    }

    /**
     * Returns the {@link #message}.
     * @return {@link #message}
     */
    public String getMessage() {
        return message;
    }
}
