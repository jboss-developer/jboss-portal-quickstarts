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
Configure Maven
---------------

<#if compatibility.portal.projectNameShort == "GateIn">
You do not need to touch your settings.xml because of this quickstart. All necessary artifacts are available in public
repositories.
<#else>
You have two options how you can configure Maven: A. Use hosted Maven repository or B. Download & setup zipped Maven repository.

### A. Use hosted Maven repository

This is the easier and thus recommended option. You need to configure the Maven user settings as follows:

* Look for the `settings.xml` file in the `${r"${user.home}"}/.m2/` directory. For example:

        For Linux or Mac: ~/.m2/settings.xml
        For Windows:       \Users\USER_NAME\.m2\settings.xml or \Documents and Settings\USER_NAME\.m2\settings.xml
* If you have an existing `settings.xml` file, modify it with the configuration information from the `settings-hosted-repo.xml`
  file located in the root folder of ${compatibility.portal.product.projectName} quickstarts. This effectivelly results in
  adding `${mvnrepo.redhat.all.url}` as `<repository>` and `<pluginRepository>` to your `settings.xml`.
* If there is no `settings.xml` file, copy the `settings-hosted-repo.xml` file to the `.m2` directory for your
  operating system and rename it to `settings.xml`.

### B. Download & setup zipped Maven repositories

1.  Download the following zipped Maven repositories from [Red Hat Customer Portal](https://access.redhat.com/),
    Downloads > JBoss Enterprise Middleware:
    * ${compatibility.portal.product.projectNameAndVersion} Maven Repository
    * Web Framework Kit ${compatibility.wfk.versionMmm} Maven Repository
    * Web Framework Kit 2.0.0 Maven Repository
    * Application Platform ${compatibility.as.versionMmm} Maven Repository
    * Application Platform 6.0.0 Maven Repository

    Unpack each of these files to a separate directory.

2.  Modify the `settings-zipped-repos.xml` file located in the root directory of ${compatibility.portal.product.projectName}
    quickstarts:
    * For each zipped repository unpacked in the previous step, replace `/path/to/repo/` within `file:///path/to/repo/...`
      with the fully qualified path of the directory where you unpacked the given zipped Maven repository in the previous
      step.
    * Note that path to each repository needs tobe set twice: one within `<repository>` tag and one within
      `<pluginRepository>` tag.
    * Be sure to use 3 forward slashes after `file:`. Two slashes are there for the protocol and one for the fully qualified
      path. For example:

            file:///home/joedoe/Quickstarts/jpp-${compatibility.portal.versionMajor}.${compatibility.portal.versionMinor}-quickstarts
3.  Configure the Maven user settings.
    * Look for the `settings.xml` file in the `${r"${user.home}"}/.m2/` directory. For example:

            For Linux or Mac: ~/.m2/settings.xml
            For Windows:       \Users\USER_NAME\.m2\settings.xml or \Documents and Settings\USER_NAME\.m2\settings.xml
    * If you have an existing `settings.xml` file, modify it with the configuration information from the `example-settings.xml`
      file.
    * If there is no `settings.xml` file, copy the modified `example-settings.xml` file to the `.m2` directory for your
      operating system and rename it to `settings.xml`.
</#if>

