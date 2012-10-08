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

1. Add the file sample-ext.ear from sample/extension/ear/target/ to the deploy directory of jboss 
2. Add the file starter.ear from starter/ear/target/ to the deploy directory of jboss 

WARNING: This can only work if a Unified ClassLoader has been configured on your JBoss (default behavior) and
the load order is first the exoplatform.ear then the sample-ext.ear and finally the starter.ear

########################################
# On Tomcat (tested on Tomcat 6.0.20)
########################################

We assume that you have a clean Tomcat version of GateIn: ie We assume that you have already all the jar files of GateIn and their dependencies 
into tomcat/lib and you have the related relam name "gatein-domain" defined in the file tomcat/conf/jaas.conf

1. Add the file sample-ext.war from sample/extension/war/target/ to the tomcat/webapps directory
2. Add the folder starter from starter/war/target/ to the tomcat/webapps directory 
3. Rename the directory (unzipped folder) starter to "starter.war" (for more details see the warning below)
4. Add the jar file exo.portal.sample.extension.config-X.Y.Z.jar from sample/extension/config/target/ to the tomcat/lib directory
5. Add the jar file exo.portal.sample.extension.jar-X.Y.Z.jar from sample/extension/jar/target/ to the tomcat/lib directory

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
3. When jboss is ready, you can launch your web browser and access to http://localhost:8080/portal 

########################################
# On Tomcat (tested on Tomcat 6.0.20)
########################################

You need to:

1. Go to the bin directory of tomcat 
2. Launch "./gatein.sh run" or "gatein.bat run"
3. When tomcat is ready, you can launch your web browser and access to http://localhost:8080/portal 
