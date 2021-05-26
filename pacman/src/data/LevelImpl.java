package data;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * An implementation of the {@link Level} interface
 *
 * @author Leia
 * @inv getElements() != null && getSize() >= 0 && getProperties() != null
 */
public class LevelImpl implements Level {
    //--------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------

    /**
     * the elements of this level (pacman and fruits), at their initial
     * locations
     */
    private final Collection<Element> elements;

    /**
     * the lines that represent the board rows
     */
    private final List<String> board;

    /**
     * the fruits map (key,fruit)
     */
    private final Map<Character, Fruit> fruits;

    /**
     * the level properties
     */
    private final Properties properties;

    //--------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------

    /**
     * Constructor
     *
     * @param elements   the elements of this level
     * @param board      the lines that represent the board rows
     * @param fruits     the fruits map (key,fruit)
     * @param properties the level properties
     * @pre elements != null && board != null && fruits != null && properties != null
     * @pre forAll (line : board | line.length() == board.size())
     */
    public LevelImpl(Collection<Element> elements, List<String> board, Map<Character, Fruit> fruits, Properties properties) {
        assert elements != null && board != null && fruits != null && properties != null : "precondition violated";
        for (String line : board) {
            assert line.length() == board.size() : "precondition violated";
        }

        this.elements = elements;
        this.board = board;
        this.fruits = fruits;
        this.properties = properties;

        invariant();
    }

    //--------------------------------------------------------------
    // Getters
    //--------------------------------------------------------------
    @Override
    public Collection<Element> getElements() {
        return elements;
    }

    @Override
    public int getSize() {
        return board.size();
    }

    @Override
    public boolean isWall(int x, int y) {
        assert x >= 0 && x < getSize() && x >= 0 && y < getSize() : "precondition violated";

        return board.get(y).charAt(x) == 'w';
    }

    @Override
    public Fruit getFruit(int x, int y) {
        assert x >= 0 && x < getSize() && x >= 0 && y < getSize() : "precondition violated";

        return fruits.get(board.get(y).charAt(x));
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    //--------------------------------------------------------------
    // Private method
    //--------------------------------------------------------------

    /**
     * Check the class invariants
     */
    private void invariant() {
        assert getElements() != null && getSize() >= 0 && getProperties() != null : "Invariant violated";
    }
}
