import java.util.ArrayList;
import java.util.List;
/**
 *@author Michal abramovitch
 * class that represents rectangle.
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;
        // Create a new rectangle with location and width/height.
    /**
     * Rectangle constructor.
     * Create a new rectangle with location and width/height.
     * @param upperLeft the upper left point of the rectangle
     * @param width the rectangle's width
     * @param height the rectangle's height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * method that create list of all the intersection points that the rectangle has with a line.
     *
     * @param line the line that intersect with the rectangle.
     * @return a list of all the intersection points.
     */
    public java.util.List intersectionPoints(Line line) {
        List interPoints = new ArrayList();
        Line[] edges = new Line[4];
        edges[0] = this.getLeftLine();
        edges[1] = this.getRightLine();
        edges[2] = this.getUpperLine();
        edges[3] = this.getDownerLine();
        for (int i = 0; i < 4; i++) {
            if (line.isIntersecting(edges[i])) {
                interPoints.add(line.intersectionWith(edges[i]));
                }
            }
        if (!interPoints.isEmpty()) {
            return interPoints;
        }
        return null;
    }


    /**
     * getWidth method.
     * @return the width and height of the rectangle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * getHeight method.
     * @return the height and height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getUpperLeft method.
     * Returns the upper-left point of the rectangle
     * @return this.upperLeft- the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * getUpperLeft method.
     * Returns the down -Left point of the rectangle
     * @return this.downLeft- the down left point of the rectangle
     */
    public Point getdownLeft() {
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.getHeight());
        return downLeft;
    }
    /**
     * getUpperLeft method.
     * Returns the upper-right  point of the rectangle
     * @return this.upperRigth- the upper left point of the rectangle
     */
    public Point getupperRigth() {
        Point upperRigth = new Point(this.upperLeft.getX() + this.getWidth(), this.upperLeft.getY());
        return upperRigth;
    }
    /**
     * getUpperLeft method.
     * Returns the down Rigth point of the rectangle
     * @return this.downRigth- the upper left point of the rectangle
     */
    public Point getdownRigth() {
        Point downRigth = new Point(this.upperLeft.getX() + this.getWidth(), this.upperLeft.getY() + this.getHeight());
        return downRigth;
    }
    /**
     * return the upper line of the rectangle's scope.
     *
     * @return the upper line.
     */
    public Line getUpperLine() {

        return new Line(this.getUpperLeft(), this.getupperRigth());
    }

    /**
     * return the downer line of the rectangle's scope.
     *
     * @return the downer line.
     */
    public Line getDownerLine() {

        return new Line(this.getdownLeft(), this.getdownRigth());
    }

    /**
     * return the left line of the rectangle's scope.
     *
     * @return the left line.
     */
    public Line getLeftLine() {

        return new Line(this.getUpperLeft(), this.getdownLeft());
    }

    /**
     * return the right line of the rectangle's scope.
     *
     * @return the right line.
     */
    public Line getRightLine() {

        return new Line(this.getupperRigth(), this.getdownRigth());

    }
    /**
     * setUpperLeft method.
     * sets the values of the upper left point of the rectangle
     * @param p the point of the rectangel
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }

}

