
import java.util.List;
/**
 * @author Michal abramovitch
 * an interface giving information for a level of the game.
 */
public interface LevelInformation {

    /**
     * numberOfBalls method.
     * @return the number of the balls for the stage
     */
    int numberOfBalls();

    /**
     * initialBallVelocities method.
     * @return the initial velocities of the balls
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed method.
     * @return the paddle's speed
     */
    int paddleSpeed();

    /**
     * paddleWidth method.
     * @return the paddle's width
     */
    int paddleWidth();

    /**
     * levelName method.
     * sets the level's name that will be displayed at the top of the screen
     * @return the level's name
     */
    String levelName();

    /**
     * getBackground method.
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * blocks method.
     * @return the Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * numberOfBlocksToRemove method.
     * @return the number of blocks that needs to be removed
     */
    int numberOfBlocksToRemove();
}