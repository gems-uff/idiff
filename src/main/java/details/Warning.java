package details;

import javax.swing.JDialog;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class Warning {

    /**
     * showWarning
     */
    public static void show(JDialog Warning) {
        Laf.setlaf();
        Warning.setLocationRelativeTo(null);
        Warning.setIconImage(Icon.getIcon());
        Warning.setVisible(true);
    }

    public static void dispose(JDialog warning) {
        if (warning.isVisible()) {
            warning.dispose();
        }
    }
}
