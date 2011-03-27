package diff;

import java.io.File;

/**
 * AlgorithmsFactory
 * @author Fernanda Floriano Silva
 */
public class AlgorithmsFactory {

    /**
     * Get comparison algorithm
     * @param fileVersionOne
     * @param fileVersionTwo
     * @return
     */
    static IDiff getComparator(File fileVersionOne, File fileVersionTwo) {
        return new LCS();
    }
}
