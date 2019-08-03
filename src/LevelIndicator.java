
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author michal Abramovitch
 */
public class LevelIndicator implements Sprite {
    private String nameOfLevel;

    /**
     * constructor.
     *
     * @param name is the name to be written on the screen.
     */
    public LevelIndicator(String name) {
        this.nameOfLevel = name;
    }

    /**
     * draws the name on the top right side of the screen.
     *
     * @param d the draw surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(550, 15, "Level Name: " + this.nameOfLevel, 15);
    }

    @Override
    public void timePassed(double dt) {

    }
    /**
     * adds to a given game.
     * @param g the game to add the sprite.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }


}