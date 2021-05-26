package data;

import java.awt.*;

/**
 * This abstract class is used to simplify the code of the subclasses. It manages the location.
 *
 * @author PodamTheBest
 */
public abstract class ACharacter implements Element {

    /**
     * The location of this object.
     */
    private Point location;

    /**
     * This object constructor. It is used to create an instance of this class with the right arguments.
     *
     * @param level The current level.
     * @param x     The character x position relatively to the game board.
     * @param y     The character y position relatively to the game board.
     */
    public ACharacter(Level level, int x, int y) {
        super();

        if (!level.isWall(x, y) && x >= 0 && y >= 0 && x < level.getSize() && y < level.getSize()) {
            this.location = new Point(x, y);
        }
    }

    /**
     * Give the initial location of the element in the game board, s.t. x is the
     * column number and y the row number
     *
     * @return the element location
     */
    public Point getLocation() {
        return this.location;
    }
}
