
/**
 * @author Michal abramovitch
 * an interface for listeners to the game.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit is the block being hit.
     * @param hitter   parameter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}