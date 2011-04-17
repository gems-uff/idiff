package diff;

import java.io.File;

/**
 * Diff
 * @author Fernanda Floriano Silva
 */
public class Diff {

    private final File fileVersionOne;
    private final File fileVersionTwo;

    /**
     *
     * @param fileVersionOne
     * @param fileVersionTwo
     */
    public Diff(File fileVersionOne, File fileVersionTwo) {
        this.fileVersionOne = fileVersionOne;
        this.fileVersionTwo = fileVersionTwo;
    }

    /**
     *
     * @param grain
     * @return
     * @throws DiffException
     */
    public IResultDiff compare(Grain grain) throws DiffException {
        IDiff comparator = AlgorithmsFactory.getComparator();
        return comparator.diff(fileVersionOne, fileVersionTwo, grain);
    }
}
