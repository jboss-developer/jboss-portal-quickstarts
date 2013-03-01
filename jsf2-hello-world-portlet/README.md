<!--~ Do not edit this derived file! See gatein-portal-quickstarts-parent/src/main/freemarker/jsf2-hello-world-portlet/README.md.ftl ~-->

jsf2-hello-world-portlet: JSF2 Hello World Portlet
============================
Author: Peter Palaga, Brian Leathem, Ken Finnigan  
Level: Beginner  
Technologies: JSF2, Portlet Bridge  
Summary: A simple portlet using JavaServer Faces 2.  
Target Product: JBoss Portal Platform (JPP) 6.0

What is it?
-----------

This project demonstrates how to create a simple portlet using JavaServer Faces 2.1 
and Portlet Bridge 3.1.2.Final-redhat-1.

An introduction and some background information to this quickstart can be found in the following chapters 
of JBoss Portal Platform (JPP) Developer Guide:

* [Starting a Portlet Project](http://documentation-devel.engineering.redhat.com/docs/en-US/JBoss_Portal_Platform/6/html/Developer_Guide/Portlet_Development_1.html#Starting_a_Portlet_Project_1)
* [JSF2 Portlet Development](https://docs.jboss.org/author/display/GTNPORTAL35/JSF2+Portlet+Development)
* [Basic JSF Portlet Development](https://docs.jboss.org/author/display/GTNPORTAL35/Basic+JSF+Portlet+Development)
* [Portlet Bridge](http://documentation-devel.engineering.redhat.com/docs/en-US/JBoss_Portal_Platform/6/html/Reference_Guide/chap-Reference_Guide-Building_JSF_Portlets.html)


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/portlet-general.md.ftl ~-->
<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/system-requirements.md.ftl ~-->
System Requirements
-------------------

All you need to build this example project is Java 6.0 (Java SDK 1.6) or newer and Maven 3.0 or newer.

The project is designed to be deployed on JBoss Portal Platform (JPP) 6.0 running on JBoss Application Platform.


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
  file located in the root folder of JBoss Portal Platform (JPP) quickstarts.
* If there is no `settings.xml` file, copy the modified `settings-hosted-repo.xml` file to the `.m2` directory for your 
  operating system and rename it to `settings.xml`.

### B. Download & setup zipped Maven repositories

1.  Download the following zipped Maven repositories from [Red Hat Customer Portal](https://access.redhat.com/), 
    Downloads > JBoss Enterprise Middleware:
    * JBoss Portal Platform (JPP) 6.0 Maven Repository 
    * Web Framework Kit 2.1.0 Maven Repository
    * Application Platform 6.0.1 Maven Repository
    * Application Platform 6.0.0 Maven Repository
    
    Unpack each of these files to a separate directory.

2.  Modify the `settings-zipped-repos.xml` file located in the root directory of JBoss Portal Platform (JPP) 
    quickstarts:
    * For each zipped repository unpacked in the previous step, replace `/path/to/repo/` within `file:///path/to/repo/...`
      with the fully qualified path of the directory where you unpacked the given zipped Maven repository in the previous 
      step.
    * Note that path to each repository needs tobe set twice: one within `<repository>` tag and one within
      `<pluginRepository>` tag.
    * Be sure to use 3 forward slashes after `file:`. Two slashes are there for the protocol and one for the fully qualified 
      path. For example:

            file:///home/joedoe/Quickstarts/jpp-6.0-quickstarts
3.  Configure the Maven user settings.
    * Look for the `settings.xml` file in the `${user.home}/.m2/` directory. For example:

            For Linux or Mac: ~/.m2/settings.xml
            For Windows:       \Users\USER_NAME\.m2\settings.xml or \Documents and Settings\USER_NAME\.m2\settings.xml
    * If you have an existing `settings.xml` file, modify it with the configuration information from the `example-settings.xml` 
      file.
    * If there is no `settings.xml` file, copy the modified `example-settings.xml` file to the `.m2` directory for your 
      operating system and rename it to `settings.xml`.



<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/start-the-portal.md.ftl ~-->
Start the Portal
----------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat


Build and Deploy the Quickstart
-------------------------------

1. Make sure you have started the Portal as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean package jboss-as:deploy

   To deploy to other than default localhost:9999 JBoss instance, copy the following configuration
   just after `<artifactId>jboss-as-maven-plugin</artifactId>` in the pom.xml file and adjust it to suit your needs.
   `username` and `password` elements can be omitted sometimes, depending on your JBoss security settings.

        <configuration>
            <hostname>127.0.0.1</hostname>
            <port>9999</port>
            <username>admin</username>
            <password>secret</password>
        </configuration>

   This will deploy `target/jsf2-hello-world-portlet.war` to the running instance of the portal.


Access the deployed Portlet
---------------------------

To ensure that the example portlet has been deployed successfully, do the following:

* Point your web browser at the base URL of your portal (URL of a default local JBoss Portal Platform (JPP) installation is
[http://127.0.0.1:8080/portal/classic](http://127.0.0.1:8080/portal/classic)).
* Sign in as root or other user with manager:/platform/administrators permissions.
* Go to Top Menu > Group > Administration > Application Registry and hit Import Applications. After that, you should
see the newly imported JSF2 Hello World Portlet under the jsf2-hello-world-portlet Category.
* Then you can add the example portlet to a page of your choice: either (a) to a new page or (b) to an existing page.
    * If you have chosen (a) to add the portlet to a new page:
        * Go to Top Menu > Group Editor > Add New Page
        * In the Page Creation Wizard, fill in:
            * Node Name: jsf2-hello-world-portlet
            * Display Name: JSF2 Hello World Portlet
        * Click Next
        * Leave Empty Layout as it is and hit Next
        * Drag-and-Drop JSF2 Hello World Portlet from the Page Editor dialog to the middle of the Page Creation Wizard
        * Click the Diskette-like Finish button in the header of the Page Editor dialog and see the newly added portlet in action.
    * If you have chosen (b) to add the portlet to an existing page:
        * Navigate to the page where you want to add the portlet and go to Top Menu > Group Editor > Edit Page
        * Drag-and-Drop JSF2 Hello World Portlet from the Page Editor dialog to a place of your choice within the page.
        * Click the Diskette-like Finish button in the header of the Page Editor dialog and see the newly added portlet in action.


Undeploy the Archive
--------------------


1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn jboss-as:undeploy


Use JBoss Developer Studio or Eclipse with JBoss Tools to Run this Quickstart
-----------------------------------------------------------------------------
You can also deploy the quickstarts from Eclipse using JBoss Tools. For more information on how to set up Maven and JBoss Tools,
refer to the
[JBoss Enterprise Application Platform 6 Development Guide](https://access.redhat.com/knowledge/docs/JBoss_Enterprise_Application_Platform/)
or [Get Started Developing Applications](http://www.jboss.org/jdf/quickstarts/jboss-as-quickstart/guide/Introduction/ "Get Started Developing Applications").


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

Please post feedback on this quickstart or JBoss Portal Platform (JPP) on [Online User Group](https://access.redhat.com/groups/jboss-enterprise-middleware).
