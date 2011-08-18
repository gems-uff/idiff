package components;

import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class EventLine {

    public EventLine() {
    }

    public void addEvent(JLabel textLeft, JLabel textRight, JEditorPane leftPane, JScrollPane leftScroll, final JEditorPane rightPane, final JScrollPane rightScroll, int leftId, int rightId) {
        leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.PAGE_AXIS));
        addMouseEvent(textLeft, rightPane, rightScroll, leftPane, leftId, rightId);
        rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.PAGE_AXIS));
        addMouseEvent(textLeft, leftPane, leftScroll, rightPane, rightId, leftId);
        setEditorPane(textRight, rightPane);
        addAdjustmentEvent(leftScroll, rightScroll, leftId);
        addAdjustmentEvent(rightScroll, leftScroll, rightId);
    }

    private void addMouseEvent(JLabel line, final JEditorPane rightPane, final JScrollPane rightScroll, JEditorPane leftPane, final int id, final int id2) {
        line.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                JLabel label = (JLabel) me.getComponent();
                label.setBackground(ColorLine.getHighLight());
                label.setOpaque(true);

                JLabel reference = (JLabel) rightPane.getComponent(id - 1);//Aqui entra a referencia do outro lado
                reference.setBackground(ColorLine.getHighLight());
                label.setOpaque(true);

                rightScroll.getVerticalScrollBar().setValue(reference.getY());
            }

            @Override
            public void mouseExited(MouseEvent me) {
                JLabel label = (JLabel) me.getComponent();
                label.setBackground(ColorLine.getMovedColor());
                label.setOpaque(true);
                rightPane.getComponent(id - 1).setBackground(ColorLine.getMovedColor());
            }
        });

        leftPane.add(line);
    }

    private void addAdjustmentEvent(JScrollPane baseFileScrollPane, final JScrollPane comparedFileScrollPane, final int id) {
        baseFileScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int newPosition = id - 1;
                int start = comparedFileScrollPane.getVerticalScrollBar().getValue();
                int end = (int) (comparedFileScrollPane.getSize().getHeight() + start);
                if (newPosition > end || newPosition < start) {
                    comparedFileScrollPane.getVerticalScrollBar().setValue(newPosition);
                }
            }
        });
    }

    private void setEditorPane(JLabel line, final JEditorPane rightPane) {
        line.setBackground(ColorLine.getMovedColor());
        line.setOpaque(true);
        rightPane.add(line);
    }
}
