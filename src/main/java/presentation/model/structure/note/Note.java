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

import java.util.*;

public final class Note {

    // INSTANCE VARIABLES

    private final int id;

    private final int noteTypeId;

    private final int deckId;

    private final Map<String, String> fieldData;

    private final Set<String> tags;


    // DEFAULT VALUES

    private static final Map<String, String> DEFAULT_FIELD_DATA = new HashMap<>();

    private static final Set<String> DEFAULT_TAGS = new HashSet<>();


    // BOUNDARY VALUES

    private static final int MAX_TAG_LENGTH = 200;


    // CONSTRUCTOR

    private Note(int id,
                 int noteTypeId,
                 int deckId,
                 Map<String, String> fieldData,
                 Set<String> tags) {
        checkArguments(fieldData, tags);

        this.id = id;
        this.noteTypeId = noteTypeId;
        this.deckId = deckId;
        this.fieldData = new HashMap<>(fieldData);
        this.tags = new HashSet<>(tags);
    }


    // CHECKERS (Constructor)

    private void checkArguments(Map<String, String> fieldData, Set<String> tags) {
        checkFieldData(fieldData);
        checkTags(tags);
    }

    private void checkFieldData(Map<String, String> fieldData) {
        if (fieldData == null)
            throw new IllegalArgumentException("Illegal fieldData (cannot be null)");

        for (String field : fieldData.keySet())
            if (field == null)
                throw new IllegalArgumentException("Illegal field in fieldData (cannot be null)");

        for (String datum : fieldData.values())
            if (datum == null)
                throw new IllegalArgumentException("Illegal datum in fieldData (cannot be null)");
    }

    private void checkTags(Set<String> tags) {
        if (tags == null)
            throw new IllegalArgumentException("Illegal tags (cannot be null)");

        for (String tag : tags)
            if (tag == null)
                throw new IllegalArgumentException("Illegal tag in tags (cannot be null)");
    }


    // ACCESSORS (Instance Variables)

    public int getId() {
        return id;
    }

    public int getNoteTypeId() {
        return noteTypeId;
    }

    public int getDeckId() {
        return deckId;
    }

    public Map<String, String> getFieldData() {
        return new HashMap<>(fieldData);
    }

    public Set<String> getTags() {
        return new HashSet<>(tags);
    }


    // ACCESSORS (Default Values)

    public static Map<String, String> getDefaultFieldData() {
        return new HashMap<>(DEFAULT_FIELD_DATA);
    }

    public static Set<String> getDefaultTags() {
        return new HashSet<>(DEFAULT_TAGS);
    }


    // ACCESSORS (Boundary Values)

    public static int getMaxTagLength() {
        return MAX_TAG_LENGTH;
    }


    // COPY METHODS

    public Note replaceFieldData(Map<String, String> newFieldData) {
        if (newFieldData == null)
            throw new IllegalArgumentException("Illegal newFieldData (cannot be null)");

        if (newFieldData.equals(fieldData)) return this;

        final Map<String, String> DEEP_FIELD_DATA = new HashMap<>(newFieldData);

        final var BUILDER = new NoteBuilder(id, noteTypeId, deckId);
        return BUILDER.fieldData(DEEP_FIELD_DATA)
                      .tags(tags)
                      .build();
    }

    public Note addFieldDatum(String field, String datum) {
        if (field == null)
            throw new IllegalArgumentException("Illegal field (cannot be null)");

        if (datum == null)
            throw new IllegalArgumentException("Illegal datum (cannot be null");

        if (fieldData.containsKey(field))
            throw new IllegalArgumentException(String.format(
                    "Illegal field (already exists, try calling updateFieldDatum): %s",
                    field
            ));

        final Map<String, String> NEW_FIELD_DATA = new HashMap<>(fieldData);
        NEW_FIELD_DATA.put(field, datum);

        final var BUILDER = new NoteBuilder(id, noteTypeId, deckId);
        return BUILDER.fieldData(NEW_FIELD_DATA)
                      .tags(tags)
                      .build();
    }

    public Note updateFieldDatum(String field, String datum) {
        if (field == null)
            throw new IllegalArgumentException("Illegal field (cannot be null)");

        if (datum == null)
            throw new IllegalArgumentException("Illegal field (cannot be null)");

        if (!fieldData.containsKey(field))
            throw new IllegalArgumentException(String.format(
                    "Illegal field (not found, try calling addFieldDatum): %s",
                    field
            ));

        if (fieldData.get(field).equals(datum)) return this;

        final Map<String, String> NEW_FIELD_DATA = new HashMap<>(fieldData);
        NEW_FIELD_DATA.put(field, datum);

        final var BUILDER = new NoteBuilder(id, noteTypeId, deckId);
        return BUILDER.fieldData(NEW_FIELD_DATA)
                      .tags(tags)
                      .build();
    }

    public Note deleteFieldDatum(String field) {
        if (field == null)
            throw new IllegalArgumentException("Illegal field (cannot be null)");

        if (!fieldData.containsKey(field))
            throw new IllegalArgumentException(String.format(
                    "Illegal field (not found) %s",
                    field
            ));

        final Map<String, String> NEW_FIELD_DATA = new HashMap<>(fieldData);
        NEW_FIELD_DATA.remove(field);

        final var BUILDER = new NoteBuilder(id, noteTypeId, deckId);
        return BUILDER.fieldData(NEW_FIELD_DATA)
                      .tags(tags)
                      .build();
    }

    public Note replaceTags(Set<String> newTags) {
        if (newTags == null)
            throw new IllegalArgumentException("Illegal newTags (cannot be null)");

        if (newTags.equals(tags)) return this;

        final Set<String> DEEP_TAGS = new HashSet<>(newTags);

        final var BUILDER = new NoteBuilder(id, noteTypeId, deckId);
        return BUILDER.fieldData(fieldData)
                      .tags(DEEP_TAGS)
                      .build();
    }

    public Note addTag(String tag) {
        if (tags == null)
            throw new IllegalArgumentException("Illegal tag (cannot be null)");

        if (tags.contains(tag))
            throw new IllegalArgumentException(String.format(
                    "Illegal tag (already exists): %s",
                    tag
            ));

        final Set<String> NEW_TAGS = new HashSet<>(tags);
        NEW_TAGS.add(tag);

        final var BUILDER = new NoteBuilder(id, noteTypeId, deckId);
        return BUILDER.fieldData(fieldData)
                      .tags(NEW_TAGS)
                      .build();
    }

    public Note deleteTag(String tag) {
        if (tag == null)
            throw new IllegalArgumentException("Illegal tag (cannot be null)");

        if (!tags.contains(tag))
            throw new IllegalArgumentException("Illegal tag (not found)");

        final Set<String> NEW_TAGS = new HashSet<>(tags);
        NEW_TAGS.remove(tag);

        final var BUILDER = new NoteBuilder(id, noteTypeId, deckId);
        return BUILDER.fieldData(fieldData)
                      .tags(NEW_TAGS)
                      .build();
    }


    // BUILDER

    public static final class NoteBuilder {

        // REQUIRED Note VARIABLES

        private final int id;

        private final int noteTypeId;

        private final int deckId;


        // OPTIONAL Note VARIABLES

        private Map<String, String> fieldData = DEFAULT_FIELD_DATA;

        private Set<String> tags = DEFAULT_TAGS;


        // CONSTRUCTOR

        public NoteBuilder(int id, int noteTypeId, int deckId) {
            this.id = id;
            this.noteTypeId = noteTypeId;
            this.deckId = deckId;
        }


        // METHODS

        public NoteBuilder fieldData(Map<String, String> fieldData) {
            this.fieldData = fieldData;
            return this;
        }

        public NoteBuilder tags(Set<String> tags) {
            this.tags = tags;
            return this;
        }


        // BUILDER

        public Note build() {
            return new Note(id, noteTypeId, deckId, fieldData, tags);
        }
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "Note{" +
               "id=" + id +
               ", noteTypeId=" + noteTypeId +
               ", deckId=" + deckId +
               ", fieldData=" + fieldData +
               ", tags=" + tags +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note note = (Note) o;
        return id == note.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
