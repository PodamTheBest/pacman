package view;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;

/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 *
 * @author Leia
 *
 * @inv direction == RIGHT || direction == DOWN || direction == LEFT ||
 * direction == UP
 */
public class Triangle extends SimpleFigure
{

    public static final int RIGHT = 0, DOWN = 1, LEFT = 2, UP = 3;

    private final int direction;  // the direction of the triangle summit

    /**
     * Create a new triangle.
     *
     * @param width     the triangle initial width
     * @param height    the triangle initial height
     * @param x         the triangle initial x location
     * @param y         the triangle initial y location
     * @param color     the triangle initial color.
     * @param direction the direction of the triangle summit
     *
     * @pre width >= 0 && height >= 0
     * @pre color.equals("white") || color.equals("black") ||
     * color.equals("red") || color.equals("blue") || color.equals("yellow") ||
     * color.equals("green")
     * @pre direction == RIGHT || direction == DOWN || direction == LEFT ||
     * direction == UP
     */
    public Triangle(int width, int height, int x, int y, Color color, int direction)
    {
        super(width, height, x, y, color);
        this.direction = direction;
    }

    @Override
    protected Shape makeShape()
    {
        int[] xpoints = null, ypoints = null;
        switch (direction) {
            case RIGHT:
                xpoints = new int[]{getX(), getX() + getWidth(), getX() + getWidth()};
                ypoints = new int[]{getY(), getY() - getHeight() / 2, getY() + getHeight() / 2};
                break;
            case LEFT:
                xpoints = new int[]{getX(), getX() - getWidth(), getX() - getWidth()};
                ypoints = new int[]{getY(), getY() - getHeight() / 2, getY() + getHeight() / 2};
                break;
            case UP:
                xpoints = new int[]{getX(), getX() - getWidth() / 2, getX() + getWidth() / 2};
                ypoints = new int[]{getY(), getY() - getHeight(), getY() - getHeight()};
                break;
            case DOWN:
                xpoints = new int[]{getX(), getX() - getWidth() / 2, getX() + getWidth() / 2};
                ypoints = new int[]{getY(), getY() + getHeight(), getY() + getHeight()};
                break;
        }
        return new Polygon(xpoints, ypoints, 3);
    }
}
