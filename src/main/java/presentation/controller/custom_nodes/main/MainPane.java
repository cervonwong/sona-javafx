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

package main.java.presentation.controller.custom_nodes.main;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.*;
import main.java.presentation.controller.custom_nodes.main.destinations.about.AboutPane;
import main.java.presentation.controller.custom_nodes.main.destinations.decks.DecksPane;
import main.java.presentation.controller.custom_nodes.main.navigation_rail.Destination;
import main.java.presentation.controller.custom_nodes.main.navigation_rail.NavigationRail;

import java.io.IOException;

public class MainPane extends HBox {

    // INSTANCE VARIABLES

    private final NavigationRail navigationRail = new NavigationRail();

    private final AnchorPane contentPane = new AnchorPane();

    // CONSTRUCTOR

    public MainPane() {
        initializeFxml();

        initializeNavigationDrawer();
        initializeContentPane();
        initializeNavigationBehavior();
        initializeInitialDestination();
    }


    // INITIALIZERS


    private void initializeFxml() {
        final String RESOURCE_PATH =
                "/view/fxml/custom_nodes/main/main_pane.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(RESOURCE_PATH));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeNavigationDrawer() {
        this.getChildren().add(navigationRail);
        navigationRail.prefHeightProperty().bind(this.heightProperty());
    }

    private void initializeContentPane() {
        this.getChildren().add(contentPane);

        HBox.setHgrow(contentPane, Priority.ALWAYS);
        contentPane.prefHeightProperty().bind(this.heightProperty());
    }

    private void initializeNavigationBehavior() {
        // Uses Android-Like navigation.
        navigationRail.activeDestinationProperty().addListener((obs, oldValue, newValue) -> {
            // Remove the previous destination pane.
            contentPane.getChildren().clear();

            switch (newValue) {
                case ABOUT:
                    final AboutPane ABOUT_PANE = new AboutPane();
                    addDestinationPane(ABOUT_PANE);
                    break;
                case DECKS:
                    final DecksPane DECKS_PANE = new DecksPane();
                    addDestinationPane(DECKS_PANE);
                    break;
                default:
                    System.out.println("Unimplemented");
            }
        });
    }

    private void addDestinationPane(AnchorPane destinationPane) {
        final ObservableList<Node> CONTENT_PANE_CHILDREN = contentPane.getChildren();

        CONTENT_PANE_CHILDREN.clear();

        CONTENT_PANE_CHILDREN.add(destinationPane);

        AnchorPane.setTopAnchor(destinationPane, 0.0);
        AnchorPane.setBottomAnchor(destinationPane, 0.0);
        AnchorPane.setRightAnchor(destinationPane, 0.0);
        AnchorPane.setLeftAnchor(destinationPane, 0.0);
    }

    private void initializeInitialDestination() {
        navigationRail.setActiveDestination(Destination.DECKS);
    }
}
