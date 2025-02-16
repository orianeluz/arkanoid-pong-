//326367570 Orian Eluz
package collidablesprites;

import geometricshapes.Point;

/**
 * The collidablesprites.CollisionInfo class represents information about a collision event.
 * It contains the point of collision and the collidable object involved in the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs a new collidablesprites.CollisionInfo object with the specified collision point and collision object.
     *
     * @param p **collision Point** the point where the collision occurred
     * @param o **collision object** the object involved in the collision
     */
    public CollisionInfo(Point p, Collidable o) {
        this.collisionPoint = p;
        this.collisionObject = o;
    }

    /**
     * Returns the point where the collision occurred.
     *
     * @return **Point** - the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the object involved in the collision.
     *
     * @return **collidablesprites.Collidable** - the collision object
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
