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
<%@ page language="java" %>
<%@ page import="org.gatein.portal.installer.PortalSetupService"%>
<%@ page import="org.exoplatform.container.PortalContainer"%>
<%@ page import="org.exoplatform.services.resources.ResourceBundleService"%>
<%@ page import="java.util.ResourceBundle"%>
<%

  PortalContainer portalContainer = PortalContainer.getCurrentInstance(session.getServletContext());
  PortalSetupService setupService = (PortalSetupService)portalContainer.getComponentInstance(PortalSetupService.class);
  
  if (setupService.isSetup(request.getContextPath().substring(1))) {
      response.sendRedirect(request.getContextPath());
  }

  String contextPath = request.getContextPath() ;
  String error = (String)request.getAttribute("org.gatein.portal.setup.error");

  ResourceBundleService service = (ResourceBundleService) portalContainer.getComponentInstanceOfType(ResourceBundleService.class);
  ResourceBundle res = service.getResourceBundle(service.getSharedResourceBundleNames(), request.getLocale()) ;

  response.setCharacterEncoding("UTF-8"); 
  response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title><%=res.getString("UISetupForm.label.setup.SetPasswordTitle")%></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>        
    <meta name="description" content="Password setup screen of the Portal"/>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <link rel="stylesheet" type="text/css" href="/portal/setup/css/default.css"/>
    <link rel="stylesheet" type="text/css" href="/portal/setup/css/enchanced.css"/>
    <!--[if lte IE 8]>
    <link rel="stylesheet" type="text/css" href="/portal/setup/css/ie8.css"/>
    <![endif]-->
  </head>
  <body>
    <h1><a href="#login-form" title="Setup"><%=res.getString("UISetupForm.label.setup.SetPasswordTitle")%></a></h1>
    <%/*Begin form*/%>
    <% if(error != null) { %>
    <div id="error-pane"><p><span><%= error %></span></p></div>
    <% } %>
    <form id="setup-form" name="setup-form" action="<%= contextPath + "/setupaction"%>" method="post">
        <fieldset>
            <legend><%=res.getString("UISetupForm.label.setup.SetPassword")%></legend>
            
            <label for="password"><%=res.getString("UISetupForm.label.setup.Password")%></label>
            <input type="password" id="password" name="password" value=""/>
            
            <label for="password2"><%=res.getString("UISetupForm.label.setup.PasswordRep")%></label>
            <input type="password" id="password2" name="password2" value=""/>
            
            <input type="submit" name="setup" value="<%=res.getString("UISetupForm.label.setup.Setup")%>"/>

        </fieldset>
    </form>
    <div id="footer">
        <p>
            <%=res.getString("UISetupForm.label.mobile.copyright.Intro")%>
            <a href="http://www.redhat.com/"><%=res.getString("UISetupForm.label.mobile.copyright.RH")%></a>
            <%=res.getString("UISetupForm.label.mobile.copyright.And")%>
            <a href="http://www.exoplatform.com"><%=res.getString("UISetupForm.label.mobile.copyright.Exo")%></a>
        </p>
    </div>
</body>   
</html>