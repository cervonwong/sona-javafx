package main.java.data.dto.element;

import main.java.data.dto.enums.*;

public class TextElementDto extends AbstractElementDto {

    // FIELDS

    private String value;

    private double fontSizeFactor;

    private FontFamilyDto fontFamily;

    private boolean hasSerif;

    private FontWeightDto fontWeight;

    private boolean isItalicized;

    private boolean isUnderlined;

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
               ", elementColor=" + elementColor +
               "} " + super.toString();
    }
}
