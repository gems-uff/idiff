package idiff.resources;

import java.awt.Image;
import javax.swing.JDialog;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class Warning {

    /**
     * showWarning
     * @param Warning 
     */
    public static void show(JDialog Warning) {
        Laf.setlaf();
        Warning.setLocationRelativeTo(null);
        Warning.setIconImage(new Icon().getIcon());
        Warning.setVisible(true);
    }

    public static void dispose(JDialog warning) {
        if (warning.isVisible()) {
            warning.dispose();
        }
    }

    private static void setIconImage(Image icon) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
