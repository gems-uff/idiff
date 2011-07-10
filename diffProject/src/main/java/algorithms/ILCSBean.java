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

    public boolean isEmptyLine() {
        return emptyLine;
    }

    public void setEmptyLine(boolean emptyLine) {
        this.emptyLine = emptyLine;
    }

    public String getGranularity() {
        return granularity;
    }

    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    public boolean isTrimLine() {
        return trimLine;
    }

    public void setTrimLine(boolean trimLine) {
        this.trimLine = trimLine;
    }

    public boolean isWhiteSpace() {
        return whiteSpace;
    }

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

    public void changeFilesOrder() {
        File baseFile = this.getBasedFile();
        this.setBasedFile(this.getComparedFile());
        this.setComparedFile(baseFile);
    }
}
