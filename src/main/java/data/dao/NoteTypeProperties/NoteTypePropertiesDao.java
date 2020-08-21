package main.java.data.dao.NoteTypeProperties;

import main.java.presentation.model.structure.note.NoteTypeProperties;

public interface NoteTypePropertiesDao {

    // DAO METHODS

    NoteTypeProperties get();

    void update(NoteTypeProperties properties);
}
