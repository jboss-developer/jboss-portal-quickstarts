<!-- Do not edit this derived file! The master file can be found under gatein-portal-quickstarts-parent/src/main -->

Simplest Hello World Portlet
============================
Author: Thomas Heute, Peter Palaga
Level: Beginner
Technologies: Portlet
Summary: The very essence of every possible Portlet.


What is it?
-----------

This project demonstrates how to create a simple portlet compliant with Portlet Specification 2.0.  

You might want to read our [Portlet Primer](http://docs.jboss.com/gatein/portal/3.4.0.M01/reference-guide/en-US/html/chap-Reference_Guide-Portlet_development.html#sect-Reference_Guide-Portlet_Primer) to get some basic information about Portlets.


System requirements
-------------------

All you need to build this project is Java 6.0 (Java SDK 1.6) or newer and Maven 3.0 or newer.

The application this project produces is designed to be deployed on GateIn Portal 3.4 running on either JBoss 
AS or JBoss EAP.

 
Configure Maven
---------------

If you have not yet done so, you need to [Configure Maven](../README.md#mavenconfiguration) before testing the quickstarts.


Start the Portal
----------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat

 
Build and Deploy the Quickstart
-------------------------------

_NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include 
Maven setting arguments on the command line. See [Build and Deploy the Quickstarts](../README.md#buildanddeploy) for 
complete instructions and additional options._

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean package jboss-as:deploy

4. This will deploy `target/simplest-hello-world-portlet.war` to the running instance of the portal.


Access the Portlet 
------------------

To ensure that the example portlet has been deployed successfully do the following: 
* Point your web browser at the base URL of your portal - URL of a default local GateIn Portal instalallation is
[http://127.0.0.1:8180/portal/classic](http://127.0.0.1:8180/portal/classic).
* Sign in as root or other user with manager:/platform/administrators permissions.
* Go to Top Menu > Group > Administration > Application Registry and hit "Import Applications". After that, you should see a 
Then you can add the example portlet to a page of your
choice: either a new page (Site Editor > Add new Page) or an existing
page (go to page and Site Editor > Edit Page). If you added a new page
you need to add it to navigation (...) .


Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn jboss-as:undeploy


Run the Quickstart in JBoss Developer Studio or Eclipse
-------------------------------------
You can also start the server and deploy the quickstarts from Eclipse using JBoss tools. For more information, see [Use JBoss Developer Studio or Eclipse to Run the Quickstarts](../README.md#useeclipse) 


Debug the Application
------------------------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

        mvn dependency:sources
        mvn dependency:resolve -Dclassifier=javadoc
