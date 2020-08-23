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

package main.java.presentation.model.structure.card.element;

abstract public class AbstractElement {

    // INSTANCE VARIABLES

    private final String name;


    // CLASS CONSTANTS

    private static final int MAX_NAME_LENGTH = 500;


    // CONSTRUCTOR

    protected AbstractElement(String name) {
        checkName(name);

        this.name = name;
    }


    // CHECK METHODS

    private void checkName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Illegal name (cannot be null)");

        if (name.isEmpty())
            throw new IllegalArgumentException("Illegal name (cannot be empty)");

        if (name.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException(String.format(
                    "Illegal name (is longer than %s characters): %s",
                    MAX_NAME_LENGTH,
                    name
            ));
    }


    // ACCESSORS

    public String getName() {
        return name;
    }

    public static int getMaxNameLength() {
        return MAX_NAME_LENGTH;
    }


    // COPY METHODS

    abstract public AbstractElement withName(String newName);


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "AbstractElement{" +
               "name='" + name + '\'' +
               '}';
    }
}
