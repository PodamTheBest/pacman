package view;

import java.awt.Color;

/**
 * A figure that represents a cell composed of a unique figure: fruit, ghost or
 * pacman or a square if the cell is empty
 *
 * @author Leia
 *
 * @inv getFigures() != null && getFigures().length == 1
 */
public class Cell extends CompoundFigure
{

    //--------------------------------------------------------------
    // Attribute
    //--------------------------------------------------------------
    /**
     * the game logic
     */
    private final GameImpl game;

    /**
     * the logic representation of the cell
     */
    private final logic.Cell cell;

    /**
     * the cell x location
     */
    private final int x;

    /**
     * the cell y location
     */
    private final int y;

    /**
     * the cell size
     */
    private final int size;

    /**
     * true if the cell contains a pacman figure
     */
    private boolean hasPacman;

    /**
     * true if the cell contains a ghost figure
     */
    private boolean hasGhost;

    /**
     * true if the cell contains a fruit figure
     */
    private boolean hasFruit;

    /**
     * true if the current ghost color is blue (ghosts are afraid)
     */
    private boolean ghostAfraid;

    //--------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------
    /**
     * Constructor
     *
     * @param game the game logic
     * @param cell the logic representation of the cell
     * @param x    the initial cell x location
     * @param y    the initial cell y location
     * @param size the cell size
     *
     * @pre game != null && cell != null
     * @pre x >= 0 && y >= 0 && size > 0
     */
    public Cell(GameImpl game, logic.Cell cell, int x, int y, int size)
    {
        super(makeFigures(cell, x, y, size));
        this.cell = cell;
        this.x = x;
        this.y = y;
        this.size = size;
        this.game = game;
        this.hasPacman = cell.hasPacman();
        this.hasGhost = !hasPacman && cell.getGhost() != null;
        this.hasFruit = !hasPacman && !hasGhost && cell.getFruit() != null;

        this.cellInvariant();
    }

    //--------------------------------------------------------------
    // Setters
    //--------------------------------------------------------------
    @Override
    public void draw()
    {
        updateCell();
        super.draw();
    }

    /**
     * Change the ghost color if the current cell contains a ghost according to
     * wether it is afraid or not
     *
     * @param afraid true if the ghost color should be blue
     */
    public void setGhostAfraid(boolean afraid)
    {
        this.ghostAfraid = afraid;
        if (getFigures()[0] instanceof Ghost) {
            ((Ghost) getFigures()[0]).setAfraid(afraid);
        }

        this.cellInvariant();
    }

    //--------------------------------------------------------------
    // Private methods
    //--------------------------------------------------------------
    /**
     * Change the figure in the cell if the cell content has changed
     */
    private void updateCell()
    {
        if (!this.cell.hasPacman()) { // the cell has no pacman

            String ghost = this.cell.getGhost();
            if (ghost == null) { // the cell has no ghost

                String fruitName = this.cell.getFruit();
                if (!this.hasFruit && fruitName != null) { // a fruit that was hidden is now visible
                    this.hasFruit = true;
                    getFigures()[0] = new Fruit(fruitName, x, y, size);
                } else if (this.hasGhost || (this.hasFruit && fruitName == null) || this.hasPacman) { // the cell has became empty
                    this.hasFruit = false;
                    Color background = cell.isWall() ? Color.blue : Color.black;
                    getFigures()[0] = new Rectangle(size, size, x, y, background);
                } // else the cell has not changed: DO NOTHING
                this.hasGhost = false;

            } else if (!this.hasGhost) { // a ghost has entered the cell

                this.hasGhost = true;
                this.hasFruit = false;
                getFigures()[0] = GhostFactory.getInstance().makeGhost(ghost, x, y, size, ghostAfraid);

            } // else the cell has not changed: DO NOTHING
            this.hasPacman = false;

        } else if (!this.hasPacman) { // pacman has entered the cell
            this.hasPacman = true;
            this.hasGhost = false;
            this.hasFruit = false;
            Pacman pacman = game.getPacman();
            pacman.move(x - pacman.getX(), y - pacman.getY());
            getFigures()[0] = pacman;
        }

        this.cellInvariant();
    }

    /**
     * Create the figure that compose the cell
     *
     * @param cell the logic representation of the cell
     * @param x    the initial cell x location
     * @param y    the initial cell y location
     * @param size the cell size
     *
     * @return the figure that compose the cell
     *
     * @pre cell != null && x >= 0 && y >= 0 && size > 0
     * @post ret != null && ret.length == 1
     */
    private static Figure[] makeFigures(logic.Cell cell, int x, int y, int size)
    {
        assert cell != null && x >= 0 && y >= 0 && size > 0 : "Precondition violated";

        Figure[] figures;

        if (cell.hasPacman()) { // cell composed of a pacman
            figures = new Figure[]{
                new Pacman(x, y, size)
            };
        } else {
            String ghost = cell.getGhost();
            if (ghost != null) { // cell composed of a ghost
                figures = new Figure[]{
                    GhostFactory.getInstance().makeGhost(ghost, x, y, size, false)
                };
            } else {
                String fruitName = cell.getFruit();
                if (fruitName != null) { // cell composed of a fruit
                    figures = new Figure[]{
                        new Fruit(fruitName, x, y, size)
                    };
                } else { // empty cell
                    Color background = cell.isWall() ? Color.blue : Color.black;
                    figures = new Figure[]{
                        new Rectangle(size, size, x, y, background)
                    };
                }
            }
        }
        assert figures != null && figures.length == 1 : "Postcondition violated";
        return figures;
    }

    //------------------------------------------------------------------------
    // Invariant
    //------------------------------------------------------------------------
    /**
     * Check the class invariant
     */
    protected final void cellInvariant()
    {
        super.invariant();
        assert getFigures() != null && getFigures().length == 1 : "Invariant violated";
    }
}
