//326367570 Orian Eluz
package collidablesprites;
import biuoop.DrawSurface;

/**
 * The collidablesprites.Sprite interface represents a game object that can be drawn
 * to the screen and can react to the passage of time.
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     *
     * @param d the DrawSurface on which the sprite will be drawn.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed, allowing it to update its state as needed.
     */
    void timePassed();
}
