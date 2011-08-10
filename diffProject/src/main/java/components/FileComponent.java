package components;

import algorithms.Grain;
import algorithms.IResultDiff;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

/**
 * FileComponent
 * @author Fernanda Floriano Silva
 */
public class FileComponent {

    private HtmlComponent htmlComponent = new HtmlComponent();

    /**
     * Constructor
     */
    public FileComponent() {
    }

    /**
     * Show Files
     * @param baseFile
     * @param comparedFile
     * @param baseEditorPane
     * @param comparedEditorPane
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void showFiles(File baseFile, File comparedFile, JEditorPane baseEditorPane, JEditorPane comparedEditorPane) throws FileNotFoundException, IOException {
        submitFile(baseFile, baseEditorPane);
        submitFile(comparedFile, comparedEditorPane);
    }

    private void submitFile(File file, JEditorPane editorPane) throws MalformedURLException, IOException {
        final URL transferURL = file.toURI().toURL();
        editorPane.setPage(transferURL);
    }

    /* private void setHtmlComponent(HtmlComponent htmlComponent, StringBuilder sb) {
    htmlComponent.setTextDecoration(sb);
    }
    
    private void setLineStatus(BufferedReader reader, HtmlComponent htmlComponent, StringBuilder sb) throws IOException {
    String line;
    for (int lineCount = 1; ((line = reader.readLine()) != null) ; lineCount++) {
    verifyLineStatus(htmlComponent, sb, line,lineCount);
    
    }
    
    //        while ((line = reader.readLine()) != null) {
    //          verifyLineStatus(htmlComponent, sb, line);
    //    }
    sb.append("</body>");
    }
    
    public void ready(File file, JEditorPane pane, HtmlComponent htmlComponent) throws FileNotFoundException, IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuilder sb = new StringBuilder();
    pane.setContentType("text/html");
    pane.setEditable(false);
    setHtmlComponent(htmlComponent, sb);
    setLineStatus(reader, htmlComponent, sb);
    pane.setText(sb.toString());
    reader.close();
    }
    
    private void verifyLineStatus(HtmlComponent htmlComponent, StringBuilder sb, String line, int idLine) {
    htmlComponent.setAddedLine(sb, line,idLine);
    }
    
    public void repaintFiles(File file, JEditorPane fileEditorPane, JScrollPane fileScrollPane, List<Grain> grainsFrom, List<Grain> grainsTo, List<Grain> differences) throws FileNotFoundException, IOException {
    ready(file, fileEditorPane, htmlComponent);
    }
    
    private void checkMoves() {
    throw new UnsupportedOperationException("Not yet implemented");
    }
    private void checkDifferences() {
    throw new UnsupportedOperationException("Not yet implemented");
    }
     */
    void repaint(File left, File right, IResultDiff result, JEditorPane fomEditorPane, JScrollPane fromScrollPane, JEditorPane toEditorPane, JScrollPane toScrollPane) throws FileNotFoundException, IOException {
        String lineLeft;
        String lineRight;

        StringBuilder sbLeft = new StringBuilder();
        BufferedReader readerLeft = new BufferedReader(new FileReader(left));

        StringBuilder sbRight = new StringBuilder();
        BufferedReader readerRight = new BufferedReader(new FileReader(right));

        while (((lineLeft = readerLeft.readLine()) != null) && ((lineRight = readerRight.readLine()) != null)) {
            System.out.println(lineLeft);
            System.out.println(lineRight);
            //TODO Conitnuar aqui - depois aplicar refatoração
        }


        List<Grain> differences = result.getDifferences();
        List<Grain> grainsFrom = result.getGrainsFrom();
        List<Grain> grainsTo = result.getGrainsTo();
        int x = 1;
    }
}