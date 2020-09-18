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

package main.java.presentation.controller.custom_nodes.main.rail_destinations.decks.decks_view_pane;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.java.i18n.ResourceBundleName;
import main.java.presentation.controller.custom_nodes.main.rail_destinations.decks.destination_controller.DestinationController;
import main.java.presentation.controller.utils.ControllerUtils;
import main.java.presentation.controller.utils.FxUtils;
import main.java.presentation.model.structure.deck.Deck;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class DecksViewPane extends AnchorPane {

    // INSTANCE VARIABLES

    private final ResourceBundle messages;

    private final DestinationController destinationController;

    private final List<Deck> decks;


    // FXML

    @FXML
    private Label titleLabel;

    @FXML
    private ScrollPane decksViewScrollPane;

    @FXML
    private VBox decksViewBox;


    // CONSTRUCTOR

    public DecksViewPane(DestinationController destinationController, List<Deck> decks) {
        messages = ControllerUtils.getMessages(ResourceBundleName.DECKS_VIEW_PANE);
        this.destinationController = destinationController;
        this.decks = decks; // Not defensive copied.

        initializeFxml();
        internationalizeText();
        FxUtils.sharpenScrollPane(decksViewScrollPane);

        instantiateDecksViewCards();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH =
                "/view/fxml/custom_nodes/main/rail_destinations/decks/decks_view_pane"
                + "/decks_view_pane"
                + ".fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(RESOURCE_PATH));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // INSTANTIATERS

    private void instantiateDecksViewCards() {
        final ObservableList<Node> CHILDREN = decksViewBox.getChildren();

        for (int i = 0; i < 3; i++) {
            for (Deck deck : decks) {
                final DecksViewCard CARD = new DecksViewCard(destinationController, deck);
                CHILDREN.add(CARD);
            }
        }
    }

    // i18n

    private void internationalizeText() {
        titleLabel.setText(messages.getString("title"));
    }
}
