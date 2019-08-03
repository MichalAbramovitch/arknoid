
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Michal abramovitch
 * KeyPressStoppableAnimation Class.
 *
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * KeyPressStoppableAnimation constructor.
     * @param sensor the keyboard sensor
     * @param key the key which is pressed
     * @param animation the animation that runs
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }

    /**
     * doOneFrame method.
     * runs the animation and checks if the key is pressed, to check if to stop the animation
     * @param d the surface
     * @param dt the amount of seconds passed since the last call
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.isAlreadyPressed) {
            this.animation.doOneFrame(d, dt);
        } else {
            this.isAlreadyPressed = false;
        }
        if (this.sensor.isPressed(this.key)) {
            this.stop = true;
        }
    }

    /**
     * shouldStop method.
     * checks if the animation should stop
     * @return true - the animation should stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
