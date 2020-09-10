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

package main.java.presentation.model.structure.note;

import main.java.presentation.model.structure.card.Card;

import java.util.*;

// Contains no scheduling information.
// TODO: 04/09/2020 Reorganise argument order.
public final class Note {

    // INSTANCE VARIABLES (Identifiers)

    private final int id;

    private final String deckName;


    // INSTANCE VARIABLES (User-editable data)

    // Ensure that this variable is always updated whenever this NoteType is updated.
    private final NoteType noteType;

    private final Set<String> tags;

    /*
     Ensure that the keys of this variable is always updated whenever the NoteType of this Note
     is updated or changed.

     A value with an empty String is the default (see DEFAULT_FIELD_DATUM).
    */
    private final Map<String, String> fieldData;


    // INSTANCE VARIABLES (Structure)

    /*
     This should not be editable by the user.
     Instead, it is automatically added or removed based on the NoteType of this Note.
     But this should be updated everytime Cards of this Note changes.
     There is no DEFAULT_CARDS as this variable is tightly bound with its NoteType.
    */
    private final Map<Integer, Card> cards;


    // DEFAULT VALUES

    private static final Set<String> DEFAULT_TAGS = new HashSet<>();

    private static final String DEFAULT_FIELD_DATUM = "";


    // BOUNDARY VALUES

    private static final int MAX_TAG_LENGTH = 200;


    // CONSTRUCTOR

    private Note(int id,
                 String deckName,
                 NoteType noteType,
                 Set<String> tags,
                 // The below arguments are NULLABLE.
                 Map<String, String> fieldData,
                 Map<Integer, Card> cards) {
        // Check and initialize non-null arguments first.
        checkNonNullArguments(deckName, noteType, tags);

        this.id = id;
        this.deckName = deckName;

        this.noteType = noteType;
        this.tags = new HashSet<>(tags);

        // Check and initialize nullable arguments later.
        // NEW_FIELD_DATA and NEW_CARDS are already defensively copied.
        final Map<String, String> NEW_FIELD_DATA = initializeFieldDataIfNecessary(fieldData);
        final Map<Integer, Card> NEW_CARDS = initializeCardsIfNecessary(cards);

        checkNullableArguments(NEW_FIELD_DATA, NEW_CARDS);

        this.fieldData = NEW_FIELD_DATA;
        this.cards = NEW_CARDS;
    }


    // INITIALIZERS


    private Map<String, String> initializeFieldDataIfNecessary(Map<String, String> fieldData) {
        if (fieldData != null) return new HashMap<>(fieldData);

        final Map<String, String> NEW_FIELD_DATA = new HashMap<>();
        for (String field : noteType.getFields())
            NEW_FIELD_DATA.put(field, DEFAULT_FIELD_DATUM);

        return NEW_FIELD_DATA;
    }

    private Map<Integer, Card> initializeCardsIfNecessary(Map<Integer, Card> cards) {
        if (cards != null) return new HashMap<>(cards);

        final Map<Integer, Card> NEW_CARDS = new HashMap<>();
        for (int cardTypeId : noteType.getCardTypes().keySet())
            // Creates a new Card with default initial values for each CardType in noteType.
            NEW_CARDS.put(cardTypeId, new Card.CardBuilder(id, cardTypeId).build());

        return NEW_CARDS;
    }


    // CHECKERS (Constructor)

    private void checkNonNullArguments(String deckName, NoteType noteType, Set<String> tags) {
        checkDeckName(deckName);
        checkNoteType(noteType);
        checkTags(tags);
    }

    private void checkDeckName(String deckName) {
        if (deckName == null)
            throw new IllegalArgumentException("Illegal deckName (cannot be null)");

        // Does not check where deckName actually exists.
    }

    private void checkNoteType(NoteType noteType) {
        if (noteType == null)
            throw new IllegalArgumentException("Illegal noteType (cannot not be null)");
    }

    private void checkTags(Set<String> tags) {
        if (tags == null)
            throw new IllegalArgumentException("Illegal tags (cannot be null)");

        for (String tag : tags) {
            if (tag == null)
                throw new IllegalArgumentException("Illegal tag in tags (cannot be null)");
            if (tag.length() > MAX_TAG_LENGTH)
                throw new IllegalArgumentException(String.format(
                        "Illegal tag in tags (cannot be longer than %s characters): %s",
                        MAX_TAG_LENGTH,
                        tag
                ));
        }
    }

    private void checkNullableArguments(Map<String, String> fieldData, Map<Integer, Card> cards) {
        // At this point the variables should not be null anymore.
        checkFieldData(fieldData);
        checkCards(cards);
    }

    private void checkFieldData(Map<String, String> fieldData) {
        // Null checks
        if (fieldData == null)
            throw new IllegalArgumentException("Illegal fieldData (cannot be null)");

        final Set<String> FIELD_DATA_KEY_SET = fieldData.keySet();
        for (String field : FIELD_DATA_KEY_SET)
            if (field == null)
                throw new IllegalArgumentException("Illegal field in fieldData (cannot be null)");

        for (String datum : fieldData.values())
            if (datum == null)
                throw new IllegalArgumentException("Illegal datum in fieldData (cannot be null)");

        // NoteType checks
        if (noteType == null)
            throw new IllegalStateException("This method should not be called before the "
                                            + "initialization of noteType");

        final List<String> NOTE_TYPE_FIELDS = noteType.getFields();

        // Checks if the keys of fieldData is equal to the fields of noteType.
        if (!(FIELD_DATA_KEY_SET.equals(new HashSet<>(NOTE_TYPE_FIELDS))
              && FIELD_DATA_KEY_SET.size() == NOTE_TYPE_FIELDS.size()))
            throw new IllegalArgumentException(String.format(
                    "Illegal fieldData (its keys do not match fields in the NoteType of this "
                    + "Note: %s): %s",
                    NOTE_TYPE_FIELDS,
                    FIELD_DATA_KEY_SET
            ));
    }

    private void checkCards(Map<Integer, Card> cards) {
        // Null checks
        if (cards == null)
            throw new IllegalArgumentException("Illegal cards (cannot be null)");

        for (Card card : cards.values())
            if (card == null)
                throw new IllegalArgumentException("Illegal card in cards (cannot be null)");

        // NoteType checks
        if (noteType == null)
            throw new IllegalStateException("This method should not be called before the "
                                            + "initialization of noteType");

        final Set<Integer> CARD_TYPES_KEY_SET = noteType.getCardTypes().keySet();
        final Set<Integer> CARDS_KEY_SET = cards.keySet();

        // Checks if the keys of cards (each Card's id) is equal to the keys of cardTypes of
        // noteType (each CardType's id).
        if (!CARDS_KEY_SET.equals(CARD_TYPES_KEY_SET)) {
            throw new IllegalArgumentException(String.format(
                    "Illegal cards (its keys do not match keys of cardTypes in the NoteType of "
                    + "this Note: %s): %s",
                    CARD_TYPES_KEY_SET,
                    CARDS_KEY_SET
            ));
        }
    }


    // ACCESSORS (Instance Variables)

    public int getId() {
        return id;
    }

    public String getDeckName() {
        return deckName;
    }

    public NoteType getNoteType() {
        return noteType;
    }

    public Set<String> getTags() {
        return new HashSet<>(tags);
    }

    public Map<String, String> getFieldData() {
        return new HashMap<>(fieldData);
    }

    public Map<Integer, Card> getCards() {
        return new HashMap<>(cards);
    }


    // ACCESSORS (Default Values)

    public static Set<String> getDefaultTags() {
        return new HashSet<>(DEFAULT_TAGS);
    }

    public static String getDefaultFieldDatum() {
        return DEFAULT_FIELD_DATUM;
    }


    // ACCESSORS (Boundary Values)

    public static int getMaxTagLength() {
        return MAX_TAG_LENGTH;
    }


    // COPY METHODS (noteType)

    public Note updateNoteType(NoteType newNoteType) {
        if (newNoteType == null)
            throw new IllegalArgumentException("Illegal newNoteType (cannot be null)");

        if (!newNoteType.equals(noteType))
            throw new IllegalArgumentException(String.format(
                    "Illegal newNoteType (they are not equal): OLD: %s, NEW: %s",
                    noteType,
                    newNoteType
            ));

        // Adds / deletes fields
        final List<String> OLD_FIELDS = noteType.getFields();
        final List<String> NEW_FIELDS = newNoteType.getFields();

        if (OLD_FIELDS.equals(NEW_FIELDS)) return this;

        final Map<String, String> NEW_FIELD_DATA = new HashMap<>(fieldData);

        final List<String> ADD_FIELDS = new ArrayList<>(NEW_FIELDS);
        ADD_FIELDS.removeAll(OLD_FIELDS);
        for (String field : ADD_FIELDS) NEW_FIELD_DATA.put(field, DEFAULT_FIELD_DATUM);

        final List<String> DELETE_FIELDS = new ArrayList<>(OLD_FIELDS);
        DELETE_FIELDS.removeAll(NEW_FIELDS);
        for (String field : DELETE_FIELDS) NEW_FIELD_DATA.remove(field);

        // TODO: 09/09/2020 Also Adds / Deletes cards ?

        final var BUILDER = NoteBuilder.newBuilder();
        return BUILDER.id(id)
                      .deckName(deckName)
                      .noteType(noteType)
                      .tags(tags)
                      .fieldData(NEW_FIELD_DATA)
                      .cards(cards)
                      .build();
    }

    // TODO: 04/09/2020
    //  public Note changeNoteType(NoteType noteType, NoteTypeChangeConfiguration config) {}

    // COPY METHODS (fieldData)

    public Note updateFieldDatum(String field, String datum) {
        if (field == null)
            throw new IllegalArgumentException("Illegal field (cannot be null)");

        if (datum == null)
            throw new IllegalArgumentException("Illegal field (cannot be null)");

        if (!fieldData.containsKey(field))
            throw new IllegalArgumentException(String.format(
                    "Illegal field (not found): %s",
                    field
            ));

        if (fieldData.get(field).equals(datum)) return this;

        final Map<String, String> NEW_FIELD_DATA = new HashMap<>(fieldData);
        NEW_FIELD_DATA.put(field, datum);

        final var BUILDER = NoteBuilder.newBuilder();
        return BUILDER.id(id)
                      .deckName(deckName)
                      .noteType(noteType)
                      .tags(tags)
                      .fieldData(NEW_FIELD_DATA)
                      .cards(cards)
                      .build();
    }


    // COPY METHODS (tags)

    public Note replaceTags(Set<String> newTags) {
        if (newTags == null)
            throw new IllegalArgumentException("Illegal newTags (cannot be null)");

        if (newTags.equals(tags)) return this;

        // TODO: 06/09/2020 Is this, and similar deep copies redundant? It is deep copied again
        //  in the constructor.
        final Set<String> DEEP_TAGS = new HashSet<>(newTags);

        final var BUILDER = NoteBuilder.newBuilder();
        return BUILDER.id(id)
                      .deckName(deckName)
                      .noteType(noteType)
                      .tags(DEEP_TAGS)
                      .fieldData(fieldData)
                      .cards(cards)
                      .build();
    }

    public Note addTag(String tag) {
        if (tags == null)
            throw new IllegalArgumentException("Illegal tag (cannot be null)");

        // Allows "add"ing a tag which already exists. Set will remove duplicated internally.

        final Set<String> NEW_TAGS = new HashSet<>(tags);
        NEW_TAGS.add(tag);

        final var BUILDER = NoteBuilder.newBuilder();
        return BUILDER.id(id)
                      .deckName(deckName)
                      .noteType(noteType)
                      .tags(NEW_TAGS)
                      .fieldData(fieldData)
                      .cards(cards)
                      .build();
    }

    public Note deleteTag(String tag) {
        if (tag == null)
            throw new IllegalArgumentException("Illegal tag (cannot be null)");

        if (!tags.contains(tag))
            throw new IllegalArgumentException("Illegal tag (not found)");

        final Set<String> NEW_TAGS = new HashSet<>(tags);
        NEW_TAGS.remove(tag);

        final var BUILDER = NoteBuilder.newBuilder();
        return BUILDER.id(id)
                      .deckName(deckName)
                      .noteType(noteType)
                      .tags(NEW_TAGS)
                      .fieldData(fieldData)
                      .cards(cards)
                      .build();
    }


    // COPY METHODS (cards)

    public Note updateCard(Card card, int cardId) {
        if (card == null)
            throw new IllegalArgumentException("Illegal card (cannot be null)");

        if (!cards.containsKey(cardId))
            throw new IllegalArgumentException(String.format(
                    "Illegal cardId (not found): %s",
                    cardId
            ));

        // Cannot check if the current Card equals to the new Card because the overridden equals
        // method only compared their ids.

        final Map<Integer, Card> NEW_CARDS = new HashMap<>(cards);
        NEW_CARDS.put(cardId, card);

        final var BUILDER = NoteBuilder.newBuilder();
        return BUILDER.id(id)
                      .deckName(deckName)
                      .noteType(noteType)
                      .tags(tags)
                      .fieldData(fieldData)
                      .cards(NEW_CARDS)
                      .build();
    }


    // BUILDER

    // This is a Step Builder!
    public static final class NoteBuilder {

        // INITIAL STEP

        public static IdStep newBuilder() {
            return new Steps();
        }


        // INTERFACES

        // 1 (-> DeckNameStep)
        public interface IdStep {
            DeckNameStep id(int id);
        }

        // 2 (-> NoteTypeStep)
        public interface DeckNameStep {
            NoteTypeStep deckName(String deckName);
        }

        // 3 (-> CardsStep)
        public interface NoteTypeStep {
            BuildableStep noteType(NoteType noteType);
        }

        // 4 (Last)
        public interface BuildableStep {
            BuildableStep tags(Set<String> tags);

            BuildableStep fieldData(Map<String, String> fieldData);

            BuildableStep cards(Map<Integer, Card> cards);

            Note build();
        }


        // IMPLEMENTATION CLASS

        private static final class Steps implements IdStep,
                                                    DeckNameStep,
                                                    NoteTypeStep,
                                                    BuildableStep {

            // REQUIRED Note VARIABLES

            // 1 - IdStep
            private int id;

            // 2 - DeckNameStep
            private String deckName;

            // 3 - NoteTypeStep
            private NoteType noteType;


            // OPTIONAL Note VARIABLES

            // 4 (Last) - BuildableStep
            private Set<String> tags = DEFAULT_TAGS;

            // 4 (Last) - BuildableStep
            private Map<String, String> fieldData = null; // NULLABLE!

            // 4 (Last) - BuildableStep
            private Map<Integer, Card> cards = null; // NULLABLE!


            // CONSTRUCTOR

            public Steps() {
                // TODO: 02/09/2020 Remove redundant constructor?
            }


            // STEP METHODS

            // IdStep -> DeckNameStep
            @Override
            public DeckNameStep id(int id) {
                this.id = id;
                return this;
            }

            // DeckNameStep -> NoteTypeStep
            @Override
            public NoteTypeStep deckName(String deckName) {
                this.deckName = deckName;
                return this;
            }

            // NoteTypeStep -> BuildableStep
            @Override
            public BuildableStep noteType(NoteType noteType) {
                this.noteType = noteType;
                return this;
            }

            // BuildableStep -> BuildableStep
            @Override
            public BuildableStep tags(Set<String> tags) {
                this.tags = tags;
                return this;
            }

            // BuildableStep -> BuildableStep
            @Override
            public BuildableStep fieldData(Map<String, String> fieldData) {
                this.fieldData = fieldData;
                return this;
            }

            // BuildableStep -> BuildableStep
            @Override
            public BuildableStep cards(Map<Integer, Card> cards) {
                this.cards = cards;
                return this;
            }


            // BUILD

            public Note build() {
                return new Note(id, deckName, noteType, tags, fieldData, cards);
            }
        }
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "Note{" +
               "id=" + id +
               ", deckName='" + deckName + '\'' +
               ", noteType=" + noteType +
               ", tags=" + tags +
               ", fieldData=" + fieldData +
               ", cards=" + cards +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note note = (Note) o;
        return id == note.id &&
               deckName.equals(note.deckName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deckName);
    }
}
