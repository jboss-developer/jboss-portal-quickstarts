<#-- This file is handled as a FreeMarker template by gatein-portal-quickstarts-parent/pom.xml -->
<#--
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
<!--~ ${derivedFileNotice}${.template_name} ~-->

${project.artifactId}: ${project.name}
============================
Author: Marek Posolda, Viliam Rockai  <#-- two spaces mean line break in MD -->
Level: Intermediate  <#-- two spaces mean line break in MD -->
Technologies: Portlet, JSP, OAuth, CDI  <#-- two spaces mean line break in MD -->
Summary: ${project.description}  <#-- two spaces mean line break in MD -->
Target Product: ${compatibility.portal.projectNameAndVersion}

What is it?
-----------

This project demonstrates how to create social portlets in ${compatibility.portal.projectName} with usage of
${compatibility.portal.projectName} API and JSP front-end. It contains:

* 3 portlets for [Facebook](${facebook.url}) integration, which are using [Facebook Graph API](${facebook.graph.url}) and leverages [RestFB library](${facebook.restfb.url}) for this purpose
* 3 portlets for [Google+](${google.plus.url}) integration, which are using [Google OAuth2 API](${google.oauth.api.url}) and [Google+ API](${google.plus.api.url})
* 1 portlet for [Twitter](${twitter.url}) integration, which is using Twitter API and leverages [Twitter4j library](${twitter.twitter4j.url}) for this purpose

Portlets require that ${compatibility.portal.projectName} integration with particular social network is enabled. You can see
chapter [OAuth - Authentication with social network accounts](${gatein.refguide.oauth.url}) in ${compatibility.portal.projectName}
reference guide for details.

Each portlet is using access token of current authenticated portal user and this access token is
used to call Social API operations. There is special portlet filter OAuthPortletFilter, which leverages
[Portal API](${gatein.devguide.api.portal.url}) to obtain informations about access token of current user. Filter saves this info
to CDI RequestContext object, which is used by particular portlet.

An introduction and some background information to this quickstart can be found in the following chapters
of ${compatibility.portal.projectName} Developer Guide:

* [Starting a Portlet Project](${gatein.devguide.starting.portlet.project.url})
* [Standard Portlet Development (JSR-286)](${gatein.devguide.jsf.plain.jsr268.portlet.url})
* [Portal API](${gatein.devguide.api.portal.url})

Informations about configuration of OAuth authentication and integration with social networks could be found in the following
chapters of  ${compatibility.portal.projectName} Reference Guide:

* [OAuth - Authentication with social network accounts](${gatein.refguide.oauth.url})
* [Facebook](${gatein.refguide.facebook.url})
* [Google+](${gatein.refguide.google.url})
* [Twitter](${gatein.refguide.twitter.url})

<#include "/include/portlet-general.md.ftl">