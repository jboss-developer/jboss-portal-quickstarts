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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<script type="text/javascript">
    function <portlet:namespace/>callResource(type) {
        var xhr = new XMLHttpRequest();
        var url;
        if (type == 'set') {
            url = '<portlet:resourceURL id="set" />';
        } else {
            url = '<portlet:resourceURL id="check" />';
        }
        xhr.onreadystatechange = function() {
            if (this.readyState == 4) {
                alert(xhr.responseText);
            }
        };
        xhr.open("GET", url, true);
        xhr.send();
    }
</script>

<div class="portlet-section-header">Hello ${bean.getText()}</div>

<br/>

<div class="portlet-font">Update text with <a href="#" onclick="<portlet:namespace/>callResource('set');">ResourceRequest</a></div>
<div class="portlet-font">Check value with <a href="#" onclick="<portlet:namespace/>callResource('check');">ResourceRequest</a></div>

<br/>

<portlet:renderURL var="myRenderURL">
    <portlet:param name="reset" value='true'/>
</portlet:renderURL>
<a href="<%= myRenderURL %>">Ask me again</a>
<hr/>