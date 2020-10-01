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

package main.java.presentation.controller.custom_nodes.shared_components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import main.java.presentation.controller.utils.FxUtils;

public class TitleBar extends AnchorPane {

    // INSTANCE VARIABLES

    private String text;

    private final boolean showDivider;

    private final boolean showBackButton;


    // FXML NODES

    @FXML
    private AnchorPane backButtonPane;

    @FXML
    private Label titleLabel;

    @FXML
    private AnchorPane dividerPane;

    @FXML
    private HBox mainBox;

    private BackButton backButton = null;


    // CONSTRUCTOR

    public TitleBar(String text, boolean showDivider, boolean showBackButton) {
        this.text = text;
        this.showDivider = showDivider;
        this.showBackButton = showBackButton;

        initializeFxml();

        initializeText();
        addDividerIfNecessary();
        addBackButtonIfNecessary();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/shared_components/title_bar.fxml";

        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // INITIALIZERS (Text)

    private void initializeText() {
        titleLabel.setText(text);
    }

    // INITIALIZERS (Adding Methods)

    private void addDividerIfNecessary() {
        if (!showDivider) return;

        final HorizontalDivider HORIZONTAL_DIVIDER = new HorizontalDivider(2.0);

        dividerPane.getChildren().add(HORIZONTAL_DIVIDER);
        FxUtils.initializeAnchorPaneAnchors(HORIZONTAL_DIVIDER);
    }

    private void addBackButtonIfNecessary() {
        if (!showBackButton) {
            // TODO: 01/10/2020 Remove this ugly statement.
            mainBox.setSpacing(0.0);
            return;
        }

        backButton = new BackButton();

        backButtonPane.getChildren().add(backButton);
        FxUtils.initializeAnchorPaneAnchors(backButton);
    }


    // PUBLIC METHODS

    private void setBackButtonOnAction(EventHandler<ActionEvent> handler) {
        if (backButton == null) return;

        backButton.setOnAction(handler);
    }
}
