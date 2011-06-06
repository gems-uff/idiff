package components;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
        setlaf();
    }

    /**
     * Constructor
     * @param ex
     * @param msg
     */
    public ComponentsException(Exception ex, String msg) {
        super(msg);
        this.initCause(ex);
        setlaf();
    }

    /**
     * Set Look and Feel
     */
    private void setlaf() {
        try {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception ex) {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (UnsupportedLookAndFeelException ex1) {
                    Logger.getLogger(ComponentsException.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
    }
}