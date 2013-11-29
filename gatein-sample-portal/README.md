<!--~ Do not edit this derived file! See gatein-portal-quickstarts-parent/src/main/freemarker/gatein-sample-portal/README.md.ftl ~-->

gatein-sample-portal: Sample Portal
============================
Author: Thomas Heute, Peter Palaga  
Level: Beginner  
Technologies: Red Hat JBoss Portal, Portal Container  
Summary: Sample Portal  
Target Product: JBoss Portal  
Source: <https://github.com/gatein/gatein-portal-quickstart>


What is it?
-----------

This project demonstrates a how to create a new Portal Container from scratch. It covers many aspects of new Portal Container
creation, such as:

* Custom Home Page Portlet
* Translation resource bundles
* Pages and Site Layouts
* Navigation Definitions
* Custom Sign In Page

For more details, see [Portal Containers](https://access.redhat.com/site/documentation/en-US/JBoss_Portal_Platform/6.1/html/Development_Guide/chap-Portal_Containers.html) page of
Red Hat JBoss Portal Developer Guide.


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/system-requirements.md.ftl ~-->
System Requirements
-------------------

All you need to build this example project is Java 6.0 (Java SDK 1.6) or newer and Maven 3.0 or newer.

The project is designed to be deployed on Red Hat JBoss Portal 6.1 running on JBoss EAP.


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/configure-maven.md.ftl ~-->
Configure Maven
---------------

You have two options how you can configure Maven: A. Use hosted Maven repository or B. Download & setup zipped Maven repository.

### A. Use hosted Maven repository

This is the easier and thus recommended option. You need to configure the Maven user settings as follows:

* Look for the `settings.xml` file in the `${user.home}/.m2/` directory. For example:

        For Linux or Mac: ~/.m2/settings.xml
        For Windows:       \Users\USER_NAME\.m2\settings.xml or \Documents and Settings\USER_NAME\.m2\settings.xml
* If you have an existing `settings.xml` file, modify it with the configuration information from the `settings-hosted-repo.xml`
  file located in the root folder of Red Hat JBoss Portal quickstarts. This effectivelly results in
  adding `http://maven.repository.redhat.com/techpreview/all` as `<repository>` and `<pluginRepository>` to your `settings.xml`.
* If there is no `settings.xml` file, copy the `settings-hosted-repo.xml` file to the `.m2` directory for your
  operating system and rename it to `settings.xml`.

### B. Download & setup zipped Maven repositories

1.  Download the following zipped Maven repositories from [Red Hat Customer Portal](https://access.redhat.com/),
    Downloads > JBoss Enterprise Middleware:
    * Red Hat JBoss Portal 6.1 Maven Repository
    * Web Framework Kit 2.3.0 Maven Repository
    * Web Framework Kit 2.2.0 Maven Repository
    * Web Framework Kit 2.1.0 Maven Repository
    * Web Framework Kit 2.0.0 Maven Repository
    * Application Platform 6.1.1 Maven Repository
    * Application Platform 6.1.0 Maven Repository
    * Application Platform 6.0.1 Maven Repository
    * Application Platform 6.0.0 Maven Repository

    Unpack each of these files to a separate directory.

2.  Modify the `settings-zipped-repos.xml` file located in the root directory of Red Hat JBoss Portal
    quickstarts:
    * For each zipped repository unpacked in the previous step, replace `/path/to/repo/` within `file:///path/to/repo/...`
      with the fully qualified path of the directory where you unpacked the given zipped Maven repository in the previous
      step.
    * Note that path to each repository needs tobe set twice: one within `<repository>` tag and one within
      `<pluginRepository>` tag.
    * Be sure to use 3 forward slashes after `file:`. Two slashes are there for the protocol and one for the fully qualified
      path. For example:

            file:///home/joedoe/Quickstarts/jpp-6.1-quickstarts
3.  Configure the Maven user settings.
    * Look for the `settings.xml` file in the `${user.home}/.m2/` directory. For example:

            For Linux or Mac: ~/.m2/settings.xml
            For Windows:       \Users\USER_NAME\.m2\settings.xml or \Documents and Settings\USER_NAME\.m2\settings.xml
    * If you have an existing `settings.xml` file, modify it with the configuration information from the `example-settings.xml`
      file.
    * If there is no `settings.xml` file, copy the modified `example-settings.xml` file to the `.m2` directory for your
      operating system and rename it to `settings.xml`.



<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/build-and-deploy-portal-container-or-extension.md.ftl ~-->
Build and Deploy
----------------

Portal Extensions and Portal Containers are not hot-deployable. Therefore, it is not possible to deploy them using 
`mvn jboss-as:deploy` or copying to the default JBoss EAP deployment folder when 
Red Hat JBoss Portal is running. Instead of that you will need to:

1. Make sure that the Portal is not running, e.g. by running 

        $JBOSS_HOME/bin/jboss-cli.sh --connect controller=127.0.0.1:9999 command=:shutdown

2. Open a command line and navigate to the root directory of this quickstart.
3. Run the following command to build the archive:

        mvn clean package

    This will create `ear/target/gatein-sample-portal.ear`.
4. Copy this file into the Red Hat JBoss Portal extension deployment folder: `$JBOSS_HOME/gatein/extensions`

Setup JBoss Datasources and Security Policies
---------------------------------------------

In JBoss, Datasources and Security Policies need to be defined on the level of Application Server and they
cannot[*] be defined within EARs because otherwise centralized management would not be possible. This is
the reason why it is not enough just to deploy `gatein-sample-portal.ear`. You also need to define the
Datasources and Security Policies e.g. in `$JBOSS_HOME/standalone/configuration/standalone.xml`.

The `standalone.xml` available in the out-of-the-box Red Hat JBoss Portal 6.1 installation contains the needed pieces
of XML commented out. Please search for `Uncommented this when deploying gatein-sample-portal` in the file and uncomment the
necessary XML code blocks. Note that you should uncomment two `datasource`s and one `security-policy`.

> Except for editting standalone.xml, JBoss Datasources and Security Policies can be defined using CLI or JBoss Web Console as
> described in [JBoss EAP 6.1 Admin Guide](https://access.redhat.com/site/documentation/en-US/JBoss_Enterprise_Application_Platform/6.1/html/Administration_and_Configuration_Guide/chap-Datasource_Management.html)

[*] Datasources can be defined in EARs but such ones are not managed, see
[JBoss EAP 6.1 Admin Guide](https://access.redhat.com/site/documentation/en-US/JBoss_Enterprise_Application_Platform/6.1/html/Administration_and_Configuration_Guide/chap-Datasource_Management.html#Deployment_of_-ds.xml_files).


Access the Sample Portal
------------------------

To ensure that the Sample Portal has been deployed successfully visit
[http://127.0.0.1:8080/sample-portal](http://127.0.0.1:8080/sample-portal/)) with your web browser.


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/start-the-portal.md.ftl ~-->
Start the Portal
----------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat


Undeploy the Archive
--------------------

To delete a Portal Extension or Portal Container:

1. Delete `JBOSS_HOME/gatein/extensions/gatein-sample-portal.ear`
2. Restart Red Hat JBoss Portal

Please note that the content written to the database by the Extension or Portal Container (such as adding new pages) is not 
reversible.


Use JBoss Developer Studio or Eclipse to Run this Quickstart
------------------------------------------------------------

Portal Extensions and Portal Containers are not directly deployable from JBoss Developer Studio nor Eclipse at this time. 
See Build and Deploy section above for an alternative.  


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/debug.md.ftl ~-->
Debug the Application
---------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following 
commands to pull them into your local repository. The IDE should then detect them.

    mvn dependency:sources
    mvn dependency:resolve -Dclassifier=javadoc


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/feedback.md.ftl ~-->
Feedback
--------

Please post feedback on this quickstart or Red Hat JBoss Portal on [Online User Group](https://access.redhat.com/groups/jboss-enterprise-middleware).
