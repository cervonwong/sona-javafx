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

package main.java.data.dto.types.element;

import main.java.data.dto.types.enums.*;

public final class TextElementDto extends AbstractElementDto {

    // FIELDS

    private String value;

    private double fontSizeFactor;

    private FontFamilyDto fontFamily;

    private boolean hasSerif;

    private FontWeightDto fontWeight;

    private boolean isItalicized;

    private boolean isUnderlined;

    private boolean isCondensed;

    private ElementColorDto elementColor;


    // CONSTRUCTOR

    public TextElementDto() {}


    // ACCESSORS

    public String getValue() {
        return value;
    }

    public double getFontSizeFactor() {
        return fontSizeFactor;
    }

    public FontFamilyDto getFontFamily() {
        return fontFamily;
    }

    public boolean isHasSerif() {
        return hasSerif;
    }

    public FontWeightDto getFontWeight() {
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

    public ElementColorDto getElementColor() {
        return elementColor;
    }


    // MUTATORS

    public void setValue(String value) {
        this.value = value;
    }

    public void setFontSizeFactor(double fontSizeFactor) {
        this.fontSizeFactor = fontSizeFactor;
    }

    public void setFontFamily(FontFamilyDto fontFamily) {
        this.fontFamily = fontFamily;
    }

    public void setHasSerif(boolean hasSerif) {
        this.hasSerif = hasSerif;
    }

    public void setFontWeight(FontWeightDto fontWeight) {
        this.fontWeight = fontWeight;
    }

    public void setIsItalicized(boolean italicized) {
        isItalicized = italicized;
    }

    public void setIsUnderlined(boolean underlined) {
        isUnderlined = underlined;
    }

    public void setIsCondensed(boolean condensed) {
        isCondensed = condensed;
    }

    public void setElementColor(ElementColorDto elementColor) {
        this.elementColor = elementColor;
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "TextElementDto{" +
               "value='" + value + '\'' +
               ", fontSizeFactor=" + fontSizeFactor +
               ", fontFamily=" + fontFamily +
               ", hasSerif=" + hasSerif +
               ", fontWeight=" + fontWeight +
               ", isItalicized=" + isItalicized +
               ", isUnderlined=" + isUnderlined +
               ", isCondensed=" + isCondensed +
               ", elementColor=" + elementColor +
               "} " + super.toString();
    }
}
