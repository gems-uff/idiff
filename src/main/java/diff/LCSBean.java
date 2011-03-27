package diff;

/**
 * LCSBean
 * @author Fernanda Floriano Silva
 */
public class LCSBean {

    /**
     * Arrows to be used in lcs algorithm 
     */
    public enum Arrow {

        LEFT,
        UP,
        DIAGONAL
    }
    private int counter = 0;
    private Arrow arrow;

    public LCSBean() {
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
     * Get counter
     * @return
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Set Counter
     * @param counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * Get Arrow
     * @return
     */
    public Arrow getArrow() {
        return arrow;
    }

    /**
     * Set Arrow
     * @param arrow
     */
    public void setArrow(Arrow arrow) {
        this.arrow = arrow;
    }
}
