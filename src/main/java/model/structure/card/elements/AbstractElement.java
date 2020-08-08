package main.java.model.structure.card.elements;

abstract public class AbstractElement {

    // INSTANCE VARIABLES

    private final String name;


    // CLASS CONSTANTS

    private static final int MAX_NAME_LENGTH = 500;


    // CONSTRUCTOR

    protected AbstractElement(String name) {
        checkName(name);

        this.name = name;
    }


    // CHECK METHODS

    private void checkName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Illegal name (cannot be null)");

        if (name.isEmpty())
            throw new IllegalArgumentException("Illegal name (cannot be empty)");

        if (name.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException("Illegal name (is too long): " + name);
    }


    // ACCESSORS

    public String getName() {
        return name;
    }

    public static int getMaxNameLength() {
        return MAX_NAME_LENGTH;
    }


    // COPY METHODS

    abstract public void withName();
}
