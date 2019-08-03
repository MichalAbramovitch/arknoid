import biuoop.DrawSurface;
/**
 * @author michal Abramovitch
 */
/**
 * Animation interface.
 */
public interface Animation {
    /**
     * doOneFrame method.
     * @param d - surface
     * @param dt the time that takes to do the operation.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * shouldStop method.
     * @return - true or false.
     */
    boolean shouldStop();
}
