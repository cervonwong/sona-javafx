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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.java.presentation.controller.utils.FxUtils;
import main.java.presentation.controller.utils.color.ColorFxUtils;
import main.java.presentation.controller.utils.color.provider.ColorProvider;

// TODO: 22/09/2020 Make sure that the button is square? Currently this is done outside the class.
public class IconButton extends Button {

    // INSTANCE VARIABLES

    private final char icon;

    private final IconSize iconSize;

    private final IconType iconType;

    private final IconEmphasis iconEmphasis;


    // ENUMS

    public enum IconSize { HEADLINE_4, HEADLINE_5 }

    public enum IconType { SOLID, BRAND }

    public enum IconEmphasis { HIGHEST, HIGH, MEDIUM, LOW }


    // JAVAFX PROPERTIES (Style (Attributive))

    private final StringProperty textFillStyle = new SimpleStringProperty();


    // JAVAFX PROPERTIES (Style (Nodal))

    private final StringProperty thisStyle = new SimpleStringProperty();


    // CONSTRUCTOR

    public IconButton(char icon, IconSize iconSize, IconType iconType, IconEmphasis iconEmphasis) {
        this.icon = icon;
        this.iconSize = iconSize;
        this.iconType = iconType;
        this.iconEmphasis = iconEmphasis;

        initializeFxml();

        initializeIcon();

        removePadding();
        initializeIconTypeAndSize();

        initializeAttributiveStyleBindings();
        initializeNodalStyleBindings();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/shared_components/icon_button.fxml";

        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // INITIALIZERS (Text)

    private void initializeIcon() {
        setText(String.valueOf(icon));
    }


    // INITIALIZERS (Style)

    private void initializeIconTypeAndSize() {
        switch (iconSize) {
            case HEADLINE_4:
                getStyleClass().add(iconType == IconType.SOLID
                                    ? "headline-icon-4"
                                    : "headline-brand-icon-4");
                break;
            case HEADLINE_5:
                getStyleClass().add(iconType == IconType.SOLID
                                    ? "headline-icon-5"
                                    : "headline-brand-icon-5");
                break;
        }
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
        final Duration TRANSITION_DURATION = Duration.millis(75);

        final ObjectProperty<Color> DESIRED_TEXT_COLOR = createDesiredTextColor();

        final StringProperty TEXT_COLOR_STRING =
                ColorFxUtils.createDynamicStringProperty(DESIRED_TEXT_COLOR,
                                                         TRANSITION_DURATION);

        textFillStyle.bind(Bindings.concat("-fx-text-fill: ", TEXT_COLOR_STRING, ";"));
    }

    private ObjectProperty<Color> createDesiredTextColor() {
        final ObjectProperty<Color> DESIRED_TEXT_COLOR = new SimpleObjectProperty<>();

        ObjectProperty<Color> TEMP_ENABLED_TEXT_COLOR = new SimpleObjectProperty<>();

        switch (iconEmphasis) {
            case HIGHEST:
                TEMP_ENABLED_TEXT_COLOR =
                        ColorFxUtils.createStaticColorProperty(ColorProvider.highestEmphasisTextColorProperty());
                break;
            case HIGH:
                TEMP_ENABLED_TEXT_COLOR =
                        ColorFxUtils.createStaticColorProperty(ColorProvider.highEmphasisTextColorProperty());
                break;
            case MEDIUM:
                TEMP_ENABLED_TEXT_COLOR =
                        ColorFxUtils.createStaticColorProperty(ColorProvider.mediumEmphasisTextColorProperty());
                break;
            case LOW:
                TEMP_ENABLED_TEXT_COLOR =
                        ColorFxUtils.createStaticColorProperty(ColorProvider.lowEmphasisTextColorProperty());
                break;
        }

        final ObjectProperty<Color> ENABLED_TEXT_COLOR = new SimpleObjectProperty<>();
        ENABLED_TEXT_COLOR.bind(TEMP_ENABLED_TEXT_COLOR);

        final ObjectProperty<Color> HOVER_TEXT_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.hoverIconButtonTextColorProperty());


        hoverProperty()
                .addListener((obs, oldValue, newValue)
                                     -> DESIRED_TEXT_COLOR.bind(newValue
                                                                     ? HOVER_TEXT_COLOR
                                                                     : ENABLED_TEXT_COLOR));

        DESIRED_TEXT_COLOR.bind(TEMP_ENABLED_TEXT_COLOR);

        return DESIRED_TEXT_COLOR;
    }


    // INITIALIZERS (Style Bindings (Internal - Nodal))

    private void initializeThisStyleBinding() {
        thisStyle.bind(textFillStyle);
        styleProperty().bind(thisStyle);
    }
}
