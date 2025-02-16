//326367570 Orian Eluz
package collidablesprites;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import geometricshapes.Point;
import geometricshapes.Rectangle;
import geometricshapes.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;
import animation.Game;

/**
 * The collidablesprites.Block class represents a block in the game that can be collided with and drawn on the screen.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructs a collidablesprites.Block with a specified upper-left point, height, width, and color.
     *
     * @param upperLeft the upper-left point of the block.
     * @param height the height of the block.
     * @param width the width of the block.
     * @param color the color of the block.
     */
    public Block(Point upperLeft, double height, double width, Color color) {
        this.block = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Returns the collision rectangle of the block.
     *
     * @return the rectangle representing the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * removing the block fromm the game.
     * @param game the game that the block is removed from.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * Checks if the color of the ball matches the color of the block.
     *
     * @param ball the ball to check against.
     * @return true if the colors match, false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        double newDx, newDy;
        newDx = currentVelocity.getDx();
        newDy = currentVelocity.getDy();

        // Check if the collision point is on the left/right line
        if ((block.getRightSide().isPointOnLine(collisionPoint) && currentVelocity.getDx() < 0)
                || (currentVelocity.getDx() > 0 && block.getLeftSide().isPointOnLine(collisionPoint))) {
            newDx = -currentVelocity.getDx();
        }
        // Check if the collision point is on the up/down line.
        if ((block.getTopSide().isPointOnLine(collisionPoint) && currentVelocity.getDy() > 0)
                || (currentVelocity.getDy() < 0 && block.getBottomSide().isPointOnLine(collisionPoint))) {
            newDy = -currentVelocity.getDy();
        }
        // Notify listeners of the hit
        this.notifyHit(hitter);
        return new Velocity(newDx, newDy);
    }


    /**
     * Draws the block on the given surface.
     *
     * @param surface the surface on which the block will be drawn.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
    }

    /**
     * Notifies the block that a unit of time has passed.
     */
    public void timePassed() {
    }

    /**
     * Adds the block to the game.
     *
     * @param g the game to which the block will be added.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Returns the current color of the block.
     *
     * @return the color of the block
     */
    public Color getColor() {
        return color;
    }
}
