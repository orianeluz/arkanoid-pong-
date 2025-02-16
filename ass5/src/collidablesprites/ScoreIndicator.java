//326367570 Orian Eluz
package collidablesprites;

import biuoop.DrawSurface;
import counters.Counter;
import animation.Game;
import geometricshapes.Rectangle;
import java.awt.Color;

/**
 * The ScoreIndicator class is responsible for displaying the current score on the screen.
 */
public class ScoreIndicator implements Sprite {
    private Rectangle rect;
    private Counter scoreCounter;

    /**
     * Constructs a ScoreIndicator with a specified rectangle and score counter.
     *
     * @param rect the rectangle representing the area for the score indicator
     * @param scoreCounter the counter that tracks the score
     */
    public ScoreIndicator(Rectangle rect, Counter scoreCounter) {
        this.rect = rect;
        this.scoreCounter = scoreCounter;
    }

    /**
     * Draws the score indicator on the provided DrawSurface.
     *
     * @param d the drawing surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(400, 15, "Score: " + this.scoreCounter.getValue(), 15);
    }

    /**
     * Updates the state of the score indicator for each time unit (not used in this implementation).
     */
    @Override
    public void timePassed() {
        // No actions needed for now
    }

    /**
     * Adds the score indicator to the specified game.
     *
     * @param game the game to which the score indicator should be added
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
