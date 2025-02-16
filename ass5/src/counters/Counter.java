package counters;

/**
 * a counter is responsible of keeping truck of numbers according to certain events.
 */
public class Counter {
    private int num;

    /**
     * constructor that puts the value 0 to the private num.
     */
    public Counter() {
        this.num = 0;
    }

    /**
     * add number to current count.
     * @param number number to be added to the count.
     */
    public void increase(int number) {
        this.num += number;
    }

    /**
     * subtract number from current count.
     * @param number number to be subtract from the count.
     */
    public void decrease(int number) {
        this.num -= number;
    }

    /**
     * getter to the count.
     * @return the count.
     */
    public int getValue() {
        return this.num;
    }
}