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
<!-- Do not edit this derived file! Rather edit the master file gatein-portal-quickstarts-parent/src/main/freemarker/${.template_name} -->

${project.artifactId}: ${project.name}
============================
Author: Thomas Heute, Peter Palaga  
Level: Beginner  
Technologies: GateIn, Portal Extension  
Summary: ${project.description}  
Target Product: ${compatibility.target.product}

What is it?
-----------

This project demonstrates a simple portal extension.
It customizes the default portal available at: http://localhost:8080/portal by doing few changes:
1. Replace the front page image
2. Customize the login page
3. Change few translation strings
4. Add a few pages

This shows how one can extend and customize the portal without modifying the files included in gatein.ear. By doing such customization, further updates for new versions of GateIn will be easier to handle as all the customization can be embedded in separate deployable packages.

<#include "/include/portal-extension-general.md.ftl">
