package idiff.resources;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class Icon {

    /**
     * 
     * @return 
     */
    public Image getIcon() {
        return new ImageIcon(getClass().getResource("/gui/icons/iDiff.png")).getImage();
    }
}
