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

    // ============================================================================================
    // CONSTANTS
    // ============================================================================================

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

    private final Color ENABLED_TEXT_BUTTON_BACKGROUND_COLOR =
            Color.TRANSPARENT;
    private final Color HOVER_TEXT_BUTTON_BACKGROUND_COLOR =
            Color.WHITE.deriveColor(0, 1, 1, 0.06);
    private final Color PRESSED_TEXT_BUTTON_BACKGROUND_COLOR =
            Color.WHITE.deriveColor(0, 1, 1, 0.18);

    private final Color ENABLED_BRAND_CONTAINED_BUTTON_FILL_COLOR =
            BRAND_COLOR;
    private final Color HOVER_BRAND_CONTAINED_BUTTON_FILL_COLOR =
            BRAND_COLOR.deriveColor(0, 1, 1.3, 1);
    private final Color PRESSED_BRAND_CONTAINED_BUTTON_FILL_COLOR =
            BRAND_COLOR.deriveColor(0, 1, 1.7, 1);

    private final Color HOVER_BACK_BUTTON_TEXT_FILL_COLOR =
            BRAND_COLOR;


    // [SPECIFIC] NAVIGATION RAIL COLORS

    private final Color NAVIGATION_RAIL_BASE_COLOR = BaseColorRepository.BLUE_800;
    private final Color NAVIGATION_RAIL_INACTIVE_TEXT_COLOR = BaseColorRepository.BLUE_100;
    private final Color NAVIGATION_RAIL_ACTIVATED_TEXT_COLOR = BRAND_COLOR;


    // [SPECIFIC] DECKS VIEW CARD

    private final Color DECKS_VIEW_CARD_HOVER_ACCENT_COLOR = BRAND_COLOR;

    // ============================================================================================
    // ACCESSORS
    // ============================================================================================

    // [SHARED] BASELINE COLORS

    @Override
    public Color getBrandColor() {
        return BRAND_COLOR;
    }

    @Override
    public Color getBackgroundColor() {
        return BACKGROUND_COLOR;
    }

    @Override
    public Color getSurfaceColor() {
        return SURFACE_COLOR;
    }

    @Override
    public Color getErrorColor() {
        return ERROR_COLOR;
    }


    // [SHARED] TEXT COLORS

    @Override
    public Color getHighEmphasisHeaderTextColor() {
        return HIGH_EMPHASIS_HEADER_TEXT_COLOR;
    }

    @Override
    public Color getHighEmphasisNonHeaderTextColor() {
        return HIGH_EMPHASIS_NON_HEADER_TEXT_COLOR;
    }

    @Override
    public Color getMediumEmphasisTextColor() {
        return MEDIUM_EMPHASIS_TEXT_COLOR;
    }

    @Override
    public Color getLowEmphasisTextColor() {
        return LOW_EMPHASIS_TEXT_COLOR;
    }


    // [SHARED] BUTTON COLORS

    @Override
    public Color getEnabledTextButtonBackgroundColor() {
        return ENABLED_TEXT_BUTTON_BACKGROUND_COLOR;
    }

    @Override
    public Color getHoverTextButtonBackgroundColor() {
        return HOVER_TEXT_BUTTON_BACKGROUND_COLOR;
    }

    @Override
    public Color getPressedTextButtonBackgroundColor() {
        return PRESSED_TEXT_BUTTON_BACKGROUND_COLOR;
    }

    @Override
    public Color getEnabledBrandContainedButtonFillColor() {
        return ENABLED_BRAND_CONTAINED_BUTTON_FILL_COLOR;
    }

    @Override
    public Color getHoverBrandContainedButtonFillColor() {
        return HOVER_BRAND_CONTAINED_BUTTON_FILL_COLOR;
    }

    @Override
    public Color getPressedBrandContainedButtonFillColor() {
        return PRESSED_BRAND_CONTAINED_BUTTON_FILL_COLOR;
    }

    @Override
    public Color getHoverBackButtonTextFillColor() {
        return HOVER_BACK_BUTTON_TEXT_FILL_COLOR;
    }


    // [SPECIFIC] NAVIGATION RAIL COLORS

    @Override
    public Color getNavigationRailBaseColor() {
        return NAVIGATION_RAIL_BASE_COLOR;
    }

    @Override
    public Color getNavigationRailInactiveTextColor() {
        return NAVIGATION_RAIL_INACTIVE_TEXT_COLOR;
    }

    @Override
    public Color getNavigationRailActivatedTextColor() {
        return NAVIGATION_RAIL_ACTIVATED_TEXT_COLOR;
    }


    // [SPECIFIC] DECKS VIEW CARD COLORS

    @Override
    public Color getDecksViewCardHoverAccentColor() {
        return DECKS_VIEW_CARD_HOVER_ACCENT_COLOR;
    }
}
