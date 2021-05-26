package view;

import java.awt.Color;

/**
 * A figure that represents a fruit, composed of a square (background) and a
 * circle which size and color depends on the fruit name
 *
 * @author Leia
 * 
 * @inv getFigures() != null && getFigures().length == 2
 */
public class Fruit extends CompoundFigure
{
    //--------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------

    /**
     * Constructor
     *
     * @param name the fruit name
     * @param x    the initial x location of the cell that contains the fruit
     * @param y    the initial y location of the cell that contains the fruit
     * @param size the size of the cell that contains the fruit
     *
     * @pre name != null && x >= 0 && y >= 0 && size > 0
     */
    public Fruit(String name, int x, int y, int size)
    {
        super(makeFigures(name, x, y, size));
        this.fruitInvariant();
    }

    //--------------------------------------------------------------
    // Private methods
    //--------------------------------------------------------------
    /**
     * Create the figures that compose the fruit
     *
     * @param name the fruit name
     * @param x    the initial x location of the cell that contains the fruit
     * @param y    the initial y location of the cell that contains the fruit
     * @param size the size of the cell that contains the fruit
     *
     * @return the figures that compose the fruit
     *
     * @pre name != null && x >= 0 && y >= 0 && size > 0
     * @post ret != null && ret.length == 2
     */
    private static Figure[] makeFigures(String name, int x, int y, int size)
    {
        assert name != null && x >= 0 && y >= 0 && size > 0 : "Precondition violated";
        
        int fruitSize = getSize(name, size);
        Figure[] figures = {
            new Rectangle(size, size, x, y, Color.black),
            new Circle(fruitSize, x + (size - fruitSize) / 2, y + (size - fruitSize) / 2, getColor(name))
        };
        assert figures != null && figures.length == 2: "Postcondition violated";
        return figures;
    }

    /**
     * Give the fruit size from its name
     *
     * @param name the fruit name
     * @param size the size of the cell that contains the fruit
     *
     * @return the fruit size
     *
     * @pre name != null && size > 0
     * @post ret > 0
     */
    private static int getSize(String name, int size)
    {
        assert name != null && size > 0 : "Precondition violated";
        
        int fruitSize = size / 6;
        switch (name) {
            case "Fraise":
            case "Orange":
            case "Pomme":
                fruitSize = size / 4;
                break;
            case "Melon":
            case "Galboss":
                fruitSize = size / 3;
                break;
            case "Cloche":
            case "Clé":
            case "Super":
                fruitSize = size * 5 / 12;
                break;
        }
        assert fruitSize > 0 : "Postcondition violated";
        return fruitSize;
    }

    /**
     * Give the fruit color from its name
     *
     * @param name the fruit name
     *
     * @return the fruit color
     * 
     * @pre name != null 
     * @post ret != null 
     */
    private static Color getColor(String name)
    {
        assert name != null : "Precondition violated";
        Color color = Color.white;
        switch (name) {
            case "Super":
            case "Cerise":
            case "Fraise":
                color = Color.red;
                break;
            case "Orange":
                color = Color.orange;
                break;
            case "Pomme":
            case "Melon":
                color = Color.yellow;
                break;
            case "Galboss":
            case "Cloche":
                color = Color.blue;
                break;
        }
        assert color != null : "Postcondition violated";
        return color;
    }

    //------------------------------------------------------------------------
    // Invariant
    //------------------------------------------------------------------------
    /**
     * Check the class invariant
     */
    protected final void fruitInvariant()
    {
        super.invariant();
        assert getFigures() != null && getFigures().length == 2: "Invariant violated";
    }
}
