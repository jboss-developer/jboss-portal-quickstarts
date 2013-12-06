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
<%@ page import="org.jboss.quickstarts.portal.social.oauth.facebook.FacebookStatusUpdatePortlet" %>
<%@ page import="javax.portlet.ActionRequest" %>
<%@ page import="org.jboss.quickstarts.portal.social.oauth.AbstractSocialPortlet" %>
<%@ page trimDirectiveWhitespaces="true" %>

<portlet:defineObjects/>

<div class="socialUserInfoPortlet socialPortlet">
    <h3 class="socialHeader facebookHeader">Status Update</h3>

    <c:set var="fbStatus" value='<%= FacebookStatusUpdatePortlet.RENDER_PARAM_STATUS %>'/>

    <c:if test="${not empty param[fbStatus]}">
        <div class="socialMessage">
            <c:if test="${param[fbStatus] != 'SUCCESS'}">
                <h3 class="socialMessageErrorHeader">Error:</h3>
            </c:if>
            <span class="socialMessageContent">
            <c:choose>
                <c:when test="${param[fbStatus] == 'SUCCESS'}">
                    Your message has been successfully published on your Facebook wall.
                </c:when>
                <c:when test="${param[fbStatus] == 'NOT_SPECIFIED_MESSAGE_OR_LINK'}">
                    Either message or link needs to be specified.
                </c:when>
                <c:when test="${param[fbStatus] == 'FACEBOOK_ERROR_INSUFFICIENT_SCOPE'}">
                    Your access token is invalid or you have insufficient privileges (Facebook scope) to publish message on your FB wall. Your access token need to have the scope:
                    <b>publish_stream</b><br/>
                    <portlet:actionURL var="privilegesUrl">
                        <portlet:param name="<%= ActionRequest.ACTION_NAME %>"
                                       value="<%= AbstractSocialPortlet.ACTION_OAUTH_REDIRECT %>"/>
                        <portlet:param name="<%= AbstractSocialPortlet.PARAM_CUSTOM_SCOPE %>" value="publish_stream"/>
                    </portlet:actionURL>
                    <br/>
                    Click <a href="${privilegesUrl}">here</a> to fix it.
                </c:when>
                <c:otherwise>
                    <c:set var='fbError' value='<%= FacebookStatusUpdatePortlet.RENDER_PARAM_ERROR_MESSAGE %>'/>
                    Error occurred during facebook processing. Error details: ${param[fbError]}.
                </c:otherwise>
            </c:choose>

            <portlet:actionURL var="backUrl">
                <portlet:param name="<%= ActionRequest.ACTION_NAME %>"
                               value="<%= FacebookStatusUpdatePortlet.ACTION_BACK %>"/>
            </portlet:actionURL>
              <a href='${backUrl}'><button>Close</button></a>
            </span>
        </div>
    </c:if>

    <portlet:actionURL var="statusUpdateUrl">
        <portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="<%= FacebookStatusUpdatePortlet.ACTION_UPDATE_STATUS %>"/>
    </portlet:actionURL>

    <form class="facebookStatusForm socialForm" method="POST" action="${statusUpdateUrl}">

        <fieldset>
            <legend class="socialFormHint">Either message or link are required fields.</legend>

            <label class="socialInputReq">
                <span>Message:</span>
                <input class="socialStatusFormInput" name="message" type="text" value="${message}" />
            </label>
        </fieldset>

        <fieldset>
            <legend class="socialFormHint">Parameters, important only if you want to publish a link.</legend>

            <label class="socialInputReq">
                <span>Link:</span>
                <input class="socialStatusFormInput" name="link" type="text" value="${link}"/>
            </label>
            <label>
                <span>Picture:</span>
                <input class="socialStatusFormInput" name="picture" type="text" value="${picture}"/>
            </label>
            <label>
                <span>Name:</span>
                <input class="socialStatusFormInput" name="name" type="text" value="${name}"/>
            </label>
            <label>
                <span>Caption:</span>
                <input class="socialStatusFormInput" name="caption" type="text" value="${caption}"/>
            </label>
            <label>
                <span>Description:</span>
                <input class="socialStatusFormInput" name="description" type="text" value="${description}"/>
            </label>
        </fieldset>

        <input class="socialFormSubmit" type="submit" value="Submit"/>
    </form>
</div>

