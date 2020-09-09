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

package main.java.data.dao.structure.deck;

import main.java.data.dao.GenericDao;
import main.java.data.dto.structure.deck.DeckDto;
import main.java.presentation.model.structure.deck.Deck;

import java.util.List;
import java.util.Optional;

public interface DeckDao {

    // CRUD METHODS

    List<DeckDto> getAll();

    Optional<DeckDto> get(String name);

    void create(DeckDto deckDto);

    void update(DeckDto deckDto);

    void delete(DeckDto deckDto);
}
