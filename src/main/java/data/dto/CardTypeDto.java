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
