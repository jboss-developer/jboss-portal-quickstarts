<#-- This file is handled as a FreeMarker template gatein-portal-quickstarts-parent/pom.xml -->
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
<#include "/include/start-the-portal.md.ftl">


Undeploy the Archive
--------------------

To delete a Portal Extension or Portal Container:
1. Delete JBOSS_HOME/gatein/extensions/${project.artifactId}.ear
2. Restart ${compatibility.target.product}

Please note that the content written to the database by the Extension or Portal Container (such as adding new pages) is not 
reversible.


Use JBoss Developer Studio or Eclipse to Run this Quickstart
------------------------------------------------------------

Portal Extensions and Portal Containers are not directly deployable from JBoss Developer Studio nor Eclipse at this time. 
See Build and Deploy section above for an alternative.  


<#include "/include/debug.md.ftl">


<#include "/include/feedback.md.ftl">
