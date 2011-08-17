package algorithms;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class GrainBean {

    private int linePosition;
    private int startPosition;
    private int length;

    public GrainBean(int linePosition, int startPosition, int length) {
        this.linePosition = linePosition;
        this.startPosition = startPosition;
        this.length = length;
    }

    public GrainBean() {
    }

    public int getLinePosition() {
        return linePosition;
    }

    public void setLinePosition(int linePosition) {
        this.linePosition = linePosition;
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
