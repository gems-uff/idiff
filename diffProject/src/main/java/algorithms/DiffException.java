package algorithms;

/**
 * DiffException
 * Under Construction
 * @author Fernanda Floriano Silva
 */
public class DiffException extends Exception {

    /**
     * Error Messages
     */
    public static final String MSG_INVALID_SITUATION = "Unsupported situation";
    public static final String MSG_INVALID_START_LINE_GRANULARITY = "Error trying to start Line Granularity";
    public static final String MSG_INVALID_START_WORD_GRANULARITY = "Error trying to start Word Granularity";
    public static final String MSG_INVALID_START_CHARACTER_GRANULARITY = "Error trying to start Character Granularity";
    public static final String MSG_INVALID_GRAIN = "Unsupported Granularity";
    public static final String MSG_INVALID_REDUCTION = "Reduction of granularity not allowed";

    /**
     * Constructor
     * @param msg
     */
    public DiffException(String msg) {
        super(msg);
    }

    /**
     * Constructor
     * @param ex
     * @param msg
     */
    public DiffException(Exception ex, String msg) {
        super(msg);
        this.initCause(ex);
    }
}
