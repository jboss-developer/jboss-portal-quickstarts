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

import com.restfb.Facebook;
import com.restfb.types.NamedFacebookType;

/**
 * Holder of basic info about Facebook user including his id, name and picture
 *
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public class UserWithPicture extends NamedFacebookType {

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

        @Facebook ("url")
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
    public boolean equals(Object o){
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
