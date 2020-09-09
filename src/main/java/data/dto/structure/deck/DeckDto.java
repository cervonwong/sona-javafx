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

package main.java.data.dto.structure.deck;

public class DeckDto {

    // FIELDS

    private String name;

    private String authorName;

    private String description;

    private DeckSchedSettingsDto schedSettings;


    // ACCESSORS

    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDescription() {
        return description;
    }

    public DeckSchedSettingsDto getSchedSettings() {
        return schedSettings;
    }


    // MUTATORS


    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSchedSettings(DeckSchedSettingsDto schedSettings) {
        this.schedSettings = schedSettings;
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "DeckDto{" +
               "name='" + name + '\'' +
               ", authorName='" + authorName + '\'' +
               ", description='" + description + '\'' +
               ", schedSettings=" + schedSettings +
               '}';
    }
}
