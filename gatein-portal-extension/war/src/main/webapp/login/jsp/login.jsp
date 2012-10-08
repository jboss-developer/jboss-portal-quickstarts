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

<%@ page import="java.net.URLEncoder"%>
<%@ page import="javax.servlet.http.Cookie"%>
<%@ page import="org.exoplatform.container.PortalContainer"%>
<%@ page import="org.exoplatform.services.resources.ResourceBundleService"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="org.gatein.common.text.EntityEncoder"%>
<%@ page language="java"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
  String contextPath = request.getContextPath() ;

  String username = (String)request.getParameter("username");
  if(username == null) username = "";
 	String password = (String)request.getParameter("password");
 	if(password == null) password = "";

 PortalContainer portalContainer = PortalContainer.getCurrentInstance(session.getServletContext());	
  ResourceBundleService service = (ResourceBundleService) portalContainer.getComponentInstanceOfType(ResourceBundleService.class);
  ResourceBundle res = service.getResourceBundle(service.getSharedResourceBundleNames(), request.getLocale()) ;
  
  String uri = (String)request.getAttribute("org.gatein.portal.login.initial_uri");

  Cookie cookie = new Cookie(org.exoplatform.web.login.LoginServlet.COOKIE_NAME, "");
	cookie.setPath(request.getContextPath());
	cookie.setMaxAge(0);
	response.addCookie(cookie);
%>
<!DOCTYPE html 
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
           "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title><%=res.getString("UILoginForm.label.Signin")%></title>
<link rel="shortcut icon" type="image/x-icon" href="/<%=portalContainer.getName()%>/favicon.ico" />
<link rel='stylesheet' type='text/css' href='/<%=portalContainer.getName()%>/login/skin/Stylesheet.css' />
</head>
<body style="text-align: center; background: #f5f5f5; font-family: arial, tahoma, verdana">
    <div class="UILogin">
        <div class="LoginHeader"><%=res.getString("UILoginForm.label.Signin")%></div>
        <div class="LoginContent">
            <div class="WelcomeText"><%=res.getString("UILoginForm.label.welcome")%></div>
            <div class="CenterLoginContent">
                <%/*Begin form*/%>
                <%
            if(username.length() > 0 || password.length() > 0) {
               EntityEncoder encoder = EntityEncoder.FULL;
               username = encoder.encode(username);
          %>
                <font color="red"><%=res.getString("UILoginForm.label.SigninFail")%></font>
                <%}%>
                <form class="ClearFix" name="loginForm" action="<%= contextPath + "/login"%>" method="post" style="margin: 0px;">
                    <% if (uri != null) { 
                   uri = EntityEncoder.FULL.encode(uri);
                %>
                    <input type="hidden" name="initialURI" value="<%=uri%>" />
                    <% } %>
                    <table>
                        <tr class="FieldContainer">
                            <td class="FieldLabel"><%=res.getString("UILoginForm.label.UserName")%></td>
                            <td><input class="UserName" name="username" value="<%=username%>" /></td>
                        </tr>
                        <tr class="FieldContainer" id="UIPortalLoginFormControl">
                            <td class="FieldLabel"><%=res.getString("UILoginForm.label.password")%></td>
                            <td><input class="Password" type="password" name="password" value="" /></td>
                        </tr>
                        <tr class="FieldContainer">
                            <td class="FieldLabel"><input type="checkbox" name="rememberme" value="true" /></td>
                            <td><%=res.getString("UILoginForm.label.RememberOnComputer")%></td>
                        </tr>
                    </table>
                    <div class="LoginButton">
                        <div class="LoginButtonContainer">
                            <div class="Button">
                                <input type="submit" name="signIn" value="<%=res.getString("UILoginForm.label.Signin")%>"></input>
                            </div>
                        </div>
                    </div>
                </form>
                <%/*End form*/%>
            </div>
        </div>
    </div>
    <span style="margin: 10px 0px 0px 5px; font-size: 11px; color: #6f6f6f; text-align: center"><%=res.getString("UILoginForm.label.Copyright")%></span>
</body>
</html>
