
/**
 * @author michal Abramovitch
 */
/**
 * Class Name: BallRemover.
 * the class removes the ball from the game
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param g the game this class removes blocks for.
     * @param c how many balls to start with.
     */
    public BallRemover(GameLevel g, Counter c) {
        this.game = g;
        this.remainingBalls = c;
    }


    /**
     * hitEvent method.
     * if an hit event has happened- removes the ball from the game
     * @param beingHit - the block that has been hit
     * @param hitter - the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCollisionRectangle().getUpperLeft().getY() == 600) {
            /* removes the ball from the game */
            hitter.removeFromGame(this.game);
            this.remainingBalls.decrease(1);
        }
    }

    }
