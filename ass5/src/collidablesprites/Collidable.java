//326367570 Orian Eluz
package collidablesprites;
import geometricshapes.Velocity;
import geometricshapes.Rectangle;
import geometricshapes.Point;

/**
 * The collidablesprites.Collidable interface represents an object that can be collided with.
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     *
     * @return the rectangle representing the shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the specified point with a given velocity.
     *
     * @param collisionPoint the point where the collision occurred.
     * @param currentVelocity the current velocity of the object that collided with this object.
     * @param hitter   the ball that hits the block
     * @return the new velocity expected after the hit, based on the force the object inflicted.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}
