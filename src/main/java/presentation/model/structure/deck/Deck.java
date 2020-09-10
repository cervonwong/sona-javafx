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

package main.java.presentation.model.structure.deck;

import main.java.presentation.model.structure.note.Note;

import java.util.*;

public final class Deck {

    // INSTANCE VARIABLES (Identifiers)

    private final String name; // Unique!!


    // INSTANCE VARIABLES (Deck Settings / Properties)

    private final String authorName; // Nullable!

    private final String description; // Nullable!

    private final DeckSchedSettings schedSettings;


    // INSTANCE VARIABLES (Structure)

    private final Map<Integer, Note> notes;


    // DEFAULT VALUES

    private static final String DEFAULT_AUTHOR_NAME = null;

    private static final String DEFAULT_DESCRIPTION = null;

    private static final DeckSchedSettings DEFAULT_DECK_SCHED_SETTINGS =
            new DeckSchedSettings.DeckSchedSettingsBuilder().build();

    private static final Map<Integer, Note> DEFAULT_NOTES = new HashMap<>();


    // BOUNDARY VALUES

    private static final int MAX_NAME_LENGTH = 500;

    private static final int MAX_AUTHOR_NAME_LENGTH = 300;

    private static final int MAX_DESCRIPTION_LENGTH = 2000;


    // NON-CONSTANT STATIC VARIABLES

    // TODO: 06/09/2020 Currently this variable is updated every time getAll() is called in
    //  DeckService, is there any way to make this more robust?
    private static Set<String> deckNames = new HashSet<>();


    // CONSTRUCTOR

    private Deck(String name,
                 String authorName,
                 String description,
                 DeckSchedSettings schedSettings,
                 Map<Integer, Note> notes) {
        checkArguments(name, authorName, description, schedSettings, notes);

        this.name = name;
        this.authorName = authorName;
        this.description = description;
        this.schedSettings = schedSettings;
        this.notes = new HashMap<>(notes);
    }


    // CHECKERS (Constructor)

    private void checkArguments(String name,
                                String authorName,
                                String description,
                                DeckSchedSettings schedSettings,
                                Map<Integer, Note> notes) {
        checkName(name);
        checkAuthorName(authorName);
        checkDescription(description);
        checkDeckSchedSettings(schedSettings);
        checkNotes(notes);
    }

    private void checkName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Illegal name (cannot be null)");

        if (name.isEmpty())
            throw new IllegalArgumentException("Illegal name (cannot be empty)");

        if (name.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException(String.format(
                    "Illegal name (cannot be longer than %s characters): %s",
                    MAX_NAME_LENGTH,
                    name
            ));
    }

    private void checkAuthorName(String authorName) {
        if (authorName == null) return;

        if (authorName.isEmpty())
            throw new IllegalArgumentException(
                    "Illegal authorName (cannot be empty, should be null)");

        if (authorName.length() > MAX_AUTHOR_NAME_LENGTH)
            throw new IllegalArgumentException(String.format(
                    "Illegal authorName (cannot be longer than %s characters): %s",
                    MAX_AUTHOR_NAME_LENGTH,
                    authorName
            ));
    }

    private void checkDescription(String description) {
        if (description == null) return;

        if (description.isEmpty())
            throw new IllegalArgumentException("Illegal description (cannot be empty, should be "
                                               + "null)");

        if (description.length() > MAX_DESCRIPTION_LENGTH)
            throw new IllegalArgumentException(String.format(
                    "Illegal description (cannot be longer than %s characters): %s",
                    MAX_DESCRIPTION_LENGTH,
                    description
            ));
    }

    private void checkDeckSchedSettings(DeckSchedSettings schedSettings) {
        if (schedSettings == null)
            throw new IllegalArgumentException("Illegal schedSettings (cannot be null)");
    }

    private void checkNotes(Map<Integer, Note> notes) {
        // TODO: 06/09/2020 FIX EXCESSIVE CHECKS!! (Maybe check in builder and not constructor,
        //  then use constructor in copy methods?).
        if (notes == null)
            throw new IllegalArgumentException("Illegal notes (cannot be null)");

        for (Map.Entry<Integer, Note> entry : notes.entrySet()) {
            final Note NOTE = entry.getValue();
            final Integer KEY = entry.getKey();

            if (NOTE == null)
                throw new IllegalArgumentException(String.format(
                        "Illegal note in notes (cannot be null): KEY: %s",
                        KEY
                ));

            if (KEY != NOTE.getId())
                throw new IllegalArgumentException(String.format(
                        "Illegal entry in notes (key does not match id of note): KEY: %s, NOTE'S "
                        + "ID: %s",
                        KEY,
                        NOTE.getId()
                ));

            if (!NOTE.getDeckName().equals(name))
                throw new IllegalArgumentException(String.format(
                        "Illegal note in notes (deckName does not match name of this Deck): "
                        + "deckName: %s, should be: %s",
                        NOTE.getDeckName(),
                        name
                ));
        }
    }


    // CHECKERS (Mutators)

    private void checkDeckNames(Set<String> deckNames) {
        if (deckNames == null)
            throw new IllegalArgumentException("Illegal deckNames (cannot be null)");

        for (String deckName : deckNames) {
            if (deckName == null)
                throw new IllegalArgumentException("Illegal name in deckNames (cannot be null)");
        }
    }


    // ACCESSORS (Instance Variables)

    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDescription() {
        return description;
    }

    public DeckSchedSettings getSchedSettings() {
        return schedSettings;
    }

    public Map<Integer, Note> getNotes() {
        return new HashMap<>(notes);
    }


    // ACCESSORS (Default Values)

    public static String getDefaultAuthorName() {
        return DEFAULT_AUTHOR_NAME;
    }

    public static String getDefaultDescription() {
        return DEFAULT_DESCRIPTION;
    }

    public static DeckSchedSettings getDefaultDeckSchedSettings() {
        return DEFAULT_DECK_SCHED_SETTINGS;
    }

    public static Map<Integer, Note> getDefaultNotes() {
        return new HashMap<>(DEFAULT_NOTES);
    }


    // ACCESSORS (Boundary Values)

    public static int getMaxNameLength() {
        return MAX_NAME_LENGTH;
    }

    public static int getMaxAuthorNameLength() {
        return MAX_AUTHOR_NAME_LENGTH;
    }

    public static int getMaxDescriptionLength() {
        return MAX_DESCRIPTION_LENGTH;
    }


    // ACCESSORS (Non-Constant Static Variables)

    public static Set<String> getDeckNames() {
        return new HashSet<>(deckNames);
    }


    // COPY METHODS (Primitives)

    public Deck withAuthorName(String newAuthorName) {
        if (newAuthorName == null) {
            if (authorName == null) return this;
        } else {
            if (newAuthorName.equals(authorName)) return this;
        }

        final var BUILDER = new DeckBuilder(name);
        return BUILDER.authorName(newAuthorName)
                      .description(description)
                      .schedSettings(schedSettings)
                      .notes(notes)
                      .build();
    }

    public Deck withDescription(String newDescription) {
        if (newDescription == null) {
            if (description == null) return this;
        } else {
            if (newDescription.equals(description)) return this;
        }

        final var BUILDER = new DeckBuilder(name);
        return BUILDER.authorName(authorName)
                      .description(newDescription)
                      .schedSettings(schedSettings)
                      .notes(notes)
                      .build();
    }

    public Deck withSchedSettings(DeckSchedSettings newSchedSettings) {
        if (newSchedSettings == null)
            throw new IllegalArgumentException("Illegal newSchedSettings (cannot be null)");

        if (newSchedSettings.equals(schedSettings)) return this;

        final var BUILDER = new DeckBuilder(name);
        return BUILDER.authorName(authorName)
                      .description(description)
                      .schedSettings(newSchedSettings)
                      .notes(notes)
                      .build();
    }


    // COPY METHODS (notes)

    public Deck replaceNotes(Map<Integer, Note> newNotes) {
        if (newNotes == null)
            throw new IllegalArgumentException("Illegal newNotes (cannot be null)");

        if (newNotes.equals(notes)) return this;

        final Map<Integer, Note> DEEP_NOTES = new HashMap<>(newNotes);

        final var BUILDER = new DeckBuilder(name);
        return BUILDER.authorName(authorName)
                      .description(description)
                      .schedSettings(schedSettings)
                      .notes(DEEP_NOTES)
                      .build();
    }

    public Deck addNote(Note note) {
        if (note == null)
            throw new IllegalArgumentException("Illegal note (cannot be null)");

        if (notes.containsValue(note))
            throw new IllegalArgumentException(String.format(
                    "Illegal note (already exists): %s",
                    note
            ));

        final Map<Integer, Note> NEW_NOTES = new HashMap<>(notes);
        NEW_NOTES.put(note.getId(), note);

        final var BUILDER = new DeckBuilder(name);
        return BUILDER.authorName(authorName)
                      .description(description)
                      .schedSettings(schedSettings)
                      .notes(NEW_NOTES)
                      .build();
    }

    public Deck deleteNote(int id) {
        if (!notes.containsKey(id))
            throw new IllegalArgumentException(String.format(
                    "Illegal id (no note exists with this id): %s",
                    id
            ));

        final Map<Integer, Note> NEW_NOTES = new HashMap<>(notes);
        NEW_NOTES.remove(id);

        final var BUILDER = new DeckBuilder(name);
        return BUILDER.authorName(authorName)
                      .description(description)
                      .schedSettings(schedSettings)
                      .notes(NEW_NOTES)
                      .build();
    }

    public Deck updateNote(Note note) {
        if (note == null)
            throw new IllegalArgumentException("Illegal note (cannot be null)");

        if (!notes.containsValue(note))
            throw new IllegalArgumentException(String.format(
                    "Illegal note (not found): %s",
                    note
            ));

        final Map<Integer, Note> NEW_NOTES = new HashMap<>(notes);
        NEW_NOTES.replace(note.getId(), note);

        final var BUILDER = new DeckBuilder(name);
        return BUILDER.authorName(authorName)
                      .description(description)
                      .schedSettings(schedSettings)
                      .notes(NEW_NOTES)
                      .build();
    }


    // STATIC MUTATAORS

    public void setDeckNames(Set<String> deckNames) {
        checkDeckNames(deckNames);

        Deck.deckNames = new HashSet<>(deckNames);
    }


    // BUILDER

    public static final class DeckBuilder {

        // REQUIRED Deck VARIABLES

        private final String name;


        // OPTIONAL Deck VARIABLES

        private String authorName = DEFAULT_AUTHOR_NAME; // Nullable!

        private String description = DEFAULT_DESCRIPTION; // Nullable!

        private DeckSchedSettings schedSettings = DEFAULT_DECK_SCHED_SETTINGS;

        private Map<Integer, Note> notes = DEFAULT_NOTES;


        // CONSTRUCTOR

        public DeckBuilder(String name) {
            this.name = name;
        }


        // METHODS

        public DeckBuilder authorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        public DeckBuilder description(String description) {
            this.description = description;
            return this;
        }

        public DeckBuilder schedSettings(DeckSchedSettings schedSettings) {
            this.schedSettings = schedSettings;
            return this;
        }

        public DeckBuilder notes(Map<Integer, Note> notes) {
            this.notes = notes;
            return this;
        }


        // BUILD

        public Deck build() {
            return new Deck(name, authorName, description, schedSettings, notes);
        }
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "Deck{" +
               "name='" + name + '\'' +
               ", authorName='" + authorName + '\'' +
               ", description='" + description + '\'' +
               ", schedSettings=" + schedSettings +
               ", notes=" + notes +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deck)) return false;
        Deck deck = (Deck) o;
        return name.equals(deck.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
