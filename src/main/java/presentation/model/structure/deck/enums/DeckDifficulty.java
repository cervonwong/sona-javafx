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

package main.java.presentation.model.structure.deck.enums;

public enum DeckDifficulty {
    VERY_EASY(1),
    EASY(2),
    NORMAL(3),
    HARD(4),
    VERY_HARD(5);


    // INSTANCE VARIABLES

    private final int factor;

    // CONSTRUCTOR

    DeckDifficulty(int factor) {
        this.factor = factor;
    }


    // ACCESSORS

    public int getFactor() {
        return factor;
    }
}
