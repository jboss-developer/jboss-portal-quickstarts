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

package org.gatein.security.oauth.portlet.google;

import com.google.api.services.plus.model.Activity;
import com.google.api.services.plus.model.CommentFeed;

/**
 * Created with IntelliJ IDEA. User: vrockai Date: 4/14/13 Time: 10:22 PM To change this template use File | Settings | File
 * Templates.
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

    public GoogleActivityBean(Activity activity) {
        this.activity = activity;
    }

}
