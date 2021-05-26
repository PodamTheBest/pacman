package logic;

import data.Level;

public class GameImpl implements Game {

    private data.Game game;
    private Level level;
    private Cell[][] cells;
    private Score score;
    private Pacman pacman;

    public GameImpl(data.Game game) {
        this.game = game;
        this.level = this.game.nextLevel();
        this.cells = new Cell[this.level.getSize()][this.level.getSize()];
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells.length; j++) {
                this.cells[i][j] = new CellImpl(this.level, i, j);
            }
        }
    }

    /**
     * Give the number of rows/columns in the game board for the current level.
     * Different invocations of this methods should return the same result if
     * getScore().getLevel() return the same result (else, it could be
     * different)
     *
     * @return the current size of the game board
     * @pre !isFinished()
     * @post ret > 0
     */
    @Override
    public int getSize() {
        return this.level.getSize();
    }

    /**
     * Give the minimum wait time between two invocations of the
     * {@link #play(int, int)} method
     *
     * @return the wait time (in ms)
     * @pre !isFinished()
     * @post ret > 0
     */
    @Override
    public int getWait() {
        return 0;
    }

    /**
     * Compute the next step. The given pacman move is processed if it is
     * allowed, then the ghosts are moved and the new state of the game is
     * computed accordingly.
     *
     * @param dx the pacman horizontal move (diffence between the current x
     *           location and the expected new one)
     * @param dy the pacman vertical move (diffence between the current y
     *           location and the expected new one)
     * @pre !isFinished()
     * @pre dx >= -1 && dx <= 1 && dy >= -1 && dy <= 1
     * @pre !(dx != 0 && dy != 0)
     */
    @Override
    public void play(int dx, int dy) {

    }

    /**
     * Give the cell at the given location
     *
     * @param x the cell column index
     * @param y the cell line index
     * @return the cell at the given location
     * @pre !isFinished()
     * @pre x >= 0 && x < getSize() && x >= 0 && y < getSize()
     * @post ret != null
     */
    @Override
    public Cell getCell(int x, int y) {
        return null;
    }

    /**
     * Check whether the current game is finished, i.e. the last level is
     * finished or the number of lives is exhausted
     *
     * @return true if the game is finished
     */
    @Override
    public boolean isFinished() {
        return false;
    }

    /**
     * Give the score representing the current results of this game.
     *
     * @return the game score
     */
    @Override
    public Score getScore() {
        return this.score;
    }

    /**
     * Check whether pacman has obtained the super power by eating a super gum
     * and this super power is still available
     *
     * @return true if pacman has currently the super power
     */
    @Override
    public boolean hasSuperPower() {
        return false;
    }
}
