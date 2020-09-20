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

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.java.i18n.ResourceBundleName;
import main.java.presentation.controller.utils.ControllerUtils;
import main.java.presentation.controller.utils.FxUtils;
import main.java.presentation.controller.utils.color.ColorFxUtils;
import main.java.presentation.controller.utils.color.provider.ColorProvider;

import java.util.*;

public class NavigationRail extends VBox {

    // INSTANCE VARIABLES (i18n)

    private final ResourceBundle messages;


    // INSTANCE VARIABLES (Nodes)

    private final Map<Destination, NavigationRailItem> navigationRailItems = new HashMap<>();


    // JAVAFX PROPERTIES (Public Accessors / Mutators)

    private final ObjectProperty<Destination> activeDestination = new SimpleObjectProperty<>();


    // JAVAFX PROPERTIES (Style (Attributive))

    private final StringProperty backgroundColorStyle = new SimpleStringProperty();


    // JAVAFX PROPERTIES (Style (Nodal))

    private final StringProperty thisStyle = new SimpleStringProperty();


    // FXML NODES

    @FXML
    private Pane dividerPane;


    // CONSTRUCTOR

    public NavigationRail() {
        messages = ControllerUtils.getMessages(ResourceBundleName.NAVIGATION_RAIL);

        initializeFxml();
        initializeItems();

        initializeActiveDestinationListener();
        initializeItemBehavior();

        initializeAttributiveStyleBindings();
        initializeNodalStyleBindings();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH =
                "/view/fxml/custom_nodes/main/navigation_rail/navigation_rail.fxml";

        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // INITIALIZERS (Style (Called in constructor))

    private void initializeAttributiveStyleBindings() {
        initializeBackgroundColorStyleBindings();
    }

    private void initializeNodalStyleBindings() {
        initializeThisStyleBinding();
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive backgroundColorStyle))

    private void initializeBackgroundColorStyleBindings() {
        final StringProperty BACKGROUND_COLOR_STRING = ColorFxUtils.createStaticStringProperty(
                ColorProvider.navigationRailBaseColorProperty());

        backgroundColorStyle.bind(Bindings.concat("-fx-background-color: ",
                                                  BACKGROUND_COLOR_STRING,
                                                  ";"));
    }


    // INITIALIZERS (Style Bindings (Internal - Nodal))

    private void initializeThisStyleBinding() {
        thisStyle.bind(backgroundColorStyle);
        styleProperty().bind(thisStyle);
    }


    // INITIALIZERS (Adding Methods)

    private void initializeItems() {
        instantiateItems();
        addItems();
        initializeItemWidthBinding();
    }

    private void instantiateItems() {
        instantiateItem(Destination.DASHBOARD, "\uF015");
        instantiateItem(Destination.DECKS, "\uF5DB");
        instantiateItem(Destination.BROWSE, "\uF002");
        instantiateItem(Destination.STATS, "\uF201");
        instantiateItem(Destination.SETTINGS, "\uF013");
        instantiateItem(Destination.HELP, "\uF059");
        instantiateItem(Destination.ABOUT, "\uF05A");
    }

    private void instantiateItem(Destination destination, String icon) {
        navigationRailItems.put(destination,
                                new NavigationRailItem(destination,
                                                       icon,
                                                       messages.getString(destination.getKey())));
    }

    private void addItems() {
        final ObservableList<Node> CHILDREN = this.getChildren();

        // TOP ITEMS
        CHILDREN.add(navigationRailItems.get(Destination.DASHBOARD));
        CHILDREN.add(navigationRailItems.get(Destination.DECKS));
        CHILDREN.add(navigationRailItems.get(Destination.BROWSE));
        CHILDREN.add(navigationRailItems.get(Destination.STATS));

        // Repositioning divider.
        CHILDREN.remove(dividerPane);
        CHILDREN.add(dividerPane);

        // BOTTOM ITEMS
        CHILDREN.add(navigationRailItems.get(Destination.SETTINGS));
        CHILDREN.add(navigationRailItems.get(Destination.HELP));
        CHILDREN.add(navigationRailItems.get(Destination.ABOUT));
    }

    // TODO: 20/09/2020 Use AnchorPanes or something to remove this.
    private void initializeItemWidthBinding() {
        for (NavigationRailItem item : navigationRailItems.values())
            item.prefWidthProperty().bind(this.widthProperty());
    }


    // INITIALIZERS (Behavior)

    private void initializeActiveDestinationListener() {
        activeDestination.addListener((obs, oldValue, newValue) -> {
            if (oldValue != null)
                navigationRailItems.get(oldValue).setActivated(false);
            navigationRailItems.get(newValue).setActivated(true);
        });
    }

    private void initializeItemBehavior() {
        for (NavigationRailItem item : navigationRailItems.values()) {
            item.setOnAction(e -> setActiveDestination(item.getDestination()));
        }
    }


    // JAVAFX ACCESSORS

    public Destination getActiveDestination() {
        return activeDestination.get();
    }

    public ObjectProperty<Destination> activeDestinationProperty() {
        return activeDestination;
    }


    // JAVAFX MUTATORS

    public void setActiveDestination(Destination destination) {
        if (destination == null)
            throw new IllegalArgumentException("Illegal destination (cannot be null)");

        this.activeDestination.set(destination);
    }
}

