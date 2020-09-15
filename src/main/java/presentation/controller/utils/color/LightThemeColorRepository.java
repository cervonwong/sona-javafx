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

public class LightThemeColorRepository implements ThemeColorRepository {

    // [SHARED] BASELINE COLORS

    private final Color BRAND_COLOR = BaseColorRepository.ORANGE_800;

    private final Color BACKGROUND_COLOR = BaseColorRepository.LIGHT_BACKGROUND_COLOR;
    private final Color SURFACE_COLOR = Color.WHITE;
    private final Color ERROR_COLOR = null;

    // [SHARED] TEXT COLORS

    private final Color HIGH_EMPHASIS_HEADER_TEXT_COLOR =
            BaseColorRepository.DARKEST_TEXT_COLOR;
    private final Color HIGH_EMPHASIS_NON_HEADER_TEXT_COLOR =
            BaseColorRepository.DARKER_TEXT_COLOR;
    private final Color MEDIUM_EMPHASIS_TEXT_COLOR =
            BaseColorRepository.DARKEST_TEXT_COLOR.deriveColor(0, 1, 1, 0.6);
    private final Color LOW_EMPHASIS_TEXT_COLOR =
            BaseColorRepository.DARKEST_TEXT_COLOR.deriveColor(0, 1, 1, 0.38);


    // [SHARED] BUTTON COLORS

    private final Color TEXT_BUTTON_HOVER_RIPPLE_COLOR = null;
    private final Color TEXT_BUTTON_PRESSED_RIPPLE_COLOR = null;

    private final Color BRAND_CONTAINED_BUTTON_ENABLED_FILL_COLOR =
            BRAND_COLOR;
    private final Color BRAND_CONTAINED_BUTTON_HOVER_FILL_COLOR =
            BRAND_COLOR.deriveColor(0, 1, 1.3, 1);
    private final Color BRAND_CONTAINED_BUTTON_PRESSED_FILL_COLOR =
            BRAND_COLOR.deriveColor(0, 1, 1.7, 1);


    // [SPECIFIC] NAVIGATION RAIL COLORS

    private final Color NAVIGATION_RAIL_BASE_COLOR = BaseColorRepository.BLUE_800;
    private final Color NAVIGATION_RAIL_INACTIVE_TEXT_COLOR = BaseColorRepository.BLUE_100;
    private final Color NAVIGATION_RAIL_ACTIVATED_TEXT_COLOR = BRAND_COLOR;
}
