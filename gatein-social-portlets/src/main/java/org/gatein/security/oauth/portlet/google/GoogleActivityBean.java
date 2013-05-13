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

package org.gatein.security.oauth.portlet.google;

import com.google.api.services.plus.model.Activity;
import com.google.api.services.plus.model.CommentFeed;

/**
 * Created with IntelliJ IDEA.
 * User: vrockai
 * Date: 4/14/13
 * Time: 10:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class GoogleActivityBean {

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    Activity activity;

    public CommentFeed getCommentFeed() {
        return commentFeed;
    }

    public void setCommentFeed(CommentFeed commentFeed) {
        this.commentFeed = commentFeed;
    }

    CommentFeed commentFeed;

    public GoogleActivityBean(Activity activity){
        this.activity = activity;
    }


}
