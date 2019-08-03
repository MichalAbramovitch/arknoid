
import biuoop.DrawSurface;
/**
 *@author Michal abramovitch
 *
 * interface Sprite.
 * draws the sprites on the surface and let them know that time has passed
 */
public interface Sprite {
    /**
     * drawOn method.
     * draw the sprite to the screen
     * @param d the surface on which we want t draw the sprite object
     */
    void drawOn(DrawSurface d);
    /**
     * timePassed method.
     * notify the sprite that time has passed
     * @param dt the time the passed since the beginning of the operation
     */
    void timePassed(double dt);

}