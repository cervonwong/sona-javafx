package main.java.data.dto.element;

import main.java.data.dto.enums.ElementColorDto;

public class DividerElementDto extends AbstractElementDto {

    // FIELDS

    private double size;

    private double thickness;

    private boolean isSymmetrical;

    private double leadingIndent;

    private double trailingIndent;

    private ElementColorDto elementColor;


    // ACCESSORS

    public double getSize() {
        return size;
    }

    public double getThickness() {
        return thickness;
    }

    public boolean isSymmetrical() {
        return isSymmetrical;
    }

    public double getLeadingIndent() {
        return leadingIndent;
    }

    public double getTrailingIndent() {
        return trailingIndent;
    }

    public ElementColorDto getElementColor() {
        return elementColor;
    }


    // MUTATORS

    public void setSize(double size) {
        this.size = size;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public void setIsSymmetrical(boolean symmetrical) {
        isSymmetrical = symmetrical;
    }

    public void setLeadingIndent(double leadingIndent) {
        this.leadingIndent = leadingIndent;
    }

    public void setTrailingIndent(double trailingIndent) {
        this.trailingIndent = trailingIndent;
    }

    public void setElementColor(ElementColorDto elementColor) {
        this.elementColor = elementColor;
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "DividerElementDto{" +
               "size=" + size +
               ", thickness=" + thickness +
               ", isSymmetrical=" + isSymmetrical +
               ", leadingIndent=" + leadingIndent +
               ", trailingIndent=" + trailingIndent +
               ", elementColor=" + elementColor +
               "} " + super.toString();
    }
}
