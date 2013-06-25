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

package org.gatein.security.oauth.portlet.facebook;

import java.util.List;

import com.restfb.types.StatusMessage;

/**
 * Created with IntelliJ IDEA. User: vrockai Date: 4/15/13 Time: 4:34 PM To change this template use File | Settings | File
 * Templates.
 */
public class FacebookUserBean {

    private String id;
    private String imageUrl;
    private String name;
    private String profileUrl;
    private String neededScope;
    private List<StatusMessage> statuses;
    private List<FacebookUserBean> friends;

    public List<FacebookUserBean> getFriends() {
        return friends;
    }

    public void setFriends(List<FacebookUserBean> friends) {
        this.friends = friends;
    }

    public int getFriendsNumber() {
        return friendsNumber;
    }

    public void setFriendsNumber(int friendsNumber) {
        this.friendsNumber = friendsNumber;
    }

    private int friendsNumber;

    public FacebookUserBean() {

    }

    public FacebookUserBean(UserWithPicture user) {
        this.id = user.getId();
        this.imageUrl = user.getPicture().getData().getUrl();
        this.name = user.getName();
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getNeededScope() {
        return neededScope;
    }

    public void setNeededScope(String neededScope) {
        this.neededScope = neededScope;
    }
}
