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

import javafx.scene.paint.Color;

public class ColorFxUtils {

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
}
