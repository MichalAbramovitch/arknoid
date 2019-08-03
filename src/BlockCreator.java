
/**
 * @author Michal abramovitch
 * BlockCreator interface.
 */
public interface BlockCreator {
    /**
     * create method.
     * @param xpos the upper left x coordinate
     * @param ypos the upper left y coordinate
     * @return the created  block
     */
    // Create a block at the specified location.
    Block create(int xpos, int ypos);
}