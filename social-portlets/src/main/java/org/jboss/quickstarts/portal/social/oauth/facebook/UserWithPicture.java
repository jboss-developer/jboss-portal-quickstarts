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

import com.restfb.Facebook;
import com.restfb.types.NamedFacebookType;

/**
 * Holder of basic info about Facebook user including his id, name and picture
 *
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public class UserWithPicture extends NamedFacebookType {

    private static final long serialVersionUID = 7486338869330318015L;

    @Facebook("picture")
    private Picture picture;

    public Picture getPicture() {
        return picture;
    }

    public static class Picture {

        @Facebook("data")
        private Data data;

        public Data getData() {
            return data;
        }
    }

    public static class Data {

        @Facebook("url")
        private String url;

        @Facebook("is_silhouette")
        private Boolean isSilhouette;

        public String getUrl() {
            return url;
        }

        public Boolean isSilhouette() {
            return isSilhouette;
        }

    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
