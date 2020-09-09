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

package main.java.presentation.controller.custom_nodes.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import main.java.i18n.ResourceBundles;
import main.java.presentation.controller.custom_nodes.main.destinations.about.AboutPane;
import main.java.presentation.controller.custom_nodes.main.navigation_rail.NavigationRail;

import java.io.IOException;
import java.util.ResourceBundle;

public class MainPane extends HBox {

    // INSTANCE VARIABLES

    private final NavigationRail navigationRail = new NavigationRail();

    private final AnchorPane contentPane = new AnchorPane();

    // CONSTRUCTOR

    public MainPane() {
        initializeFxml();

        initializeNavigationDrawer();
        initializeContentPane();
        initializeNavigationBehavior();
    }


    // INITIALIZERS


    private void initializeFxml() {
        final String RESOURCE_PATH =
                "/view/fxml/custom_nodes/main/main_pane.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(RESOURCE_PATH));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeNavigationDrawer() {
        this.getChildren().add(navigationRail);
        navigationRail.prefHeightProperty().bind(this.heightProperty());
    }

    private void initializeContentPane() {
        this.getChildren().add(contentPane);

        HBox.setHgrow(contentPane, Priority.ALWAYS);
        contentPane.prefHeightProperty().bind(this.heightProperty());
    }

    private void initializeNavigationBehavior() {
        // Uses Android-Like navigation.
        navigationRail.activeItemProperty().addListener((obs, oldValue, newValue) -> {
            // Remove the previous destination pane.
            contentPane.getChildren().clear();

            switch (newValue) {
                case "about":
                    final AboutPane aboutPane = new AboutPane();
                    contentPane.getChildren().add(aboutPane);

                    AnchorPane.setTopAnchor(aboutPane, 0.0);
                    AnchorPane.setBottomAnchor(aboutPane, 0.0);
                    AnchorPane.setRightAnchor(aboutPane, 0.0);
                    AnchorPane.setLeftAnchor(aboutPane, 0.0);


                    contentPane.widthProperty().addListener((ob, o, n) -> {
                        System.out.println("o = " + o);
                        System.out.println("n = " + n);
                    });

                    break;
                default:
                    System.out.println("Unimplemented");
            }
        });
    }
}
