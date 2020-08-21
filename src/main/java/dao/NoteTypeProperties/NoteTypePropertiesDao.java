package main.java.dao.NoteTypeProperties;

import main.java.presenter.model.structure.note.NoteTypeProperties;

public interface NoteTypePropertiesDao {

    // DAO METHODS

    NoteTypeProperties get();

    void update(NoteTypeProperties properties);
}
