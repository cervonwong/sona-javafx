package main.java.service.NoteTypeProperties;

import main.java.dao.NoteTypeProperties.NoteTypePropertiesDaoImpl;
import main.java.presenter.model.structure.note.NoteTypeProperties;

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
