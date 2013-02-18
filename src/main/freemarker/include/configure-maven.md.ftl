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
Configure Maven for ${compatibility.portal.product.projectNameAndVersion}
---------------------------------------

If you are using the ${compatibility.portal.product.projectNameAndVersion}, you need to download and configure the Maven 
repository from [Red Hat Customer Portal](https://access.redhat.com/), Downloads > JBoss Enterprise Middleware > Portal Platform.

1. Download the ${compatibility.portal.product.projectNameAndVersion} Maven repository distribution ZIP and unzip it into a 
   directory of your choice.

2. Modify the `example-settings.xml` file located in the root directory of quickstarts.
    * Replace all occurences of `path/to/repo/jpp-6.0-maven-repository` within `file:///path/to/repo/jpp-6.0-maven-repository` 
      with the fully qualified path of the directory where you unpacked the zipped Maven repository in the previous step.
    * Be sure to use 3 forward slashes after `file:`. Two slashes are there for the protocol and one for the fully qualified 
      path. For example:

            file:///home/username/Quickstarts/jpp-6.0-quickstarts
3. Configure the Maven user settings. 
    * _Note:This is the recommended approach and is required if you are running the quickstarts in JBoss Developer Studio._
    * Look for the `settings.xml` file in the `${r"${user.home}"}/.m2/` directory. For example:

            For Linux or Mac: ~/.m2/settings.xml
            For Windows:       \Documents and Settings\USER_NAME\.m2\settings.xml or \Users\USER_NAME\.m2\settings.xml
    * If you have an existing `settings.xml` file, modify it with the configuration information from the `example-settings.xml` 
      file.
    * If there is no `settings.xml` file, copy the modified `example-settings.xml` file to the `m2` directory for your operating 
      system and rename it to `settings.xml`.

4. If you choose not to configure the `settings.xml` file described in the previous step, you must append 
   `-s PATH_TO_QUICKSTARTS/example-settings.xml` to every Maven command. 
    * _Note: This only valid only when you run the quickstarts using the command line._  
    * The following is an example of a deployment passing the Maven settings using the command line:

            mvn jboss-as:deploy -s PATH_TO_QUICKSTARTS/example-settings.xml
