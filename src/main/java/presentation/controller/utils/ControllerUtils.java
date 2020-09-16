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

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import main.java.i18n.ResourceBundleName;

import java.util.ResourceBundle;

public class ControllerUtils {

    // TODO: 16/09/2020 Split into FxUtils and I18nUtils.

    // i18n

    public static ResourceBundle getMessages(ResourceBundleName name) {
        return ResourceBundle.getBundle(name.getBundleName());
    }


    // CACHING

    public static void sharpenScrollPane(ScrollPane scrollPane) {
        scrollPane.skinProperty().addListener((obs, oldValue, newValue) -> {
            if (oldValue != null) return;
            StackPane stackPane = (StackPane) scrollPane.lookup("ScrollPane .viewport");
            stackPane.setCache(false);
        });
    }
}
