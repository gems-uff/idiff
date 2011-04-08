package diff;

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
     *
     * @param counter
     * @param arrow
     */
    public LCSBean(int counter, Arrow arrow) {
        this.counter = counter;
        this.arrow = arrow;
    }

    /**
     *
     * @return
     */
    public int getCounter() {
        return counter;
    }

    /**
     *
     * @param counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     *
     * @return
     */
    public Arrow getArrow() {
        return arrow;
    }

    /**
     * 
     * @param arrow
     */
    public void setArrow(Arrow arrow) {
        this.arrow = arrow;
    }
}
