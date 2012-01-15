package ilcs.algorithms;

/**
 * LCSBean
 * @author Fernanda Floriano Silva
 */
public class LCSBean {

    private int counter = 0;
    private Arrow arrow;

    /**
     * Arrows to be used in lcs algorithm 
     */
    public enum Arrow {

        LEFT,
        UP,
        DIAGONAL
    }

    /**
     * Constructor
     * @param counter
     * @param arrow
     */
    public LCSBean(int counter, Arrow arrow) {
        this.counter = counter;
        this.arrow = arrow;
    }

    /**
     * Get Counter
     * @return int
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * Set Counter
     * @param counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * GetArrow
     * @return Arrow
     */
    public Arrow getArrow() {
        return this.arrow;
    }

    /**
     * Set Arrow
     * @param arrow
     */
    public void setArrow(Arrow arrow) {
        this.arrow = arrow;
    }
}
