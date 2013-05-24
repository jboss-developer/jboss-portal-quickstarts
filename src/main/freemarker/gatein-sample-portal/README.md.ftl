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
Author: Thomas Heute, Peter Palaga  <#-- two spaces mean line break in MD -->
Level: Beginner  <#-- two spaces mean line break in MD -->
Technologies: ${compatibility.portal.projectName}, Portal Container  <#-- two spaces mean line break in MD -->
Summary: ${project.description}  <#-- two spaces mean line break in MD -->
Target Product: ${compatibility.portal.projectNameAndVersion}


What is it?
-----------

This project demonstrates a how to create a new Portal Container from scratch. It covers many aspects of new Portal Container
creation, such as:

* Custom Home Page Portlet
* Translation resource bundles
* Pages and Site Layouts
* Navigation Definitions
* Custom Sign In Page

For more details, see [Portal Containers](${gatein.devguide.containers.url}) page of
${compatibility.portal.projectName} Developer Guide.


<#include "/include/system-requirements.md.ftl">


<#include "/include/configure-maven.md.ftl">


<#include "/include/build-and-deploy-portal-container-or-extension.md.ftl">


Setup JBoss Datasources and Security Policies
---------------------------------------------

In JBoss, Datasources and Security Policies need to be defined on the level of Application Server and they
cannot[*] be defined within EARs because otherwise centralized management would not be possible. This is 
the reason why it is not enough just to deploy `${project.artifactId}.ear`. You also need to define the 
Datasources and Security Policies e.g. in `$BOSS_HOME/standalone/configuration/standalone.xml`.

The `standalone.xml` available in the out-of-the-box ${compatibility.portal.projectNameAndVersion} installation contains the needed pieces
of XML commented out. Please search for `Uncommented this when deploying ${project.artifactId}` in the file and uncomment the
necessary XML code blocks. Note that you should uncomment two `datasource`s and one `security-policy`.

> Except for editting standalone.xml, JBoss Datasources and Security Policies can be defined using CLI or JBoss Web Console as
> described in [${compatibility.as.productAndVersion} Admin Guide](${jboss.admin.guide.ds.url})

[*] Datasources can be defined in EARs but such ones are not managed, see
[${compatibility.as.productAndVersion} Admin Guide](${jboss.admin.guide.ds.files.url}).


Access the Sample Portal
------------------------

To ensure that the Sample Portal has been deployed successfully visit
[http://127.0.0.1:8080/sample-portal](http://127.0.0.1:8080/sample-portal/)) with your web browser.


<#include "/include/portal-extension-general.md.ftl">
