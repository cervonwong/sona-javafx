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

package main.java.data.dto;

import java.util.List;
import java.util.Set;

public class NoteTypeDto {

    // FIELDS

    private int id;

    private String name;

    private int nextCardTypeId;

    private List<CardTypeDto> cardTypes;

    private Set<String> fields;


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

    public Set<String> getFields() {
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

    public void setFields(Set<String> fields) {
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
