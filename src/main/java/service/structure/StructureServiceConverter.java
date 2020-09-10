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

package main.java.service.structure;

import main.java.data.dto.structure.card.CardDto;
import main.java.data.dto.structure.card.CardScheduleDto;
import main.java.data.dto.structure.deck.DeckDto;
import main.java.data.dto.structure.deck.DeckSchedSettingsDto;
import main.java.data.dto.structure.deck.enums.DeckDifficultyDto;
import main.java.data.dto.structure.note.NoteDto;
import main.java.presentation.model.structure.card.Card;
import main.java.presentation.model.structure.card.sched.CardSchedule;
import main.java.presentation.model.structure.deck.Deck;
import main.java.presentation.model.structure.deck.DeckSchedSettings;
import main.java.presentation.model.structure.deck.enums.DeckDifficulty;
import main.java.presentation.model.structure.note.Note;
import main.java.presentation.model.structure.note.NoteType;

import java.util.List;
import java.util.Map;

// Util class.
public class StructureServiceConverter {

    // DECK CONVERTERS (DTO -> Model Objects)

    public static Deck toDeck(DeckDto deckDto, Map<Integer, Note> notesOfDeck) {
        if (deckDto == null)
            throw new IllegalArgumentException("Illegal deckDto (cannot be null)");

        final var BUILDER = new Deck.DeckBuilder(deckDto.getName());
        return BUILDER.authorName(deckDto.getAuthorName())
                      .description(deckDto.getDescription())
                      .schedSettings(toDeckSchedSettings(deckDto.getSchedSettings()))
                      .notes(notesOfDeck)
                      .build();
    }

    private static DeckSchedSettings toDeckSchedSettings(DeckSchedSettingsDto deckSchedSettingsDto) {
        if (deckSchedSettingsDto == null)
            throw new IllegalArgumentException("Illegal deckSchedSettingsDto (cannot be null)");

        final var BUILDER = new DeckSchedSettings.DeckSchedSettingsBuilder();
        return BUILDER.difficulty(toDeckDifficulty(deckSchedSettingsDto.getDifficulty()))
                      .build();
    }

    private static DeckDifficulty toDeckDifficulty(DeckDifficultyDto deckDifficultyDto) {
        if (deckDifficultyDto == null)
            throw new IllegalArgumentException("Illegal deckDifficultyDto (cannot be null)");

        return DeckDifficulty.valueOf(deckDifficultyDto.name());
    }


    // NOTE CONVERTERS (DTO -> Model Objects)

    public static Note toNote(NoteDto noteDto,
                              String deckName,
                              Map<Integer, Map<Integer, Card>> cardsOfDeck,
                              Map<Integer, NoteType> noteTypes) {
        if (noteDto == null)
            throw new IllegalArgumentException("Illegal noteDto (cannot be null)");

        if (cardsOfDeck == null)
            throw new IllegalArgumentException("Illegal cardsOfDeck (cannot be null)");

        final var BUILDER = Note.NoteBuilder.newBuilder();
        return BUILDER.id(noteDto.getId())
                      .deckName(deckName)
                      .noteType(noteTypes.get(noteDto.getNoteTypeId()))
                      .tags(noteDto.getTags())
                      .fieldData(noteDto.getFieldData())
                      .cards(cardsOfDeck.get(noteDto.getId()))
                      .build();
    }


    // CARD CONVERTERS (DTO -> Model Objects)

    public static Card toCard(CardDto cardDto) {
        if (cardDto == null)
            throw new IllegalArgumentException("Illegal cardDto (cannot be null)");

        final var BUILDER = new Card.CardBuilder(cardDto.getNoteId(), cardDto.getId());
        return BUILDER.schedule(toCardSchedule(cardDto.getSchedule()))
                      .isStarred(cardDto.isStarred())
                      .isHidden(cardDto.isHidden())
                      .build();
    }

    private static CardSchedule toCardSchedule(CardScheduleDto cardScheduleDto) {
        if (cardScheduleDto == null)
            throw new IllegalArgumentException("Illegal cardScheduleDto (cannot be null)");

        final var BUILDER = new CardSchedule.CardScheduleBuilder();
        return BUILDER.easiness(cardScheduleDto.getEf())
                      .totalReviewCount(cardScheduleDto.getTrc())
                      .effectiveReviewCount(cardScheduleDto.getErc())
                      .correctStreakCount(cardScheduleDto.getCsc())
                      .incorrectStreakCount(cardScheduleDto.getIsc())
                      .currentInterval(cardScheduleDto.getCi())
                      .nextReviewDate(cardScheduleDto.getNrd())
                      .build();
    }

    // DECK CONVERTERS (Model Objects -> DTO)

    // NOTE CONVERTERS (Model Objects -> DTO)

    // CARD CONVERTERS (Model Objects -> DTO)
}
