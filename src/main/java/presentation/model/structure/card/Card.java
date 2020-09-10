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

package main.java.presentation.model.structure.card;

import main.java.presentation.model.structure.card.sched.CardSchedule;

import java.util.Objects;

public final class Card {

    // INSTANCE VARIABLES (Identifiers)

    private final int noteId;

    private final int id;


    // INSTANCE VARIABLES (Scheduling)

    private final CardSchedule schedule; // User-uneditable scheduling data.

    private final boolean isStarred; // User-editable scheduling data.

    private final boolean isHidden; // User-editable scheduling data.


    // DEFAULT VALUES

    private static final CardSchedule DEFAULT_SCHEDULE =
            new CardSchedule.CardScheduleBuilder().build();

    private static final boolean DEFAULT_IS_STARRED = false;

    private static final boolean DEFAULT_IS_HIDDEN = false;


    // CONSTRUCTOR

    private Card(int noteId,
                 int id,
                 CardSchedule schedule,
                 boolean isStarred,
                 boolean isHidden) {
        checkArguments(schedule);

        this.noteId = noteId;
        this.id = id;
        this.schedule = schedule;
        this.isStarred = isStarred;
        this.isHidden = isHidden;
    }


    // CHECK METHODS

    private void checkArguments(CardSchedule schedule) {
        // Does not check noteId and id.
        checkSchedule(schedule);
        // Does not check isStarred and isHidden.
    }

    private void checkSchedule(CardSchedule schedule) {
        if (schedule == null)
            throw new IllegalArgumentException("Illegal schedule (cannot be null)");
    }


    // ACCESSORS (Instance Variables)

    public int getNoteId() {
        return noteId;
    }

    public int getId() {
        return id;
    }

    public CardSchedule getSchedule() {
        return schedule;
    }

    public boolean isStarred() {
        return isStarred;
    }

    public boolean isHidden() {
        return isHidden;
    }


    // ACCESSORS (Default Variables)

    public static CardSchedule getDefaultSchedule() {
        return DEFAULT_SCHEDULE;
    }

    public static boolean isStarredByDefault() {
        return DEFAULT_IS_STARRED;
    }

    public static boolean isHiddenByDefault() {
        return DEFAULT_IS_HIDDEN;
    }


    // COPY METHODS

    public Card withSchedule(CardSchedule newSchedule) {
        if (newSchedule == null)
            throw new IllegalArgumentException("Illegal newSchedule (cannot be null)");

        if (newSchedule.equals(schedule)) return this;

        final var BUILDER = new CardBuilder(noteId, id);
        return BUILDER.schedule(newSchedule)
                      .isStarred(isStarred)
                      .isHidden(isHidden)
                      .build();
    }

    public Card withIsStarred(boolean newIsStarred) {
        if (newIsStarred == isStarred) return this;

        final var BUILDER = new CardBuilder(noteId, id);
        return BUILDER.schedule(schedule)
                      .isStarred(newIsStarred)
                      .isHidden(isHidden)
                      .build();
    }

    public Card withIsHidden(boolean newIsHidden) {
        if (newIsHidden == isHidden) return this;

        final var BUILDER = new CardBuilder(noteId, id);
        return BUILDER.schedule(schedule)
                      .isStarred(isStarred)
                      .isHidden(newIsHidden)
                      .build();
    }


    // BUILDER

    public final static class CardBuilder {

        // REQUIRED Card VARIABLES

        private final int noteId;

        private final int id;


        // OPTIONAL Card VARIABLES

        private CardSchedule schedule = DEFAULT_SCHEDULE;

        private boolean isStarred = DEFAULT_IS_STARRED;

        private boolean isHidden = DEFAULT_IS_HIDDEN;


        // CONSTRUCTOR

        public CardBuilder(int noteId, int id) {
            this.noteId = noteId;
            this.id = id;
        }


        // METHODS

        public CardBuilder schedule(CardSchedule schedule) {
            this.schedule = schedule;
            return this;
        }

        public CardBuilder isStarred(boolean isStarred) {
            this.isStarred = isStarred;
            return this;
        }

        public CardBuilder isHidden(boolean isHidden) {
            this.isHidden = isHidden;
            return this;
        }

        // BUILD

        public Card build() {
            return new Card(noteId, id, schedule, isStarred, isHidden);
        }
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "Card{" +
               "noteId=" + noteId +
               ", id=" + id +
               ", schedule=" + schedule +
               ", isStarred=" + isStarred +
               ", isHidden=" + isHidden +
               '}';
    }

    // SEMANTIC EQUALS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return noteId == card.noteId &&
               id == card.id;
    }

    // SEMANTIC HASH
    @Override
    public int hashCode() {
        return Objects.hash(noteId, id);
    }
}
