package diff;

import java.io.File;
import java.util.List;

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
        IDiff comparator = AlgorithmsFactory.getComparator(fileVersionOne, fileVersionTwo);
        return comparator.diff(fileVersionOne, fileVersionTwo, grain);
    }

    /**
     *
     * @param resultDiff
     */
    public void createOutputFormat(IResultDiff resultDiff) {
        // Not implemented
    }

    /**
     *
     * @param result
     */
    public void printResult(IResultDiff result) {
        List<Grain> listResult = result.getResult();
    }
}
