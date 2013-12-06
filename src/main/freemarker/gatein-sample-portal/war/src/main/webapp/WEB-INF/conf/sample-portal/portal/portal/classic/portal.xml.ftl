<?xml version="1.0" encoding="ISO-8859-1"?>
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

<portal-config
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.portal.org/xml/ns/gatein_objects_1_4 http://www.portal.org/xml/ns/gatein_objects_1_4"
    xmlns="http://www.portal.org/xml/ns/gatein_objects_1_4">
  <portal-name>classic</portal-name>
  <label>Classic</label>
  <description>Sample Portal</description>
  <locale>en</locale>
  <access-permissions>Everyone</access-permissions>
  <edit-permission>*:/platform/administrators</edit-permission>
<#if compatibility.portal.projectNameShort == "GateIn">
  <skin>Default</skin>
<#else>
  <skin>JppSkin</skin>
</#if>
  <properties>
    <entry key="sessionAlive">onDemand</entry>
    <entry key="showPortletInfo">0</entry>
  </properties>
  <portal-layout>
    <portlet-application>
      <portlet>
        <application-ref>web</application-ref>
        <portlet-ref>BannerPortlet</portlet-ref>
        <preferences>
          <preference>
            <name>template</name>
            <value>par:/groovy/groovy/webui/component/UIBannerPortlet.gtmpl</value>
            <read-only>false</read-only>
          </preference>
        </preferences>
      </portlet>
      <access-permissions>Everyone</access-permissions>
      <show-info-bar>false</show-info-bar>
    </portlet-application>

    <portlet-application>
      <portlet>
        <application-ref>web</application-ref>
        <portlet-ref>NavigationPortlet</portlet-ref>
        <preferences>
          <preference>
            <name>useAJAX</name>
            <value>false</value>
            <read-only>false</read-only>
          </preference>
        </preferences>
      </portlet>
      <access-permissions>Everyone</access-permissions>
      <show-info-bar>false</show-info-bar>
    </portlet-application>

    <portlet-application>
      <portlet>
        <application-ref>web</application-ref>
        <portlet-ref>BreadcumbsPortlet</portlet-ref>
        <preferences>
          <preference>
            <name>useAJAX</name>
            <value>false</value>
            <read-only>false</read-only>
          </preference>
        </preferences>
      </portlet>
      <access-permissions>Everyone</access-permissions>
      <show-info-bar>false</show-info-bar>
    </portlet-application>

    <page-body></page-body>

    <portlet-application>
      <portlet>
        <application-ref>web</application-ref>
        <portlet-ref>FooterPortlet</portlet-ref>
        <preferences>
          <preference>
            <name>template</name>
            <value>par:/groovy/groovy/webui/component/UIFooterPortlet.gtmpl</value>
            <read-only>false</read-only>
          </preference>
        </preferences>
      </portlet>
      <access-permissions>Everyone</access-permissions>
      <show-info-bar>false</show-info-bar>
    </portlet-application>

  </portal-layout>

</portal-config>
