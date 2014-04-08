/**
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
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
package org.jboss.quickstarts.portal.composepage;

import org.gatein.api.Portal;
import org.gatein.api.PortalRequest;
import org.gatein.api.application.Application;
import org.gatein.api.application.ApplicationRegistry;
import org.gatein.api.page.Page;
import org.gatein.api.composition.PageBuilder;
import org.gatein.api.page.PageId;
import org.gatein.api.security.Permission;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.portlet.GenericPortlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * This is a sample portlet that consumes the Compose Page API. It shows how to query the Application Registry, to
 * get a list of portlets/gadgets, and uses the API to create pages, using some of the applications on the composed
 * page.
 *
 * In your application, this code would be in the business layer, which would make a decision on how a page would
 * be composed. Thus, there's just the bare minimum of UI code on this example.
 *
 * For more information about the API, consult the javadocs and/or the API wiki page.
 *
 * @author Juraci Paixão Kröhling
 */
public class ComposePageDemoPortlet extends GenericPortlet {

    // for this example, we are doing everything inside this portlet's doView, but this would
    // certainly be inside the business layer of your application
    public void doView(RenderRequest request, RenderResponse response) throws IOException {

        // a simple writer, to which we'll print "Create!" at the end
        PrintWriter writer = response.getWriter();

        // get an instance of the Portal, from which we'll get to the API
        Portal portal = PortalRequest.getInstance().getPortal();

        // example on how to just change a couple properties from an existing page or to create a page without content
        Page oldPage = portal.createPage(new PageId("classic", "mypage"));
        oldPage.setDisplayName("My Page");
        portal.savePage(oldPage);

        // Gets a list of portlets, gadgets or WSRP from the current portal instance.
        // Check the API for Application and ApplicationType for more details.
        // Your application could be showing this list on a page to the final user
        // or we could be matching the name of some applications that we would be replacing.
        // For instance, let's say that your company's employee registry has changed, and you want to change
        // all the pages that references the old one by the new one...
        ApplicationRegistry registry = portal.getApplicationRegistry();
        List<Application> applications;
        try {
            applications = registry.getApplications();
        } catch (Exception e) {
            e.printStackTrace();
            // we might have had some problems with the permanent storage, for instance
            writer.write("Exception while trying to create:" + e.getMessage());
            writer.close();
            return;
        }

        // This would be after the processing of the user's choices, or the representation of the new "employee registry"
        // as we exemplified before
        Application gadgetRss = applications.get(0);
        Application gadgetCalculator = gadgetRss; // for the purposes of this quickstart, all our applications are the same
        Application wsrpCompanyNews = gadgetRss;
        Application portletUsefulLinks = gadgetRss;

        // Gets a new page builder from the main API.
        PageBuilder pageBuilder = portal.newPageBuilder();

        // And now, we compose the page.
        Page page = pageBuilder
                .newRowsBuilder() // we three rows
                    .newColumnsBuilder() // tells the API that we want a column set as the first thing on the page
                        .child(gadgetRss) // this would be about 1/3 of the width of the first row on the screen
                        .child(gadgetRss) // same as above
                        .child(gadgetRss) // same as above
                    .buildToParentBuilder() // finishes the work on this column

                    .newColumnsBuilder() // second column set, in the second row
                        .child(gadgetCalculator) // this would be about 1/2 of the width of the first row on the screen
                        .child(wsrpCompanyNews) // same as above
                    .buildToParentBuilder() // finishes the work on this column

                    .newColumnsBuilder() // third column set, on the third row
                        .child(portletUsefulLinks) // about 1/3 of the width of the third row on the screen
                        .child(portletUsefulLinks) // same as above
                        .newColumnsBuilder() // a column set inside this column set: the whole set is 1/3 of the total width
                            .child(gadgetCalculator) // and this is 50% of the 1/3
                            .child(gadgetRss) // same as above
                        .buildToParentBuilder() // finishes work on the nested column set
                    .buildToParentBuilder() // finishes work on the third column set
                .buildToTopBuilder()
                .siteName("classic") // to which site should this page belong to?
                .siteType("portal")
                .name("awesome_" + UUID.randomUUID().toString()) // what would be the short name of the page?
                .displayName("Awesome page") // and how should be the display name?
                .showMaxWindow(false) // should we show the "maximize window" option?
                .accessPermission(Permission.everyone()) // the permissions that we've specified earlier
                .editPermission(Permission.any("platform", "administrators"))
                .moveAppsPermission(Permission.everyone())
                .moveContainersPermission(Permission.everyone())
                .build(); // finishes building the Page object

        portal.savePage(page); // and finally, persist the page!

        // in some situations, your application might know already the ID of the page that it wants to change
        // if that's the case, then you can just call the getPage, passing the page ID.
        Page pageFromStorage = portal.getPage(page.getId());

        // end of the quick start
        writer.write("Created!");
        writer.close();
    }
}
