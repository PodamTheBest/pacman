package view;

import java.awt.Color;

/**
 * A figure that represents a ghost, composed of a square (background), a circle
 * and a rectangle (ghost shape) and small circlesto represent waves at the
 * ghost clothing bottom
 *
 * @author Leia
 *
 * @inv getFigures() != null && getFigures().length >= 3
 */
public class Ghost extends CompoundFigure
{
    //--------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------

    /**
     * the ghost color when it is not afraid
     */
    private final Color color;

    //--------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------
    /**
     * Constructor
     *
     * @param x      the initial x location of the cell that contains the ghost
     * @param y      the initial y location of the cell that contains the ghost
     * @param size   the size of the cell that contains the ghost
     * @param color  the ghost color when it is not afraid
     * @param afraid true if the ghost is afraid (color blue)
     *
     * @pre color != null && x >= 0 && y >= 0 && size > 0
     */
    public Ghost(int x, int y, int size, Color color, boolean afraid)
    {
        super(makeFigures(x, y, size, color, afraid));
        this.color = color;
        this.ghostInvariant();
    }

    //--------------------------------------------------------------
    // Setter
    //--------------------------------------------------------------
    /**
     * Change the ghost color according to whether it is afraid or not
     *
     * @param afraid true if the ghost is afraid (color blue)
     */
    public void setAfraid(boolean afraid)
    {
        Color color = afraid ? Color.blue : this.color;
        ((SimpleFigure) getFigures()[1]).setColor(color);
        ((SimpleFigure) getFigures()[2]).setColor(color);
        this.ghostInvariant();
    }

    //--------------------------------------------------------------
    // Private methods
    //--------------------------------------------------------------
    /**
     * Create the figures that represent the ghost
     *
     * @param x      the initial x location of the cell that contains the ghost
     * @param y      the initial y location of the cell that contains the ghost
     * @param size   the size of the cell that contains the ghost
     * @param color  the ghost color when it is not afraid
     * @param afraid true if the ghost is afraid (color blue)
     *
     * @return the figures that represent the ghost
     *
     * @pre color != null && x >= 0 && y >= 0 && size > 0
     * @post ret != null && ret.length >= 3
     */
    private static Figure[] makeFigures(int x, int y, int size, Color color, boolean afraid)
    {
        assert color != null && x >= 0 && y >= 0 && size > 0 : "Precondition violated";

        Figure[] figures = {
            new Rectangle(size, size, x, y, Color.black), // backgroung
            new Circle(size / 2, x + size / 4, y + size / 4, afraid ? Color.blue : color), // ghost head
            new Rectangle(size / 2, size / 4, x + size / 4, y + size / 2, afraid ? Color.blue : color), // ghost body
            new Circle(4, x + size * 3 / 8 - 2, y + size * 3 / 8 - 2, Color.black), // waves
            new Circle(4, x + size * 5 / 8 - 2, y + size * 3 / 8 - 2, Color.black),
            new Circle(size / 8, x + size / 4, y + size * 11 / 16, Color.black),
            new Circle(size / 8, x + size * 3 / 8, y + size * 11 / 16, Color.black),
            new Circle(size / 8, x + size / 2, y + size * 11 / 16, Color.black),
            new Circle(size / 8, x + size * 5 / 8, y + size * 11 / 16, Color.black)
        };
        assert figures != null && figures.length >= 3 : "Postcondition violated";
        return figures;
    }

    //------------------------------------------------------------------------
    // Invariant
    //------------------------------------------------------------------------
    /**
     * Check the class invariant
     */
    protected final void ghostInvariant()
    {
        super.invariant();
        assert getFigures() != null && getFigures().length >= 3 : "Invariant violated";
    }
}
