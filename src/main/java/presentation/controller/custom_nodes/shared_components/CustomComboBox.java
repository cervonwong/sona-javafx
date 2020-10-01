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

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.java.presentation.controller.utils.FxUtils;
import main.java.presentation.controller.utils.color.ColorFxUtils;
import main.java.presentation.controller.utils.color.provider.ColorProvider;

// TODO: 01/10/2020 Style the list view sheet.
public class CustomComboBox<T> extends ComboBox<T> {


    // JAVAFX PROPERTIES (Style (Attributive))

    private final StringProperty backgroundColorStyle = new SimpleStringProperty();

    private final StringProperty arrowButtonBackgroundColorStyle = new SimpleStringProperty();

    private final StringProperty textFillStyle = new SimpleStringProperty();


    // JAVAFX PROPERTIES (Style (Nodal))

    private final StringProperty thisStyle = new SimpleStringProperty();

    private final StringProperty arrowButtonStyle = new SimpleStringProperty();

    private final StringProperty listCellStyle = new SimpleStringProperty();


    // CONSTRUCTOR

    public CustomComboBox() {
        initializeFxml();

        initializeAttributiveStyleBindings();
        initializeNodalStyleBindings();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/shared_components/custom_combo_box"
                                     + ".fxml";

        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // initializers (Style Binding (Called in constructor))

    private void initializeAttributiveStyleBindings() {
        initializeBackgroundColorStyleBindings();
        initializeArrowButtonBackgroundColorStyleBindings();
        initializeTextFillStyleBindings();
    }

    private void initializeNodalStyleBindings() {
        initializeThisStyleBinding();
        initializeArrowButtonStyleBinding();
        initializeListCellStyleBinding();
    }


    // INITIALIZERS (Style Binding (Internal - Attributive backgroundColorStyle))

    private void initializeBackgroundColorStyleBindings() {
        final Duration TRANSITION_DURATION = Duration.millis(150);

        final ObjectProperty<Color> DESIRED_BACKGROUND_COLOR = createDesiredBackgroundColor();

        final StringProperty BACKGROUND_COLOR_STRING =
                ColorFxUtils.createDynamicStringProperty(DESIRED_BACKGROUND_COLOR,
                                                         TRANSITION_DURATION);

        backgroundColorStyle.bind(Bindings.concat("-fx-background-color: ",
                                                  BACKGROUND_COLOR_STRING,
                                                  ";"));
    }

    private ObjectProperty<Color> createDesiredBackgroundColor() {
        final ObjectProperty<Color> DESIRED_BACKGROUND_COLOR = new SimpleObjectProperty<>();

        final ObjectProperty<Color> ENABLED_BACKGROUND_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.enabledControlBackgroundColorProperty());

        final ObjectProperty<Color> HOVER_BACKGROUND_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.hoverControlBackgroundColorProperty());

        final ObjectProperty<Color> PRESSED_BACKGROUND_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.pressedControlBackgroundColorProperty());

        hoverProperty()
                .addListener((obs, oldValue, newValue)
                                     -> DESIRED_BACKGROUND_COLOR.bind(newValue
                                                                      ? HOVER_BACKGROUND_COLOR
                                                                      : ENABLED_BACKGROUND_COLOR));

        pressedProperty()
                .addListener((obs, oldValue, newValue)
                                     -> DESIRED_BACKGROUND_COLOR.bind(newValue
                                                                      ? PRESSED_BACKGROUND_COLOR
                                                                      : isHover()
                                                                        ? HOVER_BACKGROUND_COLOR
                                                                        :
                                                                        ENABLED_BACKGROUND_COLOR));

        DESIRED_BACKGROUND_COLOR.bind(ENABLED_BACKGROUND_COLOR);

        return DESIRED_BACKGROUND_COLOR;
    }


    // INITIALIZERS (Style Binding (Internal - Attributive arrowButtonBackgroundColorStyle))

    private void initializeArrowButtonBackgroundColorStyleBindings() {
        final StringProperty BACKGROUND_COLOR_STRING =
                ColorFxUtils.createStaticStringProperty(ColorProvider.highEmphasisTextColorProperty());

        arrowButtonBackgroundColorStyle.bind(Bindings.concat("-fx-background-color: ",
                                                             BACKGROUND_COLOR_STRING,
                                                             ";"));
    }


    // INITIALIZERS (Style Binding (Internal - Attributive textFillStyle))

    private void initializeTextFillStyleBindings() {
        final StringProperty TEXT_FILL_STRING =
                ColorFxUtils.createStaticStringProperty(ColorProvider.lowEmphasisTextColorProperty());

        textFillStyle.bind(Bindings.concat("-text-fill: ", TEXT_FILL_STRING, ";"));
    }


    // INITIALIZERS (Style Bindings (Nodal))

    private void initializeThisStyleBinding() {
        thisStyle.bind(Bindings.concat(backgroundColorStyle));
        styleProperty().bind(thisStyle);
    }

    private void initializeArrowButtonStyleBinding() {
        arrowButtonStyle.bind(arrowButtonBackgroundColorStyle);
        Platform.runLater(() -> {
            this.lookup(".arrow").styleProperty().bind(arrowButtonStyle);
        });
    }

    private void initializeListCellStyleBinding() {
//        listCellStyle.bind(textFillStyle);

        // FIXME: 01/10/2020 CANNOT FIND OUT HOW TO SET THE TEXT FILL COLOR FOR COMBO BOX!
    }

}
