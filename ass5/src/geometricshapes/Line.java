//326367570 Orian Eluz
package geometricshapes;
import java.util.ArrayList;


/**
 * The geometricshapes.Line class represents a line segment in a 2D space defined by two points.
 */
public class Line {
    private Point start;
    private Point end;
    private final double comparisonThreshold = 0.00000001;

    /**
     * Constructs a geometricshapes.Line with the specified start and end points.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a geometricshapes.Line with the specified coordinates.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return end;
    }


    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other the other line to check for intersection
     * @return the intersection point if the lines intersect, and null otherwise
     */
    public Point intersectionWith(Line other) {
        double x1 = start.getX(), y1 = start.getY();
        double x2 = end.getX(), y2 = end.getY();
        double x3 = other.start().getX(), y3 = other.start().getY();
        double x4 = other.end().getX(), y4 = other.end().getY();

        double denominator = ((x1 - x2) * (y3 - y4)) - ((y1 - y2) * (x3 - x4));

        // If denominator is zero, lines are parallel or coincident
        if (Math.abs(denominator) < comparisonThreshold) {
            // Check if one line is actually a point and lies on the other line
            if (isPointOnLine(start, other) || isPointOnLine(end, other)
                    || isPointOnLine(other.start(), this) || isPointOnLine(other.end(), this)) {
                return new Point(x1, y1); // Return the shared point
            }
            return null;
        }

        double x = (((x1 * y2) - (y1 * x2)) * (x3 - x4) - (x1 - x2) * ((x3 * y4) - (y3 * x4))) / denominator;
        double y = (((x1 * y2) - (y1 * x2)) * (y3 - y4) - (y1 - y2) * ((x3 * y4) - (y3 * x4))) / denominator;

        Point intersection = new Point(x, y);

        // Check if the intersection point lies within the line segments
        if (isPointOnLine(intersection, this) && isPointOnLine(intersection, other)) {
            return intersection;
        } else {
            return null;
        }
    }

    /**
     * Returns the closest intersection point to the start of the line with the given rectangle.
     * If there are no intersection points, returns null.
     *
     * @param rect the rectangle to check for intersection
     * @return the closest intersection point to the start of the line, or null if no intersections
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        ArrayList<Point> l = (ArrayList<Point>) rect.intersectionPoints(this);
        if (l.isEmpty()) {  // Case geometricshapes.Line doesn't intersect geometricshapes.Rectangle
            return null;
        }
        int size = l.size(), i = 0;
        Point toRet = l.get(i);
        for (i = 1; i < size; i++) {
            if (start.distance(l.get(i)) < start.distance(toRet)) {
                toRet = l.get(i);
            }
        }
        return toRet;
    }

    /**
     * Checks if a point is on a given line segment.
     *
     * @param p    the point to check
     * @param line the line segment to check against
     * @return true if the point is on the line segment, false otherwise
     */
    private boolean isPointOnLine(Point p, Line line) {
        double x1 = line.start().getX();
        double y1 = line.start().getY();
        double x2 = line.end().getX();
        double y2 = line.end().getY();
        double px = p.getX();
        double py = p.getY();

        boolean onSegmentX = Math.min(x1, x2) - comparisonThreshold <= px
                && px <= Math.max(x1, x2) + comparisonThreshold;
        boolean onSegmentY = Math.min(y1, y2) - comparisonThreshold <= py
                && py <= Math.max(y1, y2) + comparisonThreshold;
        return onSegmentX && onSegmentY;
    }
    /**
     * checks if the point is on the line boolean.
     *
     * @param point the point
     * @return the boolean if point on the line or not.
     */
    public boolean isPointOnLine(Point point) {
        return (this.start().distance(this.end()) == (point.distance(this.start()) + point.distance(this.end())));
    }

}
