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

package main.java.presentation.model.structure.card.sched;

import java.time.LocalDate;
import java.util.Objects;

// Adapted from https://www.supermemo.com/en/archives1990-2015/english/ol/sm2.
public final class CardSchedule {

    // INSTANCE VARIABLES (Scheduling)

    private final double easiness; // Between 1.3 (most difficult) to 2.5 (most difficult).

    private final int totalReviewCount; // Starts from 0, becomes 1 after initial review.

    private final int effectiveReviewCount; // May be reset to zero.

    // Number of correct consecutive reviews from latest review.
    private final int correctStreakCount;

    // Number of incorrect consecutive reviews from latest review.
    private final int incorrectStreakCount;

    private final int currentInterval; // in Days.

    private final LocalDate nextReviewDate;


    // DEFAULT VALUES

    private static final double DEFAULT_EASINESS = 2.5;

    private static final int DEFAULT_COUNT = 0;

    private static final int DEFAULT_CURRENT_INTERVAL = 0;

    // The default nextReviewDate must be determined upon construction of the object. For the
    // application might be active through multiple days.


    // BOUNDARY VALUES

    private static final double MIN_EASINESS = 1.3;

    private static final double MAX_EASINESS = 2.5;

    private static final int MIN_COUNT = 0;


    // CONSTRUCTOR

    private CardSchedule(double easiness,
                         int totalReviewCount,
                         int effectiveReviewCount,
                         int correctStreakCount,
                         int incorrectStreakCount,
                         int currentInterval,
                         LocalDate nextReviewDate) {
        checkArguments(easiness, nextReviewDate);

        this.easiness = easiness;
        this.totalReviewCount = totalReviewCount;
        this.effectiveReviewCount = effectiveReviewCount;
        this.correctStreakCount = correctStreakCount;
        this.incorrectStreakCount = incorrectStreakCount;
        this.currentInterval = currentInterval;
        this.nextReviewDate = nextReviewDate;
    }


    // CHECKERS (Constructor)

    private void checkArguments(double easiness, LocalDate nextReviewDate) {
        checkEasiness(easiness);
        checkNextReviewDate(nextReviewDate);
    }

    private void checkEasiness(double easiness) {
        if (easiness < MIN_EASINESS)
            throw new IllegalArgumentException(String.format(
                    "Illegal easiness (cannot be below %s): %s",
                    MIN_EASINESS,
                    easiness
            ));

        if (easiness > MAX_EASINESS)
            throw new IllegalArgumentException(String.format(
                    "Illegal easiness (cannot be above %s): %s",
                    MAX_EASINESS,
                    easiness
            ));
    }

    private void checkNextReviewDate(LocalDate nextReviewDate) {
        if (nextReviewDate == null)
            throw new IllegalArgumentException("Illegal nextReviewDate (cannot be null)");
    }


    // ACCESSORS (Instance Variables)

    public double getEasiness() {
        return easiness;
    }

    public int getTotalReviewCount() {
        return totalReviewCount;
    }

    public int getEffectiveReviewCount() {
        return effectiveReviewCount;
    }

    public int getCorrectStreakCount() {
        return correctStreakCount;
    }

    public int getIncorrectStreakCount() {
        return incorrectStreakCount;
    }

    public int getCurrentInterval() {
        return currentInterval;
    }

    public LocalDate getNextReviewDate() {
        return nextReviewDate;
    }


    // ACCESSORS (Default Values)

    public static double getDefaultEasiness() {
        return DEFAULT_EASINESS;
    }

    public static int getDefaultCount() {
        return DEFAULT_COUNT;
    }

    public static int getDefaultCurrentInterval() {
        return DEFAULT_CURRENT_INTERVAL;
    }


    // ACCESSORS (Boundary Values)

    public static double getMinEasiness() {
        return MIN_EASINESS;
    }

    public static double getMaxEasiness() {
        return MAX_EASINESS;
    }

    public static int getMinCount() {
        return MIN_COUNT;
    }


    // COPY METHODS

    private CardSchedule withEasiness(double newEasiness) {
        if (newEasiness == easiness) return this;

        final var BUILDER = new CardScheduleBuilder();
        return BUILDER.easiness(newEasiness)
                      .totalReviewCount(totalReviewCount)
                      .effectiveReviewCount(effectiveReviewCount)
                      .correctStreakCount(correctStreakCount)
                      .incorrectStreakCount(incorrectStreakCount)
                      .currentInterval(currentInterval)
                      .nextReviewDate(nextReviewDate)
                      .build();
    }

    private CardSchedule withTotalReviewCount(int newTotalReviewCount) {
        if (newTotalReviewCount == totalReviewCount) return this;

        final var BUILDER = new CardScheduleBuilder();
        return BUILDER.easiness(easiness)
                      .totalReviewCount(newTotalReviewCount)
                      .effectiveReviewCount(effectiveReviewCount)
                      .correctStreakCount(correctStreakCount)
                      .incorrectStreakCount(incorrectStreakCount)
                      .currentInterval(currentInterval)
                      .nextReviewDate(nextReviewDate)
                      .build();
    }

    private CardSchedule withEffectiveReviewCount(int newEffectiveReviewCount) {
        if (newEffectiveReviewCount == effectiveReviewCount) return this;

        final var BUILDER = new CardScheduleBuilder();
        return BUILDER.easiness(easiness)
                      .totalReviewCount(effectiveReviewCount)
                      .effectiveReviewCount(newEffectiveReviewCount)
                      .correctStreakCount(correctStreakCount)
                      .incorrectStreakCount(incorrectStreakCount)
                      .currentInterval(currentInterval)
                      .nextReviewDate(nextReviewDate)
                      .build();
    }

    private CardSchedule withCorrectStreakCount(int newCorrectStreakCount) {
        if (newCorrectStreakCount == correctStreakCount) return this;

        final var BUILDER = new CardScheduleBuilder();
        return BUILDER.easiness(easiness)
                      .totalReviewCount(effectiveReviewCount)
                      .effectiveReviewCount(effectiveReviewCount)
                      .correctStreakCount(newCorrectStreakCount)
                      .incorrectStreakCount(incorrectStreakCount)
                      .currentInterval(currentInterval)
                      .nextReviewDate(nextReviewDate)
                      .build();
    }

    private CardSchedule withIncorrectStreakCount(int newIncorrectStreakCount) {
        if (newIncorrectStreakCount == incorrectStreakCount) return this;

        final var BUILDER = new CardScheduleBuilder();
        return BUILDER.easiness(easiness)
                      .totalReviewCount(effectiveReviewCount)
                      .effectiveReviewCount(effectiveReviewCount)
                      .correctStreakCount(correctStreakCount)
                      .incorrectStreakCount(newIncorrectStreakCount)
                      .currentInterval(currentInterval)
                      .nextReviewDate(nextReviewDate)
                      .build();
    }

    private CardSchedule withCurrentInterval(int newCurrentInterval) {
        if (newCurrentInterval == currentInterval) return this;

        final var BUILDER = new CardScheduleBuilder();
        return BUILDER.easiness(easiness)
                      .totalReviewCount(effectiveReviewCount)
                      .effectiveReviewCount(effectiveReviewCount)
                      .correctStreakCount(correctStreakCount)
                      .incorrectStreakCount(incorrectStreakCount)
                      .currentInterval(newCurrentInterval)
                      .nextReviewDate(nextReviewDate)
                      .build();
    }

    private CardSchedule withNextReviewDate(LocalDate newNextReviewDate) {
        if (newNextReviewDate == null)
            throw new IllegalArgumentException("Illegal newNextReviewDate (cannot be null)");

        if (newNextReviewDate.equals(nextReviewDate)) return this;

        final var BUILDER = new CardScheduleBuilder();
        return BUILDER.easiness(easiness)
                      .totalReviewCount(effectiveReviewCount)
                      .effectiveReviewCount(effectiveReviewCount)
                      .correctStreakCount(correctStreakCount)
                      .incorrectStreakCount(incorrectStreakCount)
                      .currentInterval(currentInterval)
                      .nextReviewDate(newNextReviewDate)
                      .build();
    }


    // BUILDER

    public static final class CardScheduleBuilder {

        // CardSchedule VARIABLES

        private double easiness = DEFAULT_EASINESS;

        private int totalReviewCount = DEFAULT_COUNT;

        private int effectiveReviewCount = DEFAULT_COUNT;

        private int correctStreakCount = DEFAULT_COUNT;

        private int incorrectStreakCount = DEFAULT_COUNT;

        private int currentInterval = DEFAULT_CURRENT_INTERVAL;

        private LocalDate nextReviewDate = LocalDate.now().plusDays(DEFAULT_CURRENT_INTERVAL);


        // METHODS

        public CardScheduleBuilder easiness(double easiness) {
            this.easiness = easiness;
            return this;
        }

        public CardScheduleBuilder totalReviewCount(int totalReviewCount) {
            this.totalReviewCount = totalReviewCount;
            return this;
        }

        public CardScheduleBuilder effectiveReviewCount(int effectiveReviewCount) {
            this.effectiveReviewCount = effectiveReviewCount;
            return this;
        }

        public CardScheduleBuilder correctStreakCount(int correctStreakCount) {
            this.correctStreakCount = correctStreakCount;
            return this;
        }

        public CardScheduleBuilder incorrectStreakCount(int incorrectStreakCount) {
            this.incorrectStreakCount = incorrectStreakCount;
            return this;
        }

        public CardScheduleBuilder currentInterval(int currentInterval) {
            this.currentInterval = currentInterval;
            return this;
        }

        public CardScheduleBuilder nextReviewDate(LocalDate nextReviewDate) {
            this.nextReviewDate = nextReviewDate;
            return this;
        }


        // BUILD

        public CardSchedule build() {
            return new CardSchedule(easiness,
                                    totalReviewCount,
                                    effectiveReviewCount,
                                    correctStreakCount,
                                    incorrectStreakCount,
                                    currentInterval,
                                    nextReviewDate);
        }
    }


    // OBJECT OVERRIDDEN METHODS


    @Override
    public String toString() {
        return "CardSchedule{" +
               "easiness=" + easiness +
               ", totalReviewCount=" + totalReviewCount +
               ", effectiveReviewCount=" + effectiveReviewCount +
               ", correctStreakCount=" + correctStreakCount +
               ", incorrectStreakCount=" + incorrectStreakCount +
               ", currentInterval=" + currentInterval +
               ", nextReviewDate=" + nextReviewDate +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardSchedule)) return false;
        CardSchedule that = (CardSchedule) o;
        return Double.compare(that.easiness, easiness) == 0 &&
               totalReviewCount == that.totalReviewCount &&
               effectiveReviewCount == that.effectiveReviewCount &&
               correctStreakCount == that.correctStreakCount &&
               incorrectStreakCount == that.incorrectStreakCount &&
               currentInterval == that.currentInterval &&
               nextReviewDate.equals(that.nextReviewDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(easiness,
                            totalReviewCount,
                            effectiveReviewCount,
                            correctStreakCount,
                            incorrectStreakCount,
                            currentInterval,
                            nextReviewDate);
    }
}
