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
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.java.presentation.controller.utils.FxUtils;
import main.java.presentation.controller.utils.color.ColorFxUtils;
import main.java.presentation.controller.utils.color.provider.ColorProvider;

// TODO: 20/09/2020 Currently, the button and icon label does not explicitly support changing text.
public class ContainedIconButton extends Button {

    // INSTANCE VARIABLES

    private final String text;

    private final char icon;

    private final IconType iconType;


    // CONSTANTS (Style)

    // JAVAFX PROPERTIES (Style (Attributive))

    private final StringProperty backgroundColorStyle = new SimpleStringProperty();

    private final StringProperty textFillStyle = new SimpleStringProperty();


    // JAVAFX PROPERTIES (Style (Nodal))

    private final StringProperty thisStyle = new SimpleStringProperty();


    // ENUM

    public enum IconType { SOLID, BRAND }


    // FXML NODES

    @FXML
    private Label iconLabel;


    // CONSTRUCTOR

    public ContainedIconButton(String text, char icon, IconType iconType) {
        this.text = text;
        this.icon = icon;
        this.iconType = iconType;

        initializeFxml();

        initializeTexts();

        initializeIconType();

        initializeAttributiveStyleBindings();
        initializeNodalStyleBindings();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/shared_components"
                                     + "/contained_icon_button.fxml";

        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // INITIALIZERS (Text)

    private void initializeTexts() {
        initializeThisText();
        initializeIconLabelText();
    }

    private void initializeThisText() {
        setText(text);
    }

    private void initializeIconLabelText() {
        iconLabel.setText(String.valueOf(icon));
    }


    // INITIALIZERS (Style)

    private void initializeIconType() {
        switch (iconType) {
            case SOLID:
                iconLabel.getStyleClass().add("button-solid-icon-3");
                break;
            case BRAND:
                iconLabel.getStyleClass().add("button-brand-icon-3");
                break;
        }
    }

    // INITIALIZERS (Style Bindings (Called in constructor))

    private void initializeAttributiveStyleBindings() {
        initializeBackgroundColorStyleBindings();
        initializeTextFillStyleBindings();
    }

    private void initializeNodalStyleBindings() {
        initializeThisStyleBinding();
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive backgroundColorStyle))

    private void initializeBackgroundColorStyleBindings() {
        final Duration TRANSITION_DURATION = Duration.millis(100);

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
                ColorFxUtils.createStaticColorProperty(ColorProvider.enabledContainedButtonFillColorProperty());

        final ObjectProperty<Color> HOVER_BACKGROUND_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.hoverContainedButtonFillColorProperty());

        final ObjectProperty<Color> PRESSED_BACKGROUND_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.pressedContainedButtonFillColorProperty());

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


    // INITIALIZERS (Style Bindings (Internal - Attributive textFillStyle))

    private void initializeTextFillStyleBindings() {
        final StringProperty TEXT_FILL_STRING =
                ColorFxUtils.createStaticStringProperty(ColorProvider.onContainedButtonTextColorProperty());

        textFillStyle.bind(Bindings.concat("-fx-text-fill: ", TEXT_FILL_STRING, ";"));
    }


    // INITIALIZERS (Style Bindings (Internal - Nodal))

    private void initializeThisStyleBinding() {
        thisStyle.bind(Bindings.concat(backgroundColorStyle, textFillStyle));
        styleProperty().bind(thisStyle);
    }
}
