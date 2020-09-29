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

package main.java.presentation.controller.utils;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class FxUtils {

    public static void initializeFxml(Node node, String resourcePath) {
        FXMLLoader fxmlLoader = new FXMLLoader(node.getClass().getResource(resourcePath));

        fxmlLoader.setRoot(node);
        fxmlLoader.setController(node);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sharpenScrollPane(ScrollPane scrollPane) {
        scrollPane.skinProperty().addListener((obs, oldValue, newValue) -> {
            if (oldValue != null) return;
            StackPane stackPane = (StackPane) scrollPane.lookup("ScrollPane .viewport");
            stackPane.setCache(false);
        });
    }

    public static void initializeAnchorPaneAnchors(Node node) {
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
    }

    public static void sharpenTextArea(TextArea textArea) {
        Platform.runLater(() -> {
            textArea.setCache(false);
            ScrollPane scrollPane = (ScrollPane) textArea.getChildrenUnmodifiable().get(0);
            scrollPane.setCache(false);
            for (Node node : scrollPane.getChildrenUnmodifiable()) {
                node.setCache(false);
            }
        });
    }

}
