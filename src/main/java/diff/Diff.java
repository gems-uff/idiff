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
     * Constructor
     *
     * @param fileVersionOne
     * @param fileVersionTwo
     */
    public Diff(File fileVersionOne, File fileVersionTwo) {
        this.fileVersionOne = fileVersionOne;
        this.fileVersionTwo = fileVersionTwo;
    }

    /**
     * Runs the diff according to the comparison algorithm
     *
     * @return
     * @throws DiffException
     */
    public IResultDiff compare(Grain grain) throws DiffException {
        IDiff comparator = AlgorithmsFactory.getComparator(fileVersionOne, fileVersionTwo);
        return comparator.diff(fileVersionOne, fileVersionTwo, grain);
    }

    /**
     * Not implemented
     * @param resultDiff
     */
    public void createOutputFormat(IResultDiff resultDiff) {
        // Not implemented
    }

    /**
     * Prints result after implementation of the lcs
     * @param result
     */
    public void printResult(IResultDiff result) {
        List<Grain> listResult = result.getResult();
    }
    /**
     * Selects the output format to display the final result
     * @throws DiffException
     */
    /*  public void setOutputFormat() throws DiffException {
    IResultDiff resultDiff = this.compare();
    this.createOutputFormat(resultDiff);
    }*/
}
