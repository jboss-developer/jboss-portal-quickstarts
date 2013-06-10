<!--~ Do not edit this derived file! See gatein-portal-quickstarts-parent/src/main/freemarker/social-portlets/README.md.ftl ~-->

social-portlets: Social Portlets
============================
Author: Marek Posolda, Viliam Rockai  
Level: Intermediate  
Technologies: Portlet, JSP, OAuth, CDI  
Summary: Portlets showing integration with Social networks  
Target Product: GateIn Portal 3.6

What is it?
-----------

This project demonstrates how to create social portlets in GateIn Portal with usage of
GateIn Portal API and JSP front-end. It contains:

* 3 portlets for [Facebook](https://www.facebook.com) integration, which are using [Facebook Graph API](https://developers.facebook.com/docs/reference/api/) and leverages [RestFB library](http://restfb.com/) for this purpose
* 3 portlets for [Google+](https://plus.google.com/) integration, which are using [Google OAuth2 API](https://developers.google.com/accounts/docs/OAuth2) and [Google+ API](https://developers.google.com/+/api/latest/)
* 1 portlet for [Twitter](https://twitter.com/) integration, which is using Twitter API and leverages [Twitter4j library](http://twitter4j.org/en/index.html) for this purpose

Portlets require that GateIn Portal integration with particular social network is enabled. You can see
chapter [OAuth - Authentication with social network accounts](https://docs.jboss.org/author/display/GTNPORTAL36/OAuth+-+Authentication+with+social+network+accounts) in GateIn Portal
reference guide for details.

Each portlet is using access token of current authenticated portal user and this access token is
used to call Social API operations. There is special portlet filter OAuthPortletFilter, which leverages
[Portal API](https://docs.jboss.org/author/display/GTNPORTAL36/Portal+API) to obtain informations about access token of current user. Filter saves this info
to CDI RequestContext object, which is used by particular portlet.

An introduction and some background information to this quickstart can be found in the following chapters
of GateIn Portal Developer Guide:

* [Starting a Portlet Project](https://docs.jboss.org/author/display/GTNPORTAL36/Starting+a+Portlet+Project)
* [Standard Portlet Development (JSR-286)](https://docs.jboss.org/author/display/GTNPORTAL36/Standard+Portlet+Development+%28JSR+286%29)
* [Portal API](https://docs.jboss.org/author/display/GTNPORTAL36/Portal+API)

Informations about configuration of OAuth authentication and integration with social networks could be found in the following
chapters of  GateIn Portal Reference Guide:

* [OAuth - Authentication with social network accounts](https://docs.jboss.org/author/display/GTNPORTAL36/OAuth+-+Authentication+with+social+network+accounts)
* [Facebook](https://docs.jboss.org/author/display/GTNPORTAL36/Facebook)
* [Google+](https://docs.jboss.org/author/display/GTNPORTAL36/Google+Plus)
* [Twitter](https://docs.jboss.org/author/display/GTNPORTAL36/Twitter)

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

   This will deploy `target/social-portlets.war` to the running instance of the portal.


Access the deployed Portlet
---------------------------

To ensure that the example portlet has been deployed successfully, do the following:

* Point your web browser at the base URL of your portal (URL of a default local GateIn Portal installation is
[http://127.0.0.1:8080/portal/classic](http://127.0.0.1:8080/portal/classic)).
* Sign in as root or other user with manager:/platform/administrators permissions.
* Go to Top Menu > Group > Administration > Application Registry and hit Import Applications. After that, you should
see the newly imported Social Portlets under the Quickstarts Category.
* Then you can add the example portlet to a page of your choice: either (a) to a new page or (b) to an existing page.
    * If you have chosen (a) to add the portlet to a new page:
        * Go to Top Menu > Group Editor > Add New Page
        * In the Page Creation Wizard, fill in:
            * Node Name: social-portlets
            * Display Name: Social Portlets
        * Click Next
        * Leave Empty Layout as it is and hit Next
        * Drag-and-Drop Social Portlets from the Page Editor dialog to the middle of the Page Creation Wizard
        * Click the Diskette-like Finish button in the header of the Page Editor dialog and see the newly added portlet in action.
    * If you have chosen (b) to add the portlet to an existing page:
        * Navigate to the page where you want to add the portlet and go to Top Menu > Group Editor > Edit Page
        * Drag-and-Drop Social Portlets from the Page Editor dialog to a place of your choice within the page.
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
