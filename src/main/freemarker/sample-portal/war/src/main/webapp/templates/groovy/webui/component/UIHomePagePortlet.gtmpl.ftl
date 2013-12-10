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
                            Sample Portal Home
                        </div>
                    </div>
                </div>
            </div>

            <div class="HomePortletContent">
                <h2>About this Sample Portal</h2>
                <p><%=_ctx.appRes("UIHomePagePortlet.Label.ExtendedHomePage")%></p>
                <ul>
                    <li><%=_ctx.appRes("UIHomePagePortlet.Label.SampleKey")%></li>
                    <li><%=_ctx.appRes("UIHomePagePortlet.Label.SampleRB.SampleKey")%></li>
                </ul>
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
public static class LinksAndVersions {
<#list linksAndVersions?keys as key>
    public static final String ${key} = "${linksAndVersions[key]}";
</#list>
}
%>
