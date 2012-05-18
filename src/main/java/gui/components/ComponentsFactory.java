package gui.components;

import ilcs.grain.Grain;

/**
 *
 * @author Sisi
 */
public class ComponentsFactory {

    /**
     * 
     * @param grain1
     * @param grain2
     * @param perpective
     * @return boolean
     */
    public static boolean verifyConditions(Grain grain1, Grain grain2, int perpective) {
        boolean condition = (grain1 != null) || (grain2 != null);
        if (perpective == 1) {
            return ((condition) && (!isFirstIteration(grain1, grain2)) && (!isSamePosition(grain1, grain2)));
        } else {
            return condition;
        }
    }

    private static boolean isFirstIteration(Grain grain1, Grain grain2) {
        return (grain1.getIdIteration() == 1) || (grain2.getIdIteration() == 1);
    }

    /**
     * Verify Position
     * @param grain1
     * @param grain2
     * @return 
     */
    private static boolean isSamePosition(Grain grain1, Grain grain2) {
        return (grain1.getOriginalReference().equals(grain2.getOriginalReference()));
    }
}
