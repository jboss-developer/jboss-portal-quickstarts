<!--~ Do not edit this derived file! Rather edit the master file gatein-portal-quickstarts-parent/src/main/freemarker/gatein-sample-portal/README.md.ftl ~-->

gatein-sample-portal: GateIn Portal Sample Portal
============================
Author: Thomas Heute, Peter Palaga  
Level: Beginner  
Technologies: GateIn, Portal  
Summary: GateIn Sample Portal  
Target Product: GateIn 3.5

What is it?
-----------

This project demonstrates a how to create a new portal.
It customizes the default portal available at: http://localhost:8080/portal by doing few changes:
1. Replace the front page image
2. Customize the login page
3. Change few translation strings
4. Add a few pages

This shows how one can create a new portal without modifying the files included in gatein.ear. By doing such customization, further updates for new versions of GateIn will be easier to handle as all the customization can be embedded in separate deployable packages.


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/portal-extension-general.md.ftl ~-->
<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/system-requirements.md.ftl ~-->
System Requirements
-------------------

All you need to build this example project is Java 6.0 (Java SDK 1.6) or newer and Maven 3.0 or newer.

The project is designed to be deployed on GateIn Portal 3.5 running on either
JBoss AS or JBoss EAP. There is no support for JBoss Enterprise Portal Platform (EPP) yet, 
but this example projects will evolve to support the upcoming EPP version 6.


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/configure-maven.md.ftl ~-->
Configure Maven
---------------

You do not need to touch you settings.xml because of this quickstart. All necessary artifacts are available in public
repositories.


Build and Deploy the Portal Extension
-------------------------------------

1. Make sure you the Portal is not running. Portal extensions are not hot-deployable and require a cold start.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build the archive:

        mvn clean package

   This will create `ear/target/gatein-sample-portal.ear`.
4. Copy this file into the GateIn extension deployment folder: JBOSS_HOME/gatein/extensions


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/start-the-portal.md.ftl ~-->
Start the Portal
----------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat


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
1. Delete JBOSS_HOME/gatein/extensions/gatein-sample-portal.ear
2. Restart JBoss Application Server


Use JBoss Developer Studio or Eclipse to Run this Quickstart
------------------------------------------------------------

Portal extensions are not directly deployable from JBoss Developer Studio nor Eclipse at this time.


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/debug.md.ftl ~-->
Debug the Application
---------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following 
commands to pull them into your local repository. The IDE should then detect them.

        mvn dependency:sources
        mvn dependency:resolve -Dclassifier=javadoc
