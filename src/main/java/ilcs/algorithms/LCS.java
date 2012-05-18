package ilcs.algorithms;

import ilcs.result.ResultLCS;
import ilcs.Result;
import ilcs.result.IResultLCS;
import ilcs.ILCSBean;
import idiff.IDiff;
import ilcs.DiffException;
import ilcs.grain.Grain;
import ilcs.algorithms.LCSBean.Arrow;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * LCS
 * @author Fernanda Floriano Silva
 */
public class LCS implements IDiff {

    private LCSBean[][] arrayLcs;
    private List<Grain> linesFileOne = new ArrayList<Grain>();
    private List<Grain> columnFileTwo = new ArrayList<Grain>();

    /**
     * Constructor
     */
    public LCS() {
    }

    /**
     * Constructor
     * @param linesFileOne
     * @param columnFileTwo
     * @param arrayLCS
     */
    public LCS(List<Grain> linesFileOne, List<Grain> columnFileTwo, LCSBean[][] arrayLCS) {
        this.linesFileOne = linesFileOne;
        this.columnFileTwo = columnFileTwo;
        this.arrayLcs = arrayLCS;
    }

    /**
     * Diff
     * @param fileVersionOne
     * @param fileVersionTwo
     * @param grain
     * @param iLCSBean
     * @return List<Grain>
     * @throws DiffException 
     */
    @Override
    public List<Grain> idiff(File fileVersionOne, File fileVersionTwo, Grain grain, ILCSBean iLCSBean) throws DiffException {
        IResultLCS result = null;
        int idIteration = 0;
        while (grain.canReduceGranularity(iLCSBean.getGranularity())) {
            grain.reduceGranularity();
            idIteration=0;
            try {
                grain.init(fileVersionOne, fileVersionTwo, grain.getLevelGrain(), result, iLCSBean);
            } catch (IOException ex) {
                throw new DiffException(DiffException.MSG_INVALID_SITUATION);
            }
            do {
                idIteration++;
                result = mainLcs(idIteration);
                removeLcs(result);
            } while (!lcsIsEmpty(result));
        }
        return storeDifferences(result);

    }

    /**
     * Lcs is Empty
     * @param result
     * @return boolean
     */
    private boolean lcsIsEmpty(IResultLCS result) {
        return (result.getLcs().isEmpty());
    }

    /**
     * Main steps of the lcs
     * @param iteration 
     * @return IResultLCS
     * @throws DiffException
     */
    public IResultLCS mainLcs(int iteration) throws DiffException {
        lcs();
        return printLCS(iteration);
    }

    /**
     * lcs
     */
    public void lcs() {

        int m = this.linesFileOne.size();
        int n = this.columnFileTwo.size();
        this.arrayLcs = null;

        this.arrayLcs = new LCSBean[this.linesFileOne.size()][this.columnFileTwo.size()];
        for (int i = 0; i < m; i++) {
            this.arrayLcs[i][0] = new LCSBean(0, Arrow.UP);
        }
        for (int j = 0; j < n; j++) {
            this.arrayLcs[0][j] = new LCSBean(0, Arrow.UP);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (compare(this.linesFileOne.get(i), this.columnFileTwo.get(j))) {
                    this.arrayLcs[i][j] = new LCSBean(this.arrayLcs[i - 1][j - 1].getCounter() + 1, Arrow.DIAGONAL);
                } else {
                    if (this.arrayLcs[i - 1][j].getCounter() >= this.arrayLcs[i][j - 1].getCounter()) {
                        this.arrayLcs[i][j] = new LCSBean(this.arrayLcs[i - 1][j].getCounter(), Arrow.UP);
                    } else {
                        this.arrayLcs[i][j] = new LCSBean(this.arrayLcs[i][j - 1].getCounter(), Arrow.LEFT);
                    }
                }
            }
        }
    }

    /**
     * Print LCS
     * @param iteration 
     * @return IResultLCS
     * @throws DiffException
     */
    public IResultLCS printLCS(int iteration) throws DiffException {
        List<Grain> lcs = new ArrayList<Grain>();
        lcs.clear();
        printLCS(lcs, this.linesFileOne.size() - 1, this.columnFileTwo.size() - 1, iteration);
        return new ResultLCS(this.linesFileOne, this.columnFileTwo, lcs);
    }

    /**
     * Print lcs
     * @param lcs
     * @param i
     * @param j
     */
    private void printLCS(List<Grain> lcs, int i, int j, int iteration) {
        Result finalResult = Result.getResult();

        if (i == 0 || j == 0) {
            return;
        }
        if (this.arrayLcs[i][j].getArrow() == Arrow.DIAGONAL) {
            printLCS(lcs, i - 1, j - 1, iteration);
            finalResult = Result.getResult();

            this.linesFileOne.get(i).setIdIteration(iteration);
            this.columnFileTwo.get(j).setIdIteration(iteration);

            lcs.add(this.linesFileOne.get(i));
            lcs.add(this.columnFileTwo.get(j));

            finalResult.setGrainsFrom(this.linesFileOne.get(i));
            finalResult.setGrainsTo(this.columnFileTwo.get(j));
        } else {
            if (this.arrayLcs[i][j].getArrow() == Arrow.UP) {
                printLCS(lcs, i - 1, j, iteration);
            } else {
                printLCS(lcs, i, j - 1, iteration);
            }
        }
    }

    /**
     * Remove LCS
     * @param result
     */
    private void removeLcs(IResultLCS result) {
        if (canRemove(result.getFileVersionOne())) {
            this.linesFileOne = remove(result.getFileVersionOne());
        }
        if (canRemove(result.getFileVersionTwo())) {
            this.columnFileTwo = remove(result.getFileVersionTwo());
        }
    }

    /**
     * Can Remove
     * @param fileVersion
     * @return boolean
     */
    private boolean canRemove(List<Grain> fileVersion) {
        return (fileVersion.size() > 1);
    }

    /**
     * Compare
     * @param grainOne
     * @param grainTwo
     * @return boolean
     */
    private boolean compare(Grain grainOne, Grain grainTwo) {
        return grainOne.getGrain().compareTo(grainTwo.getGrain()) == 0;
    }

    /**
     * Remove
     * @param arrayFile
     * @return List<Grain>
     */
    private List<Grain> remove(List<Grain> arrayFile) {
        Grain grain;
        List<Grain> result = new ArrayList<Grain>();

        result.add(null);
        for (Iterator<Grain> it = arrayFile.iterator(); it.hasNext();) {
            grain = getNext(it);
            if (grain.getSituation() != Grain.Situation.UNCHANGED) {
                result.add(grain);
            }
        }
        return result;
    }

    /**
     * Get Next
     * @param it
     * @return Grain
     */
    public Grain getNext(Iterator<Grain> it) {
        Object obj = null;
        if (it.hasNext()) {
            obj = it.next();
            if (obj == null) {
                return getNext(it);
            }
        }
        return (Grain) obj;
    }

    /**
     * Set Column File Two
     * @param columnFileTwo
     */
    public void setColumnFileTwo(List<Grain> columnFileTwo) {
        this.columnFileTwo = columnFileTwo;
    }

    /**
     * GetLinesFileOne
     * @return List<Grain>
     */
    public List<Grain> getLinesFileOne() {
        return linesFileOne;
    }

    /**
     * Set Lines File One
     * @param linesFileOne
     */
    public void setLinesFileOne(List<Grain> linesFileOne) {
        this.linesFileOne = linesFileOne;
    }

    /**
     * Store differences
     * @param result
     * @return 
     */
    private List<Grain> storeDifferences(IResultLCS result) {
        List<Grain> temp = result.getFileVersionOne();
        temp.addAll(result.getFileVersionTwo());
        return temp;
    }
}
