//326367570 Orian Eluz
package geometricshapes;

import java.util.ArrayList;
import java.util.List;

/**
 * The geometricshapes.Rectangle class represents a rectangle defined by its four sides.
 */
public class Rectangle {
    private Line topSide;
    private Line bottomSide;
    private Line leftSide;
    private Line rightSide;
    private double width;
    private double height;
    private Point upperLeft;

    /**
     * Constructs a rectangle with the specified upper-left corner, width, and height.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        this.topSide = new Line(x, y, x + width, y);
        this.bottomSide = new Line(x, y + height, x + width, y + height);
        this.leftSide = new Line(x, y, x, y + height);
        this.rightSide = new Line(x + width, y, x + width, y + height);
    }

    /**
     * Returns a (possibly empty) list of intersection points with the specified line.
     *
     * @param line the line to check for intersections
     * @return a list of intersection points with the specified line
     */
    public List<Point> intersectionPoints(Line line) {
        ArrayList<Point> interPoints = new ArrayList<>();
        Point interPoint;
        Line[] lines = this.recLines();
        for (Line rectLine : lines) {
            interPoint = line.intersectionWith(rectLine);
            if (interPoint != null && !this.ifInList(interPoints, interPoint)) {
                interPoints.add(interPoint);
            }
        }
        return interPoints;
    }

    /**
     * Generates an array of the four sides of the rectangle.
     *
     * @return an array of the four sides of the rectangle
     */
    public Line[] recLines() {
        return new Line[]{topSide, bottomSide, leftSide, rightSide};
    }

    /**
     * Checks if a given point is in the list of points.
     *
     * @param points the list of points to check
     * @param point  the point to check for
     * @return true if the point is in the list, false otherwise
     */
    private boolean ifInList(ArrayList<Point> points, Point point) {
        for (Point p : points) {
            if (p.equals(point)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
    /**
     * Gets down left.
     *
     * @return the down left
     */
    public Point getDownnerLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY() + this.height);
    }

    /**
     * Gets down right.
     *
     * @return the down right
     */
    public Point getDownRight() {
        return new Point((upperLeft.getX() + this.getWidth()), upperLeft.getY() + this.getHeight());
    }

    /**
     * Gets upper right.
     *
     * @return the upper right
     */
    public Point getUpperRight() {
        return new Point(upperLeft.getX() + this.getWidth(), upperLeft.getY());
    }

    /**
     * Returns the top side of the rectangle.
     *
     * @return the top side of the rectangle
     */
    public Line getTopSide() {
        return topSide;
    }

    /**
     * Returns the bottom side of the rectangle.
     *
     * @return the bottom side of the rectangle
     */
    public Line getBottomSide() {
        return bottomSide;
    }

    /**
     * Returns the left side of the rectangle.
     *
     * @return the left side of the rectangle
     */
    public Line getLeftSide() {
        return leftSide;
    }

    /**
     * Returns the right side of the rectangle.
     *
     * @return the right side of the rectangle
     */
    public Line getRightSide() {
        return rightSide;
    }

    /**
     * Is point in rectangle boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public boolean isPointInRectangle(Point p) {
        return (p.getX() > this.getUpperLeft().getX() && p.getX() < this.getUpperRight().getX()
                && p.getY() > this.getUpperRight().getY() && p.getY() < this.getDownnerLeft().getY());
    }
}
