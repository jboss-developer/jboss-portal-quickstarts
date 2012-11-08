<!--~ Do not edit this derived file! See gatein-portal-quickstarts-parent/src/main/freemarker/gatein-portal-extension/README.md.ftl ~-->

gatein-portal-extension: GateIn Portal Extension
============================
Author: Thomas Heute, Peter Palaga  
Level: Beginner  
Technologies: GateIn, Portal Extension  
Summary: GateIn Portal Extension  
Target Product: GateIn 3.5


What is it?
-----------

This project demonstrates a simple Portal Extension. It shows how it is possible to extend and customize a portal without 
modifying the files included in gatein.ear. The main advantage of this approach to customization is that upgrades to new 
versions of GateIn will be easier to handle as all the customization can be embedded in separate 
deployable packages.

GateIn Portal Extension customizes the default portal available at: http://localhost:8080/portal by doing few changes:

* Replace the front page image
* Customize the login page
* Change few translation strings
* Add a few pages

For more details, see [Portal Containers and Extensions](https://docs.jboss.org/author/display/GTNPORTAL35/GDG-Portal+Containers+and+Extensions) page of 
GateIn Developer Guide.


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

You do not need to touch your settings.xml because of this quickstart. All necessary artifacts are available in public
repositories.


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/build-and-deploy-portal-container-or-extension.md.ftl ~-->
Build and Deploy
----------------

Portal Extensions and Portal Containers are not hot-deployable. Therefore, it is not possible to deploy them using 
`mvn jboss-as:deploy` or copying to the default JBoss AS deployment folder when 
GateIn is running. Instead of that you will need to:

1. Make sure that the Portal is not running, e.g. by running 

      $JBOSS_HOME/bin/jboss-cli.sh --connect controller=127.0.0.1:9999 command=:shutdown

2. Open a command line and navigate to the root directory of this quickstart.
3. Run the following command to build the archive:

      mvn clean package

   This will create `ear/target/gatein-portal-extension.ear`.
4. Copy this file into the GateIn extension deployment folder: $JBOSS_HOME/gatein/extensions

Access the Extension
--------------------

To ensure that the Portal Extension has been deployed successfully, visit the the base URL of your Portal. Which is 
[http://127.0.0.1:8080/portal](http://127.0.0.1:8080/portal/) in the most cases.

You should see some differences compared to the original Portal.


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
1. Delete JBOSS_HOME/gatein/extensions/gatein-portal-extension.ear
2. Restart GateIn

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

Please post feedback on this quickstart or GateIn on [User Forum](https://community.jboss.org/en/gatein?view=discussions).
