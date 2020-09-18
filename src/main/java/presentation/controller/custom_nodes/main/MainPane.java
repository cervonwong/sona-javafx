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
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.*;
import main.java.presentation.controller.custom_nodes.main.destinations.about.AboutPane;
import main.java.presentation.controller.custom_nodes.main.destinations.decks.DecksDestinationController;
import main.java.presentation.controller.custom_nodes.main.navigation_rail.Destination;
import main.java.presentation.controller.custom_nodes.main.navigation_rail.NavigationRail;
import main.java.presentation.controller.utils.FxUtils;
import main.java.presentation.model.structure.deck.Deck;
import main.java.service.structure.deck.DeckService;
import main.java.service.structure.deck.DeckServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MainPane extends AnchorPane {

    // INSTANCE VARIABLES (Nodes)

    private final NavigationRail navigationRail = new NavigationRail();

    private final AnchorPane contentPane = new AnchorPane();


    // INSTANCE VARIABLES (Non-Nodes)

    private final List<Deck> decks;


    // FXML

    @FXML
    private HBox containerBox;


    // CONSTRUCTOR

    public MainPane() {
        initializeFxml();

        final List<Deck> TEMP_DECKS = new ArrayList<>();
        final DeckService SERVICE = new DeckServiceImpl();
        TEMP_DECKS.add(SERVICE.get("Japanese Deck"));
        decks = TEMP_DECKS; // TODO: 15/09/2020 This is temporary.

        addNavigationRail();
        addContentPane();
        initializeNavigationBehavior();
        initializeInitialDestination();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/main/main_pane.fxml";

        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // INITIALIZERS (containerBox)

    private void addNavigationRail() {
        containerBox.getChildren().add(navigationRail);
        navigationRail.prefHeightProperty().bind(this.heightProperty());
    }

    private void addContentPane() {
        containerBox.getChildren().add(contentPane);

        HBox.setHgrow(contentPane, Priority.ALWAYS);
        contentPane.prefHeightProperty().bind(this.heightProperty());
    }


    // INITIALIZERS (Navigation)

    private void initializeNavigationBehavior() {
        // Uses Android-Like navigation.
        navigationRail.activeDestinationProperty().addListener((obs, oldValue, newValue) -> {
            // Remove the previous destination pane.
            contentPane.getChildren().clear();

            switch (newValue) {
                case ABOUT:
                    final AboutPane ABOUT_PANE = new AboutPane();
                    updateDestinationController(ABOUT_PANE);
                    break;
                case DECKS:
                    updateDestinationController(new DecksDestinationController(decks));
                default:
                    System.out.println("Unimplemented");
            }
        });
    }

    private void updateDestinationController(AnchorPane destinationPane) {
        final ObservableList<Node> CHILDREN = contentPane.getChildren();

        CHILDREN.clear();
        CHILDREN.add(destinationPane);

        FxUtils.initializeAnchorPaneAnchors(destinationPane);
    }

    private void initializeInitialDestination() {
        navigationRail.setActiveDestination(Destination.DECKS);
    }
}
