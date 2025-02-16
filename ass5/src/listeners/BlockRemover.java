//326367570 Orian Eluz
package listeners;

import collidablesprites.Block;
import collidablesprites.Ball;
import animation.Game;
import counters.Counter;

/**
 * The BlockRemover class is responsible for removing blocks from the game and keeping track of the remaining blocks.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a BlockRemover with a specified game and counter for remaining blocks.
     *
     * @param game the game instance
     * @param removedBlocks the counter for tracking the remaining blocks
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Handles the hit event where a ball hits a block. If the ball's color doesn't match the block's color, the block
     * is removed from the game, and the counter is decreased.
     *
     * @param beingHit the block being hit by the ball
     * @param hitter the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.ballColorMatch(hitter)) {
            // Change ball color to block color
            hitter.setColor(beingHit.getColor());
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
}
