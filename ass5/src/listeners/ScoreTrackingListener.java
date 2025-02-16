//326367570 Orian Eluz
package listeners;

import collidablesprites.Block;
import collidablesprites.Ball;
import counters.Counter;

/**
 * The ScoreTrackingListener class is responsible for updating the score counter when a block is hit.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener with a specified score counter.
     *
     * @param scoreCounter the counter for tracking the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Handles the hit event where a ball hits a block. If the ball's color doesn't match the block's color,
     * the score is increased.
     *
     * @param beingHit the block being hit by the ball
     * @param hitter the ball that hits the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.getColor().equals(hitter.getColor())) {
            this.currentScore.increase(5);
        }
    }
}
