package view;

/**
 * A figure composed of one or more figures
 *
 * @author Leia
 *
 * @inv getFigures() != null && getFigures().length > 0
 */
public class CompoundFigure extends Figure
{

    /**
     * the figure that compose this compound figure
     */
    private final Figure[] figures;

    /**
     * Initialize a compound figure composed of the gievn figures
     *
     * @param figures the figures that compose the compound figure
     *
     * @pre figures != null && figures.length > 0
     */
    public CompoundFigure(Figure[] figures)
    {
        super();
        assert figures != null && figures.length > 0 : "empty figures list";
        this.figures = figures;
        this.compoundFigureInvariant();
    }

    //------------------------------------------------------------------------
    // Draw
    //------------------------------------------------------------------------
    @Override
    public void draw()
    {
        for (Figure figure : this.figures) {
            figure.draw();
        }
    }

    @Override
    public void erase()
    {
        for (Figure figure : this.figures) {
            figure.erase();
        }
    }

    //------------------------------------------------------------------------
    // Getters
    //------------------------------------------------------------------------
    /**
     * Give the figures that compose the current figure
     *
     * @return the figures that compose this figure
     */
    public Figure[] getFigures()
    {
        return this.figures;
    }

    @Override
    public int getX()
    {
        // look for the smallest x
        int x = this.figures[0].getX();
        for (int i = 1; i < this.figures.length; i++) {
            if (this.figures[i].getX() < x) {
                x = this.figures[i].getX();
            }
        }
        return x;
    }

    @Override
    public int getY()
    {
        // look for the smallest y
        int y = this.figures[0].getY();
        for (int i = 1; i < this.figures.length; i++) {
            if (this.figures[i].getY() < y) {
                y = this.figures[i].getY();
            }
        }
        return y;
    }

    @Override
    public int getWidth()
    {
        // look for the greatest x
        int x = this.figures[0].getX();
        for (int i = 1; i < this.figures.length; i++) {
            if (this.figures[i].getX() > x) {
                x = this.figures[i].getX();
            }
        }
        // return the difference between the greatest and the smallest x
        return x - this.getX();
    }

    @Override
    public int getHeight()
    {
        // look for the greatest x
        int y = this.figures[0].getY();
        for (int i = 1; i < this.figures.length; i++) {
            if (this.figures[i].getY() > y) {
                y = this.figures[i].getY();
            }
        }
        // return the difference between the greatest and the smallest y
        return y - this.getY();
    }

    //------------------------------------------------------------------------
    // Setters
    //------------------------------------------------------------------------
    @Override
    public void move()
    {
        for (Figure figure : this.figures) {
            figure.move();
        }
    }

    @Override
    public void move(int dx, int dy)
    {
        for (Figure figure : this.figures) {
            figure.move(dx, dy);
        }
    }

    //------------------------------------------------------------------------
    // Invariant
    //------------------------------------------------------------------------
    /**
     * Check the class invariant
     */
    protected void compoundFigureInvariant()
    {
        super.invariant();
        assert this.figures != null && this.figures.length > 0 : "Invariant violated: empty figures list";
    }
}
