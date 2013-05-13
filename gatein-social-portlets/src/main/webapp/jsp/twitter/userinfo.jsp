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
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page trimDirectiveWhitespaces="true"%>
<portlet:defineObjects />

<div class="socialUserInfoPortlet socialPortlet twitterUserInfoPortlet">
    <h3 class="socialHeader twitterHeader">User</h3>

    <img class="socialUserInfoImage" src="${twitterUserInfo.profileImageURL}" title="${twitterUserInfo.name}" />

    <div class="socialUserInfo">
        <div class="socialUserProperty"><span class="socialUserPropertyKey">Username:</span><span class="socialUserPropertyValue">${twitterUserInfo.screenName}</span></div>
        <div class="socialUserProperty"><span class="socialUserPropertyKey">Twitter Name:</span><span class="socialUserPropertyValue">${twitterUserInfo.name}</span></div>
        <div class="socialUserProperty"><span class="socialUserPropertyKey">Tweets:</span><span class="socialUserPropertyValue">${twitterUserInfo.statusesCount}</span></div>
        <div class="socialUserProperty"><span class="socialUserPropertyKey">Followers:</span><span class="socialUserPropertyValue">${twitterUserInfo.followersCount}</span></div>
        <div class="socialUserProperty"><span class="socialUserPropertyKey">Last tweet:</span><span class="socialUserPropertyValue">${twitterUserInfo.status.text}</span></div>
    </div>
</div>
