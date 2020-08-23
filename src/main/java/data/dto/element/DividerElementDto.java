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

package main.java.data.dto.element;

import main.java.data.dto.enums.ElementColorDto;

public final class DividerElementDto extends AbstractElementDto {

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
