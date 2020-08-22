package main.java.data.dao.NoteTypeProperties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.data.dao.DaoUtils;
import main.java.presentation.model.structure.note.NoteTypeProperties;

import java.io.*;
import java.util.Scanner;

// TODO: 20/08/2020 Replace e.printStackTrace().
public class NoteTypePropertiesDaoImpl implements main.java.data.dao.NoteTypeProperties.NoteTypePropertiesDao {

    // DAO METHODS

    @Override
    public NoteTypeProperties get() {
        final ObjectMapper MAPPER = new ObjectMapper();

        try {
            return MAPPER.readValue(getFile(), NoteTypeProperties.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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
