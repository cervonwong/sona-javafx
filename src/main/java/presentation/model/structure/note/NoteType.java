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

import main.java.presentation.model.structure.card.CardType;

import java.util.*;

public final class NoteType {

    // INSTANCE VARIABLES

    private final int id; // Increases per creation from 1.

    private final int nextCardTypeId;

    private final String name;

    private final Map<Integer, CardType> cardTypes; // Key is cardTypeId of Card with this CardType.

    private final List<String> fields;


    // DEFAULT VALUES

    private static final String DEFAULT_NAME = "New Note Type";

    private static final Map<Integer, CardType> DEFAULT_CARD_TYPES = new HashMap<>();

    private static final List<String> DEFAULT_FIELDS = new ArrayList<>();


    // BOUNDARY VALUES

    private static final int MAX_NAME_LENGTH = 500;

    private static final int MAX_CARD_TYPES = 50;

    private static final int MAX_FIELDS = 200;

    private static final int MAX_FIELD_LENGTH = 200;


    // CONSTRUCTOR

    private NoteType(int id,
                     int nextCardTypeId,
                     String name,
                     Map<Integer, CardType> cardTypes,
                     List<String> fields) {
        checkArguments(name, cardTypes, fields);

        this.id = id;
        this.nextCardTypeId = nextCardTypeId;
        this.name = name;
        this.cardTypes = new HashMap<>(cardTypes);
        this.fields = new ArrayList<>(fields);
    }


    // CHECK METHODS (Constructor)

    private void checkArguments(String name,
                                Map<Integer, CardType> cardTypes,
                                List<String> fields) {
        checkName(name);
        checkCardTypes(cardTypes);
        checkFields(fields);
    }

    private void checkName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Illegal name (cannot be null)");

        if (name.isEmpty())
            throw new IllegalArgumentException("Illegal name (cannot be empty)");

        if (name.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException(String.format(
                    "Illegal name (is longer than %s characters): %s",
                    MAX_NAME_LENGTH,
                    name
            ));
    }

    private void checkCardTypes(Map<Integer, CardType> cardTypes) {
        if (cardTypes == null)
            throw new IllegalArgumentException("Illegal cardTypes (cannot be null)");

        for (Integer integer : cardTypes.keySet())
            if (integer == null)
                throw new IllegalArgumentException("Illegal cardTypes key (cannot be null)");

        for (CardType value : cardTypes.values())
            if (value == null)
                throw new IllegalArgumentException("Illegal cardTypes value (cannot be null)");
    }

    private void checkFields(List<String> fields) {
        if (fields == null)
            throw new IllegalArgumentException("Illegal fields (cannot be null)");

        for (String field : fields) {
            if (field == null)
                throw new IllegalArgumentException("Illegal field in fields (cannot be null)");

            if (field.length() > MAX_FIELD_LENGTH)
                throw new IllegalArgumentException(String.format(
                        "Illegal field in fields (cannot be longer than %s characters): %s",
                        MAX_FIELD_LENGTH,
                        field
                ));
        }

        if (new HashSet<>(fields).size() < fields.size())
            throw new IllegalArgumentException("Illegal fields (contains duplicates)");
    }


    // CHECK METHODS (Copy Methods)

    private void checkCardTypeExistence(int id) {
        if (!cardTypes.containsKey(id))
            throw new IllegalArgumentException(String.format(
                    "Illegal id (no CardType with this id): %s",
                    id
            ));
    }

    private void checkFieldExistence(int index) {
        if (!(index >= 0 && index < fields.size()))
            throw new IllegalArgumentException(String.format(
                    "Illegal index (out of bounds 0 to %s inclusive): %s",
                    fields.size() - 1,
                    index
            ));
    }

    // ACCESSORS (Instance Variables)

    public int getId() {
        return id;
    }

    public int getNextCardTypeId() {
        return nextCardTypeId;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, CardType> getCardTypes() {
        return new HashMap<>(cardTypes);
    }

    public List<String> getFields() {
        return new ArrayList<>(fields);
    }


    // ACCESSORS (Default Values)

    public static String getDefaultName() {
        return DEFAULT_NAME;
    }

    public static Map<Integer, CardType> getDefaultCardTypes() {
        return new HashMap<>(DEFAULT_CARD_TYPES);
    }

    public static List<String> getDefaultFields() {
        return new ArrayList<>(DEFAULT_FIELDS);
    }


    // ACCESSORS (Boundary Values)

    public static int getMaxNameLength() {
        return MAX_NAME_LENGTH;
    }

    public static int getMaxCardTypes() {
        return MAX_CARD_TYPES;
    }

    public static int getMaxFields() {
        return MAX_FIELDS;
    }


    // COPY METHODS

    public NoteType withName(String newName) {
        if (newName == null)
            throw new IllegalArgumentException("Illegal newName (cannot be null)");

        if (newName.equals(name)) return this;

        final var BUILDER = new NoteTypeBuilder(id, nextCardTypeId);
        return BUILDER.name(newName)
                      .cardTypes(cardTypes)
                      .fields(fields)
                      .build();
    }

    public NoteType addCardType(CardType cardType) {
        if (cardType == null)
            throw new IllegalArgumentException("Illegal cardType (cannot be null)");

        final Map<Integer, CardType> NEW_CARD_TYPES = new HashMap<>(cardTypes);
        NEW_CARD_TYPES.put(nextCardTypeId, cardType);

        // TODO: 23/08/2020 Extract incrementation of nextCardTypeId.
        final var BUILDER = new NoteTypeBuilder(id, nextCardTypeId + 1);
        return BUILDER.name(name)
                      .cardTypes(NEW_CARD_TYPES)
                      .fields(fields)
                      .build();
    }

    public NoteType deleteCardType(int id) {
        checkCardTypeExistence(id);

        final Map<Integer, CardType> NEW_CARD_TYPES = new HashMap<>(cardTypes);
        NEW_CARD_TYPES.remove(id);

        final var BUILDER = new NoteTypeBuilder(id, nextCardTypeId);
        return BUILDER.name(name)
                      .cardTypes(NEW_CARD_TYPES)
                      .fields(fields)
                      .build();
    }

    public NoteType updateCardType(CardType cardType, int id) {
        checkCardTypeExistence(id);

        if (cardType.equals(cardTypes.get(id))) return this;

        final Map<Integer, CardType> NEW_CARD_TYPES = new HashMap<>(cardTypes);
        NEW_CARD_TYPES.put(id, cardType);

        final var BUILDER = new NoteTypeBuilder(id, nextCardTypeId);
        return BUILDER.name(name)
                      .cardTypes(NEW_CARD_TYPES)
                      .fields(fields)
                      .build();
    }

    public NoteType replaceFields(List<String> newFields) {
        if (newFields == null)
            throw new IllegalArgumentException("Illegal newFields (cannot be null)");

        if (newFields.equals(fields)) return this;

        final List<String> DEEP_FIELDS = new ArrayList<>(newFields);

        final var BUILDER = new NoteTypeBuilder(id, nextCardTypeId);
        return BUILDER.name(name)
                      .cardTypes(cardTypes)
                      .fields(DEEP_FIELDS)
                      .build();
    }

    public NoteType addField(String field) {
        if (field == null)
            throw new IllegalArgumentException("Illegal field (cannot be null)");

        final List<String> NEW_FIELDS = new ArrayList<>(fields);
        NEW_FIELDS.add(field);

        final var BUILDER = new NoteTypeBuilder(id, nextCardTypeId);
        return BUILDER.name(name)
                      .cardTypes(cardTypes)
                      .fields(NEW_FIELDS)
                      .build();
    }

    public NoteType deleteField(int index) {
        checkFieldExistence(index);

        final List<String> NEW_FIELDS = new ArrayList<>(fields);
        NEW_FIELDS.remove(index);

        final var BUILDER = new NoteTypeBuilder(id, nextCardTypeId);
        return BUILDER.name(name)
                      .cardTypes(cardTypes)
                      .fields(NEW_FIELDS)
                      .build();
    }

    public NoteType renameField(String field, int index) {
        checkFieldExistence(index);

        if (field == null)
            throw new IllegalArgumentException("Illegal field (cannot be null)");

        if (field.equals(fields.get(index))) return this;

        final List<String> NEW_FIELDS = new ArrayList<>(fields);
        NEW_FIELDS.set(index, field);

        final var BUILDER = new NoteTypeBuilder(id, nextCardTypeId);
        return BUILDER.name(name)
                      .cardTypes(cardTypes)
                      .fields(NEW_FIELDS)
                      .build();
    }


    // BUILDER

    public static final class NoteTypeBuilder {

        // REQUIRED NoteType VARIABLES

        private final int id;

        private final int nextCardTypeId;


        // OPTIONAL NoteType VARIABLES

        private String name = DEFAULT_NAME;

        private Map<Integer, CardType> cardTypes = DEFAULT_CARD_TYPES;

        private List<String> fields = DEFAULT_FIELDS;


        // CONSTRUCTOR

        public NoteTypeBuilder(int id, int nextCardTypeId) {
            this.id = id;
            this.nextCardTypeId = nextCardTypeId;
        }


        // METHODS

        public NoteTypeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public NoteTypeBuilder cardTypes(Map<Integer, CardType> cardTypes) {
            this.cardTypes = cardTypes;
            return this;
        }

        public NoteTypeBuilder fields(List<String> fields) {
            this.fields = fields;
            return this;
        }


        // BUILD

        public NoteType build() {
            return new NoteType(id, nextCardTypeId, name, cardTypes, fields);
        }
    }


    // OBJECT OVERRIDDEN METHODS


    @Override
    public String toString() {
        return "NoteType{" +
               "id=" + id +
               ", nextCardTypeId=" + nextCardTypeId +
               ", name='" + name + '\'' +
               ", cardTypes=" + cardTypes +
               ", fields=" + fields +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoteType)) return false;
        NoteType noteType = (NoteType) o;
        return id == noteType.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
