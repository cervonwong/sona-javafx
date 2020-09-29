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

public interface ThemeColorRepository {

    // [SHARED] BASELINE COLORS

    Color getBrandColor();

    Color getBackgroundColor();

    Color getSurfaceColor();

    Color getErrorColor();


    // [SHARED] TEXT COLORS

    Color getHighestEmphasisTextColor();

    Color getHighEmphasisTextColor();

    Color getMediumEmphasisTextColor();

    Color getLowEmphasisTextColor();


    // [SHARED] BUTTON COLORS

    Color getEnabledTextButtonBackgroundColor();

    Color getHoverTextButtonBackgroundColor();

    Color getPressedTextButtonBackgroundColor();

    Color getEnabledContainedButtonFillColor();

    Color getHoverContainedButtonFillColor();

    Color getPressedContainedButtonFillColor();

    Color getOnContainedButtonTextColor();

    Color getHoverIconButtonTextColor();


    // [SPECIFIC] NAVIGATION RAIL COLORS

    Color getNavigationRailBaseColor();

    Color getNavigationRailInactiveTextColor();

    Color getNavigationRailActivatedTextColor();


    // [SPECIFIC] DECKS VIEW CARD COLORS

    Color getDecksViewCardHoverAccentColor();


    // [SPECIFIC] DIVIDER

    Color getDividerColor();


    // [SPECIFIC] TAB BAR

    Color getEnabledTabItemBackgroundColor();

    Color getHoverTabItemBackgroundColor();

    Color getPressedTabItemBackgroundColor();

    Color getActivatedTabItemTextColor();

    Color getTabBarIndicatorColor();


    // [SPECIFIC] TEXT FIELD

    Color getEnabledTextFieldAccentColor();

    Color getHoverTextFieldAccentColor();
}
