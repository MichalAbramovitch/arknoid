
/**
 * @author michal Abramovitch
 */
/**
 * Counter Class.
 * count stuff
 */
public class Counter {
    private int number;

    /**
     * Counter constructor.
     */
    public Counter() {
        this.number = 0;
    }

    /**
     * Counter constructor.
     * @param number the number allrady count.
     */
    public Counter(int number) {
        this.number = number;
    }

    /**
     * increase method.
     * add number to current count.
     * @param num - number
     */
    public void increase(int num) {
        this.number += num;
    }

    /**
     * decrease method.
     * subtract number from current count.
     * @param num number
     */
    public void decrease(int num) {
        this.number -= num;

    }

    /**
     * getValue method.
     * get current count.
     * @return - count number.
     */
    public int getValue() {
        return this.number;
    }

}