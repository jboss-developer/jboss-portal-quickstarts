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
%>
<#noparse>
<div class="UIHomePagePortlet ClearFix" id="$uicomponent.id">
    <div class="TRContainer">
        <div class="PortletDecoration">
            <div class="GuideText"><%=_ctx.appRes("UIHomePagePortlet.Label.ExtendedHomePage")%>
                <ul>
                    <li><b><%=_ctx.appRes("UIHomePagePortlet.Label.SampleKey")%></b></li>
                    <li><b><%=_ctx.appRes("UIHomePagePortlet.Label.SampleRB.SampleKey")%></b></li>
                </ul>
            </div>
            <a class="VersionIcon" href="http://www.jboss.org/gatein/" target="_blank"></a>
            <div class="DotLine"><span></span></div>
            <div class="GuideText"><%=_ctx.appRes("UIHomePagePortlet.Label.GuideText")%></div>
            <a class="ContactIcon" href="http://community.jboss.org/en/gatein?view=discussions" target="_blank"></a>

        </div>
    </div>
    <div class="TLContainer">
        <div class="PortletDecoration">

            <div class="HomePortletAdBackround">
                <div class="AdImageLeft">
                    <div class="AdImageRight">
                        <div class="EmptyBlock"><%=_ctx.appRes("UIHomePagePortlet.Label.Slogan")%></div>
                    </div>
                </div>
            </div>

            <div class="HomePortletContent">
</#noparse>
<#if compatibility.portal.projectNameShort == "GateIn">
<#noparse>
                <div class="LeftAccountsContainer">
                    <div class="RightAccountsContainer">
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
