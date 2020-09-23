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

package main.java.presentation.controller.custom_nodes.main.navigation_rail;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.java.presentation.controller.utils.color.ColorFxUtils;
import main.java.presentation.controller.utils.color.provider.ColorProvider;

import java.io.IOException;

public class NavigationRailItem extends Button {

    // INSTANCE VARIABLES

    private final Destination destination;


    // JAVAFX PROPERTIES (Public Accessors / Mutators)

    private final BooleanProperty isActivated = new SimpleBooleanProperty();


    // JAVAFX PROPERTIES (Style (Attributive))

    private final StringProperty textFillStyle = new SimpleStringProperty();

    private final StringProperty iconLabelFontFamilyStyle = new SimpleStringProperty();

    private final StringProperty backgroundColorStyle = new SimpleStringProperty();


    // JAVAFX PROPERTIES (Style (Nodal))

    private final StringProperty thisStyle = new SimpleStringProperty();

    private final StringProperty iconLabelStyle = new SimpleStringProperty();


    // FXML NODES

    @FXML
    private Label iconLabel;


    // CONSTRUCTOR

    public NavigationRailItem(Destination destination, String icon, String text) {
        this(destination, icon, text, false);
    }

    public NavigationRailItem(Destination destination,
                              String icon,
                              String text,
                              boolean isActivated) {
        this.destination = destination;
        setActivated(isActivated);

        initializeFxml();

        initializeIconLabelText(icon);
        initializeThisText(text);

        initializeAttributiveStyleBindings();
        initializeNodalStyleBindings();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH =
                "/view/fxml/custom_nodes/main/navigation_rail/navigation_rail_item.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(RESOURCE_PATH));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // INITIALIZERS (Text)

    private void initializeIconLabelText(String icon) {
        iconLabel.setText(icon);
    }

    private void initializeThisText(String text) {
        setText(text);
    }


    // INITIALIZERS (Style Bindings (Called in constructor))

    private void initializeAttributiveStyleBindings() {
        initializeTextFillStyleBinding();
        initializeIconLabelFontFamilyStyleBinding();
        initializeBackgroundColorStyleBindings();
    }

    private void initializeNodalStyleBindings() {
        initializeThisStyleBinding();
        initializeIconLabelStyleBinding();
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive textFillStyle))

    private void initializeTextFillStyleBinding() {
        final Duration TRANSITION_DURATION = Duration.millis(50);

        final ObjectProperty<Color> DESIRED_TEXT_COLOR = createDesiredTextColor();

        final StringProperty TEXT_COLOR_STRING =
                ColorFxUtils.createDynamicStringProperty(DESIRED_TEXT_COLOR, TRANSITION_DURATION);

        textFillStyle.bind(Bindings.concat("-fx-text-fill: ", TEXT_COLOR_STRING, ";"));
    }

    private ObjectProperty<Color> createDesiredTextColor() {
        final ObjectProperty<Color> DESIRED_TEXT_COLOR = new SimpleObjectProperty<>();

        isActivated.addListener((obs, oldValue, newValue)
                                        -> bindDesiredTextColor(DESIRED_TEXT_COLOR, newValue));
        bindDesiredTextColor(DESIRED_TEXT_COLOR, isActivated.get());

        return DESIRED_TEXT_COLOR;
    }

    private void bindDesiredTextColor(ObjectProperty<Color> desiredTextColor, boolean isActivated) {
        final ObjectProperty<Color> INACTIVE_TEXT_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.navigationRailInactiveTextColorProperty());

        final ObjectProperty<Color> ACTIVATED_TEXT_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.navigationRailActivatedTextColorProperty());

        desiredTextColor.bind(isActivated ? ACTIVATED_TEXT_COLOR : INACTIVE_TEXT_COLOR);
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive iconLabelFontFamilyStyle))

    private void initializeIconLabelFontFamilyStyleBinding() {
        final StringProperty ICON_LABEL_FONT_FAMILY = createIconLabelFontFamily();

        iconLabelFontFamilyStyle.bind(Bindings.concat("-fx-font-family: ",
                                                      ICON_LABEL_FONT_FAMILY,
                                                      ";"));
    }

    private StringProperty createIconLabelFontFamily() {
        final StringProperty ICON_LABEL_FONT_FAMILY = new SimpleStringProperty();

        isActivated.addListener((obs, oldValue, newValue)
                                        -> setIconLabelFontFamily(ICON_LABEL_FONT_FAMILY,
                                                                  newValue));
        setIconLabelFontFamily(ICON_LABEL_FONT_FAMILY, isActivated.get());

        return ICON_LABEL_FONT_FAMILY;
    }

    private void setIconLabelFontFamily(StringProperty iconLabelFontFamily, boolean isActivated) {
        final String INACTIVE_FONT_FAMILY = "'Font Awesome 5 Pro Light'";
        final String ACTIVATED_FONT_FAMILY = "'Font Awesome 5 Pro Solid'";

        iconLabelFontFamily.set(isActivated ? ACTIVATED_FONT_FAMILY : INACTIVE_FONT_FAMILY);
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive backgroundColorStyle))

    private void initializeBackgroundColorStyleBindings() {
        final Duration TRANSITION_DURATION = Duration.millis(50);

        final ObjectProperty<Color> DESIRED_BACKGROUND_COLOR = createDesiredBackgroundColor();

        final StringProperty BACKGROUND_COLOR_STRING =
                ColorFxUtils.createDynamicStringProperty(DESIRED_BACKGROUND_COLOR,
                                                         TRANSITION_DURATION);

        backgroundColorStyle.bind(Bindings.concat("-fx-background-color: ", BACKGROUND_COLOR_STRING, ";"));
    }

    private ObjectProperty<Color> createDesiredBackgroundColor() {
        final ObjectProperty<Color> DESIRED_BACKGROUND_COLOR = new SimpleObjectProperty<>();

        final ObjectProperty<Color> ENABLED_BACKGROUND_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.enabledTextButtonBackgroundColorProperty());

        final ObjectProperty<Color> HOVER_BACKGROUND_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.hoverTextButtonBackgroundColorProperty());

        final ObjectProperty<Color> PRESSED_BACKGROUND_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.pressedTextButtonBackgroundColorProperty());

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


    // INITIALIZERS (Style Bindings (Internal - Nodal))

    private void initializeThisStyleBinding() {
        thisStyle.bind(Bindings.concat(textFillStyle, backgroundColorStyle));
        styleProperty().bind(thisStyle);
    }

    private void initializeIconLabelStyleBinding() {
        iconLabelStyle.bind(Bindings.concat(textFillStyle, iconLabelFontFamilyStyle));
        iconLabel.styleProperty().bind(iconLabelStyle);
    }


    // ACCESSORS

    public Destination getDestination() {
        return destination;
    }

    public boolean isActivated() {
        return isActivated.get();
    }


    // PROPERTY ACCESSORS

    public BooleanProperty isActivatedProperty() {
        return isActivated;
    }


    // MUTATORS

    public void setActivated(boolean isActivated) {
        this.isActivated.set(isActivated);
    }
}
