package ilcs.algorithms;

/**
 * Algorithm
 * @author Fernanda Floriano Silva
 */
public class Algorithm {

    private static LCS lcsInstance;

    /**
     * Get Comparator
     * @return
     */
    public static LCS getComparator() {
        if (lcsInstance == null) {
            lcsInstance = new LCS();
        }
        return lcsInstance;
    }

    public static void resetComparator() {
        lcsInstance = null;
    }
}
