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
 <!-- Do not edit this derived file! Rather edit the master file gatein-portal-quickstarts-parent/src/main/readme/${.template_name} -->

${project.artifactId}: ${project.name}
============================
Author: Peter Palaga, Brian Leathem, Ken Finnigan  
Level: Beginner  
Technologies: JSF2, Portlet Bridge  
Summary: ${project.description}  
Target Product: ${compatibility.gatein.version}

What is it?
-----------

This project demonstrates how to create a simplest portlet using JavaServer Faces 2.1.

<#include "/include/portlet-general.md.ftl">