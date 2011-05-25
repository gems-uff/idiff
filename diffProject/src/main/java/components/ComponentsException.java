package components;

public class ComponentsException extends Exception {

    /**
     * Error Messages
     */
    public static final String MSG_INVALID_TYPE = "Different types of artifacts";

    /**
     * Constructor
     * @param msg
     */
    public ComponentsException(String msg) {
        super(msg);
    }

    /**
     * Constructor
     * @param ex
     * @param msg
     */
    public ComponentsException(Exception ex, String msg) {
        super(msg);
        this.initCause(ex);
    }
}
