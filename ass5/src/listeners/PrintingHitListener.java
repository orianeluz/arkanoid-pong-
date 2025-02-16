//326367570 Orian Eluz
package listeners;

import collidablesprites.Block;
import collidablesprites.Ball;

/**
 * The PrintingHitListener class is a simple implementation of HitListener that prints a message when a block is hit.
 */
public class PrintingHitListener implements HitListener {
    /**
     * Prints a message to the console indicating that a block was hit.
     *
     * @param beingHit the block that is hit
     * @param hitter the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A collidablesprites.Block was hit.");
    }
}
