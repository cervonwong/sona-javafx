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

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.Duration;

public class DoubleFxUtils {

    // PROPERTY BINDING

    public static DoubleProperty createDynamicDoubleProperty(DoubleProperty desiredValue,
                                                              Duration duration) {
        final DoubleProperty VALUE = new SimpleDoubleProperty(desiredValue.get());

        desiredValue.addListener((obs, oldValue, newValue)
                                         -> createDoubleTimelineAnimation(VALUE,
                                                                          oldValue.doubleValue(),
                                                                          newValue.doubleValue(),
                                                                          duration).play());

        return VALUE;
    }

    private static Timeline createDoubleTimelineAnimation(DoubleProperty value,
                                                          double startValue,
                                                          double endValue,
                                                          Duration duration) {
        return new Timeline(new KeyFrame(Duration.ZERO,
                                         new KeyValue(value,
                                                      startValue,
                                                      Interpolator.EASE_BOTH)),
                            new KeyFrame(duration,
                                         new KeyValue(value,
                                                      endValue,
                                                      Interpolator.EASE_BOTH)));
    }
}
