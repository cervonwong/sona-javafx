package main.java.model.service.NoteTypeProperties;

import main.java.model.structure.note.NoteTypeProperties;

public interface NoteTypePropertiesService {

    // SERVICE METHODS

    NoteTypeProperties get();

    void update(NoteTypeProperties properties);
}
