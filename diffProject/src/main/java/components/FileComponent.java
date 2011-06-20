package components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JEditorPane;

/**
 * FileComponent
 * @author Fernanda Floriano Silva
 */
public class FileComponent {

    /**
     * Constructor
     */
    public FileComponent() {
    }

    /**
     * Show Files
     * @param basedFile
     * @param comparedFile
     * @param baseFileEditorPane
     * @param comparedFileEditorPane
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void showFiles(File basedFile, File comparedFile, JEditorPane baseFileEditorPane, JEditorPane comparedFileEditorPane) throws FileNotFoundException, IOException {
        submitFile(baseFileEditorPane, basedFile);
        submitFile(comparedFileEditorPane, comparedFile);
    }

    /**
     * Submit FileComponent
     * @param editorPane
     * @param file
     * @return boolean
     * @throws MalformedURLException
     * @throws IOException 
     */
    private boolean submitFile(JEditorPane editorPane, File file) throws MalformedURLException, IOException {
        final URL transferURL = file.toURI().toURL();
        editorPane.setPage(transferURL);
        return true;
    }
}