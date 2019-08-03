
/**
 * @author michal Abramovitch
 */
/**
 * BlockRemover Class.
 * in charge of removing blocks.
 */
public class BlockRemover implements HitListener {
    private GameLevel  game;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param game          the game this class removes blocks for.
     * @param removedBlocks how many block remain.
     */
    public BlockRemover(GameLevel  game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
    /**
     * Blocks that are hit and reach 0 hit-points will be removed.
     *
     * @param beingHit is the block being hit.
     * @param hitter   is the hitting ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitsCounter() == 0) {
            beingHit.removeFromGame(this.game);
            //removes this listener from the being hit's listeners list.
            beingHit.removeHitListener(this);
            //decreasing the amount of blocks by 1.
            this.remainingBlocks.decrease(1);

        }
    }
}