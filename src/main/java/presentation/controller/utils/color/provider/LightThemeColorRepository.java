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
            BaseColorRepository.BLUE_900.deriveColor(0, 1, 1, 0.75);
    private final Color LOW_EMPHASIS_TEXT_COLOR =
            BaseColorRepository.BLUE_900.deriveColor(0, 1, 1, 0.40);


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

    private final Color HOVER_ICON_BUTTON_TEXT_COLOR = BRAND_COLOR;


    // [SHARED] CONTROL COLORS

    private final Color ENABLED_CONTROL_BACKGROUND_COLOR = Color.TRANSPARENT;
    private final Color HOVER_CONTROL_BACKGROUND_COLOR = BRAND_COLOR.deriveColor(0, 1, 1, 0.1);
    private final Color PRESSED_CONTROL_BACKGROUND_COLOR = BRAND_COLOR.deriveColor(0, 1, 1, 0.3);


    // [SPECIFIC] NAVIGATION RAIL COLORS

    private final Color NAVIGATION_RAIL_BASE_COLOR = BaseColorRepository.BLUE_800;
    private final Color NAVIGATION_RAIL_INACTIVE_TEXT_COLOR = BaseColorRepository.BLUE_100;
    private final Color NAVIGATION_RAIL_ACTIVATED_TEXT_COLOR = BRAND_COLOR;


    // [SPECIFIC] DECKS VIEW CARD

    private final Color DECKS_VIEW_CARD_HOVER_ACCENT_COLOR = BRAND_COLOR;


    // [SPECIFIC] DIVIDER

    private final Color DIVIDER_COLOR = BaseColorRepository.BLUE_200;


    // [SPECIFIC] TAB BAR

    private final Color ACTIVATED_TAB_ITEM_TEXT_COLOR = BRAND_COLOR;

    private final Color TAB_BAR_INDICATOR_COLOR = BRAND_COLOR;


    // [SPECIFIC] TEXT FIELD

    private final Color ENABLED_TEXT_FIELD_ACCENT_COLOR = BaseColorRepository.BLUE_200;

    private final Color HOVER_TEXT_FIELD_ACCENT_COLOR = BRAND_COLOR;


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
    public Color getHoverIconButtonTextColor() {
        return HOVER_ICON_BUTTON_TEXT_COLOR;
    }


    // [SHARED] CONTROL COLORS

    @Override
    public Color getEnabledControlBackgroundColor() {
        return ENABLED_CONTROL_BACKGROUND_COLOR;
    }

    @Override
    public Color getHoverControlBackgroundColor() {
        return HOVER_CONTROL_BACKGROUND_COLOR;
    }

    @Override
    public Color getPressedControlBackgroundColor() {
        return PRESSED_CONTROL_BACKGROUND_COLOR;
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


    // [SPECIFIC] TAB BAR

    @Override
    public Color getActivatedTabItemTextColor() {
        return ACTIVATED_TAB_ITEM_TEXT_COLOR;
    }

    @Override
    public Color getTabBarIndicatorColor() {
        return TAB_BAR_INDICATOR_COLOR;
    }


    // [SPECIFIC] TEXT FIELD

    @Override
    public Color getEnabledTextFieldAccentColor() {
        return ENABLED_TEXT_FIELD_ACCENT_COLOR;
    }

    @Override
    public Color getHoverTextFieldAccentColor() {
        return HOVER_TEXT_FIELD_ACCENT_COLOR;
    }
}
