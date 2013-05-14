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
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page trimDirectiveWhitespaces="true"%>
<portlet:defineObjects />
<div class="googlePortlet socialPortlet">
<h3 class="googleHeader socialHeader">Activities</h3>
<c:forEach var="gab" items="${googleActivityBeanList}">
    <div class='socialActivity'>
        <div class="activityDetails">
            <div class="activityHeader">${gab.activity.title}</div>
            <c:forEach var="attachment" items="${gab.activity.object.attachments}">
                <div class="activityLink"><img src='${attachment.image.url}'/> ${attachment.content}</div>
            </c:forEach>
            <div class="activityPopularity">
                <div class="activityTime">
                  <jsp:useBean id="activityDate" class="java.util.Date" />
                  <jsp:setProperty name="activityDate" property="time" value="${gab.activity.updated.value}" />
                  <fmt:formatDate type="date" dateStyle="long" value="${activityDate}" pattern="EEE MMM dd HH:mm:ss zzz yyyy" />
                </div>
                <div class="activityLikes">+${gab.activity.object.plusoners.totalItems}</div>
                <div class="activityShares">${gab.activity.object.resharers.totalItems}</div>
                <%-- <a href="${gab.activity.url}" class="activityLink ActionButton LightBlueStyle">details</a> --%>
                <a href="${gab.activity.url}" class="activityMore">details &raquo;</a>
            </div>
        </div>

      <div class="activityComments">
        <c:forEach var="comment" items="${gab.commentFeed.items}">
            <div class="commentDetails">
                <div class="commentAuthor">${comment.actor.displayName}</div>
                <div class="commentTime">
                    <jsp:useBean id="commentDate" class="java.util.Date" />
                    <jsp:setProperty name="commentDate" property="time" value="${comment.updated.value}" />
                    (<fmt:formatDate type="date" dateStyle="long" value="${commentDate}" pattern="yyyy-MM-dd" />)
                </div>
                <c:if test="${comment.plusoners.totalItems > 0}">
                    <div class="commentLikes">+${comment.plusoners.totalItems}</div>
                </c:if>
                <div class="commentContent">${comment.object.content}</div>
            </div>
        </c:forEach>
        </div>
    </div>
</c:forEach>
</div>
