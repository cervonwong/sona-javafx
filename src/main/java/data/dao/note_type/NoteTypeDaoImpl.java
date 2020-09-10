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

package main.java.data.dao.note_type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.data.dao.DaoUtils;
import main.java.data.dto.types.NoteTypeDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

// TODO: 22/08/2020 Replace e.printStackTrace().
public final class NoteTypeDaoImpl implements NoteTypeDao {

    // DAO METHODS

    @Override
    public List<NoteTypeDto> getAll() {
        final ObjectMapper MAPPER = new ObjectMapper();

        try {
            return MAPPER.readValue(getFile(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // THIS METHOD SHOULD NOT BE CALLED MULTIPLE TIMES BECAUSE IT IS SLOWER THAN getAll.
    @Deprecated
    @Override
    public NoteTypeDto get(int id) {
        final List<NoteTypeDto> NOTE_TYPES = getAll();

        for (NoteTypeDto noteType : NOTE_TYPES)
            if (noteType.getId() == id)
                return noteType;

        throw new IllegalArgumentException(String.format(
                "Illegal id (no noteType found, or error reading file): %s",
                id
        ));
    }

    @Override
    public void create(NoteTypeDto noteType) {

    }

    @Override
    public void update(NoteTypeDto noteType) {

    }

    @Override
    public void delete(NoteTypeDto noteType) {

    }


    // FILE METHODS

    private File getFile() {
        final String FILE_PATH = DaoUtils.DATA_PATH + "/note_types/note_types.json";

        return new File(FILE_PATH);
    }

}
