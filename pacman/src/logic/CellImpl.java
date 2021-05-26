package logic;

import data.Level;

public class CellImpl implements Cell {
    private int x;
    private int y;
    private Level level;

    public CellImpl(Level level, int positionX, int positionY) {
        this.level = level;
        this.x = positionX;
        this.y = positionY;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    /**
     * Check whether this cell is a wall, i.e. cannot contain a fruit or an
     * element
     *
     * @return true if this cell is a wall
     */
    @Override
    public boolean isWall() {
        return this.level.isWall(this.x, this.y);
    }

    /**
     * Check whether this cell contains a pacman
     *
     * @return true if this cell contains a pacman
     */
    @Override
    public boolean hasPacman() {
        return;
    }

    /**
     * Check whether this cell contains ghosts and, if any, give the name of one
     * of them
     *
     * @return the name of one of the ghosts in the cell, if any
     */
    @Override
    public String getGhost() {
        return null;
    }

    /**
     * Check whether this cell contains a fruit and returns the name of the
     * fruit if it exists
     *
     * @return the name of the fruit in this cell, if any. Null if this cell has
     * no fruit
     */
    @Override
    public String getFruit() {
        return this.level.getFruit(this.x, this.y).getName();
    }
}
