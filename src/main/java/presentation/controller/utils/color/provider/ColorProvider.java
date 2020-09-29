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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import main.java.presentation.controller.enums.Theme;

public class ColorProvider {

    // INSTANCE VARIABLES (THEME)

    private static final ObjectProperty<Theme> theme = new SimpleObjectProperty<>();

    private static final ObjectProperty<ThemeColorRepository> themeColorRepository =
            new SimpleObjectProperty<>();


    // JAVAFX PROPERTIES ([SHARED] BASELINE COLORS)

    private static final ObjectProperty<Color> brandColor = new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> backgroundColor = new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> surfaceColor = new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> errorColor = new SimpleObjectProperty<>();


    // JAVAFX PROPERTIES ([SHARED] TEXT COLORS)

    private static final ObjectProperty<Color> highestEmphasisTextColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> highEmphasisTextColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> mediumEmphasisTextColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> lowEmphasisTextColor = new SimpleObjectProperty<>();


    // JAVAFX PROPERTIES ([SHARED] BUTTON COLORS)

    private static final ObjectProperty<Color> enabledTextButtonBackgroundColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> hoverTextButtonBackgroundColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> pressedTextButtonBackgroundColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> enabledContainedButtonFillColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> hoverContainedButtonFillColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> pressedContainedButtonFillColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> onContainedButtonTextColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> hoverIconButtonTextColor =
            new SimpleObjectProperty<>();


    // JAVAFX PROPERTIES ([SPECIFIC] NAVIGATION RAIL COLORS)

    private static final ObjectProperty<Color> navigationRailBaseColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> navigationRailInactiveTextColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> navigationRailActivatedTextColor =
            new SimpleObjectProperty<>();


    // JAVAFX PROPERTIES ([SPECIFIC] DECKS VIEW CARD COLORS)

    private static final ObjectProperty<Color> decksViewCardHoverAccentColor =
            new SimpleObjectProperty<>();


    // JAVAFX PROPERTIES ([SPECIFIC] DIVIDER)

    private static final ObjectProperty<Color> dividerColor = new SimpleObjectProperty<>();


    // JAVAFX PROPERTIES ([SPECIFIC] TAB BAR)

    private static final ObjectProperty<Color> enabledTabItemBackgroundColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> hoverTabItemBackgroundColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> pressedTabItemBackgroundColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> activatedTabItemTextColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> tabBarIndicatorColor = new SimpleObjectProperty<>();


    // JAVAFX PROPERTIES ([SPECIFIC] TEXT FIELD)

    private static final ObjectProperty<Color> enabledTextFieldAccentColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> hoverTextFieldAccentColor =
            new SimpleObjectProperty<>();


    // INITIALIZATION

    static {
        theme.set(Theme.LIGHT);

        initializeThemeListener();
        initializeThemeColorRepositoryListener();
    }

    private static void initializeThemeListener() {
        theme.addListener((obs, oldTheme, newTheme) -> setThemeColorRepository(newTheme));

        setThemeColorRepository(theme.get());
    }

    private static void setThemeColorRepository(Theme theme) {
        switch (theme) {
            case LIGHT:
                themeColorRepository.set(new LightThemeColorRepository());
                break;
            case DARK:
                themeColorRepository.set(new DarkThemeColorRepository());
                break;
        }
    }

    private static void initializeThemeColorRepositoryListener() {
        themeColorRepository.addListener((obs, oldRepo, newRepo) -> setColors(newRepo));

        setColors(themeColorRepository.get());
    }

    private static void setColors(ThemeColorRepository repository) {
        brandColor.set(repository.getBrandColor());
        backgroundColor.set(repository.getBackgroundColor());
        surfaceColor.set(repository.getSurfaceColor());
        errorColor.set(repository.getErrorColor());

        highestEmphasisTextColor.set(repository.getHighestEmphasisTextColor());
        highEmphasisTextColor.set(repository.getHighEmphasisTextColor());
        mediumEmphasisTextColor.set(repository.getMediumEmphasisTextColor());
        lowEmphasisTextColor.set(repository.getLowEmphasisTextColor());

        enabledTextButtonBackgroundColor.set(repository.getEnabledTextButtonBackgroundColor());
        hoverTextButtonBackgroundColor.set(repository.getHoverTextButtonBackgroundColor());
        pressedTextButtonBackgroundColor.set(repository.getPressedTextButtonBackgroundColor());
        enabledContainedButtonFillColor.set(repository.getEnabledContainedButtonFillColor());
        hoverContainedButtonFillColor.set(repository.getHoverContainedButtonFillColor());
        pressedContainedButtonFillColor.set(repository.getPressedContainedButtonFillColor());
        onContainedButtonTextColor.set(repository.getOnContainedButtonTextColor());
        hoverIconButtonTextColor.set(repository.getHoverIconButtonTextColor());

        navigationRailBaseColor.set(repository.getNavigationRailBaseColor());
        navigationRailInactiveTextColor.set(repository.getNavigationRailInactiveTextColor());
        navigationRailActivatedTextColor.set(repository.getNavigationRailActivatedTextColor());

        decksViewCardHoverAccentColor.set(repository.getDecksViewCardHoverAccentColor());

        dividerColor.set(repository.getDividerColor());

        enabledTabItemBackgroundColor.set(repository.getEnabledTabItemBackgroundColor());
        hoverTabItemBackgroundColor.set(repository.getHoverTabItemBackgroundColor());
        pressedTabItemBackgroundColor.set(repository.getPressedTabItemBackgroundColor());
        activatedTabItemTextColor.set(repository.getActivatedTabItemTextColor());
        tabBarIndicatorColor.set(repository.getTabBarIndicatorColor());

        enabledTextFieldAccentColor.set(repository.getEnabledTextFieldAccentColor());
        hoverTextFieldAccentColor.set(repository.getHoverTextFieldAccentColor());
    }


    // VALUE ACCESSORS

    public static Theme getTheme() {
        return theme.get();
    }


    // VALUE ACCESSORS (Color Properties)

    public static Color getBrandColor() {
        return brandColor.get();
    }

    public static Color getBackgroundColor() {
        return backgroundColor.get();
    }

    public static Color getSurfaceColor() {
        return surfaceColor.get();
    }

    public static Color getErrorColor() {
        return errorColor.get();
    }

    public static Color getHighestEmphasisTextColor() {
        return highestEmphasisTextColor.get();
    }

    public static Color getHighEmphasisTextColor() {
        return highEmphasisTextColor.get();
    }

    public static Color getMediumEmphasisTextColor() {
        return mediumEmphasisTextColor.get();
    }

    public static Color getLowEmphasisTextColor() {
        return lowEmphasisTextColor.get();
    }

    public static Color getEnabledTextButtonBackgroundColor() {
        return enabledTextButtonBackgroundColor.get();
    }

    public static Color getHoverTextButtonBackgroundColor() {
        return hoverTextButtonBackgroundColor.get();
    }

    public static Color getPressedTextButtonBackgroundColor() {
        return pressedTextButtonBackgroundColor.get();
    }

    public static Color getEnabledContainedButtonFillColor() {
        return enabledContainedButtonFillColor.get();
    }

    public static Color getHoverContainedButtonFillColor() {
        return hoverContainedButtonFillColor.get();
    }

    public static Color getPressedContainedButtonFillColor() {
        return pressedContainedButtonFillColor.get();
    }

    public static Color getOnContainedButtonTextColor() {
        return onContainedButtonTextColor.get();
    }

    public static Color getHoverIconButtonTextColor() {
        return hoverIconButtonTextColor.get();
    }

    public static Color getNavigationRailBaseColor() {
        return navigationRailBaseColor.get();
    }

    public static Color getNavigationRailInactiveTextColor() {
        return navigationRailInactiveTextColor.get();
    }

    public static Color getNavigationRailActivatedTextColor() {
        return navigationRailActivatedTextColor.get();
    }

    public static Color getDecksViewCardHoverAccentColor() {
        return decksViewCardHoverAccentColor.get();
    }

    public static Color getDividerColor() {
        return dividerColor.get();
    }

    public static Color getEnabledTabItemBackgroundColor() {
        return enabledTabItemBackgroundColor.get();
    }

    public static Color getHoverTabItemBackgroundColor() {
        return hoverTabItemBackgroundColor.get();
    }

    public static Color getPressedTabItemBackgroundColor() {
        return pressedTabItemBackgroundColor.get();
    }

    public static Color getActivatedTabItemTextColor() {
        return activatedTabItemTextColor.get();
    }

    public static Color getTabBarIndicatorColor() {
        return tabBarIndicatorColor.get();
    }

    public static Color getEnabledTextFieldAccentColor() {
        return enabledTextFieldAccentColor.get();
    }

    public static Color getHoverTextFieldAccentColor() {
        return hoverTextFieldAccentColor.get();
    }


    // PROPERTY ACCESSORS

    public static ObjectProperty<Theme> themeProperty() {
        return theme;
    }


    // PROPERTY ACCESSORS (Color Properties)

    public static ObjectProperty<Color> brandColorProperty() {
        return brandColor;
    }

    public static ObjectProperty<Color> backgroundColorProperty() {
        return backgroundColor;
    }

    public static ObjectProperty<Color> surfaceColorProperty() {
        return surfaceColor;
    }

    public static ObjectProperty<Color> errorColorProperty() {
        return errorColor;
    }

    public static ObjectProperty<Color> highestEmphasisTextColorProperty() {
        return highestEmphasisTextColor;
    }

    public static ObjectProperty<Color> highEmphasisTextColorProperty() {
        return highEmphasisTextColor;
    }

    public static ObjectProperty<Color> mediumEmphasisTextColorProperty() {
        return mediumEmphasisTextColor;
    }

    public static ObjectProperty<Color> lowEmphasisTextColorProperty() {
        return lowEmphasisTextColor;
    }

    public static ObjectProperty<Color> enabledTextButtonBackgroundColorProperty() {
        return enabledTextButtonBackgroundColor;
    }

    public static ObjectProperty<Color> hoverTextButtonBackgroundColorProperty() {
        return hoverTextButtonBackgroundColor;
    }

    public static ObjectProperty<Color> pressedTextButtonBackgroundColorProperty() {
        return pressedTextButtonBackgroundColor;
    }

    public static ObjectProperty<Color> enabledContainedButtonFillColorProperty() {
        return enabledContainedButtonFillColor;
    }

    public static ObjectProperty<Color> hoverContainedButtonFillColorProperty() {
        return hoverContainedButtonFillColor;
    }

    public static ObjectProperty<Color> pressedContainedButtonFillColorProperty() {
        return pressedContainedButtonFillColor;
    }

    public static ObjectProperty<Color> onContainedButtonTextColorProperty() {
        return onContainedButtonTextColor;
    }

    public static ObjectProperty<Color> hoverIconButtonTextColorProperty() {
        return hoverIconButtonTextColor;
    }

    public static ObjectProperty<Color> navigationRailBaseColorProperty() {
        return navigationRailBaseColor;
    }

    public static ObjectProperty<Color> navigationRailInactiveTextColorProperty() {
        return navigationRailInactiveTextColor;
    }

    public static ObjectProperty<Color> navigationRailActivatedTextColorProperty() {
        return navigationRailActivatedTextColor;
    }

    public static ObjectProperty<Color> decksViewCardHoverAccentColorProperty() {
        return decksViewCardHoverAccentColor;
    }

    public static ObjectProperty<Color> dividerColorProperty() {
        return dividerColor;
    }

    public static ObjectProperty<Color> enabledTabItemBackgroundColorProperty() {
        return enabledTabItemBackgroundColor;
    }

    public static ObjectProperty<Color> hoverTabItemBackgroundColorProperty() {
        return hoverTabItemBackgroundColor;
    }

    public static ObjectProperty<Color> pressedTabItemBackgroundColorProperty() {
        return pressedTabItemBackgroundColor;
    }

    public static ObjectProperty<Color> activatedTabItemTextColorProperty() {
        return activatedTabItemTextColor;
    }

    public static ObjectProperty<Color> tabBarIndicatorColorProperty() {
        return tabBarIndicatorColor;
    }

    public static ObjectProperty<Color> enabledTextFieldAccentColorProperty() {
        return enabledTextFieldAccentColor;
    }

    public static ObjectProperty<Color> hoverTextFieldAccentColorProperty() {
        return hoverTextFieldAccentColor;
    }


    // MUTATORS

    public static void setTheme(Theme t) {
        theme.set(t);
    }
}
