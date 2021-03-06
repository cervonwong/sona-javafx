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

package main.java.presentation.model.structure.card.element.control.text;

import main.java.presentation.model.structure.card.element.control.text.enums.*;

import java.util.HashSet;
import java.util.Objects;

public final class TextFormat {

    // INSTANCE VARIABLES

    private final double fontSizeFactor;

    private final FontFamily fontFamily;

    private final boolean hasSerif;

    private final FontWeight fontWeight;

    private final boolean isItalicized;

    private final boolean isUnderlined;

    private final boolean isCondensed;

    private final ElementColor elementColor;


    // DEFAULT VALUES

    private static final double DEFAULT_FONT_SIZE_FACTOR = 32.0;

    private static final FontFamily DEFAULT_FONT_FAMILY = FontFamily.LATIN;

    private static final boolean DEFAULT_HAS_SERIF = false;

    private static final FontWeight DEFAULT_FONT_WEIGHT = FontWeight.NORMAL;

    private static final boolean DEFAULT_IS_ITALICIZED = false;

    private static final boolean DEFAULT_IS_UNDERLINED = false;

    private static final boolean DEFAULT_IS_CONDENSED = false;

    private static final ElementColor DEFAULT_ELEMENT_COLOR = ElementColor.DEFAULT;


    // BOUNDARY VALUES

    private static final double MAX_FONT_SIZE = 200.0;

    private static final double MIN_FONT_SIZE = 1.0;


    // CONSTRUCTOR

    private TextFormat(double fontSizeFactor,
                       FontFamily fontFamily,
                       boolean hasSerif,
                       FontWeight fontWeight,
                       boolean isItalicized,
                       boolean isUnderlined,
                       boolean isCondensed,
                       ElementColor elementColor) {
        checkArguments(fontSizeFactor,
                       fontFamily,
                       hasSerif,
                       fontWeight,
                       isItalicized,
                       isCondensed,
                       elementColor); // Does not check isUnderlined

        this.fontSizeFactor = fontSizeFactor;
        this.fontFamily = fontFamily;
        this.hasSerif = hasSerif;
        this.fontWeight = fontWeight;
        this.isItalicized = isItalicized;
        this.isUnderlined = isUnderlined;
        this.isCondensed = isCondensed;
        this.elementColor = elementColor;
    }


    // CHECK METHODS

    private void checkArguments(double fontSizeFactor,
                                FontFamily fontFamily,
                                boolean hasSerif,
                                FontWeight fontWeight,
                                boolean isItalicized,
                                boolean isCondensed,
                                ElementColor elementColor) {
        checkNonNull(fontFamily, fontWeight, elementColor);
        checkFontSizeFactor(fontSizeFactor);
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

    private void checkFontSizeFactor(double fontSizeFactor) {
        if (fontSizeFactor < MIN_FONT_SIZE)
            throw new IllegalArgumentException(String.format(
                    "Illegal fontSizeFactor (is lesser than %s): %s",
                    MIN_FONT_SIZE,
                    fontSizeFactor
            ));

        if (fontSizeFactor > MAX_FONT_SIZE)
            throw new IllegalArgumentException(String.format(
                    "Illegal fontSizeFactor (is greater than %s): %s",
                    MAX_FONT_SIZE,
                    fontSizeFactor
            ));
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
    // (Called in checker methods.)

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

    public double getFontSizeFactor() {
        return fontSizeFactor;
    }

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

    public static double getDefaultFontSizeFactor() {
        return DEFAULT_FONT_SIZE_FACTOR;
    }

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


    // ACCESSORS (Boundary Values)

    public static double getMaxFontSize() {
        return MAX_FONT_SIZE;
    }

    public static double getMinFontSize() {
        return MIN_FONT_SIZE;
    }


    // COPY METHODS

    public TextFormat withFontSizeFactor(double newFontSizeFactor) {
        if (newFontSizeFactor == fontSizeFactor) return this;

        final var BUILDER = new TextFormatBuilder();
        return BUILDER.fontSizeFactor(newFontSizeFactor)
                      .fontFamily(fontFamily)
                      .hasSerif(hasSerif)
                      .fontWeight(fontWeight)
                      .isItalicized(isItalicized)
                      .isUnderlined(isUnderlined)
                      .isCondensed(isCondensed)
                      .elementColor(elementColor)
                      .build();
    }

    public TextFormat withFontFamily(FontFamily newFontFamily) {
        if (newFontFamily == null)
            throw new IllegalArgumentException("Illegal newFontFamily (cannot be null)");

        if (newFontFamily == fontFamily) return this;

        final boolean NEW_HAS_SERIF = newHasSerif(newFontFamily);
        final FontWeight NEW_FONT_WEIGHT = newFontWeight(newFontFamily, NEW_HAS_SERIF);
        final boolean NEW_IS_ITALICIZED = newIsItalicized(newFontFamily);
        final boolean NEW_IS_CONDENSED = newIsCondensed(newFontFamily);

        final var BUILDER = new TextFormatBuilder();
        return BUILDER.fontSizeFactor(fontSizeFactor)
                      .fontFamily(newFontFamily)
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

        final FontWeight NEW_FONT_WEIGHT = newFontWeight(fontFamily, newHasSerif);

        final var BUILDER = new TextFormatBuilder();
        return BUILDER.fontSizeFactor(fontSizeFactor)
                      .fontFamily(fontFamily)
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

        final var BUILDER = new TextFormatBuilder();
        return BUILDER.fontSizeFactor(fontSizeFactor)
                      .fontFamily(fontFamily)
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

        final var BUILDER = new TextFormatBuilder();
        return BUILDER.fontSizeFactor(fontSizeFactor)
                      .fontFamily(fontFamily)
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
        return BUILDER.fontSizeFactor(fontSizeFactor)
                      .fontFamily(fontFamily)
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

        final var BUILDER = new TextFormatBuilder();
        return BUILDER.fontSizeFactor(fontSizeFactor)
                      .fontFamily(fontFamily)
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
        return BUILDER.fontSizeFactor(fontSizeFactor)
                      .fontFamily(fontFamily)
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
    // (Returns possible value(s) for an instance variable based on state of this object.)

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

        private double fontSizeFactor = DEFAULT_FONT_SIZE_FACTOR;

        private FontFamily fontFamily = DEFAULT_FONT_FAMILY;

        private boolean hasSerif = DEFAULT_HAS_SERIF;

        private FontWeight fontWeight = DEFAULT_FONT_WEIGHT;

        private boolean isItalicized = DEFAULT_IS_ITALICIZED;

        private boolean isUnderlined = DEFAULT_IS_UNDERLINED;

        private boolean isCondensed = DEFAULT_IS_CONDENSED;

        private ElementColor elementColor = DEFAULT_ELEMENT_COLOR;


        // CONSTRUCTOR

        public TextFormatBuilder() {}


        // METHODS

        public TextFormatBuilder fontSizeFactor(double fontSizeFactor) {
            this.fontSizeFactor = fontSizeFactor;
            return this;
        }

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
            return new TextFormat(fontSizeFactor,
                                  fontFamily,
                                  hasSerif,
                                  fontWeight,
                                  isItalicized,
                                  isUnderlined,
                                  isCondensed,
                                  elementColor);
        }
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "TextFormat{" +
               "fontSizeFactor=" + fontSizeFactor +
               ", fontFamily=" + fontFamily +
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
        return Double.compare(fontSizeFactor, that.fontSizeFactor) == 0 &&
               hasSerif == that.hasSerif &&
               isItalicized == that.isItalicized &&
               isUnderlined == that.isUnderlined &&
               isCondensed == that.isCondensed &&
               fontFamily == that.fontFamily &&
               fontWeight == that.fontWeight &&
               elementColor == that.elementColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fontSizeFactor,
                            fontFamily,
                            hasSerif,
                            fontWeight,
                            isItalicized,
                            isUnderlined,
                            isCondensed,
                            elementColor);
    }
}
