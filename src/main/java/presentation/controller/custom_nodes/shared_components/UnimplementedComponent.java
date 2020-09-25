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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.i18n.ResourceBundleName;
import main.java.presentation.controller.utils.ControllerUtils;
import main.java.presentation.controller.utils.FxUtils;
import main.java.presentation.controller.utils.color.CommonColorBindings;

import java.util.ResourceBundle;

public class UnimplementedComponent extends AnchorPane {

    // INSTANCE VARIABLES

    private final ResourceBundle messages;

    private final String reason;


    // JAVAFX PROPERTIES (Style (Attributive))

    private final StringProperty backgroundColorStyle =
            CommonColorBindings.backgroundColorStyleProperty();

    private final StringProperty iconLabelTextFillStyle =
            CommonColorBindings.highestEmphasisTextFillStyleProperty();

    private final StringProperty titleAndReasonLabelTextFillStyle =
            CommonColorBindings.mediumEmphasisTextFillStyleProperty();


    // JAVAFX PROPERTIES (Style (Nodal))

    private final StringProperty thisStyle = new SimpleStringProperty();

    private final StringProperty iconLabelStyle = new SimpleStringProperty();

    private final StringProperty titleLabelStyle = new SimpleStringProperty();

    private final StringProperty reasonLabelStyle = new SimpleStringProperty();


    // FXML NODES

    @FXML
    private Label iconLabel, titleLabel, reasonLabel;


    // CONSTRUCTOR

    public UnimplementedComponent(String reason) {
        messages = ControllerUtils.getMessages(ResourceBundleName.UNIMPLEMENTED_COMPONENT);
        this.reason = reason;

        initializeFxml();
        initializeReasonLabelText();
        initializeI18nText();

        initializeAttributiveStyleBindings();
        initializeNodalStyleBindings();

    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/shared_components"
                                     + "/unimplemented_component.fxml";

        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }

    // INITIALIZERS (Text)

    private void initializeReasonLabelText() {
        reasonLabel.setText(reason);
    }


    // INITIALIZERS (Style Bindings (Internal - (Called in constructor))

    private void initializeAttributiveStyleBindings() {

    }

    private void initializeNodalStyleBindings() {
        initializeThisStyleBinding();
        initializeIconLabelStyleBinding();
        initializeTitleLabelStyleBinding();
        initializeReasonLabelStyleBinding();
    }

    // INITIALIZERS (Style Bindings (Internal - Nodal))

    private void initializeThisStyleBinding() {
        thisStyle.bind(backgroundColorStyle);
        styleProperty().bind(thisStyle);
    }

    private void initializeIconLabelStyleBinding() {
        iconLabelStyle.bind(iconLabelTextFillStyle);
        iconLabel.styleProperty().bind(iconLabelStyle);
    }

    private void initializeTitleLabelStyleBinding() {
        titleLabelStyle.bind(titleAndReasonLabelTextFillStyle);
        titleLabel.styleProperty().bind(titleLabelStyle);
    }

    private void initializeReasonLabelStyleBinding() {
        reasonLabelStyle.bind(titleAndReasonLabelTextFillStyle);
        reasonLabel.styleProperty().bind(reasonLabelStyle);
    }


    // INITIALIZERS (i18n)

    private void initializeI18nText() {
        titleLabel.setText(messages.getString("title"));
    }
}
