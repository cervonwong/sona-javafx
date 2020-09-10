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
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.java.presentation.controller.custom_nodes.main.MainPane;

import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Locale.setDefault(Locale.FRANCE);

        final MainPane ROOT = new MainPane();

        primaryStage.setTitle("Sona (v.DEV)");
        primaryStage.setScene(new Scene(ROOT, 0, 0, false, SceneAntialiasing.BALANCED));
        primaryStage.setMaximized(true);

        // TODO: 10/09/2020 Remove hard code.
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);

        setStageIcon(primaryStage);

        primaryStage.show();
    }

    private void setStageIcon(Stage primaryStage) {
        final String RESOURCE_PATH = "/images/stage_icon/filled_440.png";

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(RESOURCE_PATH)));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
