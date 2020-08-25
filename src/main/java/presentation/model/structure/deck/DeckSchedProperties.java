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

package main.java.presentation.model.structure.deck;

import main.java.presentation.model.structure.deck.enums.DeckDifficulty;

import java.util.Objects;

public final class DeckSchedProperties {

    // INSTANCE VARIABLES

    private final DeckDifficulty difficulty;


    // DEFAULT VALUES

    private static final DeckDifficulty DEFAULT_DIFFICULTY = DeckDifficulty.NORMAL;

    // CONSTRUCTOR

    private DeckSchedProperties(DeckDifficulty difficulty) {
        checkArguments(difficulty);

        this.difficulty = difficulty;
    }


    // CHECKERS

    private void checkArguments(DeckDifficulty difficulty) {
        checkDifficulty(difficulty);
    }

    private void checkDifficulty(DeckDifficulty difficulty) {
        if (difficulty == null)
            throw new IllegalArgumentException("Illegal difficulty (cannot be null)");
    }


    // ACCESSORS (Instance Variables)

    public DeckDifficulty getDifficulty() {
        return difficulty;
    }


    // ACCESSORS (Default Values)

    public static DeckDifficulty getDefaultDifficulty() {
        return DEFAULT_DIFFICULTY;
    }


    // COPY METHODS

    public DeckSchedProperties withDifficulty(DeckDifficulty newDifficulty) {
        if (newDifficulty == null)
            throw new IllegalArgumentException("Illegal newDifficulty (cannot be null)");

        if (newDifficulty.equals(difficulty)) return this;

        final var BUILDER = new DeckSchedPropertiesBuilder();
        return BUILDER.difficulty(newDifficulty).build();
    }


    // BUILDER

    public static final class DeckSchedPropertiesBuilder {

        // DeckSchedProperties VARIABLES

        private DeckDifficulty difficulty = DEFAULT_DIFFICULTY;


        // CONSTRUCTOR

        public DeckSchedPropertiesBuilder() {

        }


        // METHODS

        public DeckSchedPropertiesBuilder difficulty(DeckDifficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }


        // BUILDER

        public DeckSchedProperties build() {
            return new DeckSchedProperties(difficulty);
        }


    }


    // OBJECT OVERRIDDEN METHODS


    @Override
    public String toString() {
        return "DeckSchedProperties{" +
               "difficulty=" + difficulty +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeckSchedProperties)) return false;
        DeckSchedProperties that = (DeckSchedProperties) o;
        return difficulty == that.difficulty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(difficulty);
    }
}
