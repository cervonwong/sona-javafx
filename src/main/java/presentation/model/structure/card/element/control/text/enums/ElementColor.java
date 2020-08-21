package main.java.presentation.model.structure.card.element.control.text.enums;

import javafx.scene.paint.Color;

@SuppressWarnings("SpellCheckingInspection")
public enum ElementColor {
    DEFAULT("#000000", "#FFFFFF"),
    RED("#D32F2F", "#EF9A9A"),
    PINK("#C2185B", "#F48FB1"),
    PURPLE("#7B1FA2", "#CE93D8"),
    DEEP_PURPLE("#512DA8", "#B39DDB"),
    INDIGO("#303F9F", "#9FA8DA"),
    BLUE("#1976D2", "#90CAF9"),
    LIGHT_BLUE("#0288D1", "#81D4FA"),
    CYAN("#0097A7", "#80DEEA"),
    TEAL("#00796B", "#80CBC4"),
    GREEN("#388E3C", "#A5D6A7"),
    LIGHT_GREEN("#689F38", "#C5E1A5"),
    LIME("#AFB42B", "#E6EE9C"),
    YELLOW("#FBC02D", "#FFF59D"),
    AMBER("#FFA000", "#FFE082"),
    ORANGE("#F57C00", "#FFCC80"),
    DEEP_ORANGE("#E64A19", "#FFAB91"),
    BROWN("#5D4037", "#BCAAA4"),
    GRAY("#616161", "#BDBDBD"),
    BLUE_GRAY("#455A64", "#B0BEC5");


    // INSTANCE VARIABLES

    private final Color ON_LIGHT_COLOR;

    private final Color ON_DARK_COLOR;


    // CONSTRUCTOR

    ElementColor(String onLightColorHex, String onDarkColorHex) {
        this.ON_LIGHT_COLOR = Color.web(onLightColorHex);
        this.ON_DARK_COLOR = Color.web(onDarkColorHex);
    }


    // ACCESSORS

    public Color getOnLightColor() {
        return ON_LIGHT_COLOR;
    }

    public Color getOnDarkColor() {
        return ON_DARK_COLOR;
    }


    // STATIC METHODS

    public static ElementColor toElementColor(String elementColorString) {
        switch (elementColorString) {
            case "DEFAULT":
                return DEFAULT;
            case "RED":
                return RED;
            case "PINK":
                return PINK;
            case "PURPLE":
                return PURPLE;
            case "DEEP_PURPLE":
                return DEEP_PURPLE;
            case "INDIGO":
                return INDIGO;
            case "BLUE":
                return BLUE;
            case "LIGHT_BLUE":
                return LIGHT_BLUE;
            case "CYAN":
                return CYAN;
            case "TEAL":
                return TEAL;
            case "GREEN":
                return GREEN;
            case "LIGHT_GREEN":
                return LIGHT_GREEN;
            case "LIME":
                return LIME;
            case "YELLOW":
                return YELLOW;
            case "AMBER":
                return AMBER;
            case "ORANGE":
                return ORANGE;
            case "DEEP_ORANGE":
                return DEEP_ORANGE;
            case "BROWN":
                return BROWN;
            case "GRAY":
                return GRAY;
            case "BLUE_GRAY":
                return BLUE_GRAY;
            default:
                throw new IllegalArgumentException("Illegal elementColorString: "
                                                   + elementColorString);
        }
    }
}
