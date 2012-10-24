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
<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/${.template_name} ~-->
<#include "/include/system-requirements.md.ftl">


<#include "/include/configure-maven.md.ftl">


Build and Deploy the Portal Extension
-------------------------------------

1. Make sure you the Portal is not running. Portal extensions are not hot-deployable and require a cold start.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build the archive:

        mvn clean package

   This will create `ear/target/${project.artifactId}.ear`.
4. Copy this file into the GateIn extension deployment folder: JBOSS_HOME/gatein/extensions


<#include "/include/start-the-portal.md.ftl">


Access the Extension
--------------------

To ensure that the portal extension has been deployed successfully do the following: 
* Point your web browser at the base URL of your portal (URL of a default local GateIn Portal installation is
[http://127.0.0.1:8080/portal](http://127.0.0.1:8080/portal/)).

You should see some differences compares to the original portal.


Undeploy the Archive
--------------------

The extension writing content to the database (such as adding new pages), it is not completely reversible.
To delete the extension, you can still:
1. Delete JBOSS_HOME/gatein/extensions/${project.artifactId}.ear
2. Restart JBoss Application Server


Use JBoss Developer Studio or Eclipse to Run this Quickstart
------------------------------------------------------------

Portal extensions are not directly deployable from JBoss Developer Studio nor Eclipse at this time.


<#include "/include/debug.md.ftl">
