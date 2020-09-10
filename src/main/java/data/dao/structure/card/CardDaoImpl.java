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

package main.java.data.dao.structure.card;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import main.java.data.dao.DaoUtils;
import main.java.data.dto.structure.card.CardDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CardDaoImpl implements CardDao {

    // DAO METHODS

    @Override
    public List<CardDto> getAll(String deckName) {
        final ObjectMapper MAPPER = new ObjectMapper();

        // Make serdes of LocalDate possible.
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            return MAPPER.readValue(getFile(deckName), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    @Override
    public List<CardDto> getByNote(String deckName, int noteId) {
        return null;
    }

    @Deprecated
    @Override
    public CardDto get(String deckName, int noteId, int id) {
        return null;
    }

    @Override
    public void create(String deckName, CardDto cardDto) {

    }

    @Override
    public void update(String deckName, CardDto cardDto) {

    }

    @Override
    public void delete(String deckName, CardDto cardDto) {

    }


    // FILE METHODS

    private File getFile(String deckName) {
        final String FILE_PATH = String.format(
                "%s/decks/%s/cards.json",
                DaoUtils.DATA_PATH,
                deckName
        );

        return new File(FILE_PATH);
    }
}
