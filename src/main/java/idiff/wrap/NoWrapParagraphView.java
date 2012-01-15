package idiff.wrap;

import javax.swing.text.Element;
import javax.swing.text.ParagraphView;

/**
 * 
 * @author Fernanda Floriano Silva
 */
public class NoWrapParagraphView extends ParagraphView {

    /**
     * 
     * @param elem 
     */
    public NoWrapParagraphView(Element elem) {
        super(elem);
    }

    /**
     * 
     * @param width
     * @param height 
     */
    @Override
    public void layout(int width, int height) {
        super.layout(Short.MAX_VALUE, height);
    }

    /**
     * 
     * @param axis
     * @return 
     */
    @Override
    public float getMinimumSpan(int axis) {
        return super.getPreferredSpan(axis);
    }
}