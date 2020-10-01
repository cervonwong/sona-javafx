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

package main.java.presentation.controller.custom_nodes.main.rail_destinations.edit.editing_pane;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.presentation.controller.custom_nodes.shared_components.CustomTextArea;
import main.java.presentation.controller.utils.FxUtils;
import main.java.presentation.controller.utils.color.CommonColorBindings;

public class EditingField extends AnchorPane {

    // INSTANCE VARIABLES

    private final String field;

    private final String initialText;


    // JAVAFX PROPERTIES (Style (Attributive))

    private final StringProperty textFillStyle =
            CommonColorBindings.mediumEmphasisTextFillStyleProperty();


    // JAVAFX PROPERTIES (Style (Nodal))

    private final StringProperty fieldLabelStyle = new SimpleStringProperty();


    // FXML NODES

    @FXML
    private Label fieldLabel;

    @FXML
    private AnchorPane textAreaPane;

    private final CustomTextArea textArea = new CustomTextArea();


    // CONSTRUCTOR

    public EditingField(String field, String initialText) {
        this.field = field;
        this.initialText = initialText;

        initializeFxml();

        initializeFieldLabelText();

        addTextArea();
        initializeInitialText();

        initializeAttributiveStyleBindings();
        initializeNodalStyleBindings();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/main/rail_destinations/edit"
                                     + "/editing_pane/editing_field.fxml";

        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // INITIALIZERS (Text)

    private void initializeFieldLabelText() {
        fieldLabel.setText(field);
    }

    private void initializeInitialText() {
        textArea.setText(initialText);
    }


    // INITIALIZERS (Style Bindings (Internal - (Called in constructor))

    private void initializeAttributiveStyleBindings() {

    }

    private void initializeNodalStyleBindings() {
        initializeFieldLabelStyleBinding();
    }


    // INITIALIZERS (Style Bindings (Internal - Nodal))

    private void initializeFieldLabelStyleBinding() {
        fieldLabelStyle.bind(Bindings.concat(textFillStyle));
        fieldLabel.styleProperty().bind(fieldLabelStyle);
    }


    // INITIALIZERS (Adding Methods)

    private void addTextArea() {
        textAreaPane.getChildren().add(textArea);
        FxUtils.initializeAnchorPaneAnchors(textArea);
    }


    // ACCESSORS

    private String getText() {
        return textArea.getText();
    }
}
