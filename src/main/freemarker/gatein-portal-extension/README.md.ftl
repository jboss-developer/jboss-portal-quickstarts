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
<!--~ Do not edit this derived file! Rather edit the master file gatein-portal-quickstarts-parent/src/main/freemarker/${.template_name} ~-->

${project.artifactId}: ${project.name}
============================
Author: Thomas Heute, Peter Palaga  
Level: Beginner  
Technologies: GateIn, Portal Extension  
Summary: ${project.description}  
Target Product: ${compatibility.target.product}


What is it?
-----------

This project demonstrates a simple Portal Extension. It shows how it is possible to extend and customize a portal without 
modifying the files included in gatein.ear. The main advantage of this approach to customization is that upgrades to new 
versions of ${compatibility.target.productName} will be easier to handle as all the customization can be embedded in separate 
deployable packages.

${project.name} customizes the default portal available at: http://localhost:8080/portal by doing few changes:

1. Replace the front page image
2. Customize the login page
3. Change few translation strings
4. Add a few pages


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/${.template_name} ~-->
<#include "/include/system-requirements.md.ftl">


<#include "/include/configure-maven.md.ftl">


<#include "/include/build-and-deploy-portal-container-or-extension.md.ftl">


Access the Extension
--------------------

To ensure that the Portal Extension has been deployed successfully, visit the the base URL of your Portal. Which is 
[http://127.0.0.1:8080/portal](http://127.0.0.1:8080/portal/) in the most cases.

You should see some differences compared to the original Portal.


<#include "/include/portal-extension-general.md.ftl">
