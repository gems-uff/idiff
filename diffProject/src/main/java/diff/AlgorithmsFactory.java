package diff;

/**
 * AlgorithmsFactory
 * @author Fernanda Floriano Silva
 */
public class AlgorithmsFactory {

    /**
     * 
     * @return
     */
    static IDiff getComparator() {
        return new LCS();
    }
}
