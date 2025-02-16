//326367570 Orian Eluz
package collidablesprites;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import geometricshapes.Rectangle;
import geometricshapes.Point;
import geometricshapes.Velocity;
import geometricshapes.Line;
import animation.Game;

import java.awt.Color;

/**
 * The collidablesprites.Paddle class represents the paddle in the game,
 * which is controlled by the player using the keyboard.
 * The paddle can move left and right and interacts with other objects in the game.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle paddle;
    private double rightLim;
    private double leftLim;
    private int speed;

    /**
     * builds a new collidablesprites.Paddle.
     *
     * @param r        the rectangle representing the paddle's shape and position.
     * @param rightLim the right limit of the paddle's movement.
     * @param leftLim  the left limit of the paddle's movement.
     * @param speed    the speed at which the paddle moves.
     * @param key      the keyboard sensor to control the paddle's movement.
     */
    public Paddle(Rectangle r, double rightLim, double leftLim, int speed, KeyboardSensor key) {
        this.paddle = r;
        this.rightLim = rightLim;
        this.leftLim = leftLim;
        this.speed = speed;
        this.keyboard = key;
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        // Check if the paddle touches or exceeds the left limit.
        if (this.leftLim - 50 < this.paddle.getUpperLeft().getX() - (speed + 4)) {
            // Move the paddle to the left by 'speed' units.
            this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() - speed,
                    this.paddle.getUpperLeft().getY()),
                    this.paddle.getWidth(), this.paddle.getHeight());
        } else {
            // If the paddle touches the left boundary, reappear on the right side.
            this.paddle = new Rectangle(new Point(this.rightLim - this.paddle.getWidth(),
                    this.paddle.getUpperLeft().getY()),
                    this.paddle.getWidth(), this.paddle.getHeight());
        }
    }
    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        // Check if the paddle touches or exceeds the right limit.
        if (this.rightLim + 50 > this.paddle.getUpperLeft().getX() + this.paddle.getWidth() + (speed + 4)) {
            // Move the paddle to the right by 'speed' units.
            this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() + speed,
                    this.paddle.getUpperLeft().getY()),
                    this.paddle.getWidth(), this.paddle.getHeight());
        } else {
            // If the paddle touches the right boundary, reappear on the left side.
            this.paddle = new Rectangle(new Point(10, this.paddle.getUpperLeft().getY()),
                    this.paddle.getWidth(), this.paddle.getHeight());
        }
    }
    /**
     * Notifies the paddle that time has passed and checks for keyboard input to move the paddle.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param surface the surface to draw the paddle on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.ORANGE);
        surface.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }
    /**
     * Gets the collision rectangle of the paddle.
     *
     * @return the rectangle representing the paddle's shape and position.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }
    /**
     * Calculates the new velocity of the ball after it hits the paddle.
     *
     * @param collisionPoint  the collision point on the paddle.
     * @param currentVelocity the current velocity of the ball.
     * @param hitter   the ball that hits the block
     * @return the new velocity of the ball.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        double x1 = this.paddle.getUpperLeft().getX();
        double y1 = this.paddle.getUpperLeft().getY();
        double width = this.paddle.getWidth();
        // Make five lines of five regions on the paddle.
        Line left2 = new Line(x1 - 5, y1, x1 + (width / 5), y1);
        Line left1 = new Line(x1 + (width / 5), y1, x1 + (2 * width / 5), y1);
        Line middle = new Line(x1 + (2 * width / 5), y1, x1 + (3 * width / 5), y1);
        Line right1 = new Line(x1 + (3 * width / 5), y1, x1 + (4 * width / 5), y1);
        Line right2 = new Line(x1 + (4 * width / 5), y1, x1 + (width) + 5, y1);
        Line rightPadLim = new Line(this.paddle.getUpperRight(), this.paddle.getDownRight());
        Line leftPadLim = new Line(this.paddle.getUpperLeft(), this.paddle.getDownnerLeft());
        // If dy < 0 it's a sign that it is the opposite direction.
        if (currentVelocity.getDy() > 0) {
            // If it's on the most left region.
            if (left2.isPointOnLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(-60, (speed));
            }
            // If it's on the middle-left region.
            if (left1.isPointOnLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(-30, (speed));
            }
            // If it's on the middle region.
            if (middle.isPointOnLine(collisionPoint)) {
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }
            // If it's on the right-middle region.
            if (right1.isPointOnLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(30, (speed));
            }
            // If it's on the most right region.
            if (right2.isPointOnLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(60, (speed));
            }
        }
        // Check if it hits the left/right line of the paddle. If yes, it changes the dx.
        if ((leftPadLim.isPointOnLine(collisionPoint) && currentVelocity.getDx() > 0)
                || (rightPadLim.isPointOnLine(collisionPoint) && currentVelocity.getDx() < 0)
                || this.paddle.isPointInRectangle(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            return currentVelocity;
        }
    }
    /**
     * Adds this paddle to the game.
     *
     * @param g the game to add the paddle to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
