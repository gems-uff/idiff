package algorithms;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class GrainBean {

    private int startPosition;
    private int length;

    public GrainBean(int startPosition, int length) {
        this.startPosition = startPosition;
        this.length = length;
    }

    public GrainBean() {
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
