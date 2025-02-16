//326367570 Orian Eluz
package collidablesprites;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The type collidablesprites.Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * builds a new collidablesprites.Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * remove a sprite from the sprite collections.
     * @param s the sprite.
     */
    public void delSprite(Sprite s) {
        this.sprites.remove(s);
    }


    /**
     * Notify all time passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> list = new ArrayList<>(this.sprites);
        for (Sprite s : list) {
            s.timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }

}