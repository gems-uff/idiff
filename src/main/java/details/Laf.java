package details;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class Laf {

    /**
     * Set Look and Feel
     */
    public static void setlaf() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            
        } catch (Exception ex) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(Laf.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (InstantiationException ex1) {
                Logger.getLogger(Laf.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (IllegalAccessException ex1) {
                Logger.getLogger(Laf.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (UnsupportedLookAndFeelException ex1) {
                Logger.getLogger(Laf.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
}
