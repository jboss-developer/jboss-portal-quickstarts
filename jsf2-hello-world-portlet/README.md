<!--~ Do not edit this derived file! See gatein-portal-quickstarts-parent/src/main/freemarker/jsf2-hello-world-portlet/README.md.ftl ~-->

jsf2-hello-world-portlet: JSF2 Hello World Portlet
============================
Author: Peter Palaga, Brian Leathem, Ken Finnigan  
Level: Beginner  
Technologies: JSF2, Portlet Bridge  
Summary: A simple portlet using JavaServer Faces 2.  
Target Product: GateIn Portal 3.6

What is it?
-----------

This project demonstrates how to create a simple portlet using JavaServer Faces 2.1 
and [Portlet Bridge](https://docs.jboss.org/author/display/GTNPORTAL36/Reference_Guide/chap-Reference_Guide-Building_JSF_Portlets.html) 3.1.2.Final.

An introduction and some background information to this quickstart can be found in the following chapters 
of GateIn Portal Developer Guide:

* [Starting a Portlet Project](https://docs.jboss.org/author/display/GTNPORTAL36/Starting+a+Portlet+Project)
* [JSF2 Portlet Development](https://docs.jboss.org/author/display/GTNPORTAL36/JSF2+Portlet+Development)
* [Basic JSF Portlet Development](https://docs.jboss.org/author/display/GTNPORTAL36/Basic+JSF+Portlet+Development)

<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/portlet-general.md.ftl ~-->
<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/system-requirements.md.ftl ~-->
System Requirements
-------------------

All you need to build this example project is Java 6.0 (Java SDK 1.6) or newer and Maven 3.0 or newer.

The project is designed to be deployed on GateIn Portal 3.6 running on JBoss AS.


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/configure-maven.md.ftl ~-->
Configure Maven
---------------

You do not need to touch your settings.xml because of this quickstart. All necessary artifacts are available in public
repositories.



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

* Point your web browser at the base URL of your portal (URL of a default local GateIn Portal installation is
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

Please post feedback on this quickstart or GateIn Portal on [GateIn Forums](https://community.jboss.org/en/gatein?view=discussions).
