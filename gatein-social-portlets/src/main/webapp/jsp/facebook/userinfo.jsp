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

<div class="socialUserInfoPortlet socialPortlet facebookUserInfoPortlet">
    <h3 class="socialHeader facebookHeader">User</h3>
    <img class="socialUserInfoImage facebookUserImage" src="http://graph.facebook.com/${facebookUserInfo.username}/picture" title="${facebookUserInfo.name}" />
    <div class="socialUserInfo">
        <div class="socialUserProperty"><span class="socialUserPropertyKey">ID:</span><span class="socialUserPropertyValue">${facebookUserInfo.id}</span></div>
        <div class="socialUserProperty"><span class="socialUserPropertyKey">Name:</span><span class="socialUserPropertyValue">${facebookUserInfo.name}</span></div>
        <div class="socialUserProperty"><span class="socialUserPropertyKey">Username:</span><span class="socialUserPropertyValue">${facebookUserInfo.username}</span></div>
        <div class="socialUserProperty"><span class="socialUserPropertyKey">First Name:</span><span class="socialUserPropertyValue">${facebookUserInfo.firstName}</span></div>
        <div class="socialUserProperty"><span class="socialUserPropertyKey">Last Name:</span><span class="socialUserPropertyValue">${facebookUserInfo.lastName}</span></div>
        <div class="socialUserProperty"><span class="socialUserPropertyKey">Gender:</span><span class="socialUserPropertyValue">${facebookUserInfo.gender}</span></div>
        <div class="socialUserProperty"><span class="socialUserPropertyKey">Timezone:</span><span class="socialUserPropertyValue">${facebookUserInfo.timezone}</span></div>
        <div class="socialUserProperty"><span class="socialUserPropertyKey">Locale:</span><span class="socialUserPropertyValue">${facebookUserInfo.locale}</span></div>
        <div class="socialUserProperty"><span class="socialUserPropertyKey">E-mail:</span><span class="socialUserPropertyValue">${facebookUserInfo.email}</span></div>
    </div>
</div>

