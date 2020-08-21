package main.java.model.structure.card;

import main.java.model.structure.card.element.AbstractElement;

import java.util.*;

public final class CardType {

    // INSTANCE VARIABLES

    private final String name;

    private final List<AbstractElement> front;

    private final List<AbstractElement> back;


    // DEFAULT VALUES

    private static final String DEFAULT_NAME = "New Card Type";

    private static final List<AbstractElement> DEFAULT_FRONT = new ArrayList<>();

    private static final List<AbstractElement> DEFAULT_BACK = new ArrayList<>();


    // BOUNDARY VALUES

    private static final int MAX_NAME_LENGTH = 300;


    // CONSTRUCTOR

    private CardType(String name, List<AbstractElement> front, List<AbstractElement> back) {
        checkArguments(name, front, back);

        this.name = name;
        this.front = new ArrayList<>(front);
        this.back = new ArrayList<>(back);
    }


    // CHECK METHODS (Constructor)

    private void checkArguments(String name,
                                List<AbstractElement> front,
                                List<AbstractElement> back) {
        checkName(name);
        checkFace(front, "front");
        checkFace(back, "back");
    }

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

    private void checkFace(List<AbstractElement> face, String faceName) {
        if (face == null)
            throw new IllegalArgumentException(String.format(
                    "Illegal %s (cannot be null)",
                    faceName
            ));

        for (AbstractElement abstractElement : face) {
            if (abstractElement == null)
                throw new IllegalArgumentException(String.format(
                        "Illegal %s (a child is null)",
                        faceName
                ));
        }
    }


    // CHECK METHODS (Copy Methods)

    private void checkInsertableInFront(int index) {
        if (!(index >= 0 && index <= front.size()))
            throw new IllegalArgumentException(String.format(
                    "Illegal index (out of bounds 0 to %s inclusive): %s",
                    front.size(),
                    index
            ));
    }

    private void checkInsertableInBack(int index) {
        if (!(index >= 0 && index <= back.size()))
            throw new IllegalArgumentException(String.format(
                    "Illegal index (out of bounds 0 to %s inclusive): %s",
                    back.size(),
                    index
            ));
    }

    private void checkExistsInFront(int index) {
        if (!(index >= 0 && index < front.size()))
            throw new IllegalArgumentException(String.format(
                    "Illegal index (out of bounds 0 to %s inclusive): %s",
                    front.size() - 1,
                    index
            ));
    }

    private void checkExistsInBack(int index) {
        if (!(index >= 0 && index < back.size()))
            throw new IllegalArgumentException(String.format(
                    "Illegal index (out of bounds 0 to %s inclusive): %s",
                    back.size() - 1,
                    index
            ));
    }


    // ACCESSORS (Instance Variables)

    public String getName() {
        return name;
    }

    public List<AbstractElement> getFront() {
        return new ArrayList<>(front);
    }

    public List<AbstractElement> getBack() {
        return new ArrayList<>(back);
    }


    // ACCESSORS (Default Values)

    public static String getDefaultName() {
        return DEFAULT_NAME;
    }

    public static List<AbstractElement> getDefaultFront() {
        return new ArrayList<>(DEFAULT_FRONT);
    }

    public static List<AbstractElement> getDefaultBack() {
        return new ArrayList<>(DEFAULT_BACK);
    }


    // ACCESSORS (Boundary Values)

    public static int getMaxNameLength() {
        return MAX_NAME_LENGTH;
    }


    // COPY METHODS

    public CardType withName(String newName) {
        if (name == null)
            throw new IllegalArgumentException("Illegal newName (cannot be null)");

        if (newName.equals(name)) return this;

        final var BUILDER = new CardTypeBuilder();
        return BUILDER.name(newName)
                      .front(front)
                      .back(back)
                      .build();
    }

    public CardType replaceFront(List<AbstractElement> newFront) {
        if (newFront == null)
            throw new IllegalArgumentException("Illegal newFront (cannot be null)");

        if (newFront.equals(front)) return this;

        final List<AbstractElement> DEEP_FRONT = new ArrayList<>(newFront);

        final var BUILDER = new CardTypeBuilder();
        return BUILDER.name(name)
                      .front(DEEP_FRONT)
                      .back(back)
                      .build();
    }

    public CardType replaceBack(List<AbstractElement> newBack) {
        if (newBack == null)
            throw new IllegalArgumentException("Illegal newBack (cannot be null)");

        if (newBack.equals(back)) return this;

        final List<AbstractElement> DEEP_BACK = new ArrayList<>(newBack);

        final var BUILDER = new CardTypeBuilder();
        return BUILDER.name(name)
                      .front(front)
                      .back(DEEP_BACK)
                      .build();
    }

    public CardType insertElementInFront(AbstractElement element, int index) {
        if (element == null)
            throw new IllegalArgumentException("Illegal element (cannot be null)");

        checkInsertableInFront(index);

        final List<AbstractElement> NEW_FRONT = new ArrayList<>(front);
        NEW_FRONT.add(index, element);

        final var BUILDER = new CardTypeBuilder();
        return BUILDER.name(name)
                      .front(NEW_FRONT)
                      .back(back)
                      .build();
    }

    public CardType insertElementInBack(AbstractElement element, int index) {
        if (element == null)
            throw new IllegalArgumentException("Illegal element (cannot be null)");

        checkInsertableInBack(index);

        final List<AbstractElement> NEW_BACK = new ArrayList<>(back);
        NEW_BACK.add(index, element);

        final var BUILDER = new CardTypeBuilder();
        return BUILDER.name(name)
                      .front(front)
                      .back(NEW_BACK)
                      .build();
    }

    public CardType deleteElementInFront(int index) {
        checkExistsInFront(index);

        final List<AbstractElement> NEW_BACK = new ArrayList<>(front);
        NEW_BACK.remove(index);

        final var BUILDER = new CardTypeBuilder();
        return BUILDER.name(name)
                      .front(NEW_BACK)
                      .back(back)
                      .build();
    }

    public CardType deleteElementInBack(int index) {
        checkExistsInBack(index);

        final List<AbstractElement> NEW_BACK = new ArrayList<>(back);
        NEW_BACK.remove(index);

        final var BUILDER = new CardTypeBuilder();
        return BUILDER.name(name)
                      .front(front)
                      .back(NEW_BACK)
                      .build();
    }

    public CardType updateElementInFront(AbstractElement element, int index) {
        return null;
        // FIXME: 21/08/2020 IMPLEMENT METHOD.
    }

    public CardType updateElementInBack(AbstractElement element, int index) {
        return null;
        // FIXME: 21/08/2020 IMPLEMENT METHOD.
    }


    // BUILDER

    public final static class CardTypeBuilder {

        // CardType VARIABLES

        private String name = DEFAULT_NAME;

        private List<AbstractElement> front = DEFAULT_FRONT;

        private List<AbstractElement> back = DEFAULT_BACK;


        // CONSTRUCTOR

        public CardTypeBuilder() {}


        // METHODS

        public CardTypeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CardTypeBuilder front(List<AbstractElement> front) {
            this.front = front;
            return this;
        }

        public CardTypeBuilder back(List<AbstractElement> back) {
            this.back = back;
            return this;
        }


        // BUILD

        public CardType build() {
            return new CardType(name, front, back);
        }
    }


    // OBJECT OVERRIDDEN METHODS


    @Override
    public String toString() {
        return "CardType{" +
               "name='" + name + '\'' +
               ", front=" + front +
               ", back=" + back +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardType)) return false;
        CardType cardType = (CardType) o;
        return name.equals(cardType.name) &&
               front.equals(cardType.front) &&
               back.equals(cardType.back);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, front, back);
    }
}
