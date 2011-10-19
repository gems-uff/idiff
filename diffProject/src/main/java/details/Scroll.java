package details;

import java.awt.Point;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JScrollPane;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class Scroll {

    /**
     * Adjustment Scroll
     */
    public static void adjustmentScroll(JScrollPane leftScrollPane, JScrollPane rightScrollPane) {
        adjustmentHorizontalScroll(leftScrollPane, rightScrollPane);
        adjustmentVerticalScroll(leftScrollPane, rightScrollPane);
        adjustmentHorizontalScroll(rightScrollPane, leftScrollPane);
        adjustmentVerticalScroll(rightScrollPane, leftScrollPane);
    }

    /**
     * Adjustment Horizontal Scroll
     * @param scrollTo
     * @param scrollFrom 
     */
    private static void adjustmentHorizontalScroll(final JScrollPane scrollTo, final JScrollPane scrollFrom) {
        scrollTo.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent arg0) {
                Point point = scrollTo.getViewport().getViewPosition();
                scrollFrom.getViewport().setViewPosition(point);
            }
        });
    }

    /**
     * Adjustment Vertical Scroll
     * @param scrollTo
     * @param scrollFrom 
     */
    private static void adjustmentVerticalScroll(final JScrollPane scrollTo, final JScrollPane scrollFrom) {
        scrollTo.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent arg0) {
                Point point = scrollTo.getViewport().getViewPosition();
                scrollFrom.getViewport().setViewPosition(point);
            }
        });
    }
}
