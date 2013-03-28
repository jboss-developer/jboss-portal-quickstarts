/*
 * JBoss, Home of Professional Open Source
 * Copyright <Year>, Red Hat, Inc. and/or its affiliates, and individual
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

/*
 * jQuery drop-down menu plugin for quickstart navigation portlet interface. 
 * Features:
 *  - Basic on-click drop-down support.
 *  - Dynamically respond to window changes, distributes menus not to be outside the window.
 *  - Marking the currently opened node for better orientation.
 */
(function($) {
    $.fn.dropdownmenu = function(options) {

        var settings = $.extend({}, {
            // Selector for the menu-element.
            menuElement: ".menuitem",
            // Selector for the to-be-collapsed element inside this parent element.
            arrowElement: ".menuhandler",
            // Class which is set to the to-be-collapsed element when it's collapsed.
            collapsedClass: "expanded",
            // Selector for the submenu element.
            submenuElement: ".submenu",
            // Class which is set to the menu which does not fit on screen.
            inverseSubmenuClass: "inverse",
            // Class which is set to the last opened menuitem.
            lastOpenedClass: "current"
        }, options);

        console && console.log("Drop-down intialized");

        // Remember the topmenu element to be able to check if submenu fits into resized window
        var topmenu = $(this);

        // Traverse opened submenus from parent menu and inverse them if needed
        function findAndCheckOpenedSubmenu(parentMenu) {
            var openedSubmenu = parentMenu.children(settings.menuElement + ":not(." + settings.collapsedClass + ")").children(settings.submenuElement).first();

            // if no submenu is found, do nothing
            if (openedSubmenu.offset() === null) {
                return;
            }

            // Check whether the submenu fits into the window
            checkMenuFit(openedSubmenu);

            findAndCheckOpenedSubmenu(openedSubmenu);
        }

        function checkMenuFit(submenuElement) {
            var screenWidth = $(window).width();

            // If no submenu is opened, do nothing
            if (submenuElement.offset() === null)
                return;

            // Put the submenu into its default state
            submenuElement.removeClass(settings.inverseSubmenuClass);

            // Get the width and left offset of the submenut in its default state
            var elementWidth = submenuElement.outerWidth(true);
            var handlerLeft = submenuElement.offset().left;

            // If the submenu overflows over right border of page, move it to the left / reverse it (apply inverse CSS)
            if ((elementWidth + handlerLeft > screenWidth) || (handlerLeft < 0)) {
                if (!submenuElement.hasClass(settings.inverseSubmenuClass))
                    submenuElement.addClass(settings.inverseSubmenuClass);
            }
        }

        function openSubmenu(menuItem) {
            // Close sibling submenus so that only the submenu opened above is present
            menuItem.siblings().each(function() {
                //console&&console.log("close!!! "+$(this).html());
                $(this).toggleClass(settings.collapsedClass, true);
            });

            // Close all submenus and reset the current class if present in them
            menuItem.find(settings.menuElement).each(function() {
                if (!$(this).hasClass(settings.collapsedClass))
                    $(this).addClass(settings.collapsedClass);

                $(this).removeClass(settings.lastOpenedClass);
            });

            // Open the submenu under the menu handler
            menuItem.toggleClass(settings.collapsedClass);

            // Mark current menuitem and unmark its parent
            if (!menuItem.hasClass(settings.collapsedClass)) {
                menuItem.addClass(settings.lastOpenedClass);
                menuItem.parent().parent().removeClass(settings.lastOpenedClass);
            } else {
                menuItem.removeClass(settings.lastOpenedClass);
                menuItem.parent().parent().addClass(settings.lastOpenedClass);
            }

            // Find the newly opened submenu
            var submenuElement = menuItem.children(settings.submenuElement);

            // Check whether the newly opened submenu fits into the window
            checkMenuFit(submenuElement);
        }

        // Check wether the submenu fits into the resized window
        $(window).resize(function() {
            // Traverse from topmenu through opened submenus and inverse them if needed
            findAndCheckOpenedSubmenu(topmenu);
        });

        /* Apply the onClick event function for each menu handler (arrow)
         * Thanks to the usage of the on function, this is applied even to
         * the content loaded by ajax and inserted to the dom in the future.
         */
        
        $(this).on("click", settings.arrowElement, function(e) {
            console && console.log("Click event: " + $(this).attr('href'));

            // Prevent links from opening new pages
            e.preventDefault();

            var menuItem = $(this).parent(settings.menuElement);

            //console && console.log("Children: " + menuItem.children(".submenu").length);

            if (menuItem.children(".submenu").length === 0) {
                console && console.log("Is not collapsed...");
                $.ajax({
                    type: "POST",
                    url: $(this).attr('href'),
                    cache: false,
                    dataType: "text",
                    success: function(data)
                    {
                        console && console.log("Ajax done:");
                        menuItem.append(data);
                        openSubmenu(menuItem);
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown)
                    {
                        console && console.log("Ajax error");
                    }
                });
            } else {
                console && console.log("Is collapsed...");
                menuItem.children().remove(".submenu");
            }
        });
        
    };

})(jQuery);