package logic;

import java.awt.*;

public class Pacman {

    private int x;
    private int y;

    public Pacman(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
