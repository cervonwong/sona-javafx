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
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.util.Duration;
import main.java.presentation.controller.custom_nodes.main.rail_destinations.decks.deck_info_pane.tabs.DeckInfoTab;
import main.java.presentation.controller.utils.DoubleFxUtils;
import main.java.presentation.controller.utils.FxUtils;
import main.java.presentation.controller.utils.color.ColorFxUtils;
import main.java.presentation.controller.utils.color.provider.ColorProvider;

import java.util.HashMap;
import java.util.Map;

// TODO: 24/09/2020 Fix starting animation where indicator starts out at full size.
public class TabBar extends AnchorPane {

    // INSTANCE VARIABLES

    private final ObservableMap<DeckInfoTab, TabItem> tabItems = FXCollections.observableHashMap();

    private final Map<DeckInfoTab, Integer> tabItemIndices = new HashMap<>();


    // JAVAFX PROPERTIES (Public Accessors / Mutators)

    private final ObjectProperty<DeckInfoTab> activeTab = new SimpleObjectProperty<>();


    // JAVAFX PROPERTIES (Style (Attributive))

    private final StringProperty indicatorBackgroundColorStyle = new SimpleStringProperty();

    // This property needs to be a field to prevent garbage collection! Do not listen to IntelliJ
    // here.
    private DoubleProperty desiredLeftAnchor = new SimpleDoubleProperty();
    private DoubleProperty desiredRightAnchor = new SimpleDoubleProperty();

    // JAVAFX PROPERTIES (Style (Nodal))

    private final StringProperty indicatorStyle = new SimpleStringProperty();


    // FXML NODES

    @FXML
    private HBox itemBar;

    @FXML
    private Pane indicator;


    // CONSTRUCTOR

    public TabBar() {
        initializeFxml();

        initializeItemBarBehavior();
        initializeActiveTabListener();
        initializeIndicatorBehavior();

        initializeAttributiveStyleBindings();
        initializeNodalStyleBindings();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/shared_components/tab_bar/tab_bar"
                                     + ".fxml";

        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // INITIALIZERS (Behavior)

    private void initializeItemBarBehavior() {
        tabItems.addListener((MapChangeListener<DeckInfoTab, TabItem>) change -> {
            if (change.wasAdded()) {
                final TabItem TAB_ITEM = change.getValueAdded();
                addTabItem(TAB_ITEM);
            }
        });
    }

    private void initializeTabItemBehavior(TabItem tabItem) {
        tabItem.setOnAction(e -> {
            activeTab.set(tabItem.getDeckInfoTab());
        });
    }

    private void initializeActiveTabListener() {
        activeTab.addListener((obs, oldTab, newTab) -> {
            if (oldTab != null)
                tabItems.get(oldTab).setActivated(false);
            tabItems.get(newTab).setActivated(true);
        });
    }


    // INITIALIZERS (Behavior - Indicator)

    private void initializeIndicatorBehavior() {
        // There is no way to get the property of the anchor constraints of indicator.

        final IntegerProperty POSITION = createPosition();
        final IntegerProperty SIZE = createSize();

        desiredLeftAnchor = createDesiredLeftAnchor(POSITION, SIZE);
        desiredRightAnchor = createDesiredRightAnchor(POSITION, SIZE);

        bindAnchors();
    }

    private IntegerProperty createPosition() {
        final IntegerProperty POSITION = new SimpleIntegerProperty();
        activeTabProperty().addListener((obs, oldTab, newTab) -> {
            POSITION.set(tabItemIndices.get(newTab));
        });
        POSITION.set(0);
        return POSITION;
    }

    private IntegerProperty createSize() {
        final IntegerProperty SIZE = new SimpleIntegerProperty();
        tabItems.addListener((MapChangeListener<DeckInfoTab, TabItem>) change -> {
            SIZE.set(tabItems.size());
        });
        SIZE.set(1);
        return SIZE;
    }

    private DoubleProperty createDesiredLeftAnchor(IntegerProperty position, IntegerProperty size) {
        final DoubleProperty DESIRED_LEFT_ANCHOR = new SimpleDoubleProperty();
        DESIRED_LEFT_ANCHOR.set(0.0);
        DESIRED_LEFT_ANCHOR.bind(position.multiply(widthProperty().divide(size)));
        return DESIRED_LEFT_ANCHOR;
    }

    private DoubleProperty createDesiredRightAnchor(IntegerProperty position, IntegerProperty size) {
        final DoubleProperty DESIRED_RIGHT_ANCHOR = new SimpleDoubleProperty();
        DESIRED_RIGHT_ANCHOR.bind((size.subtract(position).subtract(1))
                                          .multiply(widthProperty().divide(size)));
        return DESIRED_RIGHT_ANCHOR;
    }

    private void bindAnchors() {
        final Duration TRANSITION_DURATION = Duration.millis(150);

        bindLeftAnchor(TRANSITION_DURATION);
        bindRightAnchor(TRANSITION_DURATION);
    }

    private void bindLeftAnchor(Duration transitionDuration) {
        final DoubleProperty LEFT_ANCHOR =
                DoubleFxUtils.createDynamicDoubleProperty(desiredLeftAnchor,
                                                          transitionDuration);

        LEFT_ANCHOR.addListener((obs, oldAnchor, newAnchor) -> {
            AnchorPane.setLeftAnchor(indicator, newAnchor.doubleValue());
        });
    }

    private void bindRightAnchor(Duration transitionDuration) {
        final DoubleProperty RIGHT_ANCHOR =
                DoubleFxUtils.createDynamicDoubleProperty(desiredRightAnchor,
                                                          transitionDuration);

        RIGHT_ANCHOR.addListener((obs, oldAnchor, newAnchor) -> {
            AnchorPane.setRightAnchor(indicator, newAnchor.doubleValue());
        });
    }


    // INITIALIZERS (Adding Methods)

    private void addTabItem(TabItem tabItem) {
        final AnchorPane TAB_ITEM_PANE = new AnchorPane(tabItem);
        itemBar.getChildren().add(TAB_ITEM_PANE);

        FxUtils.initializeAnchorPaneAnchors(tabItem);

        initializeTabItemPaneDistribution(TAB_ITEM_PANE);
        initializeTabItemBehavior(tabItem);
    }

    private void initializeTabItemPaneDistribution(AnchorPane tabItemPane) {
        HBox.setHgrow(tabItemPane, Priority.ALWAYS);
    }


    // INITIALIZERS (Style Bindings (Called in constructor))

    private void initializeAttributiveStyleBindings() {
        initializeIndicatorBackgroundColorStyleBindings();
    }

    private void initializeNodalStyleBindings() {
        initializeIndicatorStyleBinding();
    }


    // INITIALIZERS (Style Bindings (Internal - Attributive indicatorBackgroundColorStyle))

    private void initializeIndicatorBackgroundColorStyleBindings() {
        final StringProperty BACKGROUND_COLOR_STRING =
                ColorFxUtils.createStaticStringProperty(ColorProvider.tabBarIndicatorColorProperty());

        indicatorBackgroundColorStyle.bind(Bindings.concat("-fx-background-color: ",
                                                           BACKGROUND_COLOR_STRING,
                                                           ";"));
    }


    // INITIALIZERS (Style Bindings (Internal - Nodal))

    private void initializeIndicatorStyleBinding() {
        indicatorStyle.bind(indicatorBackgroundColorStyle);
        indicator.styleProperty().bind(indicatorStyle);
    }


    // PUBLIC METHODS

    public void addTab(DeckInfoTab deckInfoTab, String text, char icon) {
        final TabItem TAB_ITEM = new TabItem(deckInfoTab, text, icon);

        tabItems.put(deckInfoTab, TAB_ITEM);
        tabItemIndices.put(deckInfoTab, tabItemIndices.size());
    }


    // ACCESSORS

    public DeckInfoTab getActiveTab() {
        return activeTab.get();
    }


    // PROPERTY ACCESSORS

    public ObjectProperty<DeckInfoTab> activeTabProperty() {
        return activeTab;
    }


    // MUTATORS

    public void setActiveTab(DeckInfoTab activeTab) {
        this.activeTab.set(activeTab);
    }
}
