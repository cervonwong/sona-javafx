package main.java.presentation.model.structure.card.element;

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
            throw new IllegalArgumentException(String.format(
                    "Illegal name (is longer than %s characters): %s",
                    MAX_NAME_LENGTH,
                    name
            ));
    }


    // ACCESSORS

    public String getName() {
        return name;
    }

    public static int getMaxNameLength() {
        return MAX_NAME_LENGTH;
    }


    // COPY METHODS

    abstract public AbstractElement withName(String newName);


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "AbstractElement{" +
               "name='" + name + '\'' +
               '}';
    }
}
