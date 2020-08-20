package main.java.model.structure.note;

// This class is created by NoteTypePropertiesDao.
public class NoteTypeProperties {

    // INSTANCE VARIABLES

    private int nextId;


    // CONSTRUCTOR

    public NoteTypeProperties() {}


    // ACCESSORS

    public int getNextId() {
        return nextId;
    }


    // MUTATORS

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "NoteTypeProperties{" +
               "nextId=" + nextId +
               '}';
    }
}
