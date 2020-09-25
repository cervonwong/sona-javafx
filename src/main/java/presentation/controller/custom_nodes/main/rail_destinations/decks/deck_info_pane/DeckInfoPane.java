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

package main.java.presentation.controller.custom_nodes.main.rail_destinations.decks.deck_info_pane;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.i18n.ResourceBundleName;
import main.java.presentation.controller.custom_nodes.main.rail_destinations.decks.deck_info_pane.tabs.DeckInfoTab;
import main.java.presentation.controller.custom_nodes.main.rail_destinations.decks.deck_info_pane.tabs.overview.DeckInfoOverviewTabPane;
import main.java.presentation.controller.custom_nodes.main.rail_destinations.decks.destination_controller.DestinationController;
import main.java.presentation.controller.custom_nodes.screens.UnimplementedScreen;
import main.java.presentation.controller.custom_nodes.shared_components.BackButton;
import main.java.presentation.controller.custom_nodes.shared_components.HorizontalDivider;
import main.java.presentation.controller.custom_nodes.shared_components.tab_bar.TabBar;
import main.java.presentation.controller.utils.ControllerUtils;
import main.java.presentation.controller.utils.FxUtils;
import main.java.presentation.controller.utils.color.CommonColorBindings;
import main.java.presentation.model.structure.deck.Deck;

import java.util.ResourceBundle;

public class DeckInfoPane extends AnchorPane {

    // INSTANCE VARIABLES

    private final ResourceBundle messages;

    private final DestinationController destinationController;

    private final Deck deck;


    // INSTANCE VARIABLES (Nodes)

    private final TabBar tabBar = new TabBar();


    // JAVAFX PROPERTIES (Style (Attributive))

    private final StringProperty backgroundColorStyle =
            CommonColorBindings.backgroundColorStyleProperty();


    // JAVAFX PROPERTIES (Style (Nodal))

    private final StringProperty thisStyle = new SimpleStringProperty();


    // FXML NODES

    @FXML
    private AnchorPane backButtonPane;

    @FXML
    private Label titleLabel;

    @FXML
    private AnchorPane tabBarPane;

    @FXML
    private AnchorPane dividerPane;

    @FXML
    private AnchorPane tabPaneContainer;


    // CONSTRUCTOR

    public DeckInfoPane(DestinationController destinationController, Deck deck) {
        messages = ControllerUtils.getMessages(ResourceBundleName.DECK_INFO_PANE);
        this.destinationController = destinationController;
        this.deck = deck;

        initializeFxml();

        addStaticComponents();

        initializeTexts();

        initializeAttributiveStyleBindings();
        initializeNodalStyleBindings();
    }


    // INITIALIZERS (FXML)

    private void initializeFxml() {
        final String RESOURCE_PATH = "/view/fxml/custom_nodes/main/rail_destinations/decks"
                                     + "/deck_info_pane/deck_info_pane.fxml";

        FxUtils.initializeFxml(this, RESOURCE_PATH);
    }


    // INITIALIZERS (Text)

    private void initializeTexts() {
        initializeTitleLabel();
    }

    private void initializeTitleLabel() {
        titleLabel.setText(deck.getName());
    }


    // INITIALIZERS (Behavior)

    private void initializeBackButtonBehavior(BackButton backButton) {
        backButton.setOnAction(e -> destinationController.requestPop());
    }

    private void initializeTabBarBehavior() {
        initializeTabBarState();

        tabBar.activeTabProperty().addListener((obs, oldTab, newTab) -> {
            final AnchorPane NEW_TAB_PANE = createNewTabPane(newTab);

            final ObservableList<Node> CHILDREN = tabPaneContainer.getChildren();
            CHILDREN.clear();
            CHILDREN.add(NEW_TAB_PANE);

            FxUtils.initializeAnchorPaneAnchors(NEW_TAB_PANE);
        });
    }

    private void initializeTabBarState() {
        tabBar.addTab(DeckInfoTab.REVIEW, messages.getString("tab.review"), '\uF44B');
        tabBar.addTab(DeckInfoTab.BROWSE, messages.getString("tab.browse"), '\uF002');
        tabBar.addTab(DeckInfoTab.PROGRESS, messages.getString("tab.progress"), '\uF201');
        tabBar.addTab(DeckInfoTab.ACTIVITY, messages.getString("tab.activity"), '\uF1DA');
        tabBar.addTab(DeckInfoTab.INFO, messages.getString("tab.info"), '\uF05A');
        tabBar.setActiveTab(DeckInfoTab.REVIEW);
    }

    private AnchorPane createNewTabPane(DeckInfoTab newTab) {
        switch (newTab) {
            case REVIEW:
                return new DeckInfoOverviewTabPane(deck);
            case BROWSE:
                return new UnimplementedScreen("The 'Browse' tab will enable you to view and sort"
                                               + " your cards and notes of this deck easily.");
            case PROGRESS:
                return new UnimplementedScreen("The 'Progress' tab will enable you to visualise "
                                               + "how well you memorise facts.");
            case ACTIVITY:
                return new UnimplementedScreen("The 'Activity' tab will enable you to see at a "
                                               + "glance, all the work you've put into this deck.");
            case INFO:
                return new UnimplementedScreen("The 'Info' tab will show you all the fine details"
                                               + " of this deck.");
            default:
                return new AnchorPane();
        }
    }


    // INITIALIZERS (Adding Methods)

    private void addStaticComponents() {
        addBackButton();
        addTabBar();
        addHorizontalDivider();
    }

    private void addBackButton() {
        final BackButton BACK_BUTTON = new BackButton();

        initializeBackButtonBehavior(BACK_BUTTON);
        backButtonPane.getChildren().add(BACK_BUTTON);
        FxUtils.initializeAnchorPaneAnchors(BACK_BUTTON);
    }

    private void addTabBar() {
        initializeTabBarBehavior();
        tabBarPane.getChildren().add(tabBar);
        FxUtils.initializeAnchorPaneAnchors(tabBar);
    }

    private void addHorizontalDivider() {
        final HorizontalDivider HORIZONTAL_DIVIDER = new HorizontalDivider(1.0);

        dividerPane.getChildren().add(HORIZONTAL_DIVIDER);
        FxUtils.initializeAnchorPaneAnchors(HORIZONTAL_DIVIDER);
    }


    // INITIALIZERS (Style Bindings (Internal - (Called in constructor))

    private void initializeAttributiveStyleBindings() {

    }

    private void initializeNodalStyleBindings() {
        initializeThisStyleBinding();
    }


    // INITIALIZERS (Style Bindings (Internal - Nodal))

    private void initializeThisStyleBinding() {
        thisStyle.bind(backgroundColorStyle);
        styleProperty().bind(thisStyle);
    }


    // INITIALIZERS (i18n)

//    private void initializeI18nText() {
//        titleLabel.setText(messages.getString("label.title"));
//    }
}
