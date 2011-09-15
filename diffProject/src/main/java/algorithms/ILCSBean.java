package algorithms;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ILCSBean
 * @author Fernanda Floriano Silva
 */
public class ILCSBean {

    private File fileFrom;
    private File fileTo;
    private String granularity;
    private boolean trimLine;
    private boolean emptyLine;
    private boolean whiteSpace;

    /**
     * Constructor
     * @param fileFrom
     * @param fileTo 
     */
    public ILCSBean(File fileFrom, File fileTo) {
        this.fileFrom = fileFrom;
        this.fileTo = fileTo;
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
     * Get File From 
     * @return File
     */
    public File getFileFrom() {
        return fileFrom;
    }

    /**
     * Set File From 
     * @param fileFrom 
     */
    public void setFileFrom(File fileFrom) {
        this.fileFrom = fileFrom;
    }

    /**
     * Get File To
     * @return File
     */
    public File getFileTo() {
        return fileTo;
    }

    /**
     * Set File To
     * @param fileTo 
     */
    public void setFileTo(File fileTo) {
        this.fileTo = fileTo;
    }
}
