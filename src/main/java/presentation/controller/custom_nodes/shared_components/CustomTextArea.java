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
import javafx.scene.control.TextArea;
import main.java.presentation.controller.utils.FxUtils;

// TODO: 28/09/2020 INCOMPLETE STYLING!!!!!!!
// TODO: 28/09/2020 In the future, use GenericStyledArea.
public class CustomTextArea extends TextArea {

    // JAVAFX PROPERTIES (Style (Attributive))

    private final StringProperty backgroundColorStyle = new SimpleStringProperty();

    private final StringProperty backgroundInsetsStyle = new SimpleStringProperty();


    // JAVAFX PROPERTIES (Style (Nodal))

    private final StringProperty thisStyle = new SimpleStringProperty();


    // CONTROLLER

    public CustomTextArea() {
        initializeFxml();

        initializeAttributiveStyleBindings();
        initializeNodalStyleBindings();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/shared_components/custom_text_area"
                                     + ".fxml";
        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // INITIALIZERS (Style Bindings (Called in constructor))

    private void initializeAttributiveStyleBindings() {}

    private void initializeNodalStyleBindings() {}


    // INITIALIZERS (Style Bindings (Internal - Attributive backgroundColorStyle)


    // INITIALIZERS (Style Bindings (Internal - Nodal))


}
