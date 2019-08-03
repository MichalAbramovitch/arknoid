import biuoop.DrawSurface;
import biuoop.GUI;
/**
 * @author michal Abramovitch
 */
/**
 * a class that controls the animation loop.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * AnimationRunner constructor.
     * @param gui - GUI.
     * @param framesPerSecond - frames per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * run method.
     * @param animation - anumation object
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        biuoop.Sleeper sleep = new biuoop.Sleeper();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d, (double) 1 / this.framesPerSecond);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleep.sleepFor(milliSecondLeftToSleep);
            }
        }
    }


    /**
     * getter for the gui.
     *
     * @return the game's gui.
     */
    public GUI getGui() {
        return gui;
    }
}