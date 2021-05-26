package view;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link Game} interface.
 *
 * @author Leia
 */
public class GameImpl extends Game
{
    //--------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------

    /**
     * the cells that compose the board
     */
    private List<Cell> cells;

    /**
     * the pacman figure
     */
    private Pacman pacman;

    /**
     * the current level
     */
    private int level;

    /**
     * the last printed score
     */
    private String displayedScore;

    /**
     * true if pacman has currently a super power
     */
    private boolean hasSuperPower;

    //--------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------
    /**
     * Constructor
     *
     * @param game the game logic
     *
     * @pre game != null
     */
    public GameImpl(logic.Game game)
    {
        super(game);
        this.level = game.getScore().level();
        this.cells = makeCells();
    }

    //--------------------------------------------------------------
    // Setter
    //--------------------------------------------------------------
    @Override
    protected void play(int dx, int dy)
    {
        assert dx >= -1 && dx <= 1 && dy >= -1 && dy <= 1 : "Precondition violated";
        assert !(dx != 0 && dy != 0) : "Precondition violated";
        
        if (this.pacman != null) {
            this.pacman.changeMouth(dx == 1, dx == -1, dy == 1, dy == -1);
        }
        super.play(dx, dy);
    }

    //--------------------------------------------------------------
    // Getter
    //--------------------------------------------------------------
    /**
     * Give the pacman figure
     *
     * @return the pacman
     */
    Pacman getPacman()
    {
        return pacman;
    }

    //--------------------------------------------------------------
    // Private/protected methods
    //--------------------------------------------------------------
    @Override
    protected void draw()
    {
        if (this.level != this.game.getScore().level()) {
            // the level has changed. Reset the board with the new level
            this.level = this.game.getScore().level();
            this.cells = makeCells();
        }

        // update the cells according to the pacman super power status (optional)
        setSuperPower(game.hasSuperPower());

        // draw the new game status
        for (Cell cell : this.cells) {
            cell.draw();
        }
        canvas.redraw();

        // print the new score
        printScore();
    }

    /**
     * Update the cells according to the given pacman super power status, if
     * this status has changed
     *
     * @param hasSuperPower true if pacman has a super power
     */
    private void setSuperPower(boolean hasSuperPower)
    {
        if (this.hasSuperPower != hasSuperPower) {
            this.hasSuperPower = hasSuperPower;
            for (Cell cell : this.cells) {
                cell.setGhostAfraid(hasSuperPower);
            }
        }
    }

    /**
     * Print the score on the console
     */
    private void printScore()
    {
        StringBuilder builder = new StringBuilder();
        if (displayedScore != null) {
            // add backspaces to erase the previous score
            for (int i = 0; i < displayedScore.length(); i++) {
                builder.append((char) 8);
            }
        }
        displayedScore = game.getScore().toString();
        System.out.print(builder.toString() + displayedScore);
    }

    /**
     * Create the figures that compose the game board. The figures are all
     * {@link cell} instances. If a cell containing pacman is encountered,
     * initialize the pacman attribute
     *
     * @return the board figures
     *
     * @pre game != null
     */
    private List<Cell> makeCells()
    {
        assert game != null : "Precondition violated";

        int cellSize = Canvas.WIDTH / game.getSize();
        List<Cell> figures = new ArrayList<>();
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                logic.Cell logicCell = game.getCell(i, j);
                Cell cell = new Cell(this, logicCell, i * cellSize, j * cellSize, cellSize);
                figures.add(cell);
                if (logicCell.hasPacman()) {
                    this.pacman = (Pacman) cell.getFigures()[0];
                }
            }
        }
        return figures;
    }
}
