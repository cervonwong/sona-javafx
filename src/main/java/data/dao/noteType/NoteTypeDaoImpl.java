package main.java.data.dao.noteType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.data.dao.DaoUtils;
import main.java.data.dao.GenericDao;
import main.java.data.dto.NoteTypeDto;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

// TODO: 22/08/2020 Replace e.printStackTrace().
public class NoteTypeDaoImpl implements GenericDao<NoteTypeDto> {

    // DAO METHODS

    @Override
    public List<NoteTypeDto> getAll() {
        final ObjectMapper MAPPER = new ObjectMapper();

        try {
//            return MAPPER.readValue(getFile(), new TypeReference<List<NoteTypeDto>>() {});
            return MAPPER.readValue(getFile(), new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<NoteTypeDto> get(int id) {
        final List<NoteTypeDto> NOTE_TYPES = getAll();

        for (NoteTypeDto noteType : NOTE_TYPES)
            if (noteType.getId() == id)
                return Optional.of(noteType);

        return Optional.empty();
    }

    @Override
    public void create(NoteTypeDto noteType) {

    }

    @Override
    public void update(NoteTypeDto noteType) {

    }

    @Override
    public void delete(NoteTypeDto noteType) {

    }


    // FILE METHODS

    private File getFile() {
        final String FILE_PATH = DaoUtils.DATA_PATH + "/note_types/note_types.json";

        return new File(FILE_PATH);
    }

}
