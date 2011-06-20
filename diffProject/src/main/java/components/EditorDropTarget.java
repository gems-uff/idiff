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
    
    EditorDropTarget(JEditorPane pane, JTree tree) {
        this.pane = pane;
        this.tree = tree;

        // Listen for changes in the enabled property
        pane.addPropertyChangeListener(this);

        // Save the JEditorPane's background color
        //backgroundColor = pane.getBackground();

        // Create the EditorDropTarget and register
        // it with the JEditorPane.
        dropTarget = new DropTarget(pane, DnDConstants.ACTION_COPY_OR_MOVE, this, pane.isEnabled(), null);
    }

    // Implementation of the DropTargetListener interface
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        DnDUtils.debugPrintln("dragEnter, drop action = "
                + DnDUtils.showActions(dtde.getDropAction()));

        // Get the type of object being transferred and determine
        // whether it is appropriate.
        checkTransferType(dtde);

        // Accept or reject the drag.
        boolean acceptedDrag = acceptOrRejectDrag(dtde);

        // Do drag-under feedback
        dragUnderFeedback(dtde, acceptedDrag);
    }
    
    @Override
    public void dragExit(DropTargetEvent dte) {
        DnDUtils.debugPrintln("DropTarget dragExit");

        // Do drag-under feedback
        dragUnderFeedback(null, false);
    }
    
    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        DnDUtils.debugPrintln("DropTarget dragOver, drop action = "
                + DnDUtils.showActions(dtde.getDropAction()));

        // Accept or reject the drag
        boolean acceptedDrag = acceptOrRejectDrag(dtde);

        // Do drag-under feedback
        dragUnderFeedback(dtde, acceptedDrag);
    }
    
    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        DnDUtils.debugPrintln("DropTarget dropActionChanged, drop action = "
                + DnDUtils.showActions(dtde.getDropAction()));

        // Accept or reject the drag
        boolean acceptedDrag = acceptOrRejectDrag(dtde);

        // Do drag-under feedback
        dragUnderFeedback(dtde, acceptedDrag);
    }
    
    @Override
    public void drop(DropTargetDropEvent dtde) {
        DnDUtils.debugPrintln("DropTarget drop, drop action = "
                + DnDUtils.showActions(dtde.getDropAction()));

        // Check the drop action
        if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
            // Accept the drop and get the transfer data
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

    // PropertyChangeListener interface
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if (propertyName.equals("enabled")) {
            // Enable the drop target if the JEditorPane is enabled
            // and vice versa.
            dropTarget.setActive(pane.isEnabled());
        }// else if (!changingBackground && propertyName.equals("background")) {
        //  backgroundColor = pane.getBackground();
        //}
    }

    // Internal methods start here
    protected boolean acceptOrRejectDrag(DropTargetDragEvent dtde) {
        int dropAction = dtde.getDropAction();
        int sourceActions = dtde.getSourceActions();
        boolean acceptedDrag = false;
        
        DnDUtils.debugPrintln("\tSource actions are "
                + DnDUtils.showActions(sourceActions) + ", drop action is "
                + DnDUtils.showActions(dropAction));

        // Reject if the object being transferred
        // or the operations available are not acceptable.
        if (!acceptableType
                || (sourceActions & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
            DnDUtils.debugPrintln("Drop target rejecting drag");
            dtde.rejectDrag();
        } else if (!draggingFile && !pane.isEditable()) {
            // Can't drag text to a read-only JEditorPane
            DnDUtils.debugPrintln("Drop target rejecting drag");
            dtde.rejectDrag();
        } else if ((dropAction & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
            // Not offering copy or move - suggest a copy
            //TODO VER AQUI
            DnDUtils.debugPrintln("Drop target offering COPY");
            dtde.acceptDrag(DnDConstants.ACTION_COPY);
            acceptedDrag = true;
        } else {
            // Offering an acceptable operation: accept
            DnDUtils.debugPrintln("Drop target accepting drag");
            dtde.acceptDrag(dropAction);
            acceptedDrag = true;
        }
        
        return acceptedDrag;
    }
    
    protected void dragUnderFeedback(DropTargetDragEvent dtde,
            boolean acceptedDrag) {
        // if (draggingFile) {
        // When dragging a file, change the background color
        //   Color newColor = (dtde != null && acceptedDrag ? feedbackColor
        //         : backgroundColor);
        // if (newColor.equals(pane.getBackground()) == false) {
        // changingBackground = true;
        //   pane.setBackground(newColor);
        //  changingBackground = false;
        // pane.repaint();
        //}
        //} else {
        if (dtde != null && acceptedDrag) {
            // Dragging text - move the insertion cursor
            Point location = dtde.getLocation();
            pane.getCaret().setVisible(true);
            pane.setCaretPosition(pane.viewToModel(location));
        } else {
            pane.getCaret().setVisible(false);
        }
    }
    
    protected void checkTransferType(DropTargetDragEvent dtde) {
        // Accept a list of files, or data content that
        // amounts to plain text or a Unicode text string
        acceptableType = false;
        draggingFile = false;
        if ((dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) || (dtde.isDataFlavorSupported(DataFlavor.stringFlavor))) {
            acceptableType = true;
            draggingFile = true;
        }
//        }// else if (dtde.isDataFlavorSupported(DataFlavor.plainTextFlavor)
        //    || dtde.isDataFlavorSupported(DataFlavor.stringFlavor)) {
        //acceptableType = true;
        //      else if (dtde.isDataFlavorSupported(DataFlavor.stringFlavor)) {
        //         acceptableType = true;
        //   }
        DnDUtils.debugPrintln("File type acceptable - " + acceptableType);
        DnDUtils.debugPrintln("Dragging a file - " + draggingFile);
    }

    // This method handles a drop for a list of files
    protected boolean dropFile(Transferable transferable) throws IOException,
            UnsupportedFlavorException, MalformedURLException {
        List fileList = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
        File transferFile = (File) fileList.get(0);
        //   final URL transferURL = transferFile.toURL();
        final URL transferURL = transferFile.toURI().toURL();
        
        DnDUtils.debugPrintln("File URL is " + transferURL);
        
        pane.setPage(transferURL);
        
        return true;
    }

    // This method handles a drop with data content
    protected boolean dropContent(Transferable transferable,
            DropTargetDropEvent dtde) {
        if (!pane.isEditable()) {
            // Can't drop content on a read-only text control
            return false;
        }
        try {
            // Check for a match with the current content type
            DataFlavor[] flavors = dtde.getCurrentDataFlavors();
            
            DataFlavor selectedFlavor = null;

            // Look for either plain text or a String.
            for (int i = 0; i < flavors.length; i++) {
                DataFlavor flavor = flavors[i];
                DnDUtils.debugPrintln("Drop MIME type " + flavor.getMimeType()
                        + " is available");
                //if (flavor.equals(DataFlavor.plainTextFlavor)
                //      || flavor.equals(DataFlavor.stringFlavor)) {
//                if (flavor.equals(flavor.equals(DataFlavor.stringFlavor))) {
                if (flavor.equals(DataFlavor.stringFlavor)) {
                    selectedFlavor = flavor;
                    break;
                }
            }
            
            if (selectedFlavor == null) {
                // No compatible flavor - should never happen
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
                // Plain text flavor
                String charSet = selectedFlavor.getParameter("charset");
                InputStream is = (InputStream) data;
                byte[] bytes = new byte[is.available()];
                is.read(bytes);
                try {
                    insertData = new String(bytes, charSet);
                } catch (UnsupportedEncodingException e) {
                    // Use the platform default encoding
                    insertData = new String(bytes);
                }
            } else if (data instanceof String) {
                // String flavor
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
    protected boolean acceptableType; // Indicates whether data is acceptable
    protected boolean draggingFile; // True if dragging an entire file
    //protected Color backgroundColor; // Original background color of JEditorPane
    //protected boolean changingBackground;
    //   protected static final Color feedbackColor = Color.gray;
}
