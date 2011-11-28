package details;

import algorithms.GrainBean;
import components.GrainHighLight;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
     * @param leftScrollPane
     * @param rightScrollPane  
     */
    public static void setMouseAdapter(final JTextPane paneFrom, final JTextPane paneTo, final JScrollPane leftScrollPane, final JScrollPane rightScrollPane) {
        paneFrom.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                GrainHighLight.removeAllHighLight(paneFrom, paneTo);
                Scroll.adjustmentScroll(leftScrollPane, rightScrollPane);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                GrainHighLight.removeAllHighLight(paneFrom, paneTo);
                Scroll.adjustmentScroll(leftScrollPane, rightScrollPane);

            }
        });
    }

    /**
     * 
     * @param pane
     * @param msgRefactory 
     */
    public static void setMouseAdapter(final JTextPane pane, final JTextField msgRefactory) {
        pane.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                GrainHighLight.removeHighLight(pane);
                cleanTextFiel(msgRefactory);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                GrainHighLight.removeHighLight(pane);
                cleanTextFiel(msgRefactory);
            }
        });
    }

    /**
     * 
     * @param msgRefactory 
     */
    private static void cleanTextFiel(JTextField msgRefactory) {
        msgRefactory.setEnabled(false);
        msgRefactory.setText("");
    }

    /**
     * Set Mouse Motion
     * @param paneFrom
     * @param grainBeanFrom
     * @param grainBeanTo
     * @param paneTo
     * @param leftScrollPane
     * @param rightScrollPane  
     */
    public static void setMouseMotion(final JTextPane paneFrom, final GrainBean grainBeanFrom, final GrainBean grainBeanTo, final JTextPane paneTo, final JScrollPane leftScrollPane, final JScrollPane rightScrollPane) {
        paneFrom.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                Scroll.adjustmentScroll(leftScrollPane, rightScrollPane);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Scroll.removeAdjustmentScroll(leftScrollPane, rightScrollPane);
                Point pt = new Point(e.getX(), e.getY());
                GrainHighLight.setHighLightPoint(pt, grainBeanFrom, paneFrom, grainBeanTo, paneTo);
            }
        });
    }

    /**
     * 
     * @param pane
     * @param grainBean
     * @param textField
     * @param color
     * @param msg 
     */
    public static void setMouseMotion(final JTextPane pane, final GrainBean grainBean, final JTextField textField, final Color color, final String msg) {
        pane.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                GrainHighLight.removeHighLight(pane);
                cleanTextFiel(textField);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point pt = new Point(e.getX(), e.getY());
                GrainHighLight.setHighLightPoint(pt, grainBean, pane);

                if (verifyStart(pt) && verifyEnd(pt)) {
                    textField.setBackground(color);
                    textField.setText(msg);
                    textField.setEnabled(true);
                }
            }

            private boolean verifyEnd(Point pt) {
                return pane.viewToModel(pt) <= grainBean.getStartPosition() + grainBean.getLength();
            }

            private boolean verifyStart(Point pt) {
                return grainBean.getStartPosition() <= pane.viewToModel(pt);
            }
        });
    }

    /**
     * 
     * @param paneFrom
     * @param paneTo
     * @param leftScrollPane
     * @param rightScrollPane 
     */
    public static void cleanMouseListener(final JTextPane paneFrom, final JTextPane paneTo, final JScrollPane leftScrollPane, final JScrollPane rightScrollPane) {
        paneFrom.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                GrainHighLight.removeAllHighLight(paneFrom, paneTo);
                Scroll.adjustmentScroll(leftScrollPane, rightScrollPane);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Scroll.removeAdjustmentScroll(leftScrollPane, rightScrollPane);
                GrainHighLight.removeAllHighLight(paneFrom, paneTo);
            }
        });
    }

    /**
     * 
     * @param pane
     * @param textField 
     */
    public static void cleanMouseListener(final JTextPane pane, final JTextField textField) {
        pane.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                GrainHighLight.removeHighLight(pane);
                cleanTextFiel(textField);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                GrainHighLight.removeHighLight(pane);
                cleanTextFiel(textField);
            }
        });
    }
}
