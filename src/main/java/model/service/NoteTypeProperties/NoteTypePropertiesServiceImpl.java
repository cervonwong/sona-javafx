package main.java.model.service.NoteTypeProperties;

import main.java.model.dao.NoteTypeProperties.NoteTypePropertiesDaoImpl;
import main.java.model.structure.note.NoteTypeProperties;

public class NoteTypePropertiesServiceImpl implements NoteTypePropertiesService {

    // OVERRIDDEN METHODS

    @Override
    public NoteTypeProperties get() {
        return new NoteTypePropertiesDaoImpl().get();
    }

    @Override
    public void update(NoteTypeProperties properties) {
        new NoteTypePropertiesDaoImpl().update(properties);
    }
}
