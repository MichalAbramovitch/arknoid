
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
/**
 * @author Michal abramovitch
 *
 * Class Paddle
 * creates the paddle, makes it to move, draws it etc.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle paddle;
    private java.awt.Color color;
    private biuoop.KeyboardSensor keyboard;
    private double leftBorder;
    private double rightBorder;
    private double speed;
    /**
     * Paddle constructor.
     * @param paddle the game's paddle
     * @param color the paddle's color
     * @param keyboard the keyboard for the paddle
     */
    public Paddle(Rectangle paddle, Color color, KeyboardSensor keyboard) {
        this.paddle = paddle;
        this.color = color;
        this.keyboard = keyboard;
    }
    /**
     * Paddle constructor.
     * @param paddle the game's paddle
     * @param color the paddle's color
     * @param keyboard the keyboard for the paddle
     * @param leftBorder the left border of the paddle
     * @param rightBorder the right border of the paddle
     * @param speed the speed  of the paddle


     */
    public Paddle(Rectangle paddle, Color color, KeyboardSensor keyboard, double leftBorder, double rightBorder
            , double speed) {
        this.paddle = paddle;
        this.color = color;
        this.keyboard = keyboard;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.speed = speed;
    }
    /**
     * moveLeft method.
     * moves the paddle to the left
     * @param dt the amount of seconds passed since the last call
     */
    public void moveLeft(double dt) {
        double x = this.paddle.getUpperLeft().getX();
        double y = this.paddle.getUpperLeft().getY();
        this.paddle.setUpperLeft(new Point(x - this.speed * dt, y));
    }
    /**
     * moves the paddle to the right.
     * @param dt the amount of seconds passed since the last call
     */
    public void moveRight(double dt) {
        double x = this.paddle.getUpperLeft().getX();
        double y = this.paddle.getUpperLeft().getY();
        this.paddle.setUpperLeft(new Point(x + this.speed * dt, y));
    }
    /**
     * timePassed method.
     * let the paddle know that time has passed and move it according to the user's press on the keyboard
     * @param dt the amount of seconds passed since the last call
     */
    public void timePassed(double dt) {

        /* if the user pressed the left key- move the paddle to the left if possible
         * (until the left border) */
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            if (this.paddle.getUpperLeft().getX() - this.speed * dt >= this.leftBorder) {
                this.moveLeft(dt);
            } else {
                double y = this.paddle.getUpperLeft().getY();
                this.paddle.setUpperLeft(new Point(this.leftBorder, y));
            }
        }
        /* if the user pressed the right key - move the paddle to the left if possible
          (until the right border) */
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if (this.paddle.getUpperLeft().getX() + this.paddle.getWidth() + this.speed * dt <= this.rightBorder) {
                this.moveRight(dt);
            } else {
                double y = this.paddle.getUpperLeft().getY();
                this.paddle.setUpperLeft(new Point(this.rightBorder - this.paddle.getWidth(), y));
            }
        }
    }
    /**
     * drawOn method.
     * drawing the paddle on the surface
     * @param d the surface on which we want to draw the ball
     */
    public void drawOn(DrawSurface d) {
        Point p = this.paddle.getUpperLeft();
        double dx = this.paddle.getWidth();
        double dy = this.paddle.getHeight();
        d.setColor(this.color);
        d.fillRectangle((int) p.getX(), (int) p.getY(), (int) dx, (int) dy);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) p.getX(), (int) p.getY(), (int) dx, (int) dy);
    }
    /**
     * getCollisionRectangle method.
     * @return this.paddle- the paddle in which there was a collision
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     *
     * change the ball's velocity according to the collision angle.
     * @param collisionPoint - the collision point of the ball in the paddle
     * @param currentVelocity - the ball's current velocity
     * @param hitter the ball which hit the paddle
     * @return the new ball's velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double regionDX = (this.paddle.getWidth() / 5);
        double currentX = this.paddle.getUpperLeft().getX();
        double currentY = this.paddle.getUpperLeft().getY();
        double vDX = currentVelocity.getDx();
        double vDY = currentVelocity.getDy();
        double newSpeed = Math.sqrt((vDX * vDX) + (vDY * vDY));
        /* change the ball's velocity according to the hit's angle in the paddle */
        if (collisionPoint.getX() <= regionDX + currentX) {
            return currentVelocity.fromAngleAndSpeed(300, newSpeed);
        } else if (collisionPoint.getX() <= 2 * regionDX + currentX) {
            return currentVelocity.fromAngleAndSpeed(330, newSpeed);
        } else if (collisionPoint.getX() <= 3 * regionDX + currentX) {
            return new Velocity(vDX, -vDY);
        } else if (collisionPoint.getX() <= 4 * regionDX + currentX) {
            return currentVelocity.fromAngleAndSpeed(30, newSpeed);
        } else if (collisionPoint.getY() < currentY) {
            return new Velocity(-vDX, vDY);
        } else {
            return currentVelocity.fromAngleAndSpeed(60, newSpeed);
        }
    }
    /**
     * add the paddle.
     * @param g Add this paddle to the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * removeFromGame the paddle.
     * @param g - remove this paddle from the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }
}