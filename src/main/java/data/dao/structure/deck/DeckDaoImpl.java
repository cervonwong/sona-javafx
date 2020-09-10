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

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.data.dao.DaoUtils;
import main.java.data.dto.structure.deck.DeckDto;
import main.java.data.dto.structure.deck.DeckSchedSettingsDto;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DeckDaoImpl implements DeckDao {

    // DAO METHODS

    @Override
    public List<DeckDto> getAll() {
        // TODO: 09/09/2020 Unimplemented.
        return null;
    }

    @Override
    public Optional<DeckDto> get(String name) {
        final ObjectMapper MAPPER = new ObjectMapper();

        try {
            final DeckDto DECK_DTO = MAPPER.readValue(getPropertiesFile(name), DeckDto.class);
            final DeckSchedSettingsDto SCHED_SETTINGS =
                    MAPPER.readValue(getSchedSettingsFile(name), DeckSchedSettingsDto.class);

            DECK_DTO.setSchedSettings(SCHED_SETTINGS);

            return Optional.of(DECK_DTO);
        } catch (IOException e) {
            e.printStackTrace(); // TODO: 10/09/2020 Remove e.printStackTrace() ?
            return Optional.empty();
        }
    }

    @Override
    public void create(DeckDto deckDto) {
        // TODO: 09/09/2020 Unimplemented.
    }

    @Override
    public void update(DeckDto deckDto) {
        // TODO: 09/09/2020 Unimplemented.
    }

    @Override
    public void delete(DeckDto deckDto) {
        // TODO: 09/09/2020 Unimplemented.
    }


    // FILE METHODS

    private File getPropertiesFile(String name) {
        final String FILE_PATH = String.format(
                "%s/decks/%s/properties.json",
                DaoUtils.DATA_PATH,
                name
        );

        return new File(FILE_PATH);
    }

    private File getSchedSettingsFile(String name) {
        final String FILE_PATH = String.format(
                "%s/decks/%s/sched_settings.json",
                DaoUtils.DATA_PATH,
                name
        );

        return new File(FILE_PATH);
    }
}
