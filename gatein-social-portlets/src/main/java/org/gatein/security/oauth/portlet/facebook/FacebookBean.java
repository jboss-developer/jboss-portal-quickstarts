/*
 * JBoss, a division of Red Hat
 * Copyright 2013, Red Hat Middleware, LLC, and individual
 * contributors as indicated by the @authors tag. See the
 * copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.gatein.security.oauth.portlet.facebook;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vrockai
 * Date: 4/15/13
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
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
