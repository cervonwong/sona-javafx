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
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.java.presentation.controller.utils.DoubleFxUtils;
import main.java.presentation.controller.utils.FxUtils;
import main.java.presentation.controller.utils.color.ColorFxUtils;
import main.java.presentation.controller.utils.color.CommonColorBindings;
import main.java.presentation.controller.utils.color.provider.ColorProvider;

// TODO: 28/09/2020 INCOMPLETE STYLING!!!!!!!
// TODO: 28/09/2020 In the future, use GenericStyledArea.
public class CustomTextArea extends TextArea {

    // CONSTANTS (Style)

    private final static double ENABLED_ACCENT_HEIGHT = 2.0;

    private final static double HOVER_ACCENT_HEIGHT = 4.0;


    // JAVAFX PROPERTIES (Style (Attributive))

    private final StringProperty backgroundColorStyle = new SimpleStringProperty();

    private final StringProperty backgroundInsetsStyle = new SimpleStringProperty();

    private final StringProperty textFillStyle =
            CommonColorBindings.highEmphasisTextFillStyleProperty();

    private final StringProperty highlightFillStyle = new SimpleStringProperty();

    private final StringProperty highlightTextFillStyle = new SimpleStringProperty();

    // JAVAFX PROPERTIES (Style (Nodal))

    private final StringProperty thisStyle = new SimpleStringProperty();


    // CONTROLLER

    public CustomTextArea() {
        initializeFxml();

        initializeAttributiveStyleBindings();
        initializeNodalStyleBindings();
        initializePrefHeightStyleBindings();

        FxUtils.sharpenTextArea(this);
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/shared_components/custom_text_area"
                                     + ".fxml";
        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // INITIALIZERS (Style Bindings (Called in constructor))

    private void initializeAttributiveStyleBindings() {
        initializeBackgroundColorStyleBindings();
        initializeBackgroundInsetsStyleBindings();
        initializeHighlightFillStyleBindings();
        initializeHighlightTextFillStyleBindings();
    }

    private void initializeNodalStyleBindings() {
        initializeThisStyleBinding();
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive backgroundColorStyle)

    private void initializeBackgroundColorStyleBindings() {
        final Duration TRANSITION_DURATION = Duration.millis(75);

        final StringProperty BASE_BACKGROUND_COLOR_STRING =
                ColorFxUtils.createStaticStringProperty(ColorProvider.surfaceColorProperty());
        final ObjectProperty<Color> DESIRED_ACCENT_BACKGROUND_COLOR =
                createDesiredAccentBackgroundColor();

        final StringProperty ACCENT_BACKGROUND_COLOR_STRING =
                ColorFxUtils.createDynamicStringProperty(DESIRED_ACCENT_BACKGROUND_COLOR,
                                                         TRANSITION_DURATION);

        backgroundColorStyle.bind(Bindings.concat("-fx-background-color: ",
                                                  ACCENT_BACKGROUND_COLOR_STRING,
                                                  ", ",
                                                  BASE_BACKGROUND_COLOR_STRING,
                                                  ";"));
    }

    private ObjectProperty<Color> createDesiredAccentBackgroundColor() {
        final ObjectProperty<Color> DESIRED_ACCENT_BACKGROUND_COLOR = new SimpleObjectProperty<>();

        final ObjectProperty<Color> ENABLED_ACCENT_BACKGROUND_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.enabledTextFieldAccentColorProperty());

        final ObjectProperty<Color> HOVER_ACCENT_BACKGROUND_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.hoverTextFieldAccentColorProperty());

        hoverProperty()
                .addListener((obs, oldValue, newValue)
                                     -> DESIRED_ACCENT_BACKGROUND_COLOR.bind((newValue
                                                                              || isFocused())
                                                                             ?
                                                                             HOVER_ACCENT_BACKGROUND_COLOR
                                                                             :
                                                                             ENABLED_ACCENT_BACKGROUND_COLOR));

        focusedProperty().addListener((obs, oldValue, newValue)
                                              -> DESIRED_ACCENT_BACKGROUND_COLOR.bind((newValue
                                                                                       || isHover())
                                                                                      ?
                                                                                      HOVER_ACCENT_BACKGROUND_COLOR
                                                                                      :
                                                                                      ENABLED_ACCENT_BACKGROUND_COLOR));

        DESIRED_ACCENT_BACKGROUND_COLOR.bind(ENABLED_ACCENT_BACKGROUND_COLOR);

        return DESIRED_ACCENT_BACKGROUND_COLOR;
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive backgroundInsetsStyle)

    private void initializeBackgroundInsetsStyleBindings() {
        final Duration TRANSITION_DURATION = Duration.millis(75);

        final DoubleProperty DESIRED_PARAMETRIC_BACKGROUND_INSET =
                createDesiredParametricBackgroundInset();

        final DoubleProperty PARAMETRIC_BACKGROUND_INSET =
                DoubleFxUtils.createDynamicDoubleProperty(
                        DESIRED_PARAMETRIC_BACKGROUND_INSET,
                        TRANSITION_DURATION);

        backgroundInsetsStyle.bind(Bindings.concat("-fx-background-insets: 0, 0 0 ",
                                                   PARAMETRIC_BACKGROUND_INSET,
                                                   " 0;"));
    }

    private DoubleProperty createDesiredParametricBackgroundInset() {
        DoubleProperty DESIRED_PARAMETRIC_BACKGROUND_INSET = new SimpleDoubleProperty();

        hoverProperty()
                .addListener((obs, oldValue, newValue)
                                     -> DESIRED_PARAMETRIC_BACKGROUND_INSET.set((newValue
                                                                                 || isFocused())
                                                                                ?
                                                                                HOVER_ACCENT_HEIGHT
                                                                                :
                                                                                ENABLED_ACCENT_HEIGHT));

        focusedProperty()
                .addListener((obs, oldValue, newValue)
                                     -> DESIRED_PARAMETRIC_BACKGROUND_INSET.set((newValue
                                                                                 || isHover())
                                                                                ?
                                                                                HOVER_ACCENT_HEIGHT
                                                                                :
                                                                                ENABLED_ACCENT_HEIGHT));

        DESIRED_PARAMETRIC_BACKGROUND_INSET.set(ENABLED_ACCENT_HEIGHT);

        return DESIRED_PARAMETRIC_BACKGROUND_INSET;
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive highlightFillStyle))

    private void initializeHighlightFillStyleBindings() {
        final StringProperty HIGHLIGHT_COLOR_STRING =
                ColorFxUtils.createStaticStringProperty(ColorProvider.textFieldHighlightFillColorProperty());

        highlightFillStyle.bind(Bindings.concat("-fx-highlight-fill: ",
                                                HIGHLIGHT_COLOR_STRING,
                                                ";"));
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive highlightTextFillStyle))

    private void initializeHighlightTextFillStyleBindings() {
        final StringProperty HIGHLIGHT_TEXT_COLOR_STRING =
                ColorFxUtils.createStaticStringProperty(ColorProvider.highEmphasisTextColorProperty());

        highlightTextFillStyle.bind(Bindings.concat("-fx-highlight-text-fill:",
                                                    HIGHLIGHT_TEXT_COLOR_STRING,
                                                    ";"));
    }

    // INITIALIZERS (Style Bindings (prefHeightProperty))

    private void initializePrefHeightStyleBindings() {
        // FIXME: 01/10/2020 Fix issue when there is on eline of text the textarea is still too
        //  tall.
        Platform.runLater(() -> {
            Node text = this.lookup(".text");

            this.prefHeightProperty()
                .bind(Bindings.createDoubleBinding(() -> text.getBoundsInLocal().getHeight(),
                                                   text.boundsInLocalProperty()).add(20));
        });
    }


    // INITIALIZERS (Style Bindings (Internal - Nodal))

    private void initializeThisStyleBinding() {
        thisStyle.bind(Bindings.concat(backgroundColorStyle,
                                       backgroundInsetsStyle,
                                       textFillStyle,
                                       highlightFillStyle,
                                       highlightTextFillStyle));
        styleProperty().bind(thisStyle);
    }
}
