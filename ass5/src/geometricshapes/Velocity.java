//326367570 Orian Eluz
package geometricshapes;
/**
 * The geometricshapes.Velocity class represents the velocity of an object in 2D space.
 * It defines the rate of change of position along the x and y axes.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Constructs a new geometricshapes.Velocity object with the given rates of change along the x and y axes.
     *
     * @param dx the rate of change along the x-axis
     * @param dy the rate of change along the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the rate of change along the x-axis.
     *
     * @return the rate of change along the x-axis
     */
    public double getDx() {
        return dx;
    }

    /**
     * Gets the rate of change along the y-axis.
     *
     * @return the rate of change along the y-axis
     */
    public double getDy() {
        return dy;
    }

    /**
     * Applies this velocity to a point and returns the new point after the velocity is applied.
     *
     * @param p the point to which the velocity is applied
     * @return the new point after applying the velocity
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + dx;
        double newY = p.getY() + dy;
        return new Point(newX, newY);
    }

    /**
     * Creates a new geometricshapes.Velocity object based on the given angle and speed.
     *
     * @param angle the angle of motion (in degrees)
     * @param speed the speed of motion
     * @return a new geometricshapes.Velocity object representing the velocity with the given angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle - 90);
        double dx = Math.cos(radians) * speed;
        double dy = Math.sin(radians) * speed;
        return new Velocity(dx, dy);
    }

}
