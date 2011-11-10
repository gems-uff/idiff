package wrap;

import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.GlyphView;
import javax.swing.text.LabelView;
import javax.swing.text.View;

public class WrapLabelView extends LabelView {


    public WrapLabelView(Element elem) {
        super(elem);
    }

    @Override
    public int getBreakWeight(int axis, float pos, float len) {
        if (axis == View.X_AXIS) {
            checkPainter();
            int p0 = getStartOffset();
            int p1 = getGlyphPainter().getBoundedPosition(this, p0, pos, len);
            if (p1 == p0) {
                return View.BadBreakWeight;
            }
            try {
                if (getDocument().getText(p0, p1 - p0).indexOf("\r") >= 0) {
                    return View.ForcedBreakWeight;
                }
            } catch (BadLocationException ex) {
            }
        }
        return super.getBreakWeight(axis, pos, len);
    }

    @Override
    public View breakView(int axis, int p0, float pos, float len) {
        if (axis == View.X_AXIS) {
            checkPainter();
            int p1 = getGlyphPainter().getBoundedPosition(this, p0, pos, len);
            try {
                int index = getDocument().getText(p0, p1 - p0).indexOf("\r");
                if (index >= 0) {
                    GlyphView v = (GlyphView) createFragment(p0, p0 + index + 1);
                    return v;
                }
            } catch (BadLocationException ex) {
            }
        }
        return super.breakView(axis, p0, pos, len);
    }
}
