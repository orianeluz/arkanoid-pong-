//326367570 Orian Eluz
package collidablesprites;
import geometricshapes.Point;
import geometricshapes.Line;
import geometricshapes.Rectangle;
import java.util.ArrayList;

/**
 * The collidablesprites.GameEnvironment class represents the environment in which the game takes place,
 * managing all the collidable objects within it.
 */
public class GameEnvironment {
    private final ArrayList<Collidable> collidables;

    /**
     * Constructs a collidablesprites.GameEnvironment object with an empty list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds a collidable object to the list of collidables.
     *
     * @param c the collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * remove the given collidable from the environment.
     * @param c the collidable.
     */
    public void delCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Determines the closest collision that is going to occur along the given trajectory.
     *
     * @param trajectory the line representing the trajectory of an object.
     * @return the information about the closest collision, or null if no collision is detected.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collisionPoint = null;
        double minDistance = 99999;
        Collidable closestCollidable = null;

        for (Collidable c : collidables) {
            Rectangle r = c.getCollisionRectangle();
            Point findDistancePoint = trajectory.closestIntersectionToStartOfLine(r);
            if (findDistancePoint != null) {
                double distance = trajectory.start().distance(findDistancePoint);
                if (distance < minDistance) {
                    minDistance = distance;
                    collisionPoint = findDistancePoint;
                    closestCollidable = c;
                }
            }
        }
        if (closestCollidable != null) {
            return new CollisionInfo(collisionPoint, closestCollidable);
        } else {
            return null;
        }
    }
}
