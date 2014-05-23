
simplest-hello-world-portlet: Simplest Hello World Portlet
============================
Author: Thomas Heute, Peter Palaga  
Level: Beginner  
Technologies: Portlet  
Summary: The very essence of every possible portlet.  
Target Product: JPP  
Source: <https://github.com/gatein/gatein-portal-quickstart>

What is it?
-----------

This project demonstrates how to create the simplest possible portlet compliant with Portlet Specification 2.0.

An introduction and some background information to this quickstart can be found in the following chapters 
of JBoss Portal Platform Developer Guide:

* [Starting a Portlet Project](https://access.redhat.com/site/documentation/en-US/JBoss_Portal_Platform/6.1/html/Development_Guide/sect-Starting_a_Portlet_Project.html)
* [Standard Portlet Development (JSR-286)](https://access.redhat.com/site/documentation/en-US/JBoss_Portal_Platform/6.1/html/Development_Guide/sect-Standard_Portlet_Development_JSR-286.html)


System Requirements
-------------------

All you need to build this example project is Java 6.0 (Java SDK 1.6) or newer and Maven 3.0 or newer.

The project is designed to be deployed on JBoss Portal Platform 6.1 running on JBoss EAP.


Configure Maven
---------------

You have two options how you can configure Maven: A. Use hosted Maven repository or B. Download & setup zipped Maven repository.

### A. Use hosted Maven repository

This is the easier and thus recommended option. You need to configure the Maven user settings as follows:

* Look for the `settings.xml` file in the `${user.home}/.m2/` directory. For example:

        For Linux or Mac: ~/.m2/settings.xml
        For Windows:       \Users\USER_NAME\.m2\settings.xml or \Documents and Settings\USER_NAME\.m2\settings.xml
* If you have an existing `settings.xml` file, modify it with the configuration information from the `settings-hosted-repo.xml`
  file located in the root folder of JBoss Portal Platform quickstarts. This effectivelly results in
  adding `http://maven.repository.redhat.com/techpreview/all` as `<repository>` and `<pluginRepository>` to your `settings.xml`.
* If there is no `settings.xml` file, copy the `settings-hosted-repo.xml` file to the `.m2` directory for your
  operating system and rename it to `settings.xml`.

### B. Download & setup zipped Maven repositories

1.  Download the following zipped Maven repositories from [Red Hat Customer Portal](https://access.redhat.com/),
    Downloads > JBoss Enterprise Middleware:
    * JBoss Portal Platform 6.1 Maven Repository
    * Web Framework Kit 2.3.0 Maven Repository
    * Web Framework Kit 2.2.0 Maven Repository
    * Web Framework Kit 2.1.0 Maven Repository
    * Web Framework Kit 2.0.0 Maven Repository
    * Application Platform 6.1.1 Maven Repository
    * Application Platform 6.1.0 Maven Repository
    * Application Platform 6.0.1 Maven Repository
    * Application Platform 6.0.0 Maven Repository

    Unpack each of these files to a separate directory.

2.  Modify the `settings-zipped-repos.xml` file located in the root directory of JBoss Portal Platform
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

   This will deploy `target/simplest-hello-world-portlet.war` to the running instance of the portal.


Access the deployed Portlet
---------------------------

To ensure that the example portlet has been deployed successfully, do the following:

* Point your web browser at the base URL of your portal (URL of a default local JBoss Portal Platform installation is
[http://127.0.0.1:8080/portal/classic](http://127.0.0.1:8080/portal/classic)).
* Sign in as root or other user with manager:/platform/administrators permissions.
* Go to Top Menu > Group > Administration > Application Registry and hit Import Applications. After that, you should
see the newly imported Simplest Hello World Portlet under the Quickstarts Category.
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


Debug the Application
---------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following 
commands to pull them into your local repository. The IDE should then detect them.

    mvn dependency:sources
    mvn dependency:resolve -Dclassifier=javadoc


Feedback
--------

Please post feedback on this quickstart or JBoss Portal Platform on [Online User Group](https://access.redhat.com/groups/jboss-enterprise-middleware).
