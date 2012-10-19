====
    JBoss, Home of Professional Open Source
    Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
    contributors by the @authors tag. See the copyright.txt in the 
    distribution for a full listing of individual contributors.
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,  
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
====

########################################
# HOW TO DEPLOY
########################################

########################################
# On JBoss (tested on JBoss 5.1.0.GA)
########################################

We assume that you have a clean JBoss version of GateIn: ie We assume that you have already the file gatein.ear in the deploy directory 
of jboss

You need to:

1. Add the file sample-portal.ear from sample/portal/ear/target/ to the deploy directory of jboss 
2. Add the file starter.ear from starter/ear/target/ to the deploy directory of jboss 
  
WARNING: This can only work if a Unified ClassLoader has been configured on your JBoss (default behavior) and
the load order is first the gatein.ear then the sample-portal.ear and finally the starter.ear

########################################
# On Tomcat (tested on Tomcat 6.0.20)
########################################

We assume that you have a clean Tomcat version of GateIn: ie We assume that you have already all the jar files of GateIn and their dependencies 
into tomcat/lib and you have the related relam name "gatein-domain" defined in the file tomcat/conf/jaas.conf

1. Add the file sample-portal.war from sample/portal/war/target/ to the tomcat/webapps directory
2. Add the file rest-sample-portal.war from sample/portal/rest-war/target/ to the tomcat/webapps directory
3. Add the folder starter from starter/war/target/ to the tomcat/webapps directory 
4. Rename the directory (unzipped folder) starter to "starter.war" (for more details see the warning below)
5. Add the jar file exo.portal.sample.portal.config-X.Y.Z.jar from sample/portal/config/target/ to the tomcat/lib directory
6. Add the jar file exo.portal.sample.portal.jar-X.Y.Z.jar from sample/portal/jar/target/ to the tomcat/lib directory
7. Define the related realm in your file tomcat/conf/jaas.conf, as below:

gatein-domain-sample-portal {
  org.exoplatform.services.security.jaas.SharedStateLoginModule required
  	portalContainerName="sample-portal" 
  	realmName="gatein-domain-sample-portal";
  org.exoplatform.services.security.j2ee.TomcatLoginModule required 
  	portalContainerName="sample-portal" 
  	realmName="gatein-domain-sample-portal"; 
};
8. Define the context of sample-portal by creating a file called "sample-portal.xml" in tomcat/conf/Catalina/localhost/ with the following content

<Context path='/sample-portal' docBase='sample-portal' debug='0' reloadable='true' crossContext='true' privileged='true'> 
  <Logger className='org.apache.catalina.logger.SystemOutLogger' 
          prefix='localhost_portal_log.' suffix='.txt' timestamp='true'/> 
  <Manager className='org.apache.catalina.session.PersistentManager' saveOnRestart='false'/> 
  <Realm className='org.apache.catalina.realm.JAASRealm' 
         appName='gatein-domain-sample-portal' 
         userClassNames='org.exoplatform.services.security.jaas.UserPrincipal' 
         roleClassNames='org.exoplatform.services.security.jaas.RolePrincipal' 
         debug='0' cache='false'/> 
	 <Valve className='org.apache.catalina.authenticator.FormAuthenticator' characterEncoding='UTF-8'/></Context> 

9. Define the context of rest-sample-portal by creating a file called "rest-sample-portal.xml" in tomcat/conf/Catalina/localhost/ with the following content

<Context path="/rest-sample-portal" docBase="rest-sample-portal" reloadable="true" crossContext="false">

  <Logger className='org.apache.catalina.logger.SystemOutLogger'
            prefix='localhost_portal_log.' suffix='.txt' timestamp='true'/>
    <Manager className='org.apache.catalina.session.PersistentManager' saveOnRestart='false'/>
    <Realm className='org.apache.catalina.realm.JAASRealm'
           appName='gatein-domain-sample-portal'
           userClassNames="org.exoplatform.services.security.jaas.UserPrincipal"
           roleClassNames="org.exoplatform.services.security.jaas.RolePrincipal"
           debug='0' cache='false'/>
</Context>

WARNING: This can only work if the starter.war is the last war file to be loaded, so don't hesitate to rename it if your war files are loaded 
following to the alphabetic order

########################################
# HOW TO TEST
########################################

########################################
# On JBoss (tested on JBoss 5.1.0.GA)
########################################

You need to:

1. Go to the bin directory of jboss 
2. Launch "./run.sh" or "run.bat"
3. When jboss is ready, you can launch your web browser and access to http://localhost:8080/sample-portal 

########################################
# On Tomcat (tested on Tomcat 6.0.20)
########################################

You need to:

1. Go to the bin directory of tomcat 
2. Launch "./gatein.sh run" or "gatein.bat run"
3. When tomcat is ready, you can launch your web browser and access to http://localhost:8080/sample-portal 
