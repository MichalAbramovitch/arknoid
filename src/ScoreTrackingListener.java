
/**
 * @author michal Abramovitch
 */
/**
 * Class Name: ScoreTrackingListener.
 * the scores listener- updates the scores according to the hits
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * ScoreTrackingListener constructor.
     * @param scoreCounter the scores counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent.
     * @param beingHit - the block that has been hit
     * @param hitter - the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        /* there was a hit- increase the scores counter by 5 */
        this.currentScore.increase(5);
        /* if the block is destroyed- increase the scores counter by 10 */
        if (beingHit.getHitsCounter() == 0) {
            this.currentScore.increase(10);
        }
    }
}
