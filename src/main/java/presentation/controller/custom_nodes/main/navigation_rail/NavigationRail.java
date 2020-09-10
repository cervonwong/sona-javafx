/*
 * Sona is an educational SRS application written in JavaFX.
 * Copyright (C) 2020 Cervon Wong
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package main.java.presentation.controller.custom_nodes.main.navigation_rail;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.java.i18n.ResourceBundles;

import java.io.IOException;
import java.util.*;

public class NavigationRail extends VBox {

    // INSTANCE VARIABLES

    private final ResourceBundle messages;

    private final Map<String, NavigationRailItem> navigationRailItems = new HashMap<>();


    // JAVAFX PROPERTIES

    private final SimpleStringProperty activeItem = new SimpleStringProperty();

    // FXML NODES

    @FXML
    private Pane dividerPane;


    // CONSTRUCTOR

    public NavigationRail() {
        messages = initializeMessages();

        initializeFxml();
        initializeItems();

        initializeActiveItemListener();
        initializeItemListener();
        setActiveItem("dashboard");
    }


    // INITIALIZERS

    private ResourceBundle initializeMessages() {
        return ResourceBundle.getBundle(ResourceBundles.NAVIGATION_RAIL.getBundleName());
    }

    private void initializeFxml() {
        final String RESOURCE_PATH =
                "/view/fxml/custom_nodes/main/navigation_rail/navigation_rail.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(RESOURCE_PATH));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeItems() {
        createItems();
        addItems();
        initializeItemWidthProperties();
    }

    private void createItems() {
        navigationRailItems.put(Destination.DASHBOARD.getKey(),
                                new NavigationRailItem(Destination.DASHBOARD.getKey(),
                                                       "\uF015",
                                                       messages.getString(Destination.DASHBOARD.getKey())));

        navigationRailItems.put(Destination.DECKS.getKey(),
                                new NavigationRailItem(Destination.DECKS.getKey(),
                                                       "\uF5DB",
                                                       messages.getString(Destination.DECKS.getKey())));

        navigationRailItems.put(Destination.EDIT.getKey(),
                                new NavigationRailItem(Destination.EDIT.getKey(),
                                                       "\uF044",
                                                       messages.getString(Destination.EDIT.getKey())));

        navigationRailItems.put(Destination.BROWSE.getKey(),
                                new NavigationRailItem(Destination.BROWSE.getKey(),
                                                       "\uF002",
                                                       messages.getString(Destination.BROWSE.getKey())));

        navigationRailItems.put(Destination.STATS.getKey(),
                                new NavigationRailItem(Destination.STATS.getKey(),
                                                       "\uF201",
                                                       messages.getString(Destination.STATS.getKey())));

        navigationRailItems.put(Destination.SETTINGS.getKey(),
                                new NavigationRailItem(Destination.SETTINGS.getKey(),
                                                       "\uF013",
                                                       messages.getString(Destination.SETTINGS.getKey())));

        navigationRailItems.put(Destination.HELP.getKey(),
                                new NavigationRailItem(Destination.HELP.getKey(),
                                                       "\uF059",
                                                       messages.getString(Destination.HELP.getKey())));

        navigationRailItems.put(Destination.ABOUT.getKey(),
                                new NavigationRailItem(Destination.ABOUT.getKey(),
                                                       "\uF05A",
                                                       messages.getString(Destination.ABOUT.getKey())));
    }

    private void addItems() {
        final ObservableList<Node> CHILDREN = this.getChildren();

        // TOP ITEMS
        CHILDREN.add(navigationRailItems.get(Destination.DASHBOARD.getKey()));
        CHILDREN.add(navigationRailItems.get(Destination.DECKS.getKey()));
        CHILDREN.add(navigationRailItems.get(Destination.EDIT.getKey()));
        CHILDREN.add(navigationRailItems.get(Destination.BROWSE.getKey()));
        CHILDREN.add(navigationRailItems.get(Destination.STATS.getKey()));

        // Repositioning divider.
        CHILDREN.remove(dividerPane);
        CHILDREN.add(dividerPane);

        // BOTTOM ITEMS
        CHILDREN.add(navigationRailItems.get(Destination.SETTINGS.getKey()));
        CHILDREN.add(navigationRailItems.get(Destination.HELP.getKey()));
        CHILDREN.add(navigationRailItems.get(Destination.ABOUT.getKey()));
    }

    private void initializeItemWidthProperties() {
        for (NavigationRailItem item : navigationRailItems.values()) {
            item.prefWidthProperty().bind(this.widthProperty());
        }
    }

    private void initializeActiveItemListener() {
        activeItem.addListener((obs, oldValue, newValue) -> {
            if (oldValue != null)
                navigationRailItems.get(oldValue).setActivated(false);
            navigationRailItems.get(newValue).setActivated(true);
        });
    }

    private void initializeItemListener() {
        for (NavigationRailItem item : navigationRailItems.values()) {
            item.setOnAction(e -> setActiveItem(item.getKey()));
        }
    }


    // JAVAFX ACCESSORS

    public String getActiveItem() {
        return activeItem.get();
    }

    public SimpleStringProperty activeItemProperty() {
        return activeItem;
    }


    // JAVAFX MUTATORS

    public void setActiveItem(String activeItem) {
        if (activeItem == null)
            throw new IllegalArgumentException("Illegal activeItem (cannot be null)");

        if (!navigationRailItems.containsKey(activeItem))
            throw new IllegalArgumentException(String.format(
                    "Illegal activeItem (not found): %s",
                    activeItem
            ));

        this.activeItem.set(activeItem);
    }
}
