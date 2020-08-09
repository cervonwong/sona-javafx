package main.java.model.structure.card.elements.enums.text;

public enum FontWeight {
    LIGHT,
    NORMAL,
    SEMI_BOLD,
    BLACK;

    public static FontWeight toFontWeight(String fontWeightString) {
        switch (fontWeightString) {
            case "LIGHT":
                return FontWeight.LIGHT;
            case "NORMAL":
                return FontWeight.NORMAL;
            case "SEMI_BOLD":
                return FontWeight.SEMI_BOLD;
            case "BLACK":
                return FontWeight.BLACK;
            default:
                throw new IllegalArgumentException("Illegal fontWeightString: " + fontWeightString);
        }
    }
}
