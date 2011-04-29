package diff;

import diff.Grain.LevelGranularity;

/**
 * FileGrain
 * @author Fernanda Floriano Silva
 */
public class FileGrain extends Grain {

    /**
     * Constructor
     */
    public FileGrain() {
        super(LevelGranularity.FILE);
    }

    /**
     * Constructor
     * @param nameFile
     * @param idReference
     */
    public FileGrain(String nameFile, int idReference) {
        super(LevelGranularity.FILE, nameFile, idReference);
    }
}
