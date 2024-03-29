
import biuoop.DrawSurface;

import java.awt.Color;



/**
 * a class that will indicate how many lives are left.
 */
public class LivesIndicator implements Sprite {
    private Counter livesCounter;

    /**
     * constructor.
     *
     * @param c the counter to initialize the class.
     */
    public LivesIndicator(Counter c) {
        this.livesCounter = c;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the drawsurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(20, 15, "lives left :" + String.valueOf(livesCounter.getValue()), 15);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt the amount of seconds passed since the last call
     */
    public void timePassed(double dt) {
    }

    /**
     * add a sprite into game object depends on his feature.
     *
     * @param g the game to be added to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}