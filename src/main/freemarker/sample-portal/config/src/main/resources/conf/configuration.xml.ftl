<?xml version="1.0" encoding="UTF-8"?>
<!--
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
 -->

<configuration xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.exoplatform.org/xml/ns/kernel_1_2.xsd http://www.exoplatform.org/xml/ns/kernel_1_2.xsd" xmlns="http://www.exoplatform.org/xml/ns/kernel_1_2.xsd">
    <external-component-plugins>
        <!-- The full qualified name of the PortalContainerConfig -->
        <target-component>org.exoplatform.container.definition.PortalContainerConfig</target-component>
        <component-plugin>
            <!-- The name of the plugin -->
            <name>Add PortalContainer Definitions</name>
            <!-- The name of the method to call on the PortalContainerConfig in order to register the PortalContainerDefinitions -->
            <set-method>registerPlugin</set-method>
            <!-- The full qualified name of the PortalContainerDefinitionPlugin -->
            <type>org.exoplatform.container.definition.PortalContainerDefinitionPlugin</type>
            <init-params>
                <object-param>
                    <name>sample-portal</name>
                    <object type="org.exoplatform.container.definition.PortalContainerDefinition">
                        <!-- The name of the portal container -->
                        <field name="name">
                            <string>sample-portal</string>
                        </field>
                        <!-- The name of the context name of the rest web application -->
                        <field name="restContextName">
                            <string>rest-sample-portal</string>
                        </field>
                        <!-- The name of the realm -->
                        <field name="realmName">
                            <string>gatein-domain-sample-portal</string>
                        </field>
                        <field name="externalSettingsPath">
                            <string>configuration.properties</string>
                        </field>
                        <!-- All the dependencies of the portal container ordered by loading priority -->
                        <field name="dependencies">
                            <collection type="java.util.ArrayList">
                                <value>
                                    <string>eXoResources</string>
                                </value>
                                <value>
                                    <string>portal</string>
                                </value>
                                <value>
                                    <string>dashboard</string>
                                </value>
                                <value>
                                    <string>exoadmin</string>
                                </value>
                                <value>
                                    <string>eXoGadgets</string>
                                </value>
                                <value>
                                    <string>eXoGadgetServer</string>
                                </value>
                                <value>
                                    <string>rest-sample-portal</string>
                                </value>
                                <value>
                                    <string>web</string>
                                </value>
<#if compatibility.portal.projectNameShort != "GateIn">
                                <!--
                                    In Sample Portal, we rely on JppSkin for BannerPortlet and NavigationPortlet defined
                                    in jpp-ext and jpp-branding-skin. Note that jpp-ext is the display-name of
                                    jpp-branding-extension.war.
                                    You can remove them both safely, when you define your own skin.
                                -->
                                <value>
                                    <string>jpp-ext</string>
                                </value>
                                <value>
                                    <string>jpp-branding-skin</string>
                                </value>
</#if>
                                <value>
                                    <string>gatein-mobile-login</string>
                                </value>
                                <value>
                                    <string>sample-portal</string>
                                </value>
                            </collection>
                        </field>
                    </object>
                </object-param>
            </init-params>
        </component-plugin>
    </external-component-plugins>
</configuration>
