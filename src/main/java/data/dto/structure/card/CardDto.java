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

package main.java.data.dto.structure.card;

public class CardDto {

    // FIELDS

    private int noteId;

    private int id;

    private CardScheduleDto schedule;

    private boolean isStarred;

    private boolean isHidden;


    // ACCESSORS

    public int getNoteId() {
        return noteId;
    }

    public int getId() {
        return id;
    }

    public CardScheduleDto getSchedule() {
        return schedule;
    }

    public boolean isStarred() {
        return isStarred;
    }

    public boolean isHidden() {
        return isHidden;
    }


    // MUTATORS

    public CardDto setNoteId(int noteId) {
        this.noteId = noteId;
        return this;
    }

    public CardDto setId(int id) {
        this.id = id;
        return this;
    }

    public CardDto setSchedule(CardScheduleDto schedule) {
        this.schedule = schedule;
        return this;
    }

    public CardDto setIsStarred(boolean starred) {
        isStarred = starred;
        return this;
    }

    public CardDto setIsHidden(boolean hidden) {
        isHidden = hidden;
        return this;
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "CardDto{" +
               "noteId=" + noteId +
               ", id=" + id +
               ", schedule=" + schedule +
               ", isStarred=" + isStarred +
               ", isHidden=" + isHidden +
               '}';
    }
}
