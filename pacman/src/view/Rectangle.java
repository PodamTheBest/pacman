package view;

import java.awt.Color;
import java.awt.Shape;

/**
 * A rectangle that can be manipulated and drawn on a canvas.
 *
 * @author Leia
 */
public class Rectangle extends SimpleFigure
{

    /**
     * Create a new rectangle.
     *
     * @param width  the square initial width
     * @param height the square initial height
     * @param x      the square initial x location
     * @param y      the square initial y location
     * @param color  the square initial color.
     *
     * @pre size >= 0
     */
    public Rectangle(int width, int height, int x, int y, Color color)
    {
        super(width, height, x, y, color);
        this.invariant();
    }

    //------------------------------------------------------------------------
    // Draw
    //------------------------------------------------------------------------
    /**
     * {@inheritDoc }
     */
    @Override
    protected Shape makeShape()
    {
        return new java.awt.Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
