package main.java.model.dao.NoteTypeProperties;

import main.java.model.structure.note.NoteTypeProperties;

public interface NoteTypePropertiesDao {

    // DAO METHODS

    NoteTypeProperties get();

    void update(NoteTypeProperties properties);
}
