package components;

import algorithms.GrainBean;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JTextPane;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class Listener {

    /**
     * Set Mouse Adapter
     * @param paneFrom
     * @param paneTo 
     */
    public static void setMouseAdapter(final JTextPane paneFrom, final JTextPane paneTo) {
        paneFrom.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                GrainHighLight.removeAllHighLight(paneFrom, paneTo);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                GrainHighLight.removeAllHighLight(paneFrom, paneTo);
            }
        });
    }

    /**
     * Set Mouse Motion
     * @param paneFrom
     * @param grainBeanFrom
     * @param grainBeanTo
     * @param paneTo
     */
    public static void setMouseMotion(final JTextPane paneFrom, final GrainBean grainBeanFrom, final GrainBean grainBeanTo, final JTextPane paneTo) {
        paneFrom.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point pt = new Point(e.getX(), e.getY());
                GrainHighLight.setHighLightPoint(pt, grainBeanFrom, paneFrom, grainBeanTo, paneTo);
            }
        });
    }

    public static void cleanMouseListener(final JTextPane paneFrom, final JTextPane paneTo) {
        paneFrom.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                GrainHighLight.removeAllHighLight(paneFrom, paneTo);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                GrainHighLight.removeAllHighLight(paneFrom, paneTo);
            }
        });
    }
    

}
