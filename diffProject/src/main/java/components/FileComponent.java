package components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JEditorPane;

/**
 *
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
        submitFile(baseFileEditorPane, basedFile, "#FFFFFF");
        submitFile(comparedFileEditorPane, comparedFile, "#FFFFFF");
    }

    /**
     * Submit FileComponent
     * @param editorPane
     * @param file
     * @param colorName
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void submitFile(JEditorPane editorPane, File file, String colorName) throws FileNotFoundException, IOException {
        String line;
        StringBuilder sb;
        BufferedReader reader;

        editorPane.setText(null);
        reader = new BufferedReader(new FileReader(file));
        sb = new StringBuilder();

        editorPane.setContentType("text/html");
        editorPane.setEditable(false);

        for (int i = 0; (line = reader.readLine()) != null; i++) {
            sb.append("<span style='background-color:").append(colorName).append("'>").append(line).append("</span><br>");
        }

        editorPane.setText(sb.toString());
        reader.close();
        sb.delete(0, sb.length());
        reader.close();
    }
}
