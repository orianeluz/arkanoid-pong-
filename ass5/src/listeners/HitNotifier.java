//326367570 Orian Eluz
package listeners;

/**
 * The HitNotifier interface should be implemented by any class that can register and notify HitListeners of hit events.
 */
public interface HitNotifier {
    /**
     * Adds a HitListener to the list of listeners to be notified of hit events.
     *
     * @param hl the HitListener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a HitListener from the list of listeners to be notified of hit events.
     *
     * @param hl the HitListener to remove
     */
    void removeHitListener(HitListener hl);
}
