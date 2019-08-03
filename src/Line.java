
import java.util.List;

/**
 * @author Michal Abramovitch
 * Class name: Line.
 * create Line objects with start and end points
 */
public class Line {
    private Point pointstart;
    private Point pointend;
    private Point middle;

    /**
     * constructors.
     *
     * @param start is the point that the line start.
     * @param end   is the point which the line ends.
     */
    public Line(Point start, Point end) {
        this.pointstart = start;
        this.pointend = end;
        this.middle = this.calculateMiddle();
    }

    /**
     * constructors.
     *
     * @param x1 is the x value of the start point
     * @param y1 is the y value of the start point
     * @param x2 is the x value of the end point
     * @param y2 is the y value of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.pointstart = new Point(x1, y1);
        this.pointend = new Point(x2, y2);
        this.middle = this.calculateMiddle();
    }

    /**
     * Return the length of the line.
     *
     * @return length of the line
     */
    public double length() {
        return this.pointstart.distance(this.pointend);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        return this.middle;
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.pointstart;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.pointend;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other is the other line that we check if our line intersect
     *              with
     * @return if the lines intersect or not.
     */
    public boolean isIntersecting(Line other) {
        return (intersectionWith(other) != null);
    }
    /**
     *Returns the intersection point if the lines intersect,
     // and null otherwise.
     *
     * @param other is the other line that we check if our line intersect
     *              with
     * @return the intersection point of two lines in case they are intersect,
     * and null in case they are not.
     */
    public Point intersectionWith(Line other) {
        if (verticalLine(other) == 1) {
            return new Point(other.pointstart.getX(), this.pointstart.getY());
        }
        if  (verticalLine(other) == 2) {
            return new Point(this.pointstart.getX(), other.pointstart.getY());
        }
        if (this.pointstart.getX() == this.pointend.getX() && other.pointstart.getX() == other.pointend.getX()) {
            return null;
        }


        double m1 = this.slope();
        double m2 = other.slope();
        if (m1 == m2) {
            return null;
        }



        double b1 = this.pointstart.getY() - (m1 * this.start().getX());
        double b2 = other.start().getY() - (m2 * other.start().getX());
        double finalX = -((b1 - b2) / (m1 - m2));
        double finalY = ((m1 * finalX) + b1);
        if (this.pointstart.getX() == this.pointend.getX()) {
            finalX = this.pointstart.getX();
            finalY = ((m2 * finalX) +  b2);

        }
        if (other.pointstart.getX() == other.pointend.getX()) {
            finalX = other.pointstart.getX();
            finalY = ((m1 * finalX) +  b1);

        }


        if (inrange(other, finalX, finalY)) {
            return new Point(finalX, finalY);
        }
        return null;
    }

    /**
     * equals -- return true is the lines are equal, false otherwise.
     * the method check both start points and end points of the two lines.
     *
     * @param other the other line that we check with if the line are equal.
     * @return if the lines are equal or not.
     */
    public boolean equals(Line other) {
        if (this.pointstart.equals(other.pointstart) && this.pointend.equals(other.pointend)) {
            return true;
        }
        if  (this.pointstart.equals(other.pointend) && this.pointend.equals(other.pointstart)) {
            return true;
        }
        return this.pointend.equals(other.pointstart)  && this.pointstart.equals(other.pointend);
    }
    /**
     * the method calculate the middle of the line.
     *
     * @param
     * @return a point of the middle of the line.
     */
    private Point calculateMiddle() {
        double mX = (this.pointstart.getX() + this.pointend.getX()) / 2;
        double mY = (this.pointstart.getY() + this.pointend.getY()) / 2;
        return new Point(mX, mY);
    }
    /**
     * the method calculate the slope of the line equation.
     * @return a double that contains the slope value.
     */
    private double slope() {
        return ((this.pointend.getY() - this.pointstart.getY()) / (this.pointend.getX() - this.pointstart.getX()));
    }



    /**
     * the method check if a point can be found on the two lines.
     *
     * @param x1 the point that we want to check if it can be found on
     *           a specific line.
     * @param y1 the point that we want to check if it can be found on
     *           a specific line.
     * @param other the second specific line which on him we
     *               want to check if the point can be found
     * @return if the point is on both line or not.
     */
    private boolean inrange(Line other, double x1, double y1) {

        double epsilon = 0.0001;

        return (Math.min(this.pointstart.getX(), this.pointend.getX()) <= x1  + epsilon)
                && (x1 <= Math.max(this.pointstart.getX(), this.pointend.getX()) + epsilon)
                && (Math.min(this.pointstart.getY(), this.pointend.getY()) <= y1 + epsilon)
                && (y1 <= Math.max(this.pointstart.getY(), this.pointend.getY()) + epsilon)
                && (Math.min(other.pointstart.getX(), other.pointend.getX()) <= x1  + epsilon)
                && (x1 <= Math.max(other.pointstart.getX(), other.pointend.getX()) + epsilon)
                && (Math.min(other.pointstart.getY(), other.pointend.getY()) <= y1 + epsilon)
                && (y1 <= Math.max(other.pointstart.getY(), other.pointend.getY()) + epsilon);



    }
    /**
     * check if the  two lines are vertical.
     *
     * @param other the other  line  with which to check whether the line is vertical
     * @return 1 if this line Parallel to the x axis.
     *         2 if other line Parallel to the x axis
     */
    private int verticalLine(Line other) {
        if (this.slope() == 0 && (Double.compare(other.pointstart.getX(), other.pointend.getX()) == 0)) {
            if (inrange(other, other.pointstart.getX(), this.pointstart.getY())) {
                return 1;
            }
        }
        if (other.slope() == 0 && (Double.compare(this.pointstart.getX(), this.pointend.getX()) == 0)) {
            if (inrange(other, this.pointstart.getX(), other.pointstart.getY())) {
                return 2;
            }
        }
        return 0;
    }
    /**
     * method that find the closest intersection point to the start of the line.
     *
     * @param rect the rectangle the line intersect with.
     * @return the closest intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List interPoints = rect.intersectionPoints(this);
        if (interPoints == null) {
            return null;
        }
        Point p = this.pointend;
        int i = 0;
        while (i < interPoints.size()) {
            if (Double.compare(this.pointstart.distance((Point) interPoints.get(i))
                    , this.pointstart.distance(p)) <= 0) {
                p = (Point) interPoints.get(i);
            }
            i++;
        }
        return p;
    }
    /**
     * maxX method.
     * @return the maximum X value of a Line
     */
    public double maxX() {
        return Math.max(this.pointstart.getX(), this.pointend.getX());
    }

    /**
     * minX method.
     * @return the minimum X value of a Line
     */
    public double minX() {
        return Math.min(this.pointstart.getX(), this.pointend.getX());
    }
    /**
     * maxY method.
     * @return the maximum Y value of a Line
     */
    public double maxY() {
        return Math.max(this.pointstart.getY(), this.pointend.getY());
    }

    /**
     * minY method.
     * @return the minimum Y value of a Line
     */
    public double minY() {
        return Math.min(this.pointstart.getY(), this.pointend.getY());
    }


}
