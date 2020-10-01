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

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.text.Text;
import main.java.presentation.controller.utils.FxUtils;

public class TextHolder extends Group {

    // FXML NODES

    @FXML
    private Text text;


    // CONSTRUCTOR

    public TextHolder() {
        initializeFxml();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/shared_components/text_holder.fxml";

        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // ACCESSORS

    public Text getText() {
        return text;
    }


}
