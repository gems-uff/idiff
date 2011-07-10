package algorithms;

import java.io.File;

/**
 * ILCSBean
 * @author Fernanda Floriano Silva
 */
public class ILCSBean {

    private File basedFile;
    private File comparedFile;
    private String granularity;
    private boolean trimLine;
    private boolean emptyLine;
    private boolean whiteSpace;

    /**
     * Constructor
     * @param basedFile
     * @param comparedFile 
     */
    public ILCSBean(File basedFile, File comparedFile) {
        this.basedFile = basedFile;
        this.comparedFile = comparedFile;
    }

    /**
     * Constructor
     */
    public ILCSBean() {
    }

    /**
     * Is Empty Line
     * @return boolean
     */
    public boolean isEmptyLine() {
        return emptyLine;
    }

    public String trimLines(String text) {
        return text.trim();
    }

    public String removeExcessWhitespace(String text) {
        //TODO implementar
        return "teste";
    }

    /**
     * Set Empty Line
     * @param emptyLine 
     */
    public void setEmptyLine(boolean emptyLine) {
        this.emptyLine = emptyLine;
    }

    /**
     * Get Granularity
     * @return String
     */
    public String getGranularity() {
        return granularity;
    }

    /**
     * Set Granularity
     * @param granularity 
     */
    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    /**
     * Is Trim Line
     * @return 
     */
    public boolean isTrimLine() {
        return trimLine;
    }

    /**
     * Set Trim Line
     * @param trimLine 
     */
    public void setTrimLine(boolean trimLine) {
        this.trimLine = trimLine;
    }

    /**
     * Is White Space
     * @return 
     */
    public boolean isWhiteSpace() {
        return whiteSpace;
    }

    /**
     * Set White Space
     * @param whiteSpace 
     */
    public void setWhiteSpace(boolean whiteSpace) {
        this.whiteSpace = whiteSpace;
    }

    /**
     * Get Based File
     * @return File
     */
    public File getBasedFile() {
        return basedFile;
    }

    /**
     * Set Based File
     * @param basedFile 
     */
    public void setBasedFile(File basedFile) {
        this.basedFile = basedFile;
    }

    /**
     * Get Compared File
     * @return File
     */
    public File getComparedFile() {
        return comparedFile;
    }

    /**
     * Set Compared File
     * @param comparedFile 
     */
    public void setComparedFile(File comparedFile) {
        this.comparedFile = comparedFile;
    }

    /**
     * Change Files Order
     */
    public void changeFilesOrder() {
        File baseFile = this.getBasedFile();
        this.setBasedFile(this.getComparedFile());
        this.setComparedFile(baseFile);
    }
}
