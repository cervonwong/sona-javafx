package main.java.model.structure.card.elements.formats;

import main.java.model.structure.card.elements.enums.*;

import java.util.HashSet;
import java.util.Objects;

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

    private static final boolean DEFAULT_IS_ITALICIZED = false;

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
        checkArguments(fontFamily,
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


    // CHECK METHODS

    private void checkArguments(FontFamily fontFamily,
                                boolean hasSerif,
                                FontWeight fontWeight,
                                boolean isItalicized,
                                boolean isUnderlined,
                                boolean isCondensed,
                                ElementColor elementColor) {
        checkNonNull(fontFamily, fontWeight, elementColor);
        checkFont(fontFamily, hasSerif, fontWeight, isItalicized, isCondensed);
    }

    private void checkNonNull(FontFamily fontFamily,
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

    private void checkFont(FontFamily fontFamily,
                           boolean hasSerif,
                           FontWeight fontWeight,
                           boolean isItalicized,
                           boolean isCondensed) {
        if (!isValidIsItalicized(fontFamily, isItalicized))
            throw new IllegalArgumentException(String.format(
                    "Unsupported font (fontFamily %s does not support italic style)",
                    fontFamily.toString()
            ));

        if (!isValidIsCondensed(fontFamily, isCondensed))
            throw new IllegalArgumentException(String.format(
                    "Unsupported font (fontFamily %s does not support condensed style)",
                    fontFamily.toString()
            ));

        if (!isValidHasSerif(fontFamily, hasSerif))
            throw new IllegalArgumentException(String.format(
                    "Unsupported font (fontFamily %s does not support %s style)",
                    fontFamily.toString(),
                    hasSerif ? "serif" : "sans serif"
            ));

        if (!isValidFontWeight(fontFamily, hasSerif, fontWeight))
            throw new IllegalArgumentException(String.format(
                    "Unsupported font (fontFamily %s does not support %s style with fontWeight %s)",
                    fontFamily.toString(),
                    hasSerif ? "serif" : "sans serif",
                    fontWeight.toString()
            ));
    }


    // VALIDATORS
    // (Public validators: Called in copy methods when instance variables have been initialised.)
    // (Private validators: Called in constructor when instance variables yet to be initialised.)

    public boolean isValidHasSerif(boolean hasSerif) {
        if (this.hasSerif == hasSerif) return true;

        return isValidHasSerif(fontFamily, hasSerif);
    }

    public boolean isValidFontWeight(FontWeight fontWeight) {
        if (fontWeight == this.fontWeight) return true;

        return isValidFontWeight(fontFamily, hasSerif, fontWeight);
    }

    public boolean isValidIsItalicized(boolean isItalicized) {
        if (isItalicized == this.isItalicized) return true;

        return isValidIsItalicized(fontFamily, isItalicized);
    }

    public boolean isValidIsCondensed(boolean isCondensed) {
        if (isCondensed == this.isCondensed) return true;

        return isValidIsCondensed(fontFamily, isCondensed);
    }

    private boolean isValidHasSerif(FontFamily fontFamily, boolean hasSerif) {
        return hasSerif ? fontFamily.supportsSerif() : fontFamily.supportsSansSerif();
    }

    private boolean isValidFontWeight(FontFamily fontFamily,
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

    private boolean isValidIsItalicized(FontFamily fontFamily, boolean isItalicized) {
        return !isItalicized || fontFamily.supportsItalicStyle();
    }

    private boolean isValidIsCondensed(FontFamily fontFamily, boolean isCondensed) {
        return !isCondensed || fontFamily.supportsCondensedStyle();
    }


    // ACCESSORS (Instance Variables)

    public FontFamily getFontFamily() {
        return fontFamily;
    }

    public boolean hasSerif() {
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


    // ACCESSORS (Default Values)

    public static FontFamily getDefaultFontFamily() {
        return DEFAULT_FONT_FAMILY;
    }

    public static boolean hasSerifByDefault() {
        return DEFAULT_HAS_SERIF;
    }

    public static FontWeight getDefaultFontWeight() {
        return DEFAULT_FONT_WEIGHT;
    }

    public static boolean isItalicizedByDefault() {
        return DEFAULT_IS_ITALICIZED;
    }

    public static boolean isUnderlinedByDefault() {
        return DEFAULT_IS_UNDERLINED;
    }

    public static boolean isCondensedByDefault() {
        return DEFAULT_IS_CONDENSED;
    }

    public static ElementColor getDefaultElementColor() {
        return DEFAULT_ELEMENT_COLOR;
    }


    // COPY METHODS

    public TextFormat withFontFamily(FontFamily newFontFamily) {
        if (newFontFamily == null)
            throw new IllegalArgumentException("Illegal newFontFamily (cannot be null)");

        if (newFontFamily == fontFamily) return this;

        final boolean NEW_HAS_SERIF = newHasSerif(newFontFamily);
        final FontWeight NEW_FONT_WEIGHT = newFontWeight(newFontFamily, NEW_HAS_SERIF);
        final boolean NEW_IS_ITALICIZED = newIsItalicized(newFontFamily);
        final boolean NEW_IS_CONDENSED = newIsCondensed(newFontFamily);

        final var BUILDER = new TextFormatBuilder();
        return BUILDER.fontFamily(newFontFamily)
                      .hasSerif(NEW_HAS_SERIF)
                      .fontWeight(NEW_FONT_WEIGHT)
                      .isItalicized(NEW_IS_ITALICIZED)
                      .isUnderlined(isUnderlined)
                      .isCondensed(NEW_IS_CONDENSED)
                      .elementColor(elementColor)
                      .build();
    }

    public TextFormat withHasSerif(boolean newHasSerif) {
        if (newHasSerif == hasSerif) return this;

        if (!isValidHasSerif(newHasSerif))
            throw new IllegalArgumentException(String.format(
                    "Unsupported font (fontFamily %s does not support %s style)",
                    fontFamily.toString(),
                    newHasSerif ? "serif" : "sans serif"
            ));

        final FontWeight NEW_FONT_WEIGHT = newFontWeight(fontFamily, newHasSerif);

        final var BUILDER = new TextFormatBuilder();
        return BUILDER.fontFamily(fontFamily)
                      .hasSerif(hasSerif)
                      .fontWeight(NEW_FONT_WEIGHT)
                      .isItalicized(isItalicized)
                      .isUnderlined(isUnderlined)
                      .isCondensed(isCondensed)
                      .elementColor(elementColor)
                      .build();
    }

    public TextFormat withFontWeight(FontWeight newFontWeight) {
        if (newFontWeight == null)
            throw new IllegalArgumentException("Illegal newFontWeight (cannot be null)");

        if (newFontWeight == fontWeight) return this;

        if (!isValidFontWeight(newFontWeight))
            throw new IllegalArgumentException(String.format(
                    "Unsupported font (fontFamily %s does not support %s style with fontWeight %s)",
                    fontFamily.toString(),
                    hasSerif ? "serif" : "sans serif",
                    newFontWeight.toString()
            ));

        final var BUILDER = new TextFormatBuilder();
        return BUILDER.fontFamily(fontFamily)
                      .hasSerif(hasSerif)
                      .fontWeight(newFontWeight)
                      .isItalicized(isItalicized)
                      .isUnderlined(isUnderlined)
                      .isCondensed(isCondensed)
                      .elementColor(elementColor)
                      .build();
    }

    public TextFormat withIsItalicized(boolean newIsItalicized) {
        if (newIsItalicized == isItalicized) return this;

        if (!isValidIsItalicized(isItalicized)) {
            throw new IllegalArgumentException(String.format(
                    "Unsupported font (fontFamily %s does not support italic style)",
                    fontFamily.toString()
            ));
        }

        final var BUILDER = new TextFormatBuilder();
        return BUILDER.fontFamily(fontFamily)
                      .hasSerif(hasSerif)
                      .fontWeight(fontWeight)
                      .isItalicized(newIsItalicized)
                      .isUnderlined(isUnderlined)
                      .isCondensed(isCondensed)
                      .elementColor(elementColor)
                      .build();
    }

    public TextFormat withIsUnderlined(boolean newIsUnderlined) {
        if (newIsUnderlined == isUnderlined) return this;

        final var BUILDER = new TextFormatBuilder();
        return BUILDER.fontFamily(fontFamily)
                      .hasSerif(hasSerif)
                      .fontWeight(fontWeight)
                      .isItalicized(isItalicized)
                      .isUnderlined(newIsUnderlined)
                      .isCondensed(isCondensed)
                      .elementColor(elementColor)
                      .build();
    }

    public TextFormat withIsCondensed(boolean newIsCondensed) {
        if (newIsCondensed == isCondensed) return this;

        if (isValidIsCondensed(isCondensed)) {
            throw new IllegalArgumentException(String.format(
                    "Unsupported font (fontFamily %s does not support condensed style)",
                    fontFamily.toString()
            ));
        }

        final var BUILDER = new TextFormatBuilder();
        return BUILDER.fontFamily(fontFamily)
                      .hasSerif(hasSerif)
                      .fontWeight(fontWeight)
                      .isItalicized(isItalicized)
                      .isUnderlined(isUnderlined)
                      .isCondensed(newIsCondensed)
                      .elementColor(elementColor)
                      .build();
    }

    public TextFormat withElementColor(ElementColor newElementColor) {
        if (newElementColor == null)
            throw new IllegalArgumentException("Illegal newElementColor (cannot be null)");

        if (newElementColor == elementColor) return this;

        final var BUILDER = new TextFormatBuilder();
        return BUILDER.fontFamily(fontFamily)
                      .hasSerif(hasSerif)
                      .fontWeight(fontWeight)
                      .isItalicized(isItalicized)
                      .isUnderlined(isUnderlined)
                      .isCondensed(isCondensed)
                      .elementColor(newElementColor)
                      .build();
    }


    // NEW METHODS
    // (Returns the new values of other variables when one changes, so that the font is supported.)

    private boolean newHasSerif(FontFamily newFontFamily) {
        if (hasSerif ? !newFontFamily.supportsSerif() : !newFontFamily.supportsSansSerif())
            return !hasSerif;
        return hasSerif;
    }

    private FontWeight newFontWeight(FontFamily newFontFamily, boolean newHasSerif) {
        switch (fontWeight) {
            case LIGHT:
                if (newHasSerif
                    ? newFontFamily.supportsSerifLightWeight()
                    : newFontFamily.supportsSansSerifLightWeight()) return fontWeight;
                break;
            case NORMAL:
                if (newHasSerif
                    ? newFontFamily.supportsSerifNormalWeight()
                    : newFontFamily.supportsSansSerifNormalWeight()) return fontWeight;
                break;
            case SEMI_BOLD:
                if (newHasSerif
                    ? newFontFamily.supportsSerifSemiBoldWeight()
                    : newFontFamily.supportsSansSerifSemiBoldWeight()) return fontWeight;
                break;
            case BLACK:
                if (newHasSerif
                    ? newFontFamily.supportsSerifBlackWeight()
                    : newFontFamily.supportsSansSerifBlackWeight()) return fontWeight;
                break;
        }

        // TODO: 08/08/2020 Make this method return the closest fontWeight.
        return FontWeight.NORMAL;
    }

    private boolean newIsItalicized(FontFamily newFontFamily) {
        if (!newFontFamily.supportsItalicStyle()) return false;
        return isItalicized;
    }

    private boolean newIsCondensed(FontFamily newFontFamily) {
        if (!newFontFamily.supportsCondensedStyle()) return false;
        return isCondensed;
    }


    // VALID METHODS
    // (Returns a possible value(s) for an instance variable based on state of this object.)

    public HashSet<Boolean> validHasSerifSet() {
        HashSet<Boolean> validHasSerifSet = new HashSet<>();

        if (fontFamily.supportsSerif()) validHasSerifSet.add(true);
        if (fontFamily.supportsSansSerif()) validHasSerifSet.add(false);

        return validHasSerifSet;
    }

    public HashSet<FontWeight> validFontWeightSet() {
        HashSet<FontWeight> validFontWeightSet = new HashSet<>();

        if (hasSerif
            ? fontFamily.supportsSerifLightWeight()
            : fontFamily.supportsSansSerifLightWeight())
            validFontWeightSet.add(FontWeight.LIGHT);
        if (hasSerif
            ? fontFamily.supportsSerifNormalWeight()
            : fontFamily.supportsSansSerifNormalWeight())
            validFontWeightSet.add(FontWeight.NORMAL);
        if (hasSerif
            ? fontFamily.supportsSerifSemiBoldWeight()
            : fontFamily.supportsSansSerifSemiBoldWeight())
            validFontWeightSet.add(FontWeight.SEMI_BOLD);
        if (hasSerif
            ? fontFamily.supportsSerifBlackWeight()
            : fontFamily.supportsSansSerifBlackWeight())
            validFontWeightSet.add(FontWeight.BLACK);

        return validFontWeightSet;
    }

    public HashSet<Boolean> validIsItalicizedSet() {
        HashSet<Boolean> validIsItalicizedSet = new HashSet<>();

        validIsItalicizedSet.add(false); // All font families supports non-italic style.

        if (fontFamily.supportsItalicStyle()) validIsItalicizedSet.add(true);

        return validIsItalicizedSet;
    }

    public HashSet<Boolean> validIsCondensedSet() {
        HashSet<Boolean> validIsCondensedSet = new HashSet<>();

        validIsCondensedSet.add(false);

        if (fontFamily.supportsCondensedStyle()) validIsCondensedSet.add(true);

        return validIsCondensedSet;
    }


    // BUILDER

    public static final class TextFormatBuilder {

        // TextFormat VARIABLES

        private FontFamily fontFamily;

        private boolean hasSerif;

        private FontWeight fontWeight;

        private boolean isItalicized;

        private boolean isUnderlined;

        private boolean isCondensed;

        private ElementColor elementColor;


        // CONSTRUCTOR

        public TextFormatBuilder() {
            fontFamily = DEFAULT_FONT_FAMILY;
            hasSerif = DEFAULT_HAS_SERIF;
            fontWeight = DEFAULT_FONT_WEIGHT;
            isItalicized = DEFAULT_IS_ITALICIZED;
            isUnderlined = DEFAULT_IS_UNDERLINED;
            isCondensed = DEFAULT_IS_CONDENSED;
            elementColor = DEFAULT_ELEMENT_COLOR;
        }


        // METHODS

        public TextFormatBuilder fontFamily(FontFamily fontFamily) {
            this.fontFamily = fontFamily;
            return this;
        }

        public TextFormatBuilder hasSerif(boolean hasSerif) {
            this.hasSerif = hasSerif;
            return this;
        }

        public TextFormatBuilder fontWeight(FontWeight fontWeight) {
            this.fontWeight = fontWeight;
            return this;
        }

        public TextFormatBuilder isItalicized(boolean isItalicized) {
            this.isItalicized = isItalicized;
            return this;
        }

        public TextFormatBuilder isUnderlined(boolean isUnderlined) {
            this.isUnderlined = isUnderlined;
            return this;
        }

        public TextFormatBuilder isCondensed(boolean isCondensed) {
            this.isCondensed = isCondensed;
            return this;
        }

        public TextFormatBuilder elementColor(ElementColor elementColor) {
            this.elementColor = elementColor;
            return this;
        }


        // BUILD

        public TextFormat build() {
            return new TextFormat(fontFamily,
                                  hasSerif,
                                  fontWeight,
                                  isItalicized,
                                  isUnderlined,
                                  isCondensed,
                                  elementColor);
        }
    }


    // OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "TextFormat{" +
               "fontFamily=" + fontFamily +
               ", hasSerif=" + hasSerif +
               ", fontWeight=" + fontWeight +
               ", isItalicized=" + isItalicized +
               ", isUnderlined=" + isUnderlined +
               ", isCondensed=" + isCondensed +
               ", elementColor=" + elementColor +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextFormat)) return false;
        TextFormat that = (TextFormat) o;
        return hasSerif == that.hasSerif &&
               isItalicized == that.isItalicized &&
               isUnderlined == that.isUnderlined &&
               isCondensed == that.isCondensed &&
               fontFamily == that.fontFamily &&
               fontWeight == that.fontWeight &&
               elementColor == that.elementColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fontFamily,
                            hasSerif,
                            fontWeight,
                            isItalicized,
                            isUnderlined,
                            isCondensed,
                            elementColor);
    }
}
