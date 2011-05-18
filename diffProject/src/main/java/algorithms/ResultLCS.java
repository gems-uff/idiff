package algorithms;

import java.util.Iterator;
import java.util.List;

/**
 * ResultLCS
 * @author Fernanda Floriano Silva
 */
public class ResultLCS implements IResultLCS {

    /**
     * 
     */
    private List<Grain> arrayFileVersionOne;
    private List<Grain> arrayFileVersionTwo;
    private List<Grain> lcs;

    /**
     * Constructor
     * @param arrayFileVersionOne
     * @param arrayFileVersionTwo
     * @param lcs
     * @throws DiffException
     */
    public ResultLCS(List<Grain> arrayFileVersionOne, List<Grain> arrayFileVersionTwo, List<Grain> lcs) throws DiffException {
        this.arrayFileVersionOne = arrayFileVersionOne;
        this.arrayFileVersionTwo = arrayFileVersionTwo;
        this.lcs = lcs;

        loadResult();
    }

    /**
     * Load Result
     * @throws DiffException
     */
    private void loadResult() throws DiffException {
        int i = 0;
        int j = 0;

        Iterator itF1 = this.arrayFileVersionOne.iterator();
        Iterator itF2 = this.arrayFileVersionTwo.iterator();

        Grain grainF1 = getNext(itF1);
        Grain grainF2 = getNext(itF2);

        while ((grainF1 != null) || (grainF2 != null)) {
            if (isContext(grainF1)) {
                //Context
                grainF1.setSituation(Grain.Situation.UNCHANGED);
                grainF1 = getNext(itF1);
                //Context
            } else if (isContext(grainF2)) {
                grainF2.setSituation(Grain.Situation.UNCHANGED);
                grainF2 = getNext(itF2);
            } else if (isRemoving(grainF1)) {
                //Removed
                grainF1.setSituation(Grain.Situation.REMOVED);
                grainF1 = getNext(itF1);
            } else if (isAdding(grainF2)) {
                //Addition
                grainF2.setSituation(Grain.Situation.ADDED);
                grainF2 = getNext(itF2);
            } else {
                throw new DiffException(DiffException.MSG_INVALID_SITUATION);
            }
        }
    }

    /**
     * Is Context
     * @param grain
     * @return boolean
     */
    private boolean isContext(Grain grain) {
        if (grain == null) {
            return false;
        }
        return (this.lcs.contains(grain));
    }

    /**
     * isAdding
     * @param grainF2
     * @return boolean
     */
    private boolean isAdding(Grain grainF2) {
        if ((grainF2 == null)) {
            return false;
        }
        return !this.lcs.contains(grainF2);
    }

    /**
     * Is Removing
     * @param grainF1
     * @return boolean
     */
    private boolean isRemoving(Grain grainF1) {
        if ((grainF1 == null)) {
            return false;
        }
        return !this.lcs.contains(grainF1);
    }

    /**
     * Get File Version One
     * @return List<Grain>
     */
    public List<Grain> getFileVersionOne() {
        return this.arrayFileVersionOne;
    }

    /**
     * Get File Version Two
     * @return List<Grain>
     */
    public List<Grain> getFileVersionTwo() {
        return this.arrayFileVersionTwo;
    }

    /**
     * Get LCS
     * @return List<Grain>
     */
    public List<Grain> getLcs() {
        return this.lcs;
    }

    /**
     * Get Next
     * @param it
     * @return
     */
    public Grain getNext(Iterator it) {
        Object obj = null;
        if (it.hasNext()) {
            obj = it.next();
            if (obj == null) {
                return getNext(it);
            }
        }
        return (Grain) obj;
    }
}
