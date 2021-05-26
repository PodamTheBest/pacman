package data;

import java.awt.*;

/**
 * An implementation of the {@link Element} interface
 *
 * @author Leia
 * @inv getLocation() != null && getLocation().x >= 0 && getLocation().y >= 0
 */
public class ElementImpl implements Element {
    //--------------------------------------------------------------
    // Attribute
    //--------------------------------------------------------------

    /**
     * the element location
     */
    private final Point point;

    //--------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------

    /**
     * Constructor
     *
     * @param point the element location
     * @pre point != null && point.x >= 0 && point.y >= 0
     */
    public ElementImpl(Point point) {
        assert point != null && point.x >= 0 && point.y >= 0 : "Precondition violated";

        this.point = point;

        invariant();
    }

    //--------------------------------------------------------------
    // Getter
    //--------------------------------------------------------------

    @Override
    public Point getLocation() {
        return point;
    }

    //--------------------------------------------------------------
    // Private method
    //--------------------------------------------------------------

    /**
     * Check the class invariants
     */
    protected final void invariant() {
        assert getLocation() != null && getLocation().x >= 0 && getLocation().y >= 0 : "Invariant violated";
    }
}
