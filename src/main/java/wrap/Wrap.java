package wrap;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JEditorPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Keymap;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

public class Wrap {

    public static final String LINE_BREAK_ATTRIBUTE_NAME = "line_break_attribute";

    public void setWrap(JEditorPane leftPane, JEditorPane rightPane) {
        setWrapPane(leftPane);
        setWrapPane(rightPane);
    }

    private void setWrapPane(JEditorPane pane) {
        pane.setEditorKit(new WrapEditorKit());
        initKeyMap(pane);
    }

    protected void insertLineBreak(JEditorPane pane) {
        try {
            int offs = pane.getCaretPosition();
            Document doc = pane.getDocument();
            SimpleAttributeSet attrs;
            if (doc instanceof StyledDocument) {
                attrs = new SimpleAttributeSet(((StyledDocument) doc).getCharacterElement(offs).getAttributes());
            } else {
                attrs = new SimpleAttributeSet();
            }
            attrs.addAttribute(LINE_BREAK_ATTRIBUTE_NAME, Boolean.TRUE);
            doc.insertString(offs, "\r", attrs);
            pane.setCaretPosition(offs + 1);
        } catch (BadLocationException ex) {
        }
    }

    protected void initKeyMap(final JEditorPane pane) {
        Keymap kMap = pane.getKeymap();
        AbstractAction a = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                insertLineBreak(pane);
            }
        };
        kMap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.SHIFT_MASK), a);
    }
}
