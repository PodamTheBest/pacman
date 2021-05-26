package view;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * A factory to create new ghosts or obtain existing ghost from their names. The
 * ghosts names shoud be unique, i.e. only one instance for each ghost name
 * should exist. This class is a singleton: only one instance of this class
 * should exist: it is obtained through the getInstance method (the constructor
 * is private)
 *
 * @author Leia
 */
public class GhostFactory
{

    //--------------------------------------------------------------
    // Static part
    //--------------------------------------------------------------
    /**
     * the ghosts available colors
     */
    private static final Color[] COLORS = {Color.gray, Color.white, Color.cyan, Color.pink, Color.orange, Color.green};

    /**
     * single instance of the {@link GhostFactory} class
     */
    private static GhostFactory INSTANCE;

    /**
     * Give the instance of this class
     *
     * @return the unique {@link GhostFactory} instance
     */
    public static GhostFactory getInstance()
    {
        if (INSTANCE == null) {
            INSTANCE = new GhostFactory();
        }
        return INSTANCE;
    }

    //--------------------------------------------------------------
    // Instance part
    //--------------------------------------------------------------
    /**
     * the existing ghosts, accessible from their names
     */
    private final Map<String, Ghost> ghosts;

    /**
     * Private constructor
     */
    private GhostFactory()
    {
        this.ghosts = new HashMap<>();
    }

    /**
     * Make a new ghost or give an existing one from its name, having the given
     * attributes
     *
     * @param name   the ghost name
     * @param x      the ghost x location
     * @param y      the ghost y location
     * @param size   the ghost initial size
     * @param afraid true if the ghost is afraid
     *
     * @return the ghost having the given attributes
     *
     * @post ret != null && ret.getX() == x && ret.getY() == y
     */
    public Ghost makeGhost(String name, int x, int y, int size, boolean afraid)
    {
        Ghost ghost = ghosts.get(name);
        if (ghost != null) {
            int dx = x - ghost.getX();
            int dy = y - ghost.getY();
            ghost.move(dx, dy);
            ghost.setAfraid(afraid);
        } else {
            Color color = COLORS[ghosts.size() % COLORS.length];
            ghost = new Ghost(x, y, size, color, afraid);
            ghosts.put(name, ghost);
        }
        
        assert ghost.getX() == x && ghost.getY() == y : "Postcondition violated";
        
        return ghost;
    }
}
