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

import main.java.data.dto.element.AbstractElementDto;

import java.util.List;

public class CardTypeDto {

    // FIELDS

    private int id;

    private String name;

    private List<AbstractElementDto> front;

    private List<AbstractElementDto> back;


    // ACCESSORS

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<AbstractElementDto> getFront() {
        return front;
    }

    public List<AbstractElementDto> getBack() {
        return back;
    }


    // MUTATAORS

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFront(List<AbstractElementDto> front) {
        this.front = front;
    }

    public void setBack(List<AbstractElementDto> back) {
        this.back = back;
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "CardTypeDto{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", front=" + front +
               ", back=" + back +
               '}';
    }
}
