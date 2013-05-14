<!--
    JBoss, Home of Professional Open Source
    Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
    contributors by the @authors tag. See the copyright.txt in the
    distribution for a full listing of individual contributors.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 -->
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page trimDirectiveWhitespaces="true" %>
<portlet:defineObjects/>

<div class="googlePortlet socialPortlet">
    <h3 class="googleHeader socialHeader">Friends</h3>

    <div class="socialFriends">
        <!--
    <c:forEach var="googleFriend" items="${googleFriendsList.items}">
       --><a href="${googleFriend.url}" class="socialFriendItem">
             <img src="${googleFriend.image.url}" title="${googleFriend.displayName}"/>
          </a><!--
    </c:forEach>
    -->
    </div>
    <div class="googlePaginator">
        <c:if test="${pgState.currentPage > 1}">
            <portlet:renderURL var="prevUrl">
                <portlet:param name="page" value="prev"/>
            </portlet:renderURL>
            <a class="googlePaginatorLeft" href="${prevUrl}">&laquo; Previous</a>
        </c:if>

        <c:if test="${googleFriendsList.nextPageToken != null}">
            <portlet:renderURL var="nextUrl">
                <portlet:param name="page" value="next"/>
            </portlet:renderURL>
            <a class="googlePaginatorRight" href="${nextUrl}">Next &raquo;</a>
        </c:if>
    </div>
</div>