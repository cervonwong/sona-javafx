package main.java.service.NoteType;

import main.java.presentation.model.structure.note.NoteType;

import java.util.List;
import java.util.Optional;

public interface NoteTypeService {

    // SERVICE METHODS

    List<NoteType> getAll();

    Optional<NoteType> get(int id);

    void update(NoteType noteType);

    NoteType create();

    void delete(NoteType noteType);
}
