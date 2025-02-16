//326367570 Orian Eluz
package collidablesprites;

import biuoop.DrawSurface;
import java.awt.Color;
import geometricshapes.Velocity;
import geometricshapes.Point;
import geometricshapes.Line;
import animation.Game;

/**
 * The Ball class represents a ball with a center point, radius, color, velocity,
 * and screen dimensions. The ball can move and bounce off the boundaries of the screen
 * and within specified rectangular obstacles.
 */
public class Ball implements Sprite {
    private Point center;
    private final int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment env;
    private Game game;

    /**
     * Constructs a Ball with the specified center point, radius, color, and game environment.
     *
     * @param center      the center point of the ball
     * @param radius      the radius of the ball
     * @param color       the color of the ball
     * @param environment the game environment containing the ball
     * @param game        the game instance
     */
    public Ball(Point center, int radius, Color color, GameEnvironment environment, Game game) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.env = environment;
        this.velocity = new Velocity(0, 0);  // Default velocity
        this.game = game;
    }

    /**
     * Returns the x-coordinate of the center point of the ball.
     *
     * @return the x-coordinate of the center point
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Returns the y-coordinate of the center point of the ball.
     *
     * @return the y-coordinate of the center point
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Returns the center point of the ball.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return radius;
    }

    /**
     * Returns the current velocity of the ball.
     *
     * @return the current velocity of the ball
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Sets the color of the ball.
     *
     * @param color the new color of the ball
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param dx the change in x-coordinate per time unit
     * @param dy the change in y-coordinate per time unit
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param velo the new velocity of the ball
     */
    public void setVelocity(Velocity velo) {
        this.velocity = velo;
    }

    /**
     * Moves the ball one step according to its velocity, considering potential collisions.
     */
    public void moveOneStep() {
        Velocity tempA = this.getVelocity();
        if (!this.isBallCrossTheBorder()) {
            this.collisionWithObject();
        }
        if (tempA.getDx() == this.velocity.getDx() && tempA.getDy() == this.velocity.getDy()) {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * Checks if the ball crosses the screen borders and adjusts its position and velocity if needed.
     *
     * @return true if the ball crosses the border, false otherwise
     */
    public boolean isBallCrossTheBorder() {
        boolean flag = false;
        double epsilon = 0.1; // Small value to move the ball out of collision
        int blockWidth = 20; // Width of the blocks around the frame

        // Cross left limit (considering left block).
        if (this.getX() - this.getSize() + this.getVelocity().getDx() < blockWidth && this.getVelocity().getDx() < 0) {
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
            this.center = new Point(blockWidth + this.getSize() + epsilon, this.center.getY());
            flag = true;
        }
        // Cross right limit (considering right block).
        if (this.getX() + this.getSize() + this.getVelocity().getDx() > 800 - blockWidth
                && this.getVelocity().getDx() > 0) {
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
            this.center = new Point(800 - blockWidth - this.getSize() - epsilon, this.center.getY());
            flag = true;
        }
        // Cross upper limit (considering top block).
        if (this.getY() - this.getSize() + this.getVelocity().getDy() < blockWidth && this.getVelocity().getDy() < 0) {
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
            this.center = new Point(this.center.getX(), blockWidth + this.getSize() + epsilon);
            flag = true;
        }

        return flag;
    }

    /**
     * Handles the collision of the ball with objects in the game environment.
     */
    public void collisionWithObject() {
        CollisionInfo collisionInf;
        Point start, end;
        Line trajectory;
        start = new Point(this.center.getX() - this.velocity.getDx(), this.center.getY() - this.velocity.getDy());
        end = new Point(this.center.getX() + this.velocity.getDx(), this.center.getY() + this.velocity.getDy());
        trajectory = new Line(start, end);
        collisionInf = this.env.getClosestCollision(trajectory);
        if (collisionInf != null) {
            Velocity newVel = collisionInf.collisionObject().hit(collisionInf.collisionPoint(), this.velocity, this);
            setVelocity(newVel);

            // Move the ball slightly away from the collision point to avoid sticking
            double epsilon = 1.0;
            double newX;
            if (newVel.getDx() > 0) {
                newX = collisionInf.collisionPoint().getX() + epsilon;
            } else {
                newX = collisionInf.collisionPoint().getX() - epsilon;
            }
            double newY;
            if (newVel.getDy() > 0) {
                newY = collisionInf.collisionPoint().getY() + epsilon;
            } else {
                newY = collisionInf.collisionPoint().getY() - epsilon;
            }
            this.center = new Point(newX, newY);
        }
    }

    /**
     * Draws the ball on the provided DrawSurface.
     *
     * @param surface the drawing surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), radius);
    }

    /**
     * Updates the state of the ball for each time unit.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Adds the ball to the specified game.
     *
     * @param g the game to which the ball should be added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Removes the ball from the specified game.
     *
     * @param game the game from which the ball should be removed
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

    /**
     * Returns the game instance associated with this ball.
     *
     * @return the game instance
     */
    public Game getGame() {
        return this.game;
    }
}
