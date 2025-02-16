//326367570 Orian Eluz
package listeners;

import collidablesprites.Ball;
import collidablesprites.Block;
import animation.Game;
import counters.Counter;

/**
 * The BallRemover class is responsible for removing balls from the game and keeping track of the remaining balls.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a BallRemover with a specified game and counter for remaining balls.
     *
     * @param game the game instance
     * @param remainingBalls the counter for tracking the remaining balls
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Handles the hit event where a ball hits a block. The ball is removed from the game and the counter is decreased.
     *
     * @param beingHit the block being hit by the ball
     * @param hitter the ball that hits the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
