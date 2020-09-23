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

public class DarkThemeColorRepository implements ThemeColorRepository {

    // ============================================================================================
    // CONSTANTS
    // ============================================================================================

    @Override
    public Color getBrandColor() {
        return Color.PURPLE;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.PURPLE;
    }

    @Override
    public Color getSurfaceColor() {
        return Color.PURPLE;
    }

    @Override
    public Color getErrorColor() {
        return Color.PURPLE;
    }

    @Override
    public Color getHighestEmphasisTextColor() {
        return Color.PURPLE;
    }

    @Override
    public Color getHighEmphasisTextColor() {
        return Color.PURPLE;
    }

    @Override
    public Color getMediumEmphasisTextColor() {
        return Color.PURPLE;
    }

    @Override
    public Color getLowEmphasisTextColor() {
        return Color.PURPLE;
    }

    @Override
    public Color getEnabledTextButtonBackgroundColor() {
        return Color.PURPLE;
    }

    @Override
    public Color getHoverTextButtonBackgroundColor() {
        return Color.PURPLE;
    }

    @Override
    public Color getPressedTextButtonBackgroundColor() {
        return Color.PURPLE;
    }

    @Override
    public Color getEnabledContainedButtonFillColor() {
        return Color.PURPLE;
    }

    @Override
    public Color getHoverContainedButtonFillColor() {
        return Color.PURPLE;
    }

    @Override
    public Color getPressedContainedButtonFillColor() {
        return Color.PURPLE;
    }

    @Override
    public Color getOnContainedButtonTextColor() {
        return Color.WHITE;
    }

    @Override
    public Color getHoverIconButtonTextFillColor() {
        return Color.RED;
    }

    @Override
    public Color getNavigationRailBaseColor() {
        return Color.PURPLE;
    }

    @Override
    public Color getNavigationRailInactiveTextColor() {
        return Color.YELLOW;
    }

    @Override
    public Color getNavigationRailActivatedTextColor() {
        return Color.GREEN;
    }

    @Override
    public Color getDecksViewCardHoverAccentColor() {
        return Color.BLUE;
    }

    @Override
    public Color getDividerColor() {
        return Color.ORCHID;
    }

    @Override
    public Color getEnabledTabItemBackgroundColor() {
        return Color.LIGHTGOLDENRODYELLOW;
    }

    @Override
    public Color getHoverTabItemBackgroundColor() {
        return Color.MEDIUMPURPLE;
    }

    @Override
    public Color getPressedTabItemBackgroundColor() {
        return Color.PALEVIOLETRED;
    }

    @Override
    public Color getActivatedTabItemTextColor() {
        return Color.DARKORANGE;
    }

    @Override
    public Color getTabBarIndicatorColor() {
        return Color.CADETBLUE;
    }
}
