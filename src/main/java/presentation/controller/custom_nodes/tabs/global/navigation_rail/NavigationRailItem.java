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

package main.java.presentation.controller.custom_nodes.tabs.global.navigation_rail;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class NavigationRailItem extends Button {

    // INSTANCE VARIABLES

    private final String key;


    // JAVAFX PROPERTIES

    private final SimpleBooleanProperty isActivated = new SimpleBooleanProperty();


    // FXML NODES

    @FXML
    private Label iconLabel;


    // CONSTRUCTOR

    public NavigationRailItem(String key, String icon, String text) {
        this(key, icon, text, false);
    }

    public NavigationRailItem(String key, String icon, String text, boolean isActivated) {
        this.key = key;

        initializeFxml();
        initializeIcon(icon);
        initializeText(text);

        initializeIsActivatedProperty();
        updateStyleClass(isActivated);
    }


    // INITIALIZERS

    private void initializeFxml() {
        final String RESOURCE_PATH =
                "/view/fxml/tabs/global/custom_nodes/navigation_rail/navigation_rail_item.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(RESOURCE_PATH));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeIcon(String icon) {
        iconLabel.setText(icon);
    }

    private void initializeText(String text) {
        this.setText(text);
    }

    private void initializeIsActivatedProperty() {
        isActivated.addListener((obs, oldValue, newValue) -> updateStyleClass(newValue));
    }


    // UPDATERS

    private void updateStyleClass(boolean isActivated) {
        final String DESIRED_GLOBAL_STYLE_CLASS = isActivated
                                                  ? "activated-item"
                                                  : "inactive-item";

        final String UNDESIRED_GLOBAL_STYLE_CLASS = isActivated
                                                    ? "inactive-item"
                                                    : "activated-item";

        final String DESIRED_ICON_STYLE_CLASS = isActivated
                                                ? "activated-item-icon"
                                                : "inactive-item-icon";

        final String UNDESIRED_ICON_STYLE_CLASS = isActivated
                                                  ? "inactive-item-icon"
                                                  : "activated-item-icon";

        final ObservableList<String> BUTTON_STYLE_CLASS = this.getStyleClass();
        final ObservableList<String> ICON_LABEL_STYLE_CLASS = iconLabel.getStyleClass();

        // TODO: 07/09/2020 Remove hard code.
        if (!BUTTON_STYLE_CLASS.contains(DESIRED_GLOBAL_STYLE_CLASS))
            BUTTON_STYLE_CLASS.add(DESIRED_GLOBAL_STYLE_CLASS);
        BUTTON_STYLE_CLASS.remove(UNDESIRED_GLOBAL_STYLE_CLASS);

        if (!ICON_LABEL_STYLE_CLASS.contains(DESIRED_GLOBAL_STYLE_CLASS))
            ICON_LABEL_STYLE_CLASS.add(DESIRED_GLOBAL_STYLE_CLASS);
        ICON_LABEL_STYLE_CLASS.remove(UNDESIRED_GLOBAL_STYLE_CLASS);

        if (!ICON_LABEL_STYLE_CLASS.contains(DESIRED_ICON_STYLE_CLASS))
            ICON_LABEL_STYLE_CLASS.add(DESIRED_ICON_STYLE_CLASS);
        ICON_LABEL_STYLE_CLASS.remove(UNDESIRED_ICON_STYLE_CLASS);
    }


    // ACCESSORS

    public String getKey() {
        return key;
    }


    // JAVAFX PROPERTY ACCESSORS

    public boolean isActivated() {
        return isActivated.get();
    }

    public SimpleBooleanProperty isActivatedProperty() {
        return isActivated;
    }


    // JAVAFX PROPERTY MUTATORS

    public void setActivated(boolean isActivated) {
        this.isActivated.set(isActivated);
    }
}
