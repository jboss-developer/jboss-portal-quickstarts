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
package org.jboss.quickstarts.portal.navigation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.gatein.api.PortalRequest;
import org.gatein.api.navigation.Node;
import org.gatein.api.navigation.NodePath;
import org.gatein.api.navigation.Visibility;

/**
 *
 * {@link NavigationNodeBean} is representing a single navigation node. It basically encapsulates
 * the {@link org.gatein.api.navigation.Node} from the GateIn Portal API.
 *
 * @author <a href="mailto:vrockai@redhat.com">Viliam Rockai</a>
 */
public class NavigationNodeBean {

    private Node node;

    /**
     * Flag marking currently accessed node. If the page within the node is accessed in the browser, the node state should
     * be set to active.
     */
    private boolean active = false;

    // Flag used to indicate that the 1st child of the current node should be set active.
    private boolean firstActive = false;

    /**
     * Returns true, if the current node is a system node. A system node is a node with visibility status of value
     * {@code Visibility.Status.SYSTEM}.
     *
     * @return true, if the current node is a system node.
     */
    public boolean isSystem() {
        return node.getVisibility().getStatus().equals(Visibility.Status.SYSTEM);
    }

    /**
     * Constructs a new {@link NavigationNodeBean} encapsulating the {@link org.gatein.api.navigation.Node} instance specified
     * in the parameter.
     *
     * @param node A node to be encapsulated by this NavigationNodeBean.
     */
    public NavigationNodeBean(Node node) {
        this.node = node;
    }

    /**
     * Sets the current {@link NavigationNodeBean} node active state. The active state indicates that this bean is being
     * currently accessed in the user interface.
     *
     * @param active A boolean value.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the current NavigationNodeBean active state.
     *
     * @see #setActive(boolean)
     * @return The current NavigationNodeBean active state.
     */
    public boolean isActive() {
        NodePath currentPath = PortalRequest.getInstance().getNodePath();
        NodePath nodePath = node.getNodePath();

        if (!active) {
            active = (nodePath != null) ? nodePath.equals(currentPath) : false;
        }

        return active;
    }

    /**
     * Returns the {@link String} value of path to the current NavigationNodeBean.
     *
     * @return The {@link String} value of path to the current NavigationNodeBean.
     */
    public String getPath(){
        return this.node.getNodePath().toString();
    }

    /**
     * Returns true if current node has a page assigned to it, false otherwise. Node doesn't have to have the page assigned.
     * Node without any pages can serve as "categories".
     *
     * @return true if current node has a page assigned to it, false otherwise.
     */
    public boolean isPage() {
        return node.getPageId() != null;
    }

    /**
     * Returns true if parent node contains one or more children nodes.
     *
     * @return true if current node children count is higher than 0 (it contains any children nodes), false otherwise.
     */
    public boolean isParent() {
        return node.getChildCount() > 0;
    }

    /**
     * Returns the display name of the encapsulated node.
     *
     * @return The display name of the encapsulated node.
     */
    public String getName() {
        return node.getDisplayName();
    }

    /**
     * Returns the URI string of the encapsulated node.
     *
     * @return The URI string of the encapsulated node.
     */
    public String getURI() {
        return node.getURI();
    }

    /**
     * Sets the {@link #firstActive} field to true. The {@link #firstActive} field is used to indicate that the 1st child of the
     * current node should be set active.
     */
    public void setFirstActive(){
        firstActive = true;
    }

    /**
     * Returns children nodes for current bean.
     *
     * @see #setActive(boolean)
     * @see #setFirstActive()
     * @return Children nodes for current bean.
     */
    public List<NavigationNodeBean> getChildren() {
        List<NavigationNodeBean> nodes = new ArrayList<NavigationNodeBean>();

        boolean firstActiveSet = false;

        Iterator<Node> nodeIterator = node.iterator();

        while (nodeIterator.hasNext()) {
            Node childNode = nodeIterator.next();
            NavigationNodeBean childNodeBean = new NavigationNodeBean(childNode);

            if (firstActive && !firstActiveSet){
                childNodeBean.setActive(true);
                firstActiveSet = true;
            }
            nodes.add(childNodeBean);
        }

        return nodes;
    }
}