
import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * @author Michal abramovitch
 *
 * Class SpriteCollection.
 * creates a collection of sprite, adds sprite to the collection,
 * notifies them that time has passed and draws them on the surface
 */
public class SpriteCollection {
    private ArrayList<Sprite> spritesList;
    /**
     * constructor which initiate new sprites list.
     */
    public SpriteCollection() {
        this.spritesList = new ArrayList();
    }
    /**
     * addSprite method.
     * @param s the sprite that's suppose to be added to the sprites' collection
     */
    public void addSprite(Sprite s) {
        this.spritesList.add(s);


    }
    /**
     * call timePassed() on all sprites.
     * @param dt the amount of seconds passed since the last call

     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.spritesList.size(); i++) {
            Sprite s = (Sprite) (this.spritesList.get(i));
            s.timePassed(dt);
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spritesList.size(); i++) {
            Sprite s = (Sprite) (this.spritesList.get(i));
            s.drawOn(d);
        }
    }
    /**
     * removes a sprite from the collection.
     *
     * @param s the sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        this.spritesList.remove(s);
    }
}
