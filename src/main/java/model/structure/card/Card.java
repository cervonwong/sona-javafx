package main.java.model.structure.card;

import java.util.Objects;

public class Card {

    // INSTANCE VARIABLES (Identifiers)

    private final int noteId;

    private final int index;


    // INSTANCE VARIABLES (Scheduling)

    private final CardSchedule schedule;

    private final boolean isStarred;

    private final boolean isHidden;


    // DEFAULT VALUES

    private static final CardSchedule DEFAULT_SCHEDULE =
            new CardSchedule.CardScheduleBuilder().build();

    private static final boolean DEFAULT_IS_STARRED = false;

    private static final boolean DEFAULT_IS_HIDDEN = false;


    // CONSTRUCTOR

    private Card(int noteId,
                 int index,
                 CardSchedule schedule,
                 boolean isStarred,
                 boolean isHidden) {
        checkArguments(schedule);

        this.noteId = noteId;
        this.index = index;
        this.schedule = schedule;
        this.isStarred = isStarred;
        this.isHidden = isHidden;
    }


    // CHECK METHODS

    private void checkArguments(CardSchedule schedule) {
        // Does not check noteId and index.
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

    public int getIndex() {
        return index;
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

        final var BUILDER = new CardBuilder(noteId, index);
        return BUILDER.schedule(newSchedule)
                      .isStarred(isStarred)
                      .isHidden(isHidden)
                      .build();
    }

    public Card withIsStarred(boolean newIsStarred) {
        if (newIsStarred == isStarred) return this;

        final var BUILDER = new CardBuilder(noteId, index);
        return BUILDER.schedule(schedule)
                      .isStarred(newIsStarred)
                      .isHidden(isHidden)
                      .build();
    }

    public Card withIsHidden(boolean newIsHidden) {
        if (newIsHidden == isHidden) return this;

        final var BUILDER = new CardBuilder(noteId, index);
        return BUILDER.schedule(schedule)
                      .isStarred(isStarred)
                      .isHidden(newIsHidden)
                      .build();
    }


    // BUILDER

    public final static class CardBuilder {

        // REQUIRED Card VARIABLES

        private int noteId;

        private int index;


        // OPTIONAL Card VARIABLES

        private CardSchedule schedule = DEFAULT_SCHEDULE;

        private boolean isStarred = DEFAULT_IS_STARRED;

        private boolean isHidden = DEFAULT_IS_HIDDEN;


        // CONSTRUCTOR

        public CardBuilder(int noteId, int index) {
            this.noteId = noteId;
            this.index = index;
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
            return new Card(noteId, index, schedule, isStarred, isHidden);
        }
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "Card{" +
               "noteId=" + noteId +
               ", index=" + index +
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
               index == card.index;
    }

    // SEMANTIC HASH
    @Override
    public int hashCode() {
        return Objects.hash(noteId, index);
    }
}
