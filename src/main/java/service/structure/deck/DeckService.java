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

package main.java.service.structure.deck;

import main.java.presentation.model.structure.deck.Deck;

import java.util.List;

public interface DeckService {

    // DECK CRUD (Updates Deck, Note, Card)

    List<Deck> getAll();

    Deck get(String name);

    void updateShallow(Deck deck); // Does not update Notes and Cards of this Deck.

    void updateDeep(Deck deck); // Update Notes and Cards of this Deck.

    Deck create();

    void delete(Deck deck);
}
