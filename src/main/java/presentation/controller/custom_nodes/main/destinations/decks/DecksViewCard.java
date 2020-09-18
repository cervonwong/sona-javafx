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

package main.java.presentation.controller.custom_nodes.main.destinations.decks;

import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.java.i18n.ResourceBundleName;
import main.java.presentation.controller.utils.ControllerUtils;
import main.java.presentation.controller.utils.color.ColorFxUtils;
import main.java.presentation.controller.utils.color.ColorProvider;
import main.java.presentation.model.structure.deck.Deck;

import java.io.IOException;
import java.util.ResourceBundle;

// FIXME: 18/09/2020 Fix bug where the entire button will have an orange background when moving
//  mouse rapidly in and out of the cards.
public class DecksViewCard extends AnchorPane {

    // INSTANCE VARIABLES

    private final ResourceBundle messages;

    private final Deck deck;


    // JAVAFX PROPERTIES (Public Accessors / Mutators)


    // CONSTANTS (Style)

    private static final double hoverAccentHeight = 4.0;

    private static final double backgroundRadius = 8.0;


    // JAVAFX PROPERTIES (Style (Attributive))

    private final StringProperty backgroundColorStyle = new SimpleStringProperty();

    private final StringProperty backgroundInsetsStyle = new SimpleStringProperty();

    private final StringProperty backgroundRadiusStyle = new SimpleStringProperty();


    // JAVAFX PROPERTIES (Style (Nodal))

    private final StringProperty thisStyle = new SimpleStringProperty();


    // FXML NODES

    @FXML
    private Label deckNameLabel;

    @FXML
    private Label dueLabelLabel,
            dueCountLabel,
            newLabelLabel,
            newCountLabel,
            seenLabelLabel,
            seenCountLabel;

    @FXML
    private Button actionButton;


    // CONSTRUCTOR

    public DecksViewCard(Deck deck) {
        messages = ControllerUtils.getMessages(ResourceBundleName.DECKS_VIEW_CARD);
        this.deck = deck;

        initializeFxml();

        initializeDeckNameLabelText();

        initializeI18nText();

        initializeAttributiveStyleBindings();
        initializeNodalStyleBindings();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH =
                "/view/fxml/custom_nodes/main/destinations/decks/decks_view_card.fxml";

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

    private void initializeDeckNameLabelText() {
        deckNameLabel.setText(deck.getName());
    }


    // INITIALIZERS (Style Bindings (Called in constructor))

    private void initializeAttributiveStyleBindings() {
        initializeBackgroundColorStyleBindings();
        initializeBackgroundInsetsStyleBindings();
        initializeBackgroundRadiusStyleBindings();
    }

    private void initializeNodalStyleBindings() {
        initializeThisStyleBinding();
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive backgroundColorStyle))

    private void initializeBackgroundColorStyleBindings() {
        final Duration TRANSITION_DURATION = Duration.millis(150);

        final ObjectProperty<Color> DESIRED_BASE_BACKGROUND_COLOR =
                createDesiredBaseBackgroundColor();
        final ObjectProperty<Color> DESIRED_ACCENT_BACKGROUND_COLOR =
                createDesiredAccentBackgroundColor();

        final StringProperty BASE_BACKGROUND_COLOR_STRING =
                ColorFxUtils.createDynamicStringProperty(DESIRED_BASE_BACKGROUND_COLOR,
                                                         TRANSITION_DURATION);

        final StringProperty ACCENT_BACKGROUND_COLOR_STRING =
                ColorFxUtils.createDynamicStringProperty(DESIRED_ACCENT_BACKGROUND_COLOR,
                                                         TRANSITION_DURATION);

        backgroundColorStyle.bind(Bindings.concat("-fx-background-color: ",
                                                  BASE_BACKGROUND_COLOR_STRING,
                                                  ", ",
                                                  ACCENT_BACKGROUND_COLOR_STRING, ";"));
    }

    private ObjectProperty<Color> createDesiredBaseBackgroundColor() {
        final ObjectProperty<Color> DESIRED_BASE_BACKGROUND_COLOR = new SimpleObjectProperty<>();

        final ObjectProperty<Color> SURFACE_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.surfaceColorProperty());

        DESIRED_BASE_BACKGROUND_COLOR.bind(SURFACE_COLOR);

        return DESIRED_BASE_BACKGROUND_COLOR;
    }

    private ObjectProperty<Color> createDesiredAccentBackgroundColor() {
        final ObjectProperty<Color> DESIRED_ACCENT_BACKGROUND_COLOR = new SimpleObjectProperty<>();

        final ObjectProperty<Color> ENABLED_BACKGROUND_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.surfaceColorProperty());

        final ObjectProperty<Color> HOVER_BACKGROUND_COLOR =
                ColorFxUtils.createStaticColorProperty(ColorProvider.decksViewCardHoverAccentColorProperty());

        actionButton.hoverProperty()
                .addListener((obs, oldValue, newValue)
                                     -> DESIRED_ACCENT_BACKGROUND_COLOR.bind(newValue
                                                                             ?
                                                                             HOVER_BACKGROUND_COLOR
                                                                             :
                                                                             ENABLED_BACKGROUND_COLOR));

        DESIRED_ACCENT_BACKGROUND_COLOR.bind(ENABLED_BACKGROUND_COLOR);

        return DESIRED_ACCENT_BACKGROUND_COLOR;
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive backgroundInsetsStyle))

    private void initializeBackgroundInsetsStyleBindings() {
        final Duration TRANSITION_DURATION = Duration.millis(50);

        final DoubleProperty DESIRED_BASE_PARAMETRIC_BACKGROUND_INSET =
                createDesiredBaseParametricBackgroundInset();

        final DoubleProperty DESIRED_ACCENT_PARAMETRIC_BACKGROUND_INSET =
                createDesiredAccentParametricBackgroundInset();

        final DoubleProperty BASE_PARAMETRIC_BACKGROUND_INSET =
                createDynamicDoubleProperty(DESIRED_BASE_PARAMETRIC_BACKGROUND_INSET,
                                            TRANSITION_DURATION);

        final DoubleProperty ACCENT_PARAMETRIC_BACKGROUND_INSET =
                createDynamicDoubleProperty(DESIRED_ACCENT_PARAMETRIC_BACKGROUND_INSET,
                                            TRANSITION_DURATION);

        backgroundInsetsStyle.bind(Bindings.concat("-fx-background-insets: 0 0 ",
                                                   BASE_PARAMETRIC_BACKGROUND_INSET,
                                                   " 0, ",
                                                   ACCENT_PARAMETRIC_BACKGROUND_INSET,
                                                   " 0 0 0;"));
    }

    private DoubleProperty createDesiredBaseParametricBackgroundInset() {
        final DoubleProperty DESIRED_BASE_PARAMETRIC_BACKGROUND_INSET = new SimpleDoubleProperty();

        final DoubleProperty ENABLED_INSET = new SimpleDoubleProperty(0.0);

        final DoubleProperty HOVER_INSET = new SimpleDoubleProperty(hoverAccentHeight);

        actionButton.hoverProperty()
                .addListener((obs, oldValue, newValue)
                                     -> DESIRED_BASE_PARAMETRIC_BACKGROUND_INSET.bind(newValue
                                                                                      ? HOVER_INSET
                                                                                      :
                                                                                      ENABLED_INSET));

        DESIRED_BASE_PARAMETRIC_BACKGROUND_INSET.bind(ENABLED_INSET);

        return DESIRED_BASE_PARAMETRIC_BACKGROUND_INSET;
    }

    private DoubleProperty createDesiredAccentParametricBackgroundInset() {
        final DoubleProperty DESIRED_ACCENT_PARAMETRIC_BACKGROUND_INSET =
                new SimpleDoubleProperty();

        final DoubleProperty ENABLED_INSET = new SimpleDoubleProperty();
        ENABLED_INSET.bind(heightProperty());

        final DoubleProperty HOVER_INSET = new SimpleDoubleProperty();
        HOVER_INSET.bind(heightProperty().subtract(hoverAccentHeight));

        actionButton.hoverProperty()
                .addListener((obs, oldValue, newValue)
                                     -> DESIRED_ACCENT_PARAMETRIC_BACKGROUND_INSET.bind(newValue
                                                                                        ?
                                                                                        HOVER_INSET
                                                                                        :
                                                                                        ENABLED_INSET));

        DESIRED_ACCENT_PARAMETRIC_BACKGROUND_INSET.bind(ENABLED_INSET);

        return DESIRED_ACCENT_PARAMETRIC_BACKGROUND_INSET;
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive backgroundRadiusStyle))

    private void initializeBackgroundRadiusStyleBindings() {
        final DoubleProperty DESIRED_BASE_PARAMETRIC_BACKGROUND_RADIUS =
                createDesiredBaseParametricBackgroundRadius();

        backgroundRadiusStyle.bind(Bindings.concat("-fx-background-radius: ",
                                                   backgroundRadius,
                                                   " ",
                                                   backgroundRadius,
                                                   " ",
                                                   DESIRED_BASE_PARAMETRIC_BACKGROUND_RADIUS,
                                                   " ",
                                                   DESIRED_BASE_PARAMETRIC_BACKGROUND_RADIUS,
                                                   ", 0 0 ",
                                                   backgroundRadius,
                                                   " ",
                                                   backgroundRadius,
                                                   ";"));
    }

    private DoubleProperty createDesiredBaseParametricBackgroundRadius() {
        final DoubleProperty DESIRED_BASE_PARAMETRIC_BACKGROUND_RADIUS = new SimpleDoubleProperty();

        final double HOVER_RADIUS = 0.0;

        actionButton.hoverProperty()
                .addListener((obs, oldValue, newValue)
                                     -> DESIRED_BASE_PARAMETRIC_BACKGROUND_RADIUS.set(newValue
                                                                                      ?
                                                                                      HOVER_RADIUS
                                                                                      :
                                                                                      backgroundRadius));
        DESIRED_BASE_PARAMETRIC_BACKGROUND_RADIUS.set(backgroundRadius);

        return DESIRED_BASE_PARAMETRIC_BACKGROUND_RADIUS;
    }


    // INITIALIZERS (Style Bindings (Internal - Nodal))

    private void initializeThisStyleBinding() {
        thisStyle.bind(Bindings.concat(backgroundColorStyle,
                                       backgroundInsetsStyle,
                                       backgroundRadiusStyle));

        styleProperty().bind(thisStyle);
    }


    // INITIALIZERS (i18n)

    private void initializeI18nText() {
        dueLabelLabel.setText(messages.getString("label.due"));
        newLabelLabel.setText(messages.getString("label.new"));
        seenLabelLabel.setText(messages.getString("label.seen"));
    }


    // TRANSITION METHODS

    // TODO: 17/09/2020 Extract this, together with a similar one in ColorFxProperty, to a
    //  generic method.
    private DoubleProperty createDynamicDoubleProperty(DoubleProperty desiredValue,
                                                       Duration duration) {
        final DoubleProperty VALUE = new SimpleDoubleProperty(desiredValue.get());

        desiredValue.addListener((obs, oldValue, newValue)
                                         -> createDoubleTimelineAnimation(VALUE,
                                                                          oldValue.doubleValue(),
                                                                          newValue.doubleValue(),
                                                                          duration).play());

        return VALUE;
    }

    private Timeline createDoubleTimelineAnimation(DoubleProperty value,
                                                   double startValue,
                                                   double endValue,
                                                   Duration duration) {
        return new Timeline(new KeyFrame(Duration.ZERO,
                                         new KeyValue(value,
                                                      startValue,
                                                      Interpolator.EASE_BOTH)),
                            new KeyFrame(duration,
                                         new KeyValue(value,
                                                      endValue,
                                                      Interpolator.EASE_BOTH)));
    }
}
