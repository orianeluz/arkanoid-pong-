//326367570 Orian Eluz
package animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collidablesprites.Ball;
import collidablesprites.SpriteCollection;
import collidablesprites.GameEnvironment;
import collidablesprites.Collidable;
import collidablesprites.Sprite;
import collidablesprites.Paddle;
import collidablesprites.Block;
import collidablesprites.ScoreIndicator;
import geometricshapes.Rectangle;
import geometricshapes.Velocity;
import geometricshapes.Point;
import listeners.BlockRemover;
import listeners.BallRemover;
import listeners.ScoreTrackingListener;
import counters.Counter;
import java.awt.Color;


/**
 * The Game class manages the game logic, including initializing and running the game.
 */
public class Game {
    private biuoop.KeyboardSensor keyboard;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter scoreCounter;



    /**
     * Constructs a Game object, initializing the sprite collection and game environment.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the sprite collection.
     *
     * @param s the sprite object to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a collidable from the game.
     * @param c the collidable to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.environment.delCollidable(c);
    }

    /**
     * Removes a sprite from the game.
     * @param s the sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.delSprite(s);
    }

    /**
     * Initializes the game, setting up the GUI, creating the paddle, balls, blocks, and frame.
     */
    public void initialize() {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.keyboard = gui.getKeyboardSensor();
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.scoreCounter = new Counter();
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(scoreCounter);


        // Create and add paddle
        Paddle paddle = new Paddle(new Rectangle(new Point(350, 560), 100, 20), 790, 10, 4, this.keyboard);
        paddle.addToGame(this);

        // Create and add balls
        Ball ball = new Ball(new Point(400, 300), 5, Color.BLACK, this.environment, this);
        ball.setVelocity(Velocity.fromAngleAndSpeed(45, 5));
        ball.addToGame(this);
        this.remainingBalls.increase(1);

        Ball ball2 = new Ball(new Point(200, 300), 5, Color.BLACK, this.environment, this);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(45, 5));
        ball2.addToGame(this);
        this.remainingBalls.increase(1);

        // Define block colors
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};

        // Create a BlockRemover
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);

        // Add blocks
        int blockWidth = 60;
        int blockHeight = 20;
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < (12 - i); j++) {
                Block block = new Block(new Point(795 - j * blockWidth, 70 + i * blockHeight),
                        blockHeight, blockWidth, colors[i]);
                block.addToGame(this);
                block.addHitListener(scoreListener);
                block.addHitListener(blockRemover);
                this.remainingBlocks.increase(1);
            }
        }

        // Add death-region block
        Block deathRegion = new Block(new Point(0, 605), 20, 800, Color.GRAY);
        deathRegion.addToGame(this);
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        deathRegion.addHitListener(ballRemover);
        ScoreIndicator scoreIndicator = new ScoreIndicator(new Rectangle(new Point(0, 0), 800, 20), scoreCounter);
        scoreIndicator.addToGame(this);


        // Add gray frame blocks
        Block topBlock = new Block(new Point(0, 20), 20, 800, Color.GRAY);
        topBlock.addToGame(this);

        Block leftBlock = new Block(new Point(0, 40), 605, 20, Color.GRAY);
        leftBlock.addToGame(this);

        Block rightBlock = new Block(new Point(780, 40), 605, 20, Color.GRAY);
        rightBlock.addToGame(this);
    }

    /**
     * Runs the game loop, which includes drawing all sprites on the surface and notifying them that time has passed.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (this.remainingBlocks.getValue() > 0 && this.remainingBalls.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (this.remainingBlocks.getValue() == 0)  {
            scoreCounter.increase(100); // Add 100 points for clearing the level
        }
        gui.close();
    }

}
