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

package main.java.data.dto.types.element;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DividerElementDto.class, name = "DIVIDER"),
        @JsonSubTypes.Type(value = RowElementDto.class, name = "ROW"),
        @JsonSubTypes.Type(value = TextElementDto.class, name = "TEXT")
})
// Reference -> https://programmerbruce.blogspot.com/2011/05/deserialize-json-with-jackson-into.html
public class AbstractElementDto {

    // FIELDS

    private String name;


    // CONSTRUCTOR

    public AbstractElementDto() {}


    // ACCESSORS

    public String getName() {
        return name;
    }


    // MUTATORS

    public void setName(String name) {
        this.name = name;
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "AbstractElementDto{" +
               "name='" + name + '\'' +
               '}';
    }
}
