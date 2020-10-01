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

package main.java.presentation.controller.custom_nodes.shared_components.tab_bar;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.java.presentation.controller.custom_nodes.main.rail_destinations.decks.deck_info_pane.tabs.DeckInfoTab;
import main.java.presentation.controller.utils.FxUtils;
import main.java.presentation.controller.utils.color.ColorFxUtils;
import main.java.presentation.controller.utils.color.provider.ColorProvider;

public class TabItem extends Button {

    // INSTANCE VARIABLES

    private final DeckInfoTab deckInfoTab;

    private final String text;

    private final char icon;


    // CONSTANTS (Style)


    // JAVAFX PROPERTIES (Public Accessors / Mutators)

    private final BooleanProperty isActivated = new SimpleBooleanProperty();


    // JAVAFX PROPERTIES (Style (Attributive))

    private final StringProperty backgroundColorStyle = new SimpleStringProperty();

    private final StringProperty textFillStyle = new SimpleStringProperty();


    // JAVAFX PROPERTIES (Style (Nodal))

    private final StringProperty thisStyle = new SimpleStringProperty();

    private final StringProperty iconLabelStyle = new SimpleStringProperty();


    // FXML NODES

    @FXML
    private Label iconLabel;


    // CONSTRUCTOR

    public TabItem(DeckInfoTab deckInfoTab, String text, char icon) {
        this.deckInfoTab = deckInfoTab;
        this.text = text;
        this.icon = icon;

        initializeFxml();

        initializeTexts();

        initializeAttributiveStyleBindings();
        initializeNodalStyleBindings();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/shared_components/tab_bar/tab_item"
                                     + ".fxml";

        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // INITIALIZERS (Text)

    private void initializeTexts() {
        initializeThisText();
        initializeIconLabelText();
    }

    private void initializeThisText() {
        this.setText(text);
    }

    private void initializeIconLabelText() {
        iconLabel.setText(String.valueOf(icon));
    }


    // INITIALIZERS (Style Bindings (Called in constructor))

    private void initializeAttributiveStyleBindings() {
        initializeBackgroundColorStyleBindings();
        initializeTextFillStyleBindings();
    }

    private void initializeNodalStyleBindings() {
        initializeThisStyleBinding();
        initializeIconLabelStyleBinding();
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive backgroundColorStyle))

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


    // INITIALIZERS (Style Bindings (Internal - Attributive textFillStyle))

    private void initializeTextFillStyleBindings() {
        final Duration TRANSITION_DURATION = Duration.millis(150);

        final ObjectProperty<Color> DESIRED_TEXT_COLOR = createDesiredTextColor();

        final StringProperty TEXT_FILL_STRING =
                ColorFxUtils.createDynamicStringProperty(DESIRED_TEXT_COLOR, TRANSITION_DURATION);

        textFillStyle.bind(Bindings.concat("-fx-text-fill: ", TEXT_FILL_STRING, ";"));
    }

    private ObjectProperty<Color> createDesiredTextColor() {
        final ObjectProperty<Color> DESIRED_TEXT_COLOR = new SimpleObjectProperty<>();

        final ObjectProperty<Color> ENABLED_TEXT_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.mediumEmphasisTextColorProperty());

        final ObjectProperty<Color> ACTIVATED_TEXT_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.activatedTabItemTextColorProperty());

        isActivated.addListener((obs, oldValue, newValue)
                                        -> DESIRED_TEXT_COLOR.bind(newValue
                                                                   ? ACTIVATED_TEXT_COLOR
                                                                   : ENABLED_TEXT_COLOR));
        DESIRED_TEXT_COLOR.bind(ENABLED_TEXT_COLOR);

        return DESIRED_TEXT_COLOR;
    }


    // INITIALIZERS (Style Bindings (Internal - Nodal))

    private void initializeThisStyleBinding() {
        thisStyle.bind(Bindings.concat(backgroundColorStyle, textFillStyle));
        styleProperty().bind(thisStyle);
    }

    private void initializeIconLabelStyleBinding() {
        iconLabelStyle.bind(textFillStyle);
        iconLabel.styleProperty().bind(iconLabelStyle);
    }


    // ACCESSORS

    public DeckInfoTab getDeckInfoTab() {
        return deckInfoTab;
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
