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
    public static Image getIcon() {
        return new ImageIcon("src/main/resources/gui/icons/iDiff.png").getImage();
    }
}
