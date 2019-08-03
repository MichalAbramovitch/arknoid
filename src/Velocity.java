
/**
 * @author Michal abramovitch
 * Class name: Velocity.
 * the class creates Velocity objects.
 * each object represent its velocity by differences in x and y coordinates.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     *
     * @param dx - the dx
     * @param dy - the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * receives a point and returns a new point after velocity changes.
     *
     * @param p  the previous point.
     * @param dt the game time counter.
     * @return returns the new point.
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(p.getX() + (this.dx * dt), p.getY() + (this.dy * dt));
    }

    /**
     * @return - returns dx
     */
    public double getDx() {
        return dx;
    }
    /**
     * @return - returns dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * fromAngleAndSpeed method.
     * @param angle - angle in degrees
     * @param speed - speed of the moving object
     * @return Velocity object with dx and dy by the angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        /* calculate the dx by the sine formula */
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        /* calculate the dy by the cosines formula */
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }


}