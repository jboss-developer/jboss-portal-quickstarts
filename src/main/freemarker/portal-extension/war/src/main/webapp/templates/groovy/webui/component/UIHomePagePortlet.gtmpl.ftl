<%
    /*
     * JBoss, Home of Professional Open Source
     * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
     * contributors by the @authors tag. See the copyright.txt in the
     * distribution for a full listing of individual contributors.
     *
     * Licensed under the Apache License, Version 2.0 (the "License");
     * you may not use this file except in compliance with the License.
     * You may obtain a copy of the License at
     * http://www.apache.org/licenses/LICENSE-2.0
     * Unless required by applicable law or agreed to in writing, software
     * distributed under the License is distributed on an "AS IS" BASIS,
     * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     * See the License for the specific language governing permissions and
     * limitations under the License.
     */

    /**
     * This Groovy template overrides the template available in the default Portal installation
     * under portal.war/templates/groovy/webui/component/UIHomePagePortlet.gtmpl.
     */


    String initialURI = _ctx.getRequestContext().getParentAppRequestContext().getInitialURI();

    /* In gtmpl, static classes do not work as expected. As a workaround we create an instance. */
    LinksAndVersions LinksAndVersions = new LinksAndVersions();

%>
<#noparse>
<div class="UIHomePagePortlet ClearFix" id="$uicomponent.id">
</#noparse>
<#if compatibility.portal.projectNameShort == "GateIn">
<#noparse>
    <div class="TRContainer">
        <div class="PortletDecoration">
            <div class="MiddleAccountsContainer">
                <div class="InstructionTitle"><%=_ctx.appRes("UIHomePagePortlet.Label.Title")%></div>
                <div class="AccountsContainerDeco ClearFix">
                    <div class="AccountBlock AdministratorUser">
                        <div class="AccountInfos">
                            <div class="AccountTitle"><a href="${_ctx.getPortalContextPath()}/login?username=root&amp;password=gtn&amp;initialURI=<%=initialURI%>"><%=_ctx.appRes("UIHomePagePortlet.Label.Administrator")%></a></div>
                            <div class="Username ClearFix">
                                <div class="Lable"><%=_ctx.appRes("UIHomePagePortlet.Label.Username")%></div><span>root</span>
                            </div>
                            <div class="Passwords ClearFix">
                                <div class="Lable"><%=_ctx.appRes("UIHomePagePortlet.Label.Password")%></div><span>gtn</span>
                            </div>
                        </div>
                    </div>
                    <div class="SeparatorLine"><span></span></div>
                    <div class="AccountBlock ManagerUser">
                        <div class="AccountInfos">
                            <div class="AccountTitle"><a href="${_ctx.getPortalContextPath()}/login?username=john&amp;password=gtn&amp;initialURI=<%=initialURI%>"><%=_ctx.appRes("UIHomePagePortlet.Label.Manager")%></a></div>
                            <div class="Username ClearFix">
                                <div class="Lable"><%=_ctx.appRes("UIHomePagePortlet.Label.Username")%></div><span>john</span>
                            </div>
                            <div class="Passwords ClearFix">
                                <div class="Lable"><%=_ctx.appRes("UIHomePagePortlet.Label.Password")%></div><span>gtn</span>
                            </div>
                        </div>
                    </div>
                    <div class="SeparatorLine"><span></span></div>
                    <div class="AccountBlock NormalUser">
                        <div class="AccountInfos">
                            <div class="AccountTitle"><a href="${_ctx.getPortalContextPath()}/login?username=mary&amp;password=gtn&amp;initialURI=<%=initialURI%>"><%=_ctx.appRes("UIHomePagePortlet.Label.User")%></a></div>
                            <div class="Username ClearFix">
                                <div class="Lable"><%=_ctx.appRes("UIHomePagePortlet.Label.Username")%></div><span>mary</span>
                            </div>
                            <div class="Passwords ClearFix">
                                <div class="Lable"><%=_ctx.appRes("UIHomePagePortlet.Label.Password")%></div><span>gtn</span>
                            </div>
                        </div>
                    </div>
                    <div class="SeparatorLine"><span></span></div>
                    <div class="AccountBlock DemoUser" style="margin-right: 0px;">
                        <div class="AccountInfos">
                            <div class="AccountTitle"><a href="${_ctx.getPortalContextPath()}/login?username=demo&amp;password=gtn&amp;initialURI=<%=initialURI%>"><%=_ctx.appRes("UIHomePagePortlet.Label.Demo")%></a></div>
                            <div class="Username ClearFix">
                                <div class="Lable"><%=_ctx.appRes("UIHomePagePortlet.Label.Username")%></div><span>demo</span>
                            </div>
                            <div class="Passwords ClearFix">
                                <div class="Lable"><%=_ctx.appRes("UIHomePagePortlet.Label.Password")%></div><span>gtn</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#noparse>
</#if>
<#noparse>
    <div class="TLContainer">
        <div class="PortletDecoration">

            <div class="HomePortletAdBackround">
                <div class="AdImageLeft">
                    <div class="AdImageRight">
                        <div class="EmptyBlock">
                            <%=_ctx.appRes("UIHomePagePortlet.Label.Slogan")%>
                            <div><%=_ctx.appRes("UIHomePagePortlet.Label.SubSlogan")%></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="HomePortletContent">
                <h2>About this Portal Extension</h2>
                <p>
                    This Extension modifies the Portal Container called "portal" available in a default ${LinksAndVersions.compatibility_portal_projectName}
                    installation.
                </p>
                <p>
                    Please read the
                    <a href="${LinksAndVersions.portal_devguide_extensions_url}">Portal Extension</a> section of
                    <a href="${LinksAndVersions.portal_devguide_url}">${LinksAndVersions.compatibility_portal_projectName} Developer Guide</a>
                    to learn how the Extension Mechanism works.
                </p>
                <p>
                    This Portal Extension customizes the following aspects of the default portal accessible under <code>/portal</code>:
                </p>

                <h3>1. Custom Groovy Template for <code>HomePagePortlet</code></h3>
                <p>
                    <code>HomePagePortlet</code> produces the present UI block.
                </p>
                <p>
                    Important file:
                    <ul>
                        <li><code>war/src/main/webapp/templates/groovy/webui/component/UIHomePagePortlet.gtmpl</code></li>
                    </ul>
                </p>
                <p>
                    See also <a href="${LinksAndVersions.portal_devguide_custom_groovy_template_url}">Custom Groovy Template for a Portlet</a> section of
                    <a href="${LinksAndVersions.portal_devguide_url}">${LinksAndVersions.compatibility_portal_projectName} Developer Guide</a>.
                </p>

                <h3>2. Custom Skins for <code>BannerPortlet</code> and <code>HomePagePortlet</code></h3>
                <p>
                    <code>BannerPortlet</code> produces the above block with ${LinksAndVersions.compatibility_portal_projectName} logo. In the custom skin, we override the
                    default ${LinksAndVersions.compatibility_portal_projectName} logo with the above ${LinksAndVersions.compatibility_portal_projectName} Portal Extension logo.
                </p>
                <p>
                    Important files:
                    <ul>
                        <li><code>war/src/main/webapp/WEB-INF/gatein-resources.xml</code></li>
                        <li>CSS and image files under <code>war/src/main/webapp/templates/skin/webui/component/UIBannerPortlet</code></li>
                    </ul>
                </p>
                <p>
                    See also <a href="${LinksAndVersions.portal_devguide_custom_portlet_skin_url}">Custom Skin for a Portlet</a> section of
                    <a href="${LinksAndVersions.portal_devguide_url}">${LinksAndVersions.compatibility_portal_projectName} Developer Guide</a>.
                </p>

                <h3>3. Custom Navigation and Pages</h3>
                <p>
                    In this Portal Extension, we have added a couple of navigation nodes and pages to ones available in the
                    default ${LinksAndVersions.compatibility_portal_projectName} installation.
                </p>
                <p>
                    Important files:
                    <ul>
                        <li><code>war/src/main/webapp/WEB-INF/conf/sample-ext/portal/portal-configuration.xml</code></li>
                        <li><code>page.xml</code> and <code>navigation.xml</code> files under <code>war/src/main/webapp/WEB-INF/conf/sample-ext/portal</code></li>
                        <li><code>war/src/main/webapp/WEB-INF/conf/sample-ext/common/common-configuration.xml</code></li>
                        <li><code>*.properties</code> files under <code>war/src/main/webapp/WEB-INF/classes/locale/navigation</code></li>
                    </ul>
                </p>
                <p>
                    See also <a href="${LinksAndVersions.portal_devguide_nav_pages_url}">Custom Navigation and Pages</a> section of
                    <a href="${LinksAndVersions.portal_devguide_url}">${LinksAndVersions.compatibility_portal_projectName} Developer Guide</a>.
                </p>

                <h3>4. Custom Internationalization Resource Bundles</h3>
                <p>
                    In the above gray box we use the resource key <code>UIHomePagePortlet.Label.Slogan</code>.
                    Although it is already defined in
                    <code>portal.ear/web.war/WEB-INF/classes/locale/portlet/web/GroovyPortlet_en.properties</code> as
                    <pre>UIHomePagePortlet.Label.Slogan=The Best of eXo and JBoss Portal&lt;div&gt;GateIn #{portal.version}&lt;/div&gt;</pre>
                    here, within the Portal Extension, we redefine it as
                    <pre>UIHomePagePortlet.Label.Slogan=Congratulations!</pre>
                </p>
                <p>
                    Important files:
                    <ul>
                        <li><code>war/src/main/webapp/WEB-INF/conf/sample-ext/common/common-configuration.xml</code></li>
                        <li><code>war/src/main/webapp/WEB-INF/classes/locale/portal/extension_en.properties</code></li>
                    </ul>
                </p>
                <p>
                    See also <a href="${LinksAndVersions.portal_devguide_i18n_url}">Custom Internationalization Resource Bundles</a> section of
                    <a href="${LinksAndVersions.portal_devguide_url}">${LinksAndVersions.compatibility_portal_projectName} Developer Guide</a>.
                </p>

                <h3>5. Custom Login Page</h3>
                <p>
                    To see it, ensure that you are not signed in and visit this
                    <a href="/portal/g/:platform:administrators/administration/registry">link</a>.
                </p>
                <p>
                    Important file:
                    <ul>
                        <li><code>war/src/main/webapp/login/jsp/login.jsp</code></li>
                    </ul>
                </p>
                <hr/>
                <p>
                    Your feedback is welcome on <a href="${LinksAndVersions.portal_user_forum_url}">Online User Group</a>.
                </p>
            </div>

        </div>
    </div>
</div>
<div class="BottomDecoratorHome">
    <div class="BottomDecoratorLeft">
        <div class="BottomDecoratorRight">
            <div class="BottomDecoratorMiddle"><span></span></div>
        </div>
    </div>
</div>
</#noparse>
<%
/**
 * LinksAndVersions is here as a workaround for GTNPORTAL-2871
 *
 * A helper class generated through <code>mvn install -P generate-readmes</code>.
 * The constants in this class mirror properties from top pom.xml file.
 *
 * @author Peter Palaga
 *
 */
public class LinksAndVersions {
<#list linksAndVersions?keys as key>
    public static final String ${key} = "${linksAndVersions[key]}";
</#list>
}
%>
