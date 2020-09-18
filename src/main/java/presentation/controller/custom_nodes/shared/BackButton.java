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

package main.java.presentation.controller.custom_nodes.shared;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.java.presentation.controller.utils.FxUtils;
import main.java.presentation.controller.utils.color.ColorFxUtils;
import main.java.presentation.controller.utils.color.ColorProvider;

public class BackButton extends Button {

    // CONSTANTS (Style)

    // JAVAFX PROPERTIES (Style (Attributive))

    private final StringProperty textFillStyle = new SimpleStringProperty();


    // JAVAFX PROPERTIES (Style (Nodal))

    private final StringProperty thisStyle = new SimpleStringProperty();


    // CONTROLLER

    public BackButton() {
        initializeFxml();

        removePadding();

        initializeAttributiveStyleBindings();
        initializeNodalStyleBindings();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/shared/back_button.fxml";

        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // INITIALIZERS (Additional Static Properties)

    private void removePadding() {
        setPadding(Insets.EMPTY);
    }


    // INITIALIZERS (Style Bindings (Called in constructor))

    private void initializeAttributiveStyleBindings() {
        initializeTextFillStyleBindings();
    }

    private void initializeNodalStyleBindings() {
        initializeThisStyleBinding();
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive textFillStyle))

    private void initializeTextFillStyleBindings() {
        final Duration TRANSITION_DURATION = Duration.millis(50);

        final ObjectProperty<Color> DESIRED_TEXT_FILL_COLOR = createDesiredTextFillColor();

        final StringProperty TEXT_FILL_COLOR_STRING =
                ColorFxUtils.createDynamicStringProperty(DESIRED_TEXT_FILL_COLOR,
                                                         TRANSITION_DURATION);

        textFillStyle.bind(Bindings.concat("-fx-text-fill: ", TEXT_FILL_COLOR_STRING, ";"));
    }

    private ObjectProperty<Color> createDesiredTextFillColor() {
        final ObjectProperty<Color> DESIRED_TEXT_FILL_COLOR = new SimpleObjectProperty<>();

        final ObjectProperty<Color> ENABLED_TEXT_FILL_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.highEmphasisHeaderTextColorProperty());

        final ObjectProperty<Color> HOVER_TEXT_FILL_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.hoverBackButtonTextFillColorProperty());


        hoverProperty()
                .addListener((obs, oldValue, newValue)
                                     -> DESIRED_TEXT_FILL_COLOR.bind(newValue
                                                                     ? HOVER_TEXT_FILL_COLOR
                                                                     : ENABLED_TEXT_FILL_COLOR));

        DESIRED_TEXT_FILL_COLOR.bind(ENABLED_TEXT_FILL_COLOR);

        return DESIRED_TEXT_FILL_COLOR;
    }


    // INITIALIZERS (Style Bindings (Internal - Nodal))

    private void initializeThisStyleBinding() {
        thisStyle.bind(textFillStyle);
        styleProperty().bind(thisStyle);
    }
}
