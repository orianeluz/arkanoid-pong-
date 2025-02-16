//326367570 Orian Eluz
package geometricshapes;

/**
 * The Point class represents a point in a 2D space.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * Constructs a point with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns true if this point is equal to another point.
     *
     * @param other the other point
     * @return true if this point is equal to the other point, false otherwise
     */
    public boolean equals(Point other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }

}
