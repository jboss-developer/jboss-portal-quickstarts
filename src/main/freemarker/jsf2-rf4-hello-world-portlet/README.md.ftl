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
Author: Peter Palaga, Brian Leathem, Ken Finnigan  
Level: Beginner  
Technologies: RF4, JSF2, Portlet Bridge  
Summary: ${project.description}  
Target Product: ${compatibility.target.productAndVersion}

What is it?
-----------

This project demonstrates how to create a simplest portlet using JavaServer Faces ${version.javax["faces-api"]}, 
RichFaces ${version.org.richfaces} and Portlet Bridge ${version.org.jboss.portletbridge}.

An introduction and some background information to this quickstart can be found in the following chapters 
of ${compatibility.target.product} Developer Guide:
* [Starting a Portlet Project](${gatein.devguide.starting.portlet.project.url})
* [JSF2 Portlet Development](${gatein.devguide.jsf.portlets.url})
* [Basic JSF Portlet Development](${gatein.devguide.jsf.basic.portlet.url})
* [JSF portlet development with RichFaces](${gatein.devguide.jsf.rf.portlet.url})


<#include "/include/portlet-general.md.ftl">