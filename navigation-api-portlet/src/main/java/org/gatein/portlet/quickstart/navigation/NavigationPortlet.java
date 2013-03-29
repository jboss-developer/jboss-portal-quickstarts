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
package org.gatein.portlet.quickstart.navigation;

import java.io.IOException;
import java.util.List;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.PortletRequestDispatcher;
import org.gatein.api.PortalRequest;
import org.gatein.api.navigation.Navigation;
import org.gatein.api.navigation.Node;
import org.gatein.api.navigation.NodePath;
import org.gatein.api.navigation.Nodes;

/**
 * Navigation portlet implemented using the GateIn navigation API.
 *
 * @author <a href="mailto:vrockai@redhat.com">Viliam Rockai</a>
 */
public class NavigationPortlet extends GenericPortlet {

    // The root navigation bean contains the top-menu elements (Home and Sitemap by default) as its direct children nodes.
    private NavigationNodeBean navigationRootNodeBean;

    /**
     * Method responsible for the VIEW mode of the navigation portlet.
     * This method passes the navigationRootNodeBean as an attribute to the JSP page.
     * The navigationRootNodeBean is the root node of the navigation and contains main menu (top-menu) elements (Home and Sitemap
     * by default) as children nodes.
     *
     * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
     * @param request the portlet request
     * @param response the render response
     * @throws PortletException if the portlet cannot fulfilling the request
     * @throws IOException if the streaming causes an I/O problem
     */
    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {

        PortalRequest portalRequest = PortalRequest.getInstance();

        Navigation navigation = PortalRequest.getInstance().getNavigation();
                
        // Diving two levels so the information about children count of children nodes is available
        Node rootNode = navigation.getRootNode(Nodes.visitNodes(2));

        navigationRootNodeBean = new NavigationNodeBean(rootNode);

        List<NavigationNodeBean> rootNodeChildrenList = navigationRootNodeBean.getChildren();

        /* Setting the 1st node to be active when accesing the root node "/" */
        if (!rootNodeChildrenList.isEmpty() && portalRequest.getNodePath().equals(NodePath.root())) {
            navigationRootNodeBean.setFirstActive();
        }

        request.setAttribute("navigationRootNode", navigationRootNodeBean);

        PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/navigation.jsp");
        prd.include(request, response);
    }

    /**
     * The serveResource method is used for handling AJAX requests. It's used for the rendering of sub-menus. Anytime
     * users clicks on the menu item, the URI parameter is passed to the serveResource method. This parameter contains the URI
     * of the node which sub-menu is about to be rendered.
     *
     * @see javax.portlet.GenericPortlet#serveResource(javax.portlet.ResourceRequest, javax.portlet.ResourceResponse)
     *
     * @param request the resource request
     * @param response the resource response
     * @throws PortletException if the portlet has problems fulfilling the rendering request
     * @throws IOException if the streaming causes an I/O problem
     */
    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, IOException {

        Navigation navigation = PortalRequest.getInstance().getNavigation();
        
        String chosenNodeURI = request.getParameter("uri");

        Node chosenNode = navigation.getNode(NodePath.fromString(chosenNodeURI), Nodes.visitNodes(2));
        
        request.setAttribute("parentNode", new NavigationNodeBean(chosenNode));

        PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/node.jsp");
        prd.include(request, response);
    }
}
