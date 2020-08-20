package main.java.model.dao.NoteTypeProperties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.model.dao.DaoUtils;
import main.java.model.structure.note.NoteTypeProperties;

import java.io.*;
import java.util.Scanner;

// TODO: 20/08/2020 Replace e.printStackTrace().
public class NoteTypePropertiesDaoImpl implements NoteTypePropertiesDao {

    // DAO METHODS

    @Override
    public NoteTypeProperties get() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(readFile(), NoteTypeProperties.class);
        } catch (JsonProcessingException e) {
            System.out.println("Error with ObjectMapper to read value in "
                               + "NoteTypePropertiesDaoImpl");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(NoteTypeProperties properties) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            final String JSON_STRING = mapper.writerWithDefaultPrettyPrinter()
                                             .writeValueAsString(properties);
            writeFile(JSON_STRING);
        } catch (JsonProcessingException e) {
            System.out.println("Error with ObjectMapper to write value in "
                               + "NoteTypePropertiesDaoImpl");
            e.printStackTrace();
        }
    }


    // FILE METHODS

    private File getFile() {
        final String FILE_PATH = DaoUtils.DATA_PATH + "/note_types/properties.json";

        return new File(FILE_PATH);
    }

    private String readFile() {
        final File FILE = getFile();

        try {
            final Scanner INPUT = new Scanner(FILE);

            final StringBuilder BUILDER = new StringBuilder();

            while (INPUT.hasNextLine()) BUILDER.append(INPUT.nextLine());

            INPUT.close();

            return BUILDER.toString();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading note_types/properties.json in "
                               + "NoteTypePropertiesDaoImpl");
            e.printStackTrace();
            return null;
        }
    }

    private void writeFile(String jsonString) {
        final File FILE = getFile();

        try {
            final PrintWriter OUTPUT = new PrintWriter(FILE);

            OUTPUT.write(jsonString);

            OUTPUT.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error writing note_types/properties.json in "
                               + "NoteTypePropertiesDaoImpl");
            e.printStackTrace();
        }
    }
}
