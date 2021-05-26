package view;

import java.awt.Color;
import static view.Triangle.DOWN;
import static view.Triangle.LEFT;
import static view.Triangle.RIGHT;
import static view.Triangle.UP;

/**
 * A figure that represents a pacman composed of a yellow circle and a triangle
 * that represents the mouth. The mouth is opened and closed periodically and is
 * oriented relatively to the pacman move direction
 *
 * @author Leia
 *
 * @inv getFigures() != null && getFigures().length == 3
 */
public class Pacman extends CompoundFigure
{
    //--------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------

    /**
     * true if the mouth is currently opened
     */
    private boolean mouthOpened;

    /**
     * the current mouth direction (DOWN, LEFT, RIGHT, UP)
     */
    private int direction;

    /**
     * the triangles that represent the pacman mouth in different conditions
     * (opened/closed, for the different directions)
     */
    private final Figure[] mouths;

    //--------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------
    /**
     * Constructor
     *
     * @param x    the initial x location of the cell that contains the pacman
     * @param y    the initial y location of the cell that contains the pacman
     * @param size the size of the cell that contains the pacman
     *
     * @pre x >= 0 && y >= 0 && size > 0
     */
    public Pacman(int x, int y, int size)
    {
        super(makeFigures(x, y, size));
        this.mouths = makeMouths(x, y, size);
        this.direction = LEFT;
        
        pacmanInvariant();
    }

    //--------------------------------------------------------------
    // Setters
    //--------------------------------------------------------------
    /**
     * Change the mouth from opened to closed (or from closed to opened) and
     * change its direction if needed
     *
     * @param right true if the new direction is RIGHT
     * @param left  true if the new direction is LEFT
     * @param down  true if the new direction is DOWN
     * @param up    true if the new direction is UP
     */
    public void changeMouth(boolean right, boolean left, boolean down, boolean up)
    {
        mouthOpened = !mouthOpened;
        this.direction = right ? RIGHT : left ? LEFT : down ? DOWN : up ? UP : this.direction;
        int idx = 2 * direction + (mouthOpened ? 0 : 1);
        getFigures()[2] = mouths[idx];
        
        pacmanInvariant();
    }

    @Override
    public void move(int dx, int dy)
    {
        super.move(dx, dy);
        for (Figure mouth : mouths) {
            if (mouth != getFigures()[2]) {
                mouth.move(dx, dy);
            }
        }
        
        pacmanInvariant();
    }

    //--------------------------------------------------------------
    // Private methods
    //--------------------------------------------------------------
    /**
     * Create the pacman mouths in diverse positions and state (opened/closed).
     * The resulting array contains, for i in RIGHT, LEFT, UP, DOWN, at i the
     * opened mouth and at i+1, the closed mouth of the corresponding direction
     *
     * @param x    the pacman x location
     * @param y    the pacman y location
     * @param size the pacman size
     *
     * @return the mouths
     * 
     * @pre x >= 0 && y >= 0 && size > 0
     * @post ret != null && ret.length == 8
     */
    private Figure[] makeMouths(int x, int y, int size)
    {
        assert x >= 0 && y >= 0 && size > 0 : "Precondition violated";

        Figure[] mouths = new Figure[8];
        mouths[2 * LEFT] = getFigures()[2];
        mouths[2 * LEFT + 1] = new Triangle(size / 2, size / 2, x + size / 2, y + size / 2, Color.black, LEFT);
        mouths[2 * UP] = new Triangle(size / 4, size / 2, x + size / 2, y + size / 2, Color.black, UP);
        mouths[2 * UP + 1] = new Triangle(size / 2, size / 2, x + size / 2, y + size / 2, Color.black, UP);
        mouths[2 * DOWN] = new Triangle(size / 4, size / 2, x + size / 2, y + size / 2, Color.black, DOWN);
        mouths[2 * DOWN + 1] = new Triangle(size / 2, size / 2, x + size / 2, y + size / 2, Color.black, DOWN);
        mouths[2 * RIGHT] = new Triangle(size / 2, size / 4, x + size / 2, y + size / 2, Color.black, RIGHT);
        mouths[2 * RIGHT + 1] = new Triangle(size / 2, size / 2, x + size / 2, y + size / 2, Color.black, RIGHT);
        
        assert mouths != null && mouths.length == 8 : "Postcondition violated";
        
        return mouths;
    }

    /**
     * Create the figures that represent the pacman: a black rectangle
     * (background), a yellow circle and a triangle (black mouth)
     *
     * @param x    the pacman initial x location
     * @param y    the pacman initial y location
     * @param size the pacman size
     *
     * @return the pacman figures
     *
     * @pre x >= 0 && y >= 0 && size > 0
     * @post ret != null && ret.length == 3
     */
    private static Figure[] makeFigures(int x, int y, int size)
    {
        assert x >= 0 && y >= 0 && size > 0 : "Precondition violated";

        Figure[] figures ={
            new Rectangle(size, size, x, y, Color.black),
            new Circle(size * 3 / 4, x + size / 8, y + size / 8, Color.yellow),
            new Triangle(size / 2, size / 4, x + size / 2, y + size / 2, Color.black, LEFT)
        };
        assert figures != null && figures.length == 3 : "Postcondition violated";
        return figures;
    }

    //------------------------------------------------------------------------
    // Invariant
    //------------------------------------------------------------------------
    /**
     * Check the class invariant
     */
    protected final void pacmanInvariant()
    {
        super.invariant();
        assert getFigures() != null && getFigures().length == 3: "Invariant violated";
    }
}
