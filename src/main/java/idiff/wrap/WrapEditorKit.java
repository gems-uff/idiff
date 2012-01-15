package idiff.wrap;

import idiff.wrap.WrapColumnFactory;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.ViewFactory;

/**
 * 
 * @author Fernanda Floriano Silva
 */
public class WrapEditorKit extends StyledEditorKit {

    private ViewFactory defaultFactory = new WrapColumnFactory();

    /**
     * 
     * @return 
     */
    @Override
    public ViewFactory getViewFactory() {
        return getDefaultFactory();
    }

    /**
     * 
     * @return 
     */
    @Override
    public MutableAttributeSet getInputAttributes() {
        MutableAttributeSet mAttrs = super.getInputAttributes();
        mAttrs.removeAttribute("line_break_attribute");
        return mAttrs;
    }

    /**
     * @return the defaultFactory
     */
    public ViewFactory getDefaultFactory() {
        return defaultFactory;
    }

    /**
     * @param defaultFactory the defaultFactory to set
     */
    public void setDefaultFactory(ViewFactory defaultFactory) {
        this.defaultFactory = defaultFactory;
    }
}
