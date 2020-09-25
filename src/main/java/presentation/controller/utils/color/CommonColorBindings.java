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

package main.java.presentation.controller.utils.color;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.scene.paint.Color;
import main.java.presentation.controller.utils.color.provider.ColorProvider;

public class CommonColorBindings {

    // -fx-background-color

    public static StringProperty backgroundColorStyleProperty() {
        final StringProperty BACKGROUND_COLOR_STRING =
                ColorFxUtils.createStaticStringProperty(ColorProvider.backgroundColorProperty());

        final StringProperty BACKGROUND_COLOR_STYLE = new SimpleStringProperty();
        BACKGROUND_COLOR_STYLE.bind(Bindings.concat("-fx-background-color: ",
                                                    BACKGROUND_COLOR_STRING,
                                                    ";"));

        return BACKGROUND_COLOR_STYLE;
    }


    // -fx-text-fill

    private static StringProperty textFillStyleProperty(ObjectProperty<Color> colorProperty) {
        final StringProperty TEXT_COLOR_STRING =
                ColorFxUtils.createStaticStringProperty(colorProperty);

        final StringProperty TEXT_FILL_STYLE = new SimpleStringProperty();
        TEXT_FILL_STYLE.bind(Bindings.concat("-fx-text-fill: ", TEXT_COLOR_STRING, ";"));

        return TEXT_FILL_STYLE;
    }

    public static StringProperty highestEmphasisTextFillStyleProperty() {
        return textFillStyleProperty(ColorProvider.highestEmphasisTextColorProperty());
    }

    public static StringProperty highEmphasisTextFillStyleProperty() {
        return textFillStyleProperty(ColorProvider.highEmphasisTextColorProperty());
    }

    public static StringProperty mediumEmphasisTextFillStyleProperty() {
        return textFillStyleProperty(ColorProvider.mediumEmphasisTextColorProperty());
    }

    public static StringProperty lowEmphasisTextFillStyleProperty() {
        return textFillStyleProperty(ColorProvider.lowEmphasisTextColorProperty());
    }
}
