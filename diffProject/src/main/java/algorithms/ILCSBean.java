package algorithms;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * Verify if is Empty Line
     * @return 
     */
    public boolean isEmptyLine() {
        return emptyLine;
    }

    /** 
     * Verify if is White Space
     * @return boolean
     */
    public boolean isWhiteSpace() {
        return whiteSpace;
    }

    /**
     * Set Empty Line
     * @param emptyLine 
     */
    public void setEmptyLine(boolean emptyLine) {
        this.emptyLine = emptyLine;
    }

    /**
     * Set Trim Line
     * @param trimLine 
     */
    public void setTrimLine(boolean trimLine) {
        this.trimLine = trimLine;
    }

    /**
     * Set White Space
     * @param whiteSpace 
     */
    public void setWhiteSpace(boolean whiteSpace) {
        this.whiteSpace = whiteSpace;
    }

    /**
     * Verify if is Trim Line
     * @return boolean
     */
    public boolean isTrimLine() {
        return trimLine;
    }

    /**
     * Is Remove Empty Line
     * @return boolean
     */
    public boolean isRemoveEmptyLine() {
        return emptyLine;
    }

    /**
     * Is Remove White Spaces
     * @return boolean
     */
    public boolean isRemoveWhiteSpaces() {
        return whiteSpace;
    }

    /**
     * Remove White Spaces
     * @param inputStr
     * @return String
     */
    public String removeWhiteSpaces(String inputStr) {
        Matcher matcher = Pattern.compile("\\s+").matcher(inputStr);
        return matcher.replaceAll(" ");
    }

    /**
     * Verify Parameters
     * @param line
     * @return String
     */
    public String verifyParameters(String line) {
        if (this.isTrimLine()) {
            line = line.trim();
        }
        if (this.isRemoveWhiteSpaces()) {
            line = this.removeWhiteSpaces(line);
        }
        return line;
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
