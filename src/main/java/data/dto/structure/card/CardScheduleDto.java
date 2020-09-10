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

import java.time.LocalDate;

public class CardScheduleDto {

    // FIELDS

    private double ef;

    private int trc;

    private int erc;

    private int csc;

    private int isc;

    private int ci;

    private LocalDate nrd;


    // ACCESSORS

    public double getEf() {
        return ef;
    }

    public int getTrc() {
        return trc;
    }

    public int getErc() {
        return erc;
    }

    public int getCsc() {
        return csc;
    }

    public int getIsc() {
        return isc;
    }

    public int getCi() {
        return ci;
    }

    public LocalDate getNrd() {
        return nrd;
    }


    // MUTATORS

    public CardScheduleDto setEf(double ef) {
        this.ef = ef;
        return this;
    }

    public CardScheduleDto setTrc(int trc) {
        this.trc = trc;
        return this;
    }

    public CardScheduleDto setErc(int erc) {
        this.erc = erc;
        return this;
    }

    public CardScheduleDto setCsc(int csc) {
        this.csc = csc;
        return this;
    }

    public CardScheduleDto setIsc(int isc) {
        this.isc = isc;
        return this;
    }

    public CardScheduleDto setCi(int ci) {
        this.ci = ci;
        return this;
    }

    public CardScheduleDto setNrd(LocalDate nrd) {
        this.nrd = nrd;
        return this;
    }


    // OBJECT OVERRIDDEN METHODS


    @Override
    public String toString() {
        return "CardScheduleDto{" +
               "easiness=" + ef +
               ", totalReviewCount=" + trc +
               ", effectiveReviewCount=" + erc +
               ", correctStreakCount=" + csc +
               ", incorrectStreakCount=" + isc +
               ", currentInterval=" + ci +
               ", nextReviewDate=" + nrd +
               '}';
    }
}
