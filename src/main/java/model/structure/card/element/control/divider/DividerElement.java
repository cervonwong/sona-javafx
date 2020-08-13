package main.java.model.structure.card.element.control.divider;

import main.java.model.structure.card.element.AbstractElement;
import main.java.model.structure.card.element.control.text.enums.ElementColor;
import main.java.model.structure.card.element.control.ControlElement;

import java.util.Objects;

public final class DividerElement extends AbstractElement implements ControlElement {

    // INSTANCE VARIABLES

    private final double size; // Height (horizontal), width (vertical).

    private final double thickness;

    private final boolean isSymmetrical; // If true, leadingIndent/Percentage takes precedence.

    private final IndentType indentType;

    private final double leadingIndent; // Left for LTR languages (horizontal), top (vertical).

    private final double trailingIndent; // Right for LTR languages (horizontal), bottom (vertical).

    private final double leadingIndentPercentage;

    private final double trailingIndentPercentage;

    private final ElementColor elementColor;


    // DEFAULT VALUES

    private static final String DEFAULT_NAME = "New Divider Element";

    private static final double DEFAULT_SIZE = 10;

    private static final double DEFAULT_THICKNESS = 1;

    private static final boolean DEFAULT_IS_SYMMETRICAL = true;

    private static final IndentType DEFAULT_INDENT_TYPE = IndentType.PIXELS;

    private static final double DEFAULT_LEADING_INDENT = 5;

    private static final double DEFAULT_TRAILING_INDENT = 5;

    private static final double DEFAULT_LEADING_INDENT_PERCENTAGE = 0.05;

    private static final double DEFAULT_TRAILING_INDENT_PERCENTAGE = 0.05;

    private static final ElementColor DEFAULT_ELEMENT_COLOR = ElementColor.GRAY;


    // BOUNDARY VALUES

    private static final double MAX_SIZE = 100;

    private static final double MIN_SIZE = 0; // Allows semantic divider.

    private static final double MAX_THICKNESS = 100;

    private static final double MIN_THICKNESS = 0; // Allows divider w/o line.

    private static final double MIN_INDENT = 0;

    private static final double MAX_INDENT_PERCENTAGE = 1;

    private static final double MIN_INDENT_PERCENTAGE = 0;


    // CONSTRUCTOR

    private DividerElement(String name,
                           double size,
                           double thickness,
                           boolean isSymmetrical,
                           IndentType indentType,
                           double leadingIndent,
                           double trailingIndent,
                           double leadingIndentPercentage,
                           double trailingIndentPercentage,
                           ElementColor elementColor) {
        super(name); // Parent class does checking, no checking needed for name.

        checkArguments(size, thickness, indentType, leadingIndent, trailingIndent,
                       leadingIndentPercentage, trailingIndentPercentage, elementColor);

        this.size = size;
        this.thickness = thickness;
        this.isSymmetrical = isSymmetrical;
        this.indentType = indentType;
        this.leadingIndent = leadingIndent;
        this.trailingIndent = trailingIndent;
        this.leadingIndentPercentage = leadingIndentPercentage;
        this.trailingIndentPercentage = trailingIndentPercentage;
        this.elementColor = elementColor;
    }


    // CHECK METHODS

    private void checkArguments(double size,
                                double thickness,
                                IndentType indentType,
                                double leadingIndent,
                                double trailingIndent,
                                double leadingIndentPercentage,
                                double trailingIndentPercentage,
                                ElementColor elementColor) {
        checkNonNull(elementColor, indentType);
        checkSize(size);
        checkThickness(thickness);
        checkIndents(leadingIndent, trailingIndent);
        checkIndentPercentages(leadingIndentPercentage, trailingIndentPercentage);
    }

    private void checkNonNull(ElementColor elementColor, IndentType indentType) {
        if (elementColor == null)
            throw new IllegalArgumentException("Illegal elementColor (cannot be null)");
        if (indentType == null)
            throw new IllegalArgumentException("Illegal indentType (cannot be null)");
    }

    private void checkSize(double size) {
        if (size < MIN_SIZE)
            throw new IllegalArgumentException(String.format(
                    "Illegal size (is lesser than %s): %s",
                    MIN_SIZE,
                    size
            ));

        if (size > MAX_SIZE)
            throw new IllegalArgumentException(String.format(
                    "Illegal size (is greater than %s): %s",
                    MAX_SIZE,
                    size
            ));
    }

    private void checkThickness(double thickness) {
        if (thickness < MIN_THICKNESS)
            throw new IllegalArgumentException(String.format(
                    "Illegal thickness (is lesser than %s): %s",
                    MIN_THICKNESS,
                    thickness
            ));

        if (size > MAX_THICKNESS)
            throw new IllegalArgumentException(String.format(
                    "Illegal thickness (is greater than %s): %s",
                    MAX_THICKNESS,
                    thickness
            ));
    }

    private void checkIndents(double leadingIndent, double trailingIndent) {
        // Does not check whether the sum of indents is greater than the number of pixels.
        checkMinIndent(leadingIndent, "leadingIndent");
        checkMinIndent(trailingIndent, "trailingIndent");
    }

    private void checkMinIndent(double indent, String indentName) {
        if (indent < MIN_INDENT)
            throw new IllegalArgumentException(String.format(
                    "Illegal %s (is lesser than %s): %s",
                    indentName,
                    MIN_INDENT,
                    indent
            ));
    }

    private void checkIndentPercentages(double leadingIndentPercentage,
                                        double trailingIndentPercentage) {
        // Does not check whether the sum of indent percentages is greater than 1.0.
        checkMinMaxIndentPercentage(leadingIndentPercentage, "leadingIndentPercentage");
        checkMinMaxIndentPercentage(trailingIndentPercentage, "trailingIndentPercentage");
    }

    private void checkMinMaxIndentPercentage(double indentPercentage, String indentPercentageName) {
        if (indentPercentage < MIN_INDENT_PERCENTAGE)
            throw new IllegalArgumentException(String.format(
                    "Illegal %s (is lesser than %s): %s",
                    indentPercentageName,
                    MIN_INDENT_PERCENTAGE,
                    indentPercentage
            ));

        if (indentPercentage > MAX_INDENT_PERCENTAGE)
            throw new IllegalArgumentException(String.format(
                    "Illegal %s (is greater than %s): %s",
                    indentPercentageName,
                    MAX_INDENT_PERCENTAGE,
                    indentPercentage
            ));
    }


    // ACCESSORS (Instance Variables)

    public double getSize() {
        return size;
    }

    public double getThickness() {
        return thickness;
    }

    public boolean isSymmetrical() {
        return isSymmetrical;
    }

    public IndentType getIndentType() {
        return indentType;
    }

    public double getLeadingIndent() {
        return leadingIndent;
    }

    public double getTrailingIndent() {
        return trailingIndent;
    }

    public double getLeadingIndentPercentage() {
        return leadingIndentPercentage;
    }

    public double getTrailingIndentPercentage() {
        return trailingIndentPercentage;
    }

    public ElementColor getElementColor() {
        return elementColor;
    }


    // ACCESSORS (Default Values)

    public static double getDefaultSize() {
        return DEFAULT_SIZE;
    }

    public static double getDefaultThickness() {
        return DEFAULT_THICKNESS;
    }

    public static boolean isDefaultIsSymmetrical() {
        return DEFAULT_IS_SYMMETRICAL;
    }

    public static IndentType getDefaultIndentType() {
        return DEFAULT_INDENT_TYPE;
    }

    public static double getDefaultLeadingIndent() {
        return DEFAULT_LEADING_INDENT;
    }

    public static double getDefaultTrailingIndent() {
        return DEFAULT_TRAILING_INDENT;
    }

    public static double getDefaultLeadingIndentPercentage() {
        return DEFAULT_LEADING_INDENT_PERCENTAGE;
    }

    public static double getDefaultTrailingIndentPercentage() {
        return DEFAULT_TRAILING_INDENT_PERCENTAGE;
    }

    public static ElementColor getDefaultElementColor() {
        return DEFAULT_ELEMENT_COLOR;
    }


    // ACCESSORS (Boundary Values)

    public static double getMaxSize() {
        return MAX_SIZE;
    }

    public static double getMinSize() {
        return MIN_SIZE;
    }

    public static double getMaxThickness() {
        return MAX_THICKNESS;
    }

    public static double getMinThickness() {
        return MIN_THICKNESS;
    }

    public static double getMinIndent() {
        return MIN_INDENT;
    }

    public static double getMaxIndentPercentage() {
        return MAX_INDENT_PERCENTAGE;
    }

    public static double getMinIndentPercentage() {
        return MIN_INDENT_PERCENTAGE;
    }


    // COPY METHODS

    @Override
    public DividerElement withName(String newName) {
        if (newName == null)
            throw new IllegalArgumentException("Illegal newName (cannot be null)");

        if (newName.equals(getName())) return this;

        final var BUILDER = new DividerElementBuilder();
        return BUILDER.name(newName)
                      .size(size)
                      .thickness(thickness)
                      .isSymmetrical(isSymmetrical)
                      .indentType(indentType)
                      .leadingIndent(leadingIndent)
                      .trailingIndent(trailingIndent)
                      .leadingIndentPercentage(leadingIndentPercentage)
                      .trailingIndentPercentage(trailingIndentPercentage)
                      .elementColor(elementColor)
                      .build();
    }

    public DividerElement withSize(double newSize) {
        if (newSize == size) return this;

        final var BUILDER = new DividerElementBuilder();
        return BUILDER.name(getName())
                      .size(newSize)
                      .thickness(thickness)
                      .isSymmetrical(isSymmetrical)
                      .indentType(indentType)
                      .leadingIndent(leadingIndent)
                      .trailingIndent(trailingIndent)
                      .leadingIndentPercentage(leadingIndentPercentage)
                      .trailingIndentPercentage(trailingIndentPercentage)
                      .elementColor(elementColor)
                      .build();
    }

    public DividerElement withThickness(double newThickness) {
        if (newThickness == thickness) return this;

        final var BUILDER = new DividerElementBuilder();
        return BUILDER.name(getName())
                      .size(size)
                      .thickness(newThickness)
                      .isSymmetrical(isSymmetrical)
                      .indentType(indentType)
                      .leadingIndent(leadingIndent)
                      .trailingIndent(trailingIndent)
                      .leadingIndentPercentage(leadingIndentPercentage)
                      .trailingIndentPercentage(trailingIndentPercentage)
                      .elementColor(elementColor)
                      .build();
    }

    public DividerElement withIsSymmetrical(boolean newIsSymmetrical) {
        if (isSymmetrical == newIsSymmetrical) return this;

        final var BUILDER = new DividerElementBuilder();
        return BUILDER.name(getName())
                      .size(size)
                      .thickness(thickness)
                      .isSymmetrical(newIsSymmetrical)
                      .indentType(indentType)
                      .leadingIndent(leadingIndent)
                      .trailingIndent(trailingIndent)
                      .leadingIndentPercentage(leadingIndentPercentage)
                      .trailingIndentPercentage(trailingIndentPercentage)
                      .elementColor(elementColor)
                      .build();
    }

    public DividerElement withIndentType(IndentType newIndentType) {
        if (newIndentType == null)
            throw new IllegalArgumentException("Illegal newIndentType (cannot be null)");

        if (newIndentType == indentType) return this;

        final var BUILDER = new DividerElementBuilder();
        return BUILDER.name(getName())
                      .size(size)
                      .thickness(thickness)
                      .isSymmetrical(isSymmetrical)
                      .indentType(newIndentType)
                      .leadingIndent(leadingIndent)
                      .trailingIndent(trailingIndent)
                      .leadingIndentPercentage(leadingIndentPercentage)
                      .trailingIndentPercentage(trailingIndentPercentage)
                      .elementColor(elementColor)
                      .build();
    }

    public DividerElement withLeadingIndent(double newLeadingIndent) {
        if (newLeadingIndent == leadingIndent) return this;

        final var BUILDER = new DividerElementBuilder();
        return BUILDER.name(getName())
                      .size(size)
                      .thickness(thickness)
                      .isSymmetrical(isSymmetrical)
                      .indentType(indentType)
                      .leadingIndent(newLeadingIndent)
                      .trailingIndent(trailingIndent)
                      .leadingIndentPercentage(leadingIndentPercentage)
                      .trailingIndentPercentage(trailingIndentPercentage)
                      .elementColor(elementColor)
                      .build();
    }

    public DividerElement withTrailingIndent(double newTrailingIndent) {
        if (newTrailingIndent == trailingIndent) return this;

        final var BUILDER = new DividerElementBuilder();
        return BUILDER.name(getName())
                      .size(size)
                      .thickness(thickness)
                      .isSymmetrical(isSymmetrical)
                      .indentType(indentType)
                      .leadingIndent(leadingIndent)
                      .trailingIndent(newTrailingIndent)
                      .leadingIndentPercentage(leadingIndentPercentage)
                      .trailingIndentPercentage(trailingIndentPercentage)
                      .elementColor(elementColor)
                      .build();
    }

    public DividerElement withLeadingIndentPercentage(double newLeadingIndentPercentage) {
        if (newLeadingIndentPercentage == leadingIndentPercentage) return this;

        final var BUILDER = new DividerElementBuilder();
        return BUILDER.name(getName())
                      .size(size)
                      .thickness(thickness)
                      .isSymmetrical(isSymmetrical)
                      .indentType(indentType)
                      .leadingIndent(leadingIndent)
                      .trailingIndent(trailingIndent)
                      .leadingIndentPercentage(newLeadingIndentPercentage)
                      .trailingIndentPercentage(trailingIndentPercentage)
                      .elementColor(elementColor)
                      .build();
    }

    public DividerElement withTrailingIndentPercentage(double newTrailingIndentPercentage) {
        if (newTrailingIndentPercentage == trailingIndentPercentage) return this;

        final var BUILDER = new DividerElementBuilder();
        return BUILDER.name(getName())
                      .size(size)
                      .thickness(thickness)
                      .isSymmetrical(isSymmetrical)
                      .indentType(indentType)
                      .leadingIndent(leadingIndent)
                      .trailingIndent(trailingIndent)
                      .leadingIndentPercentage(leadingIndentPercentage)
                      .trailingIndentPercentage(newTrailingIndentPercentage)
                      .elementColor(elementColor)
                      .build();
    }

    public DividerElement withElementColor(ElementColor newElementColor) {
        if (newElementColor == null)
            throw new IllegalArgumentException("Illegal newElementColor (cannot be null)");

        if (newElementColor == elementColor) return this;

        final var BUILDER = new DividerElementBuilder();
        return BUILDER.name(getName())
                      .size(size)
                      .thickness(thickness)
                      .isSymmetrical(isSymmetrical)
                      .indentType(indentType)
                      .leadingIndent(leadingIndent)
                      .trailingIndent(trailingIndent)
                      .leadingIndentPercentage(leadingIndentPercentage)
                      .trailingIndentPercentage(trailingIndentPercentage)
                      .elementColor(newElementColor)
                      .build();
    }


    // BUILDER

    public static final class DividerElementBuilder {

        // DividerElement VARIABLES

        private String name = DEFAULT_NAME;

        private double size = DEFAULT_SIZE;

        private double thickness = DEFAULT_THICKNESS;

        private boolean isSymmetrical = DEFAULT_IS_SYMMETRICAL;

        private IndentType indentType = DEFAULT_INDENT_TYPE;

        private double leadingIndent = DEFAULT_LEADING_INDENT;

        private double trailingIndent = DEFAULT_TRAILING_INDENT;

        private double leadingIndentPercentage = DEFAULT_LEADING_INDENT_PERCENTAGE;

        private double trailingIndentPercentage = DEFAULT_TRAILING_INDENT_PERCENTAGE;

        private ElementColor elementColor = DEFAULT_ELEMENT_COLOR;


        // CONSTRUCTOR

        public DividerElementBuilder() {}


        // METHODS

        public DividerElementBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DividerElementBuilder size(double size) {
            this.size = size;
            return this;
        }

        public DividerElementBuilder thickness(double thickness) {
            this.thickness = thickness;
            return this;
        }

        public DividerElementBuilder isSymmetrical(boolean isSymmetrical) {
            this.isSymmetrical = isSymmetrical;
            return this;
        }

        public DividerElementBuilder indentType(IndentType indentType) {
            this.indentType = indentType;
            return this;
        }

        public DividerElementBuilder leadingIndent(double leadingIndent) {
            this.leadingIndent = leadingIndent;
            return this;
        }

        public DividerElementBuilder trailingIndent(double trailingIndent) {
            this.trailingIndent = trailingIndent;
            return this;
        }

        public DividerElementBuilder leadingIndentPercentage(double leadingIndentPercentage) {
            this.leadingIndentPercentage = leadingIndentPercentage;
            return this;
        }

        public DividerElementBuilder trailingIndentPercentage(double trailingIndentPercentage) {
            this.trailingIndentPercentage = trailingIndentPercentage;
            return this;
        }

        public DividerElementBuilder elementColor(ElementColor elementColor) {
            this.elementColor = elementColor;
            return this;
        }


        // BUILD

        public DividerElement build() {
            return new DividerElement(name,
                                      size,
                                      thickness,
                                      isSymmetrical,
                                      indentType,
                                      leadingIndent,
                                      trailingIndent,
                                      leadingIndentPercentage,
                                      trailingIndentPercentage,
                                      elementColor);
        }
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "DividerElement{" +
               "name='" + getName() + '\'' +
               ", size=" + size +
               ", thickness=" + thickness +
               ", isSymmetrical=" + isSymmetrical +
               ", indentType=" + indentType +
               ", leadingIndent=" + leadingIndent +
               ", trailingIndent=" + trailingIndent +
               ", leadingIndentPercentage=" + leadingIndentPercentage +
               ", trailingIndentPercentage=" + trailingIndentPercentage +
               ", elementColor=" + elementColor +
               "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DividerElement)) return false;
        DividerElement that = (DividerElement) o;
        return getName().equals(that.getName()) &&
               Double.compare(that.size, size) == 0 &&
               Double.compare(that.thickness, thickness) == 0 &&
               isSymmetrical == that.isSymmetrical &&
               Double.compare(that.leadingIndent, leadingIndent) == 0 &&
               Double.compare(that.trailingIndent, trailingIndent) == 0 &&
               Double.compare(that.leadingIndentPercentage, leadingIndentPercentage) == 0 &&
               Double.compare(that.trailingIndentPercentage, trailingIndentPercentage) == 0 &&
               indentType == that.indentType &&
               elementColor == that.elementColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),
                            size,
                            thickness,
                            isSymmetrical,
                            indentType,
                            leadingIndent,
                            trailingIndent,
                            leadingIndentPercentage,
                            trailingIndentPercentage,
                            elementColor);
    }
}
