package components;

import java.awt.Color;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class FileChooser extends JFileChooser {
    private static final long serialVersionUID = 1L;

    private JFileChooser fileChooser;

    public FileChooser() {
       this.fileChooser = new JFileChooser();
    }

    public void setFileChooser(JTextField tField) {
        getFileChooser().setDialogTitle("File Chooser");
        getFileChooser().setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int resultado = getFileChooser().showOpenDialog(this);
        if (resultado == JFileChooser.CANCEL_OPTION) {
            getFileChooser().setVisible(false);
        } else {
            tField.setText(getFileChooser().getSelectedFile().getAbsolutePath());
            tField.setForeground(Color.BLACK);
        }
    }

    /**
     * Translate File Chooser
     */
    //JFileChooser
    public static void translateFileChooser() {
        UIManager.put("FileChooser.lookInLabelMnemonic", "L");
        UIManager.put("FileChooser.lookInLabelText", "Look in");
        UIManager.put("FileChooser.saveInLabelMnemonic", "I");
        UIManager.put("FileChooser.saveInLabelText", "Save in");
        UIManager.put("FileChooser.upFolderToolTipText", "Up Folder");
        UIManager.put("FileChooser.upFolderAccessibleName", "Up Folder");
        UIManager.put("FileChooser.homeFolderToolTipText", "Home Folder");
        UIManager.put("FileChooser.homeFolderAccessibleName", "Home Folder");
        UIManager.put("FileChooser.newFolderToolTipText", "New Folder");
        UIManager.put("FileChooser.newFolderAccessibleName", "New Folder");
        UIManager.put("FileChooser.listViewButtonToolTipText", "List View");
        UIManager.put("FileChooser.listViewButtonAccessibleName", "List View");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Details");
        UIManager.put("FileChooser.detailsViewButtonAccessibleName", "Details");
        UIManager.put("FileChooser.fileNameLabelMnemonic", "N");
        UIManager.put("FileChooser.fileNameLabelText", "File Name");
        UIManager.put("FileChooser.filesOfTypeLabelMnemonic", "A");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Files of Type");
        UIManager.put("FileChooser.fileNameHeaderText", "Name");
        UIManager.put("FileChooser.fileSizeHeaderText", "File Size");
        UIManager.put("FileChooser.fileTypeHeaderText", "File Type");
        UIManager.put("FileChooser.fileDateHeaderText", "File Date");
        UIManager.put("FileChooser.fileAttrHeaderText", "File Attr");
        UIManager.put("FileChooser.cancelButtonText", "Cancel");
        UIManager.put("FileChooser.cancelButtonMnemonic", "C");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Cancel");
        UIManager.put("FileChooser.openButtonText", "Open");
        UIManager.put("FileChooser.openButtonMnemonic", "O");
        UIManager.put("FileChooser.openButtonToolTipText", "Open");
        UIManager.put("FileChooser.saveButtonText", "Save");
        UIManager.put("FileChooser.saveButtonToolTipText", "S");
        UIManager.put("FileChooser.saveButtonToolTipText", "Save");
        UIManager.put("FileChooser.updateButtonText", "Update");
        UIManager.put("FileChooser.updateButtonToolTipText", "U");
        UIManager.put("FileChooser.updateButtonToolTipText", "Update");
        UIManager.put("FileChooser.helpButtonText", "Help");
        UIManager.put("FileChooser.helpButtonToolTipText", "H");
        UIManager.put("FileChooser.helpButtonToolTipText", "Help");
        UIManager.put("FileChooser.acceptAllFileFilterText", "All files");
    }

    /**
     * @return the fileChooser
     */
    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    /**
     * @param fileChooser the fileChooser to set
     */
    public void setFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }
}
