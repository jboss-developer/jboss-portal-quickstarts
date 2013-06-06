<%--
    JBoss, Home of Professional Open Source
    Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
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
--%>
<%@ page language="java"%>
<%
  String contextPath = request.getContextPath() ;

  String uri = (String)request.getAttribute("org.gatein.portal.login.initial_uri");
  boolean error = request.getAttribute("org.gatein.portal.login.error") != null;

  response.setCharacterEncoding("UTF-8"); 
  response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html 
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
           "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Log In</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="shortcut icon" type="image/x-icon" href="<%=contextPath%>/favicon.ico" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/login/skin/Stylesheet.css" />
</head>
<body style="text-align: center; background: #b5b6b6; font-family: arial, tahoma, verdana">
    <div style="color: #334AF6; font-weight: bold;">This modified login form comes from Sample Portal.</div>
    <div class="UILogin">
        <div class="LoginHeader"></div>
        <div class="LoginContent">
            <div class="CenterLoginContent">
                <% if(error) { %>
                <span style="color: #ff0000">Failed to log in</span>
                <% } %>
                <form class="ClearFix" id="loginForm" action="<%= contextPath + "/login"%>" method="post" style="margin: 0px;">
                    <table>
                        <tr class="FieldContainer">
                            <td class="FieldLabel">Username</td>
                            <td><input class="UserName" name="username" /></td>
                        </tr>
                        <tr class="FieldContainer" id="UIPortalLoginFormControl">
                            <td class="FieldLabel">Password</td>
                            <td><input class="Password" type="password" name="password" value="" /></td>
                        </tr>
                    </table>
                    <div class="LoginButton">
                        <table class="LoginButtonContainer">
                            <tr>
                                <td class="Button"><input type="submit" name="signIn"
                                    value="Log In"></input> 
                                <input type="hidden" name="initialURI" value="<%=uri%>" /></td>
                            </tr>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <p style="font-size: 11px; color: #3f3f3f; text-align: center">Copyright - MyCompany</p>
</body>
</html>
