package algorithms;

/**
 * GrainBean
 * @author Fernanda Floriano Silva
 */
public class GrainBean {

    private int startPosition;
    private int length;

    /**
     * 
     * @param startPosition
     * @param length 
     */
    public GrainBean(int startPosition, int length) {
        this.startPosition = startPosition;
        this.length = length;
    }

    /**
     * Constructor
     */
    public GrainBean() {
    }

    /**
     * Get character start position 
     * @return 
     */
    public int getStartPosition() {
        return startPosition;
    }

    /**
     * Set character start position 
     * @param startPosition 
     */
    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    /**
     * Get grain length
     * @return 
     */
    public int getLength() {
        return length;
    }

    /**
     * Set grain length
     * @param length 
     */
    public void setLength(int length) {
        this.length = length;
    }
}
