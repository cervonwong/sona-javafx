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

package main.java.presentation.controller.utils.color.provider;


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

    private final Color HIGHEST_EMPHASIS_TEXT_COLOR =
            BaseColorRepository.DARKEST_TEXT_COLOR;
    private final Color HIGH_EMPHASIS_TEXT_COLOR =
            BaseColorRepository.DARKER_TEXT_COLOR;
    private final Color MEDIUM_EMPHASIS_TEXT_COLOR =
            BaseColorRepository.BLUE_900.deriveColor(0, 1, 1, 0.6);
    private final Color LOW_EMPHASIS_TEXT_COLOR =
            BaseColorRepository.BLUE_900.deriveColor(0, 1, 1, 0.38);


    // [SHARED] BUTTON COLORS

    private final Color ENABLED_TEXT_BUTTON_BACKGROUND_COLOR =
            Color.TRANSPARENT;
    private final Color HOVER_TEXT_BUTTON_BACKGROUND_COLOR =
            Color.WHITE.deriveColor(0, 1, 1, 0.06);
    private final Color PRESSED_TEXT_BUTTON_BACKGROUND_COLOR =
            Color.WHITE.deriveColor(0, 1, 1, 0.18);

    private final Color ENABLED_CONTAINED_BUTTON_FILL_COLOR =
            BRAND_COLOR;
    private final Color HOVER_CONTAINED_BUTTON_FILL_COLOR =
            BRAND_COLOR.interpolate(Color.WHITE, 0.12);
    private final Color PRESSED_CONTAINED_BUTTON_FILL_COLOR =
            BRAND_COLOR.interpolate(Color.WHITE, 0.24);
    private final Color ON_CONTAINED_BUTTON_TEXT_COLOR = BaseColorRepository.DARKEST_TEXT_COLOR;

    private final Color HOVER_ICON_BUTTON_TEXT_FILL_COLOR = BRAND_COLOR;


    // [SPECIFIC] NAVIGATION RAIL COLORS

    private final Color NAVIGATION_RAIL_BASE_COLOR = BaseColorRepository.BLUE_800;
    private final Color NAVIGATION_RAIL_INACTIVE_TEXT_COLOR = BaseColorRepository.BLUE_100;
    private final Color NAVIGATION_RAIL_ACTIVATED_TEXT_COLOR = BRAND_COLOR;


    // [SPECIFIC] DECKS VIEW CARD

    private final Color DECKS_VIEW_CARD_HOVER_ACCENT_COLOR = BRAND_COLOR;


    // [SPECIFIC] DIVIDER

    private final Color DIVIDER_COLOR = BaseColorRepository.BLUE_200;


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
    public Color getHighestEmphasisTextColor() {
        return HIGHEST_EMPHASIS_TEXT_COLOR;
    }

    @Override
    public Color getHighEmphasisTextColor() {
        return HIGH_EMPHASIS_TEXT_COLOR;
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
    public Color getEnabledContainedButtonFillColor() {
        return ENABLED_CONTAINED_BUTTON_FILL_COLOR;
    }

    @Override
    public Color getHoverContainedButtonFillColor() {
        return HOVER_CONTAINED_BUTTON_FILL_COLOR;
    }

    @Override
    public Color getPressedContainedButtonFillColor() {
        return PRESSED_CONTAINED_BUTTON_FILL_COLOR;
    }

    @Override
    public Color getOnContainedButtonTextColor() {
        return ON_CONTAINED_BUTTON_TEXT_COLOR;
    }

    @Override
    public Color getHoverIconButtonTextFillColor() {
        return HOVER_ICON_BUTTON_TEXT_FILL_COLOR;
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


    // [SPECIFIC] DIVIDER

    @Override
    public Color getDividerColor() {
        return DIVIDER_COLOR;
    }
}
