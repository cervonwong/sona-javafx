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

import javafx.animation.*;
import javafx.beans.property.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ColorFxUtils {

    // CONVERTERS

    public static String toHexCode(Color color) {
        return "#"
               + format(color.getRed())
               + format(color.getGreen())
               + format(color.getBlue())
               + format(color.getOpacity()).toUpperCase();
    }

    private static String format(double value) {
        String hexString = Integer.toHexString((int) Math.round(value * 255));
        return hexString.length() == 1 ? "0" + hexString : hexString;
    }


    // BINDING

    // Static color bindings are colors that will only change when the theme changes, but nowhere
    // else.
    public static StringProperty createStaticStringProperty(ObjectProperty<Color> desiredColor) {
        final StringProperty STRING_PROPERTY =
                new SimpleStringProperty(ColorFxUtils.toHexCode(desiredColor.get()));

        final ObjectProperty<Color> COLOR = createStaticColorProperty(desiredColor);

        COLOR.addListener((obs, oldColor, newColor)
                                  -> STRING_PROPERTY.set(ColorFxUtils.toHexCode(newColor)));

        return STRING_PROPERTY;
    }

    public static ObjectProperty<Color> createStaticColorProperty(ObjectProperty<Color> desiredColor) {
        final Duration THEME_TRANSITION_DURATION = Duration.millis(400);

        final ObjectProperty<Color> COLOR = new SimpleObjectProperty<>(desiredColor.get());

        desiredColor.addListener((obs, oldColor, newColor)
                                         -> createColorTimelineAnimation(COLOR,
                                                                         oldColor,
                                                                         newColor,
                                                                         THEME_TRANSITION_DURATION).play());

        return COLOR;
    }


    // ANIMATIONS

    public static Timeline createColorTimelineAnimation(ObjectProperty<Color> color,
                                                        Color startColor,
                                                        Color endColor,
                                                        Duration duration) {
        return new Timeline(new KeyFrame(Duration.ZERO,
                                         new KeyValue(color,
                                                      startColor,
                                                      Interpolator.EASE_BOTH)),
                            new KeyFrame(duration,
                                         new KeyValue(color,
                                                      endColor,
                                                      Interpolator.EASE_BOTH)));
    }
}
