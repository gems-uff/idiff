package algorithms;

import java.io.File;

/**
 * Diff
 * @author Fernanda Floriano Silva
 */
public class Diff {

    private final File fileVersionOne;
    private final File fileVersionTwo;

    /**
     * Constructor
     * @param fileVersionOne
     * @param fileVersionTwo
     */
    public Diff(File fileVersionOne, File fileVersionTwo) {
        this.fileVersionOne = fileVersionOne;
        this.fileVersionTwo = fileVersionTwo;
    }

    /**
     * Compare
     * @param grain
     * @return IResultDiff
     * @throws DiffException 
     */
    public IResultDiff compare(Grain grain) throws DiffException {
        IDiff comparator = Algorithm.getComparator();
        Result.getResult().setDifferences(comparator.diff(this.fileVersionOne, this.fileVersionTwo, grain));
        return Result.getResult();
    }
}
