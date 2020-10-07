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

import main.java.presentation.model.structure.card.element.AbstractElement;
import main.java.presentation.model.structure.card.element.control.ControlElement;

import java.util.Objects;

// TODO: 09/08/2020 Implement alignment
public final class TextElement extends AbstractElement implements ControlElement {

    // INSTANCE VARIABLES

    private final String value;

    private final TextFormat textFormat; // Abstracted so that copying formats are easier.


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
            throw new IllegalArgumentException("Illegal value (cannot be null)");
        if (value.length() > MAX_VALUE_LENGTH)
            throw new IllegalArgumentException(String.format(
                    "Illegal value (is longer than %s characters): %s",
                    MAX_VALUE_LENGTH,
                    value
            ));
    }

    private void checkTextFormat(TextFormat textFormat) {
        if (textFormat == null)
            throw new IllegalArgumentException("Illegal textFormat (cannot be null)");
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


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "TextElement{" +
               "value='" + value + '\'' +
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
