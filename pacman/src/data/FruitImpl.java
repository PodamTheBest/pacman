package data;

/**
 * An implementation of the {@link Fruit} interface
 *
 * @author Leia
 * @inv getName() != null && !getName().isEmpty() && getValue() > 0
 */
public class FruitImpl implements Fruit {
    //--------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------

    /**
     * the key that identifies the fruit
     */
    private final char key;

    /**
     * the name of the fruit
     */
    private final String name;

    /**
     * the value of the fruit, i.e. the number of points gained when a pacman
     * eats this fruit
     */
    private final int value;

    //--------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------

    /**
     * Constructor
     *
     * @param key   the key that identifies the fruit
     * @param name  the name of the fruit
     * @param value the value of the fruit
     * @pre name != null && !name.isEmpty() && value > 0
     */
    public FruitImpl(char key, String name, int value) {
        assert name != null && !name.isEmpty() && value > 0 : "Precondition violated";

        this.key = key;
        this.name = name;
        this.value = value;

        invariant();
    }

    //--------------------------------------------------------------
    // Getters
    //--------------------------------------------------------------

    @Override
    public char getKey() {
        return key;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }

    //--------------------------------------------------------------
    // Private method
    //--------------------------------------------------------------

    /**
     * Check the class invariants
     */
    private void invariant() {
        assert getName() != null && !getName().isEmpty() && getValue() > 0 : "Invariant violated";
    }
}
