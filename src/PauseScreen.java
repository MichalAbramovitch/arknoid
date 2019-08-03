import biuoop.DrawSurface;

/**
 * @author michal Abramovitch
 *
 * PauseScreen method.
 * the class of the pause screen
 */
public class PauseScreen implements Animation {


    /**
     * PauseScreen constructor.
     */
    public PauseScreen() {

    }

    /**
     * the frame of the screen.
     * @param d - the surface we need to draw on
     * @param dt the amount of seconds passed since the last call
     * draws the pause screen
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(130, d.getHeight() / 2, "paused -- press space to continue", 32);

    }

    /**
     * shouldStop method.
     * @return boolean
     */
    public boolean shouldStop() {
        return true;
    }
}
