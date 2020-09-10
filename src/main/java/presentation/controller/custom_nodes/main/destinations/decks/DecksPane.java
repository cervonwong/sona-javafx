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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import main.java.i18n.ResourceBundles;

import java.io.IOException;
import java.util.ResourceBundle;

public class DecksPane extends AnchorPane {

    // INSTANCE VARIABLES

    private final ResourceBundle messages;


    // FXML

    @FXML
    private Label titleLabel;

    @FXML
    private ScrollPane decksViewScrollPane;


    // CONSTRUCTOR

    public DecksPane() {
        messages = initializeMessages();

        initializeFxml();
        internationalizeText();
    }


    // INITIALIZERS


    private void initializeFxml() {
        final String RESOURCE_PATH =
                "/view/fxml/custom_nodes/main/destinations/decks/decks_pane.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(RESOURCE_PATH));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // i18n

    private ResourceBundle initializeMessages() {
        return ResourceBundle.getBundle(ResourceBundles.DECKS_PANE.getBundleName());
    }

    private void internationalizeText() {
        titleLabel.setText(messages.getString("title"));
    }
}
