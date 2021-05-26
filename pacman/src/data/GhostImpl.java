package data;

import java.awt.*;

/**
 * An implementation of the {@link Ghost} interface
 *
 * @author Leia
 * @inv getLocation() != null && getLocation().x >= 0 && getLocation().y >= 0
 * @inv getName() != null && !getName().isEmpty()
 */
public class GhostImpl extends ElementImpl implements Ghost {

    //--------------------------------------------------------------
    // Attribute
    //--------------------------------------------------------------
    /**
     * the ghost name
     */
    private final String name;

    //--------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------

    /**
     * Constructor
     *
     * @param point the element location
     * @param name  the ghost name
     * @pre point != null && point.x >= 0 && point.y >= 0
     * @pre name != null && !name.isEmpty()
     */
    public GhostImpl(Point point, String name) {
        super(point);

        assert name != null && !name.isEmpty() : "Precondition violated";

        this.name = name;

        ghostInvariant();
    }

    //--------------------------------------------------------------
    // Getter
    //--------------------------------------------------------------

    @Override
    public String getName() {
        return name;
    }

    //--------------------------------------------------------------
    // Private method
    //--------------------------------------------------------------

    /**
     * Check the class invariants
     */
    private void ghostInvariant() {
        super.invariant();
        assert getName() != null && !getName().isEmpty() : "Invariant violated";
    }
}
