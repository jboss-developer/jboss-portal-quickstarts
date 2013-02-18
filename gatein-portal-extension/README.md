<!--~ Do not edit this derived file! See gatein-portal-quickstarts-parent/src/main/freemarker/gatein-portal-extension/README.md.ftl ~-->

gatein-portal-extension: Portal Extension
============================
Author: Thomas Heute, Peter Palaga  
Level: Beginner  
Technologies: JBoss Portal Platform (JPP), Portal Extension  
Summary: Portal Extension  
Target Product: JBoss Portal Platform (JPP) 6.0


What is it?
-----------

This project demonstrates a simple Portal Extension. It shows how it is possible to extend and customize a portal without
modifying the files included in gatein.ear. The main advantage of this approach to customization is that upgrades to new
versions of JBoss Portal Platform (JPP) will be easier to handle as all the customization can be embedded in separate
deployable packages.

Portal Extension customizes the default portal available at [http://localhost:8080/portal](http://localhost:8080/portal) by doing few changes:

* Replace the front page image
* Customize the login page
* Change few translation strings
* Add a few pages

For more details, see [Portal Extension](http://documentation-devel.engineering.redhat.com/docs/en-US/JBoss_Portal_Platform/6/html/Developer_Guide/Portal_Extension.html) page of
JBoss Portal Platform (JPP) Developer Guide.


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/system-requirements.md.ftl ~-->
System Requirements
-------------------

All you need to build this example project is Java 6.0 (Java SDK 1.6) or newer and Maven 3.0 or newer.

The project is designed to be deployed on JBoss Portal Platform (JPP) 6.0 running on JBoss Application Platform.


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/configure-maven.md.ftl ~-->
Configure Maven for JBoss Portal Platform (JPP) 6.0
---------------------------------------

If you are using the JBoss Portal Platform (JPP) 6.0, you need to download and configure the Maven 
repository from [Red Hat Customer Portal](https://access.redhat.com/), Downloads > JBoss Enterprise Middleware > Portal Platform.

1. Download the JBoss Portal Platform (JPP) 6.0 Maven repository distribution ZIP and unzip it into a 
   directory of your choice.

2. Modify the `example-settings.xml` file located in the root directory of quickstarts.
    * Replace all occurences of `path/to/repo/jpp-6.0-maven-repository` within `file:///path/to/repo/jpp-6.0-maven-repository` 
      with the fully qualified path of the directory where you unpacked the zipped Maven repository in the previous step.
    * Be sure to use 3 forward slashes after `file:`. Two slashes are there for the protocol and one for the fully qualified 
      path. For example:

            file:///home/username/Quickstarts/jpp-6.0-quickstarts
3. Configure the Maven user settings. 
    * _Note:This is the recommended approach and is required if you are running the quickstarts in JBoss Developer Studio._
    * Look for the `settings.xml` file in the `${user.home}/.m2/` directory. For example:

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


<!--~ Included from gatein-portal-quickstarts-parent/src/main/freemarker/include/build-and-deploy-portal-container-or-extension.md.ftl ~-->
Build and Deploy
----------------

Portal Extensions and Portal Containers are not hot-deployable. Therefore, it is not possible to deploy them using 
`mvn jboss-as:deploy` or copying to the default JBoss Application Platform deployment folder when 
JBoss Portal Platform (JPP) is running. Instead of that you will need to:

1. Make sure that the Portal is not running, e.g. by running 

        $JBOSS_HOME/bin/jboss-cli.sh --connect controller=127.0.0.1:9999 command=:shutdown

2. Open a command line and navigate to the root directory of this quickstart.
3. Run the following command to build the archive:

        mvn clean package

    This will create `ear/target/gatein-portal-extension.ear`.
4. Copy this file into the JBoss Portal Platform (JPP) extension deployment folder: `$JBOSS_HOME/gatein/extensions`

Access the Extension
--------------------

To ensure that the Portal Extension has been deployed successfully, visit the the base URL of your Portal. Which is
[http://127.0.0.1:8080/portal](http://127.0.0.1:8080/portal/) in the most common case.

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

1. Delete `JBOSS_HOME/gatein/extensions/gatein-portal-extension.ear`
2. Restart JBoss Portal Platform (JPP)

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

Please post feedback on this quickstart or JBoss Portal Platform (JPP) on [Online User Group](https://access.redhat.com/groups/jboss-enterprise-middleware).
