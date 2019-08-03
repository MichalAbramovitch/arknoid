
import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 *  @author Michal abramovitch
 *
 * Class Name: Block.
 *the class will create a block, draw a block, change its direction according to the hit
 *and will add it to the game
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rec;
    private java.awt.Color color;
    private int hitsCounter;
    private java.awt.Color[] colorArr;
    private Image[] imageArr;
    private Color stroke;
    private List<HitListener> hitListeners = new ArrayList<>();


    /**
     * getCollisionRectangle method.
     *
     * @return the block in which there was a collision
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }


    /**
     * Block constructor.
     *
     * @param block - a rectangle which will become a block
     * @param color - the color of the block
     */
    public Block(Rectangle block, java.awt.Color color) {
        this.rec = block;
        this.color = color;
        this.hitsCounter = 5;

    }
    /**
     * Block constructor.
     *
     * @param block - a rectangle which will become a block
     * @param hitsCounter - the hits of the block
     */
    public Block(Rectangle block, int hitsCounter) {
        this.rec = block;
        this.hitsCounter = hitsCounter;
        this.stroke = null;
        this.colorArr = new Color[this.hitsCounter];
        this.imageArr = new Image[this.hitsCounter];

    }


    /**
     * constructor.
     *
     * @param block       the rectangle that represent the block.
     * @param color       the color of the block.
     * @param hitsCounter the number of hits.
     */
    public Block(Rectangle block, Color color, int hitsCounter) {
        this.rec = block;
        this.color = color;
        this.hitsCounter = hitsCounter;
    }



    /**
     * Sets this stroke.
     * @param s our new stroke.
     */
    public void setStroke(java.awt.Color s) {
        this.stroke = s;
    }


    /**
     *
     * @param hitter the ball that hit the block.

     * @param collisionPoint  the point in which there was a collision
     * @param currentVelocity the current velocity of the object that collided with the block
     * @return velocity- the new velocity of the collision object
     * change the ball's velocity according to the collision place in the block
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.getY() - rec.getDownerLine().start().getY() == 0
                || collisionPoint.getY() - rec.getUpperLine().start().getY() == 0) {
            // change the 'y' value of the velocity.
            currentVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
        } else if ((collisionPoint.getX() - rec.getLeftLine().start().getX() == 0)
                || collisionPoint.getX() - rec.getRightLine().start().getX() == 0) {
            //update the 'x' value of the velocity.
            currentVelocity = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            currentVelocity = new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy() * -1);
        }
        if (this.hitsCounter > 0) {
            this.hitsCounter--;
        }
        //returns the new velocity after the hit.
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * drawing the block on the surface.
     * @param surface the surface that we want to draw on
     */
    public void drawOn(DrawSurface surface) {
        Point p = this.rec.getUpperLeft();
        double dx = this.rec.getWidth();
        double dy = this.rec.getHeight();
        if (this.hitsCounter == -1) {
            surface.setColor(Color.GRAY);
            surface.fillRectangle((int) p.getX(), (int) p.getY(), (int) dx, (int) dy);
        } else if (this.hitsCounter == 0) {
            surface.setColor(this.color);
            surface.fillRectangle((int) p.getX(), (int) p.getY(), (int) dx, (int) dy);
        } else if (this.colorArr[this.hitsCounter - 1] != null) {
            surface.setColor(this.colorArr[this.hitsCounter - 1]);
            surface.fillRectangle((int) p.getX(), (int) p.getY(), (int) dx, (int) dy);
        } else if (this.imageArr[this.hitsCounter - 1] != null) {
            surface.drawImage((int) p.getX(), (int) p.getY(), this.imageArr[this.hitsCounter - 1]);
        } else if (this.colorArr[0] != null) {
            surface.setColor(this.colorArr[0]);
            surface.fillRectangle((int) p.getX(), (int) p.getY(), (int) dx, (int) dy);
        } else {
            surface.drawImage((int) p.getX(), (int) p.getY(), this.imageArr[0]);
        }
        if (this.stroke != null) {
            surface.setColor(this.stroke);
            surface.drawRectangle((int) p.getX(), (int) p.getY(), (int) dx, (int) dy);
        }
        surface.setColor(Color.WHITE);
    }

    /**
     * timePassed.
     * letting the block know that time has passed
     * @param dt the speed
     */
    public void timePassed(double dt) {
    }

    /**
     * addToGame the block.
     * add the block to the game
     *
     * @param g the game
     */
    public void addToGame(GameLevel  g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removeFromGame method.
     * add the block to the game
     * @param game the game
     */
    public void removeFromGame(GameLevel  game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl hit listener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl hit listener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * /**
     * call this method whenever a hit occurs.
     * notifies all listeners that this block has been hit.
     *
     * @param hitter the ball hitting this block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


    /**
     * returns the hitting points.
     *
     * @return hitting points.
     */
    public int getHitsCounter() {
        return hitsCounter;
    }

    /**
     * @return the block's width.
     */
    public int getWidth() {
        return ((int) this.rec.getWidth());
    }
    /**
     * setColor to the block.
     * @param c - the color in which we want the rectangle to be
     */
    public void setColor(java.awt.Color c) {
        this.color = c;
    }


    /**
     * setImageArr.
     * @param image the image in which we want the rectangle to be
     *@param index the place of the image im the array
     */
    public void setImageArr(Image image, int index) {
        this.imageArr[index] = image;
    }
    /**
     * setColorArr.
     * @param c - the color in which we want the rectangle to be
     *@param index the place of the color im the array
     */
    public void setColorArr(Color c, int index) {
        this.colorArr[index] = c;
    }


}