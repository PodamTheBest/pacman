package data;

import java.awt.*;

/**
 * An implementation of the {@link Pacman} interface
 *
 * @author Leia
 * @inv getLocation() != null && getLocation().x >= 0 && getLocation().y >= 0
 */
public class PacmanImpl extends ElementImpl implements Pacman {

    //--------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------

    /**
     * Constructor
     *
     * @param point the element location
     * @pre point != null && point.x >= 0 && point.y >= 0
     */
    public PacmanImpl(Point point) {
        super(point);
    }
}
