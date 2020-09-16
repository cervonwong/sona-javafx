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

    private static final ObjectProperty<Color> highEmphasisHeaderTextColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> highEmphasisNonHeaderTextColor =
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

    private static final ObjectProperty<Color> enabledBrandContainedButtonFillColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> hoverBrandContainedButtonFillColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> pressedBrandContainedButtonFillColor =
            new SimpleObjectProperty<>();


    // JAVAFX PROPERTIES ([SPECIFIC] NAVIGATION RAIL COLORS)

    private static final ObjectProperty<Color> navigationRailBaseColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> navigationRailInactiveTextColor =
            new SimpleObjectProperty<>();

    private static final ObjectProperty<Color> navigationRailActivatedTextColor =
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

        highEmphasisHeaderTextColor.set(repository.getHighEmphasisHeaderTextColor());
        highEmphasisNonHeaderTextColor.set(repository.getHighEmphasisNonHeaderTextColor());
        mediumEmphasisTextColor.set(repository.getMediumEmphasisTextColor());
        lowEmphasisTextColor.set(repository.getLowEmphasisTextColor());

        enabledTextButtonBackgroundColor.set(repository.getEnabledTextButtonBackgroundColor());
        hoverTextButtonBackgroundColor.set(repository.getHoverTextButtonBackgroundColor());
        pressedTextButtonBackgroundColor.set(repository.getPressedTextButtonBackgroundColor());
        enabledBrandContainedButtonFillColor.set(repository.getEnabledBrandContainedButtonFillColor());
        hoverBrandContainedButtonFillColor.set(repository.getHoverBrandContainedButtonFillColor());
        pressedBrandContainedButtonFillColor.set(repository.getPressedBrandContainedButtonFillColor());

        navigationRailBaseColor.set(repository.getNavigationRailBaseColor());
        navigationRailInactiveTextColor.set(repository.getNavigationRailInactiveTextColor());
        navigationRailActivatedTextColor.set(repository.getNavigationRailActivatedTextColor());
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

    public static Color getHighEmphasisHeaderTextColor() {
        return highEmphasisHeaderTextColor.get();
    }

    public static Color getHighEmphasisNonHeaderTextColor() {
        return highEmphasisNonHeaderTextColor.get();
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

    public static Color getEnabledBrandContainedButtonFillColor() {
        return enabledBrandContainedButtonFillColor.get();
    }

    public static Color getHoverBrandContainedButtonFillColor() {
        return hoverBrandContainedButtonFillColor.get();
    }

    public static Color getPressedBrandContainedButtonFillColor() {
        return pressedBrandContainedButtonFillColor.get();
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

    public static ObjectProperty<Color> highEmphasisHeaderTextColorProperty() {
        return highEmphasisHeaderTextColor;
    }

    public static ObjectProperty<Color> highEmphasisNonHeaderTextColorProperty() {
        return highEmphasisNonHeaderTextColor;
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

    public static ObjectProperty<Color> enabledBrandContainedButtonFillColorProperty() {
        return enabledBrandContainedButtonFillColor;
    }

    public static ObjectProperty<Color> hoverBrandContainedButtonFillColorProperty() {
        return hoverBrandContainedButtonFillColor;
    }

    public static ObjectProperty<Color> pressedBrandContainedButtonFillColorProperty() {
        return pressedBrandContainedButtonFillColor;
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


    // MUTATORS

    public static void setTheme(Theme t) {
        theme.set(t);
    }
}
