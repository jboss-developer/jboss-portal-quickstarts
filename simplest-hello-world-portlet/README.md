<!--~ Do not edit this derived file! See gatein-portal-quickstarts-parent/src/main/freemarker/simplest-hello-world-portlet/README.md.ftl ~-->

simplest-hello-world-portlet: Simplest Hello World Portlet
============================
Author: Thomas Heute, Peter Palaga  
Level: Beginner  
Technologies: Portlet  
Summary: The very essence of every possible portlet.  
Target Product: JBoss Portal Platform (JPP) 6.0

What is it?
-----------

This project demonstrates how to create the simplest possible portlet compliant with Portlet Specification 2.0.

An introduction and some background information to this quickstart can be found in the following chapters 
of JBoss Portal Platform (JPP) Developer Guide:

* [Starting a Portlet Project](http://documentation-devel.engineering.redhat.com/docs/en-US/JBoss_Portal_Platform/6/html/Developer_Guide/Portlet_Development_1.html#Starting_a_Portlet_Project_1)
* [Standard Portlet Development (JSR-286)](http://documentation-devel.engineering.redhat.com/docs/en-US/JBoss_Portal_Platform/6/html/Developer_Guide/Standard_Portlet_Development_JSR-286_1.html)


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

This is the easier and thus recommended option.

1.  Configure the Maven user settings. 
    * _Note:This is the recommended approach and is required if you are running the quickstarts in JBoss Developer Studio._
    * Look for the `settings.xml` file in the `${user.home}/.m2/` directory. For example:

            For Linux or Mac: ~/.m2/settings.xml
            For Windows:       \Users\USER_NAME\.m2\settings.xml or \Documents and Settings\USER_NAME\.m2\settings.xml
    * If you have an existing `settings.xml` file, modify it with the configuration information from the `settings-hosted-repo.xml` 
      file located in the root folder of JBoss Portal Platform (JPP) quickstarts.
    * If there is no `settings.xml` file, copy the modified `settings-hosted-repo.xml` file to the `.m2` directory for your 
      operating system and rename it to `settings.xml`.

2.  If you choose not to configure the `settings.xml` file described in the previous step, you must append 
    `-s PATH_TO_QUICKSTARTS/settings-hosted-repo.xml` to every Maven command. 
    * _Note: This only valid only when you run the quickstarts using the command line._  
    * The following is an example of a deployment passing the Maven settings using the command line:

            mvn jboss-as:deploy -s PATH_TO_QUICKSTARTS/settings-hosted-repo.xml

### B. Download & setup zipped Maven repositories

1.  Download the following zipped Maven repositories from [Red Hat Customer Portal](https://access.redhat.com/), 
    Downloads > JBoss Enterprise Middleware:
    * JBoss Portal Platform (JPP) 6.0 Maven Repository 
    * Web Framework Kit 2.1.0 Maven Repository
    * Application Platform 6.0.1 Maven Repository
    
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
    * _Note:This is the recommended approach and is required if you are running the quickstarts in JBoss Developer Studio._
    * Look for the `settings.xml` file in the `${user.home}/.m2/` directory. For example:

            For Linux or Mac: ~/.m2/settings.xml
            For Windows:       \Users\USER_NAME\.m2\settings.xml or \Documents and Settings\USER_NAME\.m2\settings.xml
    * If you have an existing `settings.xml` file, modify it with the configuration information from the `example-settings.xml` 
      file.
    * If there is no `settings.xml` file, copy the modified `example-settings.xml` file to the `.m2` directory for your 
      operating system and rename it to `settings.xml`.

4.  If you choose not to configure the `settings.xml` file described in the previous step, you must append 
   `-s PATH_TO_QUICKSTARTS/example-settings.xml` to every Maven command. 
    * _Note: This only valid only when you run the quickstarts using the command line._  
    * The following is an example of a deployment passing the Maven settings using the command line:

            mvn jboss-as:deploy -s PATH_TO_QUICKSTARTS/example-settings.xml


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
   just after `<version>${jboss.as.plugin.version}</version>` in the pom.xml file and adjust it to suit your needs.
   `username` and `password` elements can be omitted sometimes, depending on your JBoss security settings.

        <configuration>
            <hostname>127.0.0.1</hostname>
            <port>9999</port>
            <username>admin</username>
            <password>secret</password>
        </configuration>

   This will deploy `target/simplest-hello-world-portlet.war` to the running instance of the portal.


Access the deployed Portlet
---------------------------

To ensure that the example portlet has been deployed successfully, do the following:

* Point your web browser at the base URL of your portal (URL of a default local JBoss Portal Platform (JPP) installation is
[http://127.0.0.1:8080/portal/classic](http://127.0.0.1:8080/portal/classic)).
* Sign in as root or other user with manager:/platform/administrators permissions.
* Go to Top Menu > Group > Administration > Application Registry and hit Import Applications. After that, you should
see the newly imported Simplest Hello World Portlet under the simplest-hello-world-portlet Category.
* Then you can add the example portlet to a page of your choice: either (a) to a new page or (b) to an existing page.
    * If you have chosen (a) to add the portlet to a new page:
        * Go to Top Menu > Group Editor > Add New Page
        * In the Page Creation Wizard, fill in:
            * Node Name: simplest-hello-world-portlet
            * Display Name: Simplest Hello World Portlet
        * Click Next
        * Leave Empty Layout as it is and hit Next
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
