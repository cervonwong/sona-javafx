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

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;
import main.java.presentation.controller.utils.FxUtils;
import main.java.presentation.controller.utils.color.ColorFxUtils;
import main.java.presentation.controller.utils.color.provider.ColorProvider;

public class HorizontalDivider extends Pane {

    // INSTANCE VARIABLES

    private final double thickness;


    // JAVAFX PROPERTIES (Style (Attributive))

    private final StringProperty backgroundColorStyle = new SimpleStringProperty();


    // JAVAFX PROPERTIES (Style (Nodal))

    private final StringProperty thisStyle = new SimpleStringProperty();


    // CONSTRUCTOR

    public HorizontalDivider(double thickness) {
        this.thickness = thickness;

        initializeFxml();

        initializeThickness();

        initializeAttributiveStyleBindings();
        initializeNodalStyleBindings();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/shared_components"
                                     + "/horizontal_divider.fxml";
        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // INITIALIZERS (Style)

    private void initializeThickness() {
        setMinHeight(thickness);
        setMaxHeight(thickness);
        setPrefHeight(thickness);
    }


    // INITIALIZERS (Style Bindings (Internal - Called in constructor))

    private void initializeAttributiveStyleBindings() {
        initializeBackgroundColorStyleBindings();
    }


    private void initializeNodalStyleBindings() {
        initializeThisStyleBinding();
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive backgroundColorStyle))

    private void initializeBackgroundColorStyleBindings() {
        final StringProperty BACKGROUND_COLOR_STRING =
                ColorFxUtils.createStaticStringProperty(ColorProvider.dividerColorProperty());

        backgroundColorStyle.bind(Bindings.concat("-fx-background-color: ",
                                                  BACKGROUND_COLOR_STRING,
                                                  ";"));
    }


    // INITIALIZERS (Style Bindings (Internal - Nodal))

    private void initializeThisStyleBinding() {
        thisStyle.bind(backgroundColorStyle);
        styleProperty().bind(thisStyle);
    }
}
