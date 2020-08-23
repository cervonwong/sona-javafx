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

package main.java.data.dao.NoteTypeProperties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.data.dao.DaoUtils;
import main.java.presentation.model.structure.note.NoteTypeProperties;

import java.io.*;
import java.util.Scanner;

// TODO: 20/08/2020 Replace e.printStackTrace().
public final class NoteTypePropertiesDaoImpl implements main.java.data.dao.NoteTypeProperties.NoteTypePropertiesDao {

    // DAO METHODS

    @Override
    public NoteTypeProperties get() {
        final ObjectMapper MAPPER = new ObjectMapper();

        try {
            return MAPPER.readValue(getFile(), NoteTypeProperties.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(NoteTypeProperties properties) {
        if (properties == null)
            throw new IllegalArgumentException("Illegal properties (cannot be null)");

        final ObjectMapper MAPPER = new ObjectMapper();

        try {
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(getFile(), properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // FILE METHODS

    private File getFile() {
        final String FILE_PATH = DaoUtils.DATA_PATH + "/note_types/properties.json";

        return new File(FILE_PATH);
    }
}
