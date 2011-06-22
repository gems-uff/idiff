package components;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.swing.JEditorPane;
import javax.swing.JTree;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class EditorDropTarget implements DropTargetListener, PropertyChangeListener {

    /**
     * Constructor
     * @param pane
     * @param tree 
     */
    EditorDropTarget(JEditorPane pane, JTree tree) {
        this.pane = pane;
        this.tree = tree;
        pane.addPropertyChangeListener(this);
        dropTarget = new DropTarget(pane, DnDConstants.ACTION_COPY_OR_MOVE, this, pane.isEnabled(), null);
    }

    /**
     * Drag Enter
     * @param dtde 
     */
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        DnDUtils.debugPrintln("dragEnter, drop action = "
                + DnDUtils.showActions(dtde.getDropAction()));
        checkTransferType(dtde);
        boolean acceptedDrag = acceptOrRejectDrag(dtde);
        dragUnderFeedback(dtde, acceptedDrag);
    }

    /**
     * drag Exit
     * @param dte 
     */
    @Override
    public void dragExit(DropTargetEvent dte) {
        DnDUtils.debugPrintln("DropTarget dragExit");
        dragUnderFeedback(null, false);
    }

    /**
     * drag Over
     * @param dtde 
     */
    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        DnDUtils.debugPrintln("DropTarget dragOver, drop action = "
                + DnDUtils.showActions(dtde.getDropAction()));
        boolean acceptedDrag = acceptOrRejectDrag(dtde);
        dragUnderFeedback(dtde, acceptedDrag);
    }

    /**
     * Drop Action Changed
     * @param dtde 
     */
    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        DnDUtils.debugPrintln("DropTarget dropActionChanged, drop action = "
                + DnDUtils.showActions(dtde.getDropAction()));
        boolean acceptedDrag = acceptOrRejectDrag(dtde);
        dragUnderFeedback(dtde, acceptedDrag);
    }

    /**
     * Drop
     * @param dtde 
     */
    @Override
    public void drop(DropTargetDropEvent dtde) {
        DnDUtils.debugPrintln("DropTarget drop, drop action = "
                + DnDUtils.showActions(dtde.getDropAction()));
        if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
            dtde.acceptDrop(dtde.getDropAction());
            Transferable transferable = dtde.getTransferable();
            try {
                boolean result = false;
                if (draggingFile) {
                    result = dropFile(transferable);
                } else {
                    result = dropContent(transferable, dtde);
                }

                dtde.dropComplete(result);
                DnDUtils.debugPrintln("Drop completed, success: " + result);
            } catch (Exception e) {
                DnDUtils.debugPrintln("Exception while handling drop " + e);
                dtde.rejectDrop();
            }
        } else {
            DnDUtils.debugPrintln("Drop target rejected drop");
            dtde.dropComplete(false);
        }
    }

    /**
     * Property Change
     * @param evt 
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if (propertyName.equals("enabled")) {
            dropTarget.setActive(pane.isEnabled());
        }
    }

    /**
     * Accept Or Reject Drag
     * @param dtde
     * @return boolean
     */
    protected boolean acceptOrRejectDrag(DropTargetDragEvent dtde) {
        int dropAction = dtde.getDropAction();
        int sourceActions = dtde.getSourceActions();
        boolean acceptedDrag = false;
        DnDUtils.debugPrintln("\tSource actions are "
                + DnDUtils.showActions(sourceActions) + ", drop action is "
                + DnDUtils.showActions(dropAction));
        if (!acceptableType
                || (sourceActions & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
            DnDUtils.debugPrintln("Drop target rejecting drag");
            dtde.rejectDrag();
        } else if (!draggingFile && !pane.isEditable()) {
            DnDUtils.debugPrintln("Drop target rejecting drag");
            dtde.rejectDrag();
        } else if ((dropAction & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
            DnDUtils.debugPrintln("Drop target offering COPY");
            dtde.acceptDrag(DnDConstants.ACTION_COPY);
            acceptedDrag = true;
        } else {
            DnDUtils.debugPrintln("Drop target accepting drag");
            dtde.acceptDrag(dropAction);
            acceptedDrag = true;
        }

        return acceptedDrag;
    }

    /**
     * Drag Under Feedback
     * @param dtde
     * @param acceptedDrag 
     */
    protected void dragUnderFeedback(DropTargetDragEvent dtde,
            boolean acceptedDrag) {
        if (dtde != null && acceptedDrag) {
            Point location = dtde.getLocation();
            pane.getCaret().setVisible(true);
            pane.setCaretPosition(pane.viewToModel(location));
        } else {
            pane.getCaret().setVisible(false);
        }
    }

    /**
     * Check Transfer Type
     * @param dtde 
     */
    protected void checkTransferType(DropTargetDragEvent dtde) {
        acceptableType = false;
        draggingFile = false;

        if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            acceptableType = true;
            draggingFile = true;
        }
        acceptableType = true;

        DnDUtils.debugPrintln("File type acceptable - " + acceptableType);
        DnDUtils.debugPrintln("Dragging a file - " + draggingFile);
    }

    /**
     * Drop File
     * @param transferable
     * @return boolean
     * @throws IOException
     * @throws UnsupportedFlavorException
     * @throws MalformedURLException 
     */
    protected boolean dropFile(Transferable transferable) throws IOException,
            UnsupportedFlavorException, MalformedURLException {
        List fileList = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
        File transferFile = (File) fileList.get(0);
        final URL transferURL = transferFile.toURI().toURL();
        DnDUtils.debugPrintln("File URL is " + transferURL);
        pane.setPage(transferURL);
        return true;
    }

    /**
     * Drop Content
     * @param transferable
     * @param dtde
     * @return boolean
     */
    protected boolean dropContent(Transferable transferable,
            DropTargetDropEvent dtde) {
        if (!pane.isEditable()) {
            return false;
        }
        try {
            DataFlavor[] flavors = dtde.getCurrentDataFlavors();
            DataFlavor selectedFlavor = null;
            for (int i = 0; i < flavors.length; i++) {
                DataFlavor flavor = flavors[i];
                DnDUtils.debugPrintln("Drop MIME type " + flavor.getMimeType()
                        + " is available");
                if (flavor.equals(DataFlavor.stringFlavor)) {
                    selectedFlavor = flavor;
                    break;
                }
            }

            if (selectedFlavor == null) {
                return false;
            }

            DnDUtils.debugPrintln("Selected flavor is "
                    + selectedFlavor.getHumanPresentableName());
            // Get the transferable and then obtain the data
            Object data = transferable.getTransferData(selectedFlavor);

            DnDUtils.debugPrintln("Transfer data type is "
                    + data.getClass().getName());

            String insertData = null;
            if (data instanceof InputStream) {
                String charSet = selectedFlavor.getParameter("charset");
                InputStream is = (InputStream) data;
                byte[] bytes = new byte[is.available()];
                is.read(bytes);
                try {
                    insertData = new String(bytes, charSet);
                } catch (UnsupportedEncodingException e) {
                    insertData = new String(bytes);
                }
            } else if (data instanceof String) {
                insertData = (String) data;
            }

            if (insertData != null) {
                int selectionStart = pane.getCaretPosition();
                pane.replaceSelection(insertData);
                pane.select(selectionStart, selectionStart
                        + insertData.length());
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    protected JEditorPane pane;
    protected JTree tree;
    protected DropTarget dropTarget;
    protected boolean acceptableType; 
    protected boolean draggingFile; 
}
