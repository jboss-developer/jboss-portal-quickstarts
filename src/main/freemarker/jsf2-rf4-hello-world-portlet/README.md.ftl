<#-- This file is handled as a FreeMarker template by gatein-portal-quickstarts-parent/pom.xml -->
<!-- Do not edit this derived file! Rather edit the master file gatein-portal-quickstarts-parent/src/main/readme/${.template_name} -->

${project.name}
============================
Author: Peter Palaga, Brian Leathem, Ken Finnigan  
Level: Beginner  
Technologies: RF4, JSF2, Portlet Bridge  
Summary: ${project.description}


What is it?
-----------

This project demonstrates how to create a simplest portlet using JavaServer Faces 2.1 and RichFaces 4.2.

Known Issues
------------

[https://issues.jboss.org/browse/GTNPORTAL-2581](GTNPORTAL-2581): When adding this portlet to a page for the first time, 
there comes an alert saying "RichFaces is not defined". The alert can be ignored. Just close it and refresh the page with F5.
A fix is underway for GateIn 3.5.0.Beta01.

<#include "/include/portlet-general.md.ftl">