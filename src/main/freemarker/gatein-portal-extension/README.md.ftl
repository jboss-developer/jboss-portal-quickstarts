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
Technologies: ${compatibility.portal.projectName}, Portal Extension  <#-- two spaces mean line break in MD -->
Summary: ${project.description}  <#-- two spaces mean line break in MD -->
Target Product: ${compatibility.portal.projectNameAndVersion}


What is it?
-----------

This project demonstrates a simple Portal Extension. It shows how it is possible to extend and customize a portal without
modifying the files included in gatein.ear. The main advantage of this approach to customization is that upgrades to new
versions of ${compatibility.portal.projectName} will be easier to handle as all the customization can be embedded in separate
deployable packages.

${project.name} customizes the default portal available at [http://localhost:8080/portal](http://localhost:8080/portal) by doing few changes:

* Replace the front page image
* Customize the login page
* Change few translation strings
* Add a few pages

For more details, see [Portal Extension](${gatein.devguide.extensions.url}) page of
${compatibility.portal.projectName} Developer Guide.


<#include "/include/system-requirements.md.ftl">


<#include "/include/configure-maven.md.ftl">


<#include "/include/build-and-deploy-portal-container-or-extension.md.ftl">


Access the Extension
--------------------

To ensure that the Portal Extension has been deployed successfully, visit the the base URL of your Portal. Which is
[http://127.0.0.1:8080/portal](http://127.0.0.1:8080/portal/) in the most common case.

You should see some differences compared to the original Portal.


<#include "/include/portal-extension-general.md.ftl">
