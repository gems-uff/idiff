package algorithms;

import java.io.File;

/**
 * ILCSBean
 * @author Fernanda Floriano Silva
 */
public class ILCSBean {

    private File basedFile;
    private File comparedFile;

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
    
    public void changeFilesOrder(){
        File baseFile = this.getBasedFile();
        this.setBasedFile(this.getComparedFile());
        this.setComparedFile(baseFile);
    }
}
