package main.java.data.dto;

import java.util.List;

public class NoteTypeDto {

    // FIELDS

    private int id;

    private String name;

    private int nextCardTypeId;

    private List<CardTypeDto> cardTypes;

    private List<String> fields;


    // CONSTRUCTOR

    public NoteTypeDto() {}


    // ACCESSORS

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNextCardTypeId() {
        return nextCardTypeId;
    }

    public List<CardTypeDto> getCardTypes() {
        return cardTypes;
    }

    public List<String> getFields() {
        return fields;
    }


    // MUTATORS

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNextCardTypeId(int nextCardTypeId) {
        this.nextCardTypeId = nextCardTypeId;
    }

    public void setCardTypes(List<CardTypeDto> cardTypes) {
        this.cardTypes = cardTypes;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "NoteTypeDto{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", nextCardTypeId=" + nextCardTypeId +
               ", cardTypes=" + cardTypes +
               ", fields=" + fields +
               '}';
    }
}
