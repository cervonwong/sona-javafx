/*
 * Sona is an educational SRS application written in JavaFX.
 * Copyright (C) 2020 Cervon Wong
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package main.java.service.structure.deck;

import main.java.data.dao.structure.card.CardDao;
import main.java.data.dao.structure.card.CardDaoImpl;
import main.java.data.dao.structure.deck.DeckDao;
import main.java.data.dao.structure.deck.DeckDaoImpl;
import main.java.data.dao.structure.note.NoteDao;
import main.java.data.dao.structure.note.NoteDaoImpl;
import main.java.data.dto.structure.card.CardDto;
import main.java.data.dto.structure.deck.DeckDto;
import main.java.data.dto.structure.note.NoteDto;
import main.java.presentation.model.structure.card.Card;
import main.java.presentation.model.structure.deck.Deck;
import main.java.presentation.model.structure.note.Note;
import main.java.presentation.model.structure.note.NoteType;
import main.java.service.note_type.NoteTypeService;
import main.java.service.note_type.NoteTypeServiceImpl;
import main.java.service.structure.StructureServiceConverter;

import java.util.*;

public class DeckServiceImpl implements DeckService {

    // DAO Instances

    private final CardDao cardDao = new CardDaoImpl();

    private final NoteDao noteDao = new NoteDaoImpl();

    private final DeckDao deckDao = new DeckDaoImpl();


    // OVERRIDDEN METHODS

    @Override
    public List<Deck> getAll() {
        return null;
    }

    // TODO: 10/09/2020 Split this method into smaller methods.
    @Override
    public Deck get(String name) {
        if (name == null)
            throw new IllegalArgumentException("Illegal name (cannot be null)");

        final List<CardDto> CARD_DTO_LIST = cardDao.getAll(name);
        final List<NoteDto> NOTE_DTO_LIST = noteDao.getAll(name);
        final DeckDto DECK_DTO = deckDao.get(name);

        // The outer key is the noteId of the Card.
        // The inner key is the id of the Card.
        final Map<Integer, Map<Integer, Card>> CARD_MAP = new HashMap<>();
        for (CardDto cardDto : CARD_DTO_LIST) {
            final Card CARD = StructureServiceConverter.toCard(cardDto);

            final int CARD_NOTE_ID = CARD.getNoteId();
            final int CARD_ID = CARD.getId();
            if (CARD_MAP.containsKey(CARD_NOTE_ID)) {
                CARD_MAP.get(CARD_NOTE_ID).put(CARD_ID, CARD);
            } else {
                final Map<Integer, Card> VALUE = new HashMap<>();
                VALUE.put(CARD_ID, CARD);
                CARD_MAP.put(CARD_NOTE_ID, VALUE);
            }
        }

        final NoteTypeService NOTE_TYPE_SERVICE = new NoteTypeServiceImpl();
        final List<NoteType> NOTE_TYPE_LIST = NOTE_TYPE_SERVICE.getAll();
        final Map<Integer, NoteType> NOTE_TYPE_MAP = new HashMap<>();

        for (NoteType noteType : NOTE_TYPE_LIST) {
            NOTE_TYPE_MAP.put(noteType.getId(), noteType);
        }

        // The key is the id of the Note.
        final Map<Integer, Note> NOTE_MAP = new HashMap<>();
        for (NoteDto noteDto : NOTE_DTO_LIST) {
            final Note NOTE = StructureServiceConverter.toNote(noteDto,
                                                               name,
                                                               CARD_MAP,
                                                               NOTE_TYPE_MAP);

            NOTE_MAP.put(NOTE.getId(), NOTE);
        }

        return StructureServiceConverter.toDeck(DECK_DTO, NOTE_MAP);
    }

    // Does not update Notes and Cards of this Deck.
    @Override
    public void updateShallow(Deck deck) {

    }

    // Update Notes and Cards of this Deck.
    @Override
    public void updateDeep(Deck deck) {

    }

    @Override
    public Deck create() {
        return null;
    }

    @Override
    public void delete(Deck deck) {

    }
}
