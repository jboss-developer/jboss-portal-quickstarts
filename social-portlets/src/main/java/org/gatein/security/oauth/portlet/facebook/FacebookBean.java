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

package org.gatein.security.oauth.portlet.facebook;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: vrockai Date: 4/15/13 Time: 5:47 PM To change this template use File | Settings | File
 * Templates.
 */
public class FacebookBean {

    int friendNumber;

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    int currentPageNumber;
    List<String> friendPaginatorUrls;

    public int getFriendNumber() {
        return friendNumber;
    }

    public void setFriendNumber(int friendNumber) {
        this.friendNumber = friendNumber;
    }

    public List<String> getFriendPaginatorUrls() {
        return friendPaginatorUrls;
    }

    public void setFriendPaginatorUrls(List<String> friendPaginatorUrls) {
        this.friendPaginatorUrls = friendPaginatorUrls;
    }
}
