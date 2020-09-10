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

package main.java.data.dto.structure.note;

import java.util.Map;
import java.util.Set;

public class NoteDto {

    // FIELDS

    private int id;

    private int noteTypeId;

    private Set<String> tags;

    private Map<String, String> fieldData;


    // ACCESSORS

    public int getId() {
        return id;
    }

    public int getNoteTypeId() {
        return noteTypeId;
    }

    public Set<String> getTags() {
        return tags;
    }

    public Map<String, String> getFieldData() {
        return fieldData;
    }


    // MUTATORS

    public NoteDto setId(int id) {
        this.id = id;
        return this;
    }

    public NoteDto setNoteTypeId(int noteTypeId) {
        this.noteTypeId = noteTypeId;
        return this;
    }

    public NoteDto setTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }

    public NoteDto setFieldData(Map<String, String> fieldData) {
        this.fieldData = fieldData;
        return this;
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "NoteDto{" +
               "id=" + id +
               ", noteTypeId=" + noteTypeId +
               ", tags=" + tags +
               ", fieldData=" + fieldData +
               '}';
    }
}
