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

<div class="googlePortlet socialPortlet">
    <h3 class="googleHeader socialHeader">User</h3>
    <img class="googleUserImage" src="${googleUserInfo.picture}?size=100" title="${googleUserInfo.name}" />
    <div class="googleUserInfo">
        <div class="googleUserProperty"><span class="googleUserPropertyKey">Given name:</span><span class="googleUserPropertyValue">${googleUserInfo.given_name}</span></div>
        <div class="googleUserProperty"><span class="googleUserPropertyKey">Family name:</span><span class="googleUserPropertyValue">${googleUserInfo.family_name}</span></div>
        <div class="googleUserProperty"><span class="googleUserPropertyKey">Email:</span><span class="googleUserPropertyValue">${googleUserInfo.email}</span></div>
        <div class="googleUserProperty"><span class="googleUserPropertyKey">Birthday:</span><span class="googleUserPropertyValue">${googleUserInfo.birthday}</span></div>
        <div class="googleUserProperty"><span class="googleUserPropertyKey">Gender:</span><span class="googleUserPropertyValue">${googleUserInfo.gender}</span></div>
        <div class="googleUserProperty"><span class="googleUserPropertyKey">Locale:</span><span class="googleUserPropertyValue">${googleUserInfo.locale}</span></div>
    </div>
</div>