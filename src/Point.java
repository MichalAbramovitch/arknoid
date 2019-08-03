/**
 * @author Michal Abramovitch
 */
/**
 * the class is an object represents a point.
 */
public class Point {
     private double x;
     private double y;
    /**
     * constructor.
     *
     * @param x the x value of the point.
     * @param y the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other the point that we want the check her length from our
     *              point
     * @return the distance between the two points
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }


    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other the point that we compare with.
     * @return whether the points are equal or not
     */
    public boolean equals(Point other) {
        return ((this.x == other.getX()) && (this.y == other.getY()));
    }

    /**
     * Return the x  of this point.
     *
     * @return the x value of the point.
     */
    public double getX() {
        return this.x;
    }
    /**
     * Return the y  of this point.
     *
     * @return the y value of the point.
     */
    public double getY() {
        return  this.y;
    }
}
