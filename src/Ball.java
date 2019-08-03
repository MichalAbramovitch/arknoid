
import biuoop.DrawSurface;

import java.awt.Color;


/**
 * @author michal Abramovitch
 */

/**
 * Class name: Ball.
 * the class creates Ball objects.
 * each Ball have center point, radius and color.
 * the user can create also a moving ball by giving the object velocity and borders.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity vel;
    private GameEnvironment gameEnvironment;

    /**
     * constructor.
     *
     * @param x - point center
     * @param y - point center
     * @param r      - radius
     * @param color  - color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.vel = null;
        this.gameEnvironment = null;
    }

    /**
     * constructor.
     *
     * @param x     - x of center
     * @param y     - y of center
     * @param r     - radius of ball
     * @param color - color of ball
     * @param collidables - the
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment collidables) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.gameEnvironment = collidables;
    }
    /**
     * sets the game environment.
     *
     * @param collidables the game environment.
     */
    public void setGame(GameEnvironment collidables) {
        this.gameEnvironment = collidables;
    }

    /**
     * @return - returns the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * @param surface - the surface that we draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(Color.black);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * getX method.
     * @return the x of the center point
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * getY method.
     *
     * @return the y of the center point
     */
    public int getY() {
        return (int) center.getY();
    }
    /**
     * set velocity.
     * @param v - velocity
     *
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }

    /**
     * set velocity.
     *
     * @param dx - dx
     * @param dy - dy
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }

    /**
     * @return - returns velocity
     */
    public Velocity getVelocity() {
        return vel;
    }

    /**
     * moveOneStep method.
     * when the ball gets to its borders, the method change the velocity of the ball
     * @param dt the speed.
     */
    public void moveOneStep(double dt) {

        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center, dt));
        CollisionInfo collision = this.gameEnvironment.getClosestCollision(trajectory);
        if (collision == null) {

            this.center = trajectory.end();
        } else {
            Velocity currentVelocity = collision.collisionObject().hit(this,
                     collision.collisionPoint(), this.getVelocity());
            this.setVelocity(currentVelocity);
        }
    }

    /**
     * let the ball that time has passed- and move it one step.
     * @param dt the amount of seconds passed since the last call
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }
    /**
     * add the ball to the game.
     * @param g the game that the ball needs to be added to.
     */
    public void addToGame(GameLevel  g) {
        g.addSprite(this);
    }

    /**
     * removeFromGame the ball to the game.
     * @param g - the game that the ball needs to be added to
     */
    public void removeFromGame(GameLevel  g) {
        g.removeSprite(this);
    }


}
