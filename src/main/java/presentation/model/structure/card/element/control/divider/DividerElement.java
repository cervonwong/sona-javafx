package main.java.presentation.model.structure.card.element.control.divider;

import main.java.presentation.model.structure.card.element.AbstractElement;
import main.java.presentation.model.structure.card.element.control.ControlElement;
import main.java.presentation.model.structure.card.element.control.text.enums.ElementColor;

import java.util.Objects;

public final class DividerElement extends AbstractElement implements ControlElement {

    // INSTANCE VARIABLES

    private final double size; // Height (horizontal), width (vertical).

    private final double thickness;

    private final boolean isSymmetrical; // If true, leadingIndent takes precedence.

    private final double leadingIndent; // Left for LTR languages (horizontal), top (vertical).

    private final double trailingIndent; // Right for LTR languages (horizontal), bottom (vertical).

    private final ElementColor elementColor;


    // DEFAULT VALUES

    private static final String DEFAULT_NAME = "New Divider Element";

    private static final double DEFAULT_SIZE = 10.0;

    private static final double DEFAULT_THICKNESS = 1.0;

    private static final boolean DEFAULT_IS_SYMMETRICAL = true;

    private static final double DEFAULT_LEADING_INDENT = 0.05;

    private static final double DEFAULT_TRAILING_INDENT = 0.05;

    private static final ElementColor DEFAULT_ELEMENT_COLOR = ElementColor.GRAY;


    // BOUNDARY VALUES

    private static final double MAX_SIZE = 100.0;

    private static final double MIN_SIZE = 0.0; // Allows semantic divider.

    private static final double MAX_THICKNESS = 100.0;

    private static final double MIN_THICKNESS = 0.0; // Allows divider w/o line.

    private static final double MAX_INDENT = 1.0;

    private static final double MIN_INDENT = 0.0;


    // CONSTRUCTOR

    private DividerElement(String name,
                           double size,
                           double thickness,
                           boolean isSymmetrical,
                           double leadingIndent,
                           double trailingIndent,
                           ElementColor elementColor) {
        super(name); // Parent class does checking, no checking needed for name.

        checkArguments(size, thickness, leadingIndent, trailingIndent, elementColor);

        this.size = size;
        this.thickness = thickness;
        this.isSymmetrical = isSymmetrical;
        this.leadingIndent = leadingIndent;
        this.trailingIndent = trailingIndent;
        this.elementColor = elementColor;
    }


    // CHECK METHODS

    private void checkArguments(double size,
                                double thickness,
                                double leadingIndent,
                                double trailingIndent,
                                ElementColor elementColor) {
        checkNonNull(elementColor);
        checkSize(size);
        checkThickness(thickness);
        checkIndent(leadingIndent, trailingIndent);
    }

    private void checkNonNull(ElementColor elementColor) {
        if (elementColor == null)
            throw new IllegalArgumentException("Illegal elementColor (cannot be null)");
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

    private void checkIndent(double leadingIndent,
                             double trailingIndent) {
        checkMinMaxIndent(leadingIndent, "leadingIndent");
        checkMinMaxIndent(trailingIndent, "trailingIndent");
    }

    // Does not check whether the sum is greater than 1.
    // If the sum is greater than 1, the divider has no line.
    private void checkMinMaxIndent(double indent, String indentName) {
        if (indent < MIN_INDENT)
            throw new IllegalArgumentException(String.format(
                    "Illegal %s (is lesser than %s): %s",
                    indentName,
                    MIN_INDENT,
                    indent
            ));

        if (indent > MAX_INDENT)
            throw new IllegalArgumentException(String.format(
                    "Illegal %s (is greater than %s): %s",
                    indentName,
                    MAX_INDENT,
                    indent
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

    public double getLeadingIndent() {
        return leadingIndent;
    }

    public double getTrailingIndent() {
        return trailingIndent;
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

    public static boolean isSymmetricalByDefault() {
        return DEFAULT_IS_SYMMETRICAL;
    }

    public static double getDefaultLeadingIndent() {
        return DEFAULT_LEADING_INDENT;
    }

    public static double getDefaultTrailingIndent() {
        return DEFAULT_TRAILING_INDENT;
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

    public static double getMaxIndent() {
        return MAX_INDENT;
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
                      .leadingIndent(leadingIndent)
                      .trailingIndent(trailingIndent)
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
                      .leadingIndent(leadingIndent)
                      .trailingIndent(trailingIndent)
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
                      .leadingIndent(leadingIndent)
                      .trailingIndent(trailingIndent)
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
                      .leadingIndent(leadingIndent)
                      .trailingIndent(trailingIndent)
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
                      .leadingIndent(newLeadingIndent)
                      .trailingIndent(trailingIndent)
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
                      .leadingIndent(leadingIndent)
                      .trailingIndent(newTrailingIndent)
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
                      .leadingIndent(leadingIndent)
                      .trailingIndent(trailingIndent)
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

        private double leadingIndent = DEFAULT_LEADING_INDENT;

        private double trailingIndent = DEFAULT_TRAILING_INDENT;

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

        public DividerElementBuilder leadingIndent(double leadingIndent) {
            this.leadingIndent = leadingIndent;
            return this;
        }

        public DividerElementBuilder trailingIndent(double trailingIndent) {
            this.trailingIndent = trailingIndent;
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
                                      leadingIndent,
                                      trailingIndent,
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
               ", leadingIndent=" + leadingIndent +
               ", trailingIndent=" + trailingIndent +
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
               elementColor == that.elementColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),
                            size,
                            thickness,
                            isSymmetrical,
                            leadingIndent,
                            trailingIndent,
                            elementColor);
    }
}
