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

package org.jboss.quickstarts.portal.social.oauth.facebook;

import java.util.List;

import com.restfb.types.StatusMessage;

/**
 * Created with IntelliJ IDEA. User: vrockai Date: 4/15/13 Time: 4:34 PM To change this template use File | Settings | File
 * Templates.
 */
public class FacebookFriendBean {

    public boolean isScope() {
        return scope;
    }

    public void setScope(boolean scope) {
        this.scope = scope;
    }

    boolean scope;

    public List<StatusMessage> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<StatusMessage> statuses) {
        this.statuses = statuses;
    }

    List<StatusMessage> statuses;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    private String imageUrl;
    private String name;
    private String profileUrl;
}
