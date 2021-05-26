package logic;

import data.Fruit;
import data.Level;

public class ScoreImpl implements Score {

    private int numberOfLives;
    private int levelNumber;
    private int points;
    private Level level;

    public ScoreImpl(Level level) {
        this.numberOfLives = 3;
        this.points = 0;
        this.levelNumber = 1;
        this.level = level;
    }

    void loseLife() {
        assert this.numberOfLives > 0;
        this.numberOfLives--;
    }

    void changeLevel() {
        this.levelNumber++;
    }

    void addPoints(CellImpl cell) {
        if (!cell.isWall() && cell.hasPacman()) {
            this.points += level.getFruit(cell.getX(), cell.getY()).getValue();
        }
    }

    /**
     * Give the number of remaining lives for the pacman
     *
     * @return the number of lives
     */
    @Override
    public int lives() {
        return this.numberOfLives;
    }

    /**
     * Give the current number of points, i.e. the sum of the values of the
     * eaten fruits
     *
     * @return the current number of points
     */
    @Override
    public int points() {
        return this.points;
    }

    /**
     * Give the current level number
     *
     * @return the current level
     */
    @Override
    public int level() {
        return this.levelNumber;
    }
}
