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


<#include "/include/start-the-portal.md.ftl">


Build and Deploy the Quickstart
-------------------------------

1. Make sure you have started the Portal as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean package jboss-as:deploy

   To deploy to other than default localhost:9999 JBoss instance, copy the following configuration 
   just after `<version>${r"${jboss.as.plugin.version}"}</version>` in the pom.xml file and adjust it to suit your needs.
   `username` and `password` elements can be omitted sometimes, depending on your JBoss security settings.
                 
        <configuration>
            <hostname>127.0.0.1</hostname>
            <port>9999</port>
            <username>admin</username>
            <password>secret</password>
        </configuration>

   This will deploy `target/${project.artifactId}.war` to the running instance of the portal.


Access the deployed Portlet
---------------------------

To ensure that the example portlet has been deployed successfully do the following: 
* Point your web browser at the base URL of your portal (URL of a default local GateIn Portal installation is
[http://127.0.0.1:8080/portal/classic](http://127.0.0.1:8080/portal/classic)).
* Sign in as root or other user with manager:/platform/administrators permissions.
* Go to Top Menu > Group > Administration > Application Registry and hit Import Applications. After that, you should
see the newly imported ${project.name} under the ${project.artifactId} Category.
* Then you can add the example portlet to a page of your choice: either (a) to a new page or (b) to an existing page.
  * If you have chosen (a) to add the portlet to a new page:
    * Go to Top Menu > Group Editor > Add New Page
    * In the Page Creation Wizard, fill in:
      * Node Name: ${project.artifactId}
      * Display Name: ${project.name}
    * Click Next
    * Leave Empty Layout as it is and hit Mext
    * Drag-and-Drop Simplest Hello World Portlet from the Page Editor dialog to the middle of the Page Creation Wizard
    * Click the Diskette-like Finish button in the header of the Page Editor dialog and see the newly added portlet in action.
  * If you have chosen (b) to add the portlet to an existing page:
    * Navigate to the page where you want to add the portlet and go to Top Menu > Group Editor > Edit Page
    * Drag-and-Drop Simplest Hello World Portlet from the Page Editor dialog to a place of your choice within the page.
    * Click the Diskette-like Finish button in the header of the Page Editor dialog and see the newly added portlet in action.


Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn jboss-as:undeploy


Use JBoss Developer Studio or Eclipse to Run this Quickstart
------------------------------------------------------------
You can also deploy the quickstarts from Eclipse using JBoss tools. For more information on how to set up Maven and the JBoss 
tools, refer to the 
[JBoss Enterprise Application Platform 6 Development Guide](https://access.redhat.com/knowledge/docs/JBoss_Enterprise_Application_Platform/) 
or [Get Started Developing Applications](http://www.jboss.org/jdf/quickstarts/jboss-as-quickstart/guide/Introduction/ "Get Started Developing Applications").


<#include "/include/debug.md.ftl">


<#include "/include/feedback.md.ftl">
