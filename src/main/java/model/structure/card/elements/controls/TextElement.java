package main.java.model.structure.card.elements.controls;

import main.java.model.structure.card.elements.AbstractElement;
import main.java.model.structure.card.elements.formats.TextFormat;

import java.util.Objects;

public final class TextElement extends AbstractElement {

    // INSTANCE VARIABLES

    private final String value;

    private final TextFormat textFormat; // Extracted so that copying formats are easier.


    // DEFAULT VALUES

    private static final String DEFAULT_NAME = "New Text Element";

    private static final String DEFAULT_VALUE = "Double-click to edit.";

    private static final TextFormat DEFAULT_TEXT_FORMAT =
            new TextFormat.TextFormatBuilder().build();


    // BOUNDARY VALUES

    private static final int MAX_VALUE_LENGTH = 10_000;


    // CONSTRUCTOR

    private TextElement(String name, String value, TextFormat textFormat) {
        super(name); // Parent class does checking, no checking needed for name.

        checkTextFormat(textFormat);

        this.value = value;
        this.textFormat = textFormat;
    }


    // CHECK METHODS

    private void checkValue(String value) {
        // Value is allowed to be empty.
        if (value == null)
            throw new IllegalArgumentException("Invalid value (cannot be null)");
        if (value.length() > MAX_VALUE_LENGTH)
            throw new IllegalArgumentException(String.format(
                    "Invalid value (is longer than %s characters): %s",
                    MAX_VALUE_LENGTH,
                    value
            ));
    }

    private void checkTextFormat(TextFormat textFormat) {
        if (textFormat == null)
            throw new IllegalArgumentException("Invalid textFormat (cannot be null)");
    }


    // ACCESSORS (Instance variables)

    public String getValue() {
        return value;
    }

    public TextFormat getTextFormat() {
        return textFormat;
    }


    // ACCESSORS (Default values)

    public static String getDefaultName() {
        return DEFAULT_NAME;
    }

    public static String getDefaultValue() {
        return DEFAULT_VALUE;
    }

    public static TextFormat getDefaultTextFormat() {
        return DEFAULT_TEXT_FORMAT;
    }


    // ACCESSORS (Boundary values)

    public static int getMaxValueLength() {
        return MAX_VALUE_LENGTH;
    }


    // COPY METHODS

    @Override
    public TextElement withName(String newName) {
        if (newName == null)
            throw new IllegalArgumentException("Illegal newName (cannot be null)");

        if (newName.equals(getName())) return this;

        final var BUILDER = new TextElementBuilder();
        return BUILDER.name(newName)
                      .value(value)
                      .textFormat(textFormat)
                      .build();
    }

    public TextElement withValue(String newValue) {
        if (newValue == null)
            throw new IllegalArgumentException("Illegal newValue (cannot be null)");

        if (newValue.equals(value)) return this;

        final var BUILDER = new TextElementBuilder();
        return BUILDER.name(getName())
                      .value(newValue)
                      .textFormat(textFormat)
                      .build();
    }

    public TextElement withTextFormat(TextFormat newTextFormat) {
        if (newTextFormat == null)
            throw new IllegalArgumentException("Illegal newTextFormat (cannot be null)");

        if (newTextFormat.equals(textFormat)) return this;

        final var BUILDER = new TextElementBuilder();
        return BUILDER.name(getName())
                      .value(value)
                      .textFormat(newTextFormat)
                      .build();
    }


    // BUILDER

    public static final class TextElementBuilder {

        // TextElement VARIABLES

        private String name;

        private String value;

        private TextFormat textFormat;


        // CONSTRUCTOR

        public TextElementBuilder() {
            name = DEFAULT_NAME;
            value = DEFAULT_VALUE;
            textFormat = DEFAULT_TEXT_FORMAT;
        }


        // METHODS

        public TextElementBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TextElementBuilder value(String value) {
            this.value = value;
            return this;
        }

        public TextElementBuilder textFormat(TextFormat textFormat) {
            this.textFormat = textFormat;
            return this;
        }


        // BUILD

        public TextElement build() {
            return new TextElement(name, value, textFormat);
        }
    }


    // OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "TextElement{" +
               "name='" + getName() + '\'' +
               ", value='" + value + '\'' +
               ", textFormat=" + textFormat +
               "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextElement)) return false;
        TextElement that = (TextElement) o;
        return getName().equals(that.getName()) &&
               value.equals(that.value) &&
               textFormat.equals(that.textFormat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), value, textFormat);
    }
}
