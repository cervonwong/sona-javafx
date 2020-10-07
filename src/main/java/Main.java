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

package main.java;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.java.presentation.controller.custom_nodes.main.MainPane;
import main.java.presentation.controller.enums.Theme;
import main.java.presentation.controller.utils.color.provider.ColorProvider;

public class Main extends Application {

    // VARIABLES

    private static final ObjectProperty<KeyEvent> keyEvent = new SimpleObjectProperty<>();


    // START METHODS

    @Override
    public void start(Stage primaryStage) {
//        Locale.setDefault(Locale.FRANCE);

        final MainPane ROOT = new MainPane();
        final Scene SCENE = new Scene(ROOT, 0, 0, false, SceneAntialiasing.BALANCED);

        primaryStage.setTitle("Sona (v.DEV)");
        primaryStage.setScene(SCENE);
        primaryStage.setMaximized(true);

        // TODO: 10/09/2020 Remove hard code.
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);

        initializeKeyEventBinding(SCENE);
        initializeThemeToggleBehavior(); // TODO: 06/10/2020 Migrate this to RootPane.java in the
        // future.

        setStageIcon(primaryStage);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    // HELPER METHODS

    private void setStageIcon(Stage primaryStage) {
        final String RESOURCE_PATH = "/images/stage_icon/filled_440.png";

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(RESOURCE_PATH)));
    }

    private void initializeKeyEventBinding(Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent::set);
    }

    private void initializeThemeToggleBehavior() {
        keyEvent.addListener((obs, oldValue, newValue) -> {
            if (newValue.isControlDown()
                && newValue.isAltDown()
                && newValue.getCode().getChar().equals("T")) {
                ColorProvider.setTheme(ColorProvider.getTheme() == Theme.LIGHT
                                       ? Theme.DARK
                                       : Theme.LIGHT);
            }
        });
    }


    // ACCESSORS

    public static ObjectProperty<KeyEvent> keyEventProperty() {
        return keyEvent;
    }
}
