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

package main.java.presentation.controller.custom_nodes.main.destinations.decks;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import main.java.presentation.controller.custom_nodes.main.destinations.decks.decks_view_pane.DecksViewPane;
import main.java.presentation.controller.utils.FxUtils;
import main.java.presentation.model.structure.deck.Deck;

import java.util.List;
import java.util.Stack;

public class DecksDestinationController extends AnchorPane implements DestinationController {

    // INSTANCE VARIABLES (Data)

    private final List<Deck> decks;


    // INSTANCE VARIABLES (Navigation)

    private final Stack<AnchorPane> decksDestinationStack = new Stack<>();


    // CONSTRUCTOR

    public DecksDestinationController(List<Deck> decks) {
        this.decks = decks;

        initializeFxml();
        initializeDefaultPane();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/main/destinations/decks"
                                     + "/decks_destination_controller.fxml";

        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // INITIALIZERS (Destination)

    private void initializeDefaultPane() {
        final DecksViewPane DEFAULT_PANE = new DecksViewPane(this, decks);

        requestPush(DEFAULT_PANE);
    }


    // DESTINATION METHODS

    @Override
    public void requestPush(AnchorPane newDestination) {
        decksDestinationStack.push(newDestination);
        showPushed();
    }

    @Override
    public void requestPop() {
        decksDestinationStack.pop();
    }

    private void showPushed() {
        final ObservableList<Node> CHILDREN = getChildren();
        final AnchorPane DESTINATION = decksDestinationStack.peek();

        CHILDREN.clear();
        CHILDREN.add(DESTINATION);

        FxUtils.initializeAnchorPaneAnchors(DESTINATION);
    }

    private void showPopped() {

    }

    @Override
    public Stack<AnchorPane> getStack() {
        return decksDestinationStack; // Not defensively-copied.
    }
}
