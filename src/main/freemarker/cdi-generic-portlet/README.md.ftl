<#-- This file is handled as a FreeMarker template by gatein-portal-quickstarts-parent/pom.xml -->
<#--
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
 -->
<!--~ ${derivedFileNotice}${.template_name} ~-->

${project.artifactId}: ${project.name}
============================
Author: Ken Finnigan, Peter Palaga  <#-- two spaces mean line break in MD -->
Level: Intermediate  <#-- two spaces mean line break in MD -->
Technologies: Portlet, CDI  <#-- two spaces mean line break in MD -->
Summary: ${project.description}  <#-- two spaces mean line break in MD -->
Target Product: ${compatibility.portal.projectNameShort}  <#-- two spaces mean line break in MD -->
Source: <${parent.scm.url}>

What is it?
-----------

This project demonstrates how to use CDI in Portlets and Portlet Filters. We have made the example as self-explaining as
possible through commenting all necessary details directly in the included files. We suggest to study the example in the
following order:

* `pom.xml`
* Configuration files under `src/main/webapp/WEB-INF`
* Java source files under `src/main/java`

An introduction and some background information to this quickstart can be found in the following chapters
of ${compatibility.portal.projectName} Developer Guide:

* [Starting a Portlet Project](${portal.devguide.starting.portlet.project.url})
* [Standard Portlet Development (JSR-286)](${portal.devguide.jsf.plain.jsr268.portlet.url})
* [Generic CDI Portlet](${portal.devguide.cdi.generic.portlet.url})


<#include "/include/portlet-general.md.ftl">