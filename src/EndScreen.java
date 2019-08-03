
import biuoop.DrawSurface;

/**
 * @author michal Abramovitch
 */
/**
 * EndScreen Class.
 */
public class EndScreen implements Animation {
    private Counter score;
    private Counter lives;

    /**
     * EndScreen constructor.
     * @param score - score counter
     * @param lives - lives counter.
     */
    public EndScreen(Counter score, Counter lives) {
        this.score = score;
        this.lives = lives;
    }

    /**
     * @param d - surface
     * @param dt the amount of seconds passed since the last call
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.lives.getValue() == 0) {
            d.drawText(130, d.getHeight() / 2, "Game Over :(  Your score is " + this.score.getValue(), 35);
        } else {
            d.drawText(130, d.getHeight() / 2, "YOU WIN :) Your Score is " + this.score.getValue(), 35);
            d.drawText(130, d.getHeight() / 2, "press space to exit" , 35);

        }
    }

    /**
     * @return - true or false.
     */
    public  boolean shouldStop() {
        return true;
    }


}
