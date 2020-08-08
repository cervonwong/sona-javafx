package main.java.model.structure.card.elements.formats;

import main.java.model.structure.card.elements.enums.ElementColor;
import main.java.model.structure.card.elements.enums.FontFamily;
import main.java.model.structure.card.elements.enums.FontWeight;

public final class TextFormat {

    // INSTANCE VARIABLES

    private final FontFamily fontFamily;

    private final boolean hasSerif;

    private final FontWeight fontWeight;

    private final boolean isItalicized;

    private final boolean isUnderlined;

    private final boolean isCondensed;

    private final ElementColor elementColor;


    // DEFAULT VALUES

    private static final FontFamily DEFAULT_FONT_FAMILY = FontFamily.LATIN;

    private static final boolean DEFAULT_HAS_SERIF = false;

    private static final FontWeight DEFAULT_FONT_WEIGHT = FontWeight.NORMAL;

    private static final boolean DEFAULT_IS_ITALICISED = false;

    private static final boolean DEFAULT_IS_UNDERLINED = false;

    private static final boolean DEFAULT_IS_CONDENSED = false;

    private static final ElementColor DEFAULT_ELEMENT_COLOR = ElementColor.DEFAULT;


    // CONSTRUCTOR


    private TextFormat(FontFamily fontFamily,
                       boolean hasSerif,
                       FontWeight fontWeight,
                       boolean isItalicized,
                       boolean isUnderlined,
                       boolean isCondensed,
                       ElementColor elementColor) {
        validateArguments(fontFamily,
                          hasSerif,
                          fontWeight,
                          isItalicized,
                          isUnderlined,
                          isCondensed,
                          elementColor);

        this.fontFamily = fontFamily;
        this.hasSerif = hasSerif;
        this.fontWeight = fontWeight;
        this.isItalicized = isItalicized;
        this.isUnderlined = isUnderlined;
        this.isCondensed = isCondensed;
        this.elementColor = elementColor;
    }


    // VALIDATORS

    private void validateArguments(FontFamily fontFamily,
                                   boolean hasSerif,
                                   FontWeight fontWeight,
                                   boolean isItalicized,
                                   boolean isUnderlined,
                                   boolean isCondensed,
                                   ElementColor elementColor) {
        validateNonNull(fontFamily, fontWeight, elementColor);
        validateFont(fontFamily, hasSerif, fontWeight, isItalicized, isCondensed);
    }

    private void validateNonNull(FontFamily fontFamily,
                                 FontWeight fontWeight,
                                 ElementColor elementColor) {
        // Throws IAE instead of NPE because NPE should never be caught.
        if (fontFamily == null)
            throw new IllegalArgumentException("Illegal fontFamily (cannot be null)");
        if (fontWeight == null)
            throw new IllegalArgumentException("Illegal fontWeight (cannot be null)");
        if (elementColor == null)
            throw new IllegalArgumentException("Illegal elementColor (cannot be null)");
    }

    private void validateFont(FontFamily fontFamily,
                              boolean hasSerif,
                              FontWeight fontWeight,
                              boolean isItalicized,
                              boolean isCondensed) {
        if (isItalicized) if (!fontFamily.supportsItalicStyle())
            throw new IllegalArgumentException(String.format(
                    "Unsupported font (fontFamily %s does not support italic style)",
                    fontFamily.toString()
            ));

        if (isCondensed) if (!fontFamily.supportsCondensedStyle())
            throw new IllegalArgumentException(String.format(
                    "Unsupported font (fontFamily %s does not support condensed style)",
                    fontFamily.toString()
            ));

        if (!isSerifSupported(fontFamily, hasSerif))
            throw new IllegalArgumentException(String.format(
                    "Unsupported font (fontFamily %s does not support %s style)",
                    fontFamily.toString(),
                    hasSerif ? "serif" : "sans serif"
            ));

        if (!isFontWeightSupported(fontFamily, hasSerif, fontWeight))
            throw new IllegalArgumentException(String.format(
                    "Unsupported font (fontFamily %s does not support %s style with fontWeight %s)",
                    fontFamily.toString(),
                    hasSerif ? "serif" : "sans serif",
                    fontWeight.toString()
            ));
    }


    // IS SUPPORTED METHODS

    private boolean isSerifSupported(FontFamily fontFamily, boolean hasSerif) {
        return hasSerif ? fontFamily.supportsSerif() : fontFamily.supportsSansSerif();
    }

    private boolean isFontWeightSupported(FontFamily fontFamily,
                                          boolean hasSerif,
                                          FontWeight fontWeight) {
        return hasSerif
               ? fontFamily.supportsSerifLightWeight() && fontWeight == FontWeight.LIGHT
                 || fontFamily.supportsSerifNormalWeight() && fontWeight == FontWeight.NORMAL
                 || fontFamily.supportsSerifSemiBoldWeight() && fontWeight == FontWeight.SEMI_BOLD
                 || fontFamily.supportsSerifBlackWeight() && fontWeight == FontWeight.BLACK
               : fontFamily.supportsSansSerifLightWeight() && fontWeight == FontWeight.LIGHT
                 || fontFamily.supportsSansSerifNormalWeight() && fontWeight == FontWeight.NORMAL
                 || fontFamily.supportsSansSerifSemiBoldWeight()
                    && fontWeight == FontWeight.SEMI_BOLD
                 || fontFamily.supportsSansSerifBlackWeight() && fontWeight == FontWeight.BLACK;
    }


    // ACCESSORS

    public FontFamily getFontFamily() {
        return fontFamily;
    }

    public boolean isHasSerif() {
        return hasSerif;
    }

    public FontWeight getFontWeight() {
        return fontWeight;
    }

    public boolean isItalicized() {
        return isItalicized;
    }

    public boolean isUnderlined() {
        return isUnderlined;
    }

    public boolean isCondensed() {
        return isCondensed;
    }

    public ElementColor getElementColor() {
        return elementColor;
    }

    public static FontFamily getDefaultFontFamily() {
        return DEFAULT_FONT_FAMILY;
    }

    public static boolean isDefaultHasSerif() {
        return DEFAULT_HAS_SERIF;
    }

    public static FontWeight getDefaultFontWeight() {
        return DEFAULT_FONT_WEIGHT;
    }

    public static boolean isDefaultIsItalicised() {
        return DEFAULT_IS_ITALICISED;
    }

    public static boolean isDefaultIsUnderlined() {
        return DEFAULT_IS_UNDERLINED;
    }

    public static boolean isDefaultIsCondensed() {
        return DEFAULT_IS_CONDENSED;
    }

    public static ElementColor getDefaultElementColor() {
        return DEFAULT_ELEMENT_COLOR;
    }
}
