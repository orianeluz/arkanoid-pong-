//326367570 Orian Eluz
package animation;

/**
 * The animation.Ass3Game class is the entry for the game application.
 * It initializes and runs the game.
 */
public class Ass5Game {
    /**
     * The main method serves as the entry for the game application.
     * It creates a new Game object, initializes it, and then runs the game.
     *
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
