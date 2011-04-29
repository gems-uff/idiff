package diff;

import diff.LCSBean.Arrow;
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
    private Grain.LevelGranularity level;

    /**
     *
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
     * @throws DiffException
     */
    @Override
    public void diff(File fileVersionOne, File fileVersionTwo, Grain grain) throws DiffException {
        IResultLCS result = null;
        while (grain.canReduceGranularity()) {
            grain.reduceGranularity();
            try {
                grain.init(fileVersionOne, fileVersionTwo, grain.getLevelGrain(), result);
            } catch (IOException ex) {
                throw new DiffException(DiffException.MSG_INVALID_SITUATION);
            }
            do {
                result = mainLcs();
                removeLcs(result);
            } while (!lcsIsEmpty(result));
        }
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
     * @return
     * @throws DiffException
     */
    public IResultLCS mainLcs() throws DiffException {
        lcs();
        return printLCS();
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
     * @return IResultLCS
     * @throws DiffException
     */
    public IResultLCS printLCS() throws DiffException {
        List<Grain> lcs = new ArrayList();
        lcs.clear();
        printLCS(lcs, this.linesFileOne.size() - 1, this.columnFileTwo.size() - 1);
        //TODO - RETIRAR print(lcs): Apenas para debug
        print(lcs);
        return new ResultLCS(this.linesFileOne, this.columnFileTwo, lcs);
    }

    /**
     * Print lcs
     * @param lcs
     * @param i
     * @param j
     */
    private void printLCS(List<Grain> lcs, int i, int j) {
        Result finalResult = Result.getResult();

        if (i == 0 || j == 0) {
            return;
        }
        if (this.arrayLcs[i][j].getArrow() == Arrow.DIAGONAL) {
            printLCS(lcs, i - 1, j - 1);
            finalResult = Result.getResult();

            lcs.add(this.linesFileOne.get(i));
            finalResult.setGrainsFrom(this.linesFileOne.get(i));
            finalResult.setGrainsTo(this.columnFileTwo.get(j));
        } else {
            if (this.arrayLcs[i][j].getArrow() == Arrow.UP) {
                printLCS(lcs, i - 1, j);
            } else {
                printLCS(lcs, i, j - 1);
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
     * @return
     */
    private boolean canRemove(List<Grain> fileVersion) {
        return (fileVersion.size() > 1);
    }

    /**
     * Compare
     * @param grainOne
     * @param grainTwo
     * @return
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
        for (Iterator it = arrayFile.iterator(); it.hasNext();) {
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

////////////////////   APENAS PARA VERIFICAÇÃO DO RESULTADO ////////////////////
    /**
     *
     * @param lcs
     */
    private void print(List<Grain> lcs) {
        System.out.println();
        System.out.println("====================================================");
        System.out.println();
        printFile(this.linesFileOne, ">> Arquivo 1");
        System.out.println();
        System.out.println();
        printFile(this.columnFileTwo, ">> Arquivo 2");
        System.out.println();
        printFile(lcs, " >> LCS: ");
        System.out.println();
    }

    /**
     *
     * @param file
     * @param texto
     */
    private void printFile(List<Grain> file, String texto) {
        System.out.println(texto);
        System.out.println();
        for (Iterator<Grain> it = file.iterator(); it.hasNext();) {
            Grain grain = it.next();
            if (grain != null) {
                System.out.print(grain.getGrain() + " (");
                printReference(grain);
                System.out.print(")" + " , ");
            }
        }
    }

    private void printReference(Grain grain) {
        char grainLevel = 'F';
        for (Iterator<Integer> it = grain.getOriginalReference().iterator(); it.hasNext();) {
            int id = it.next();
            grainLevel = getGrainLevel(grainLevel);
            System.out.print(" " + grainLevel + " " + id);
        }
    }

    private char getGrainLevel(char levelGrain) {
        switch (levelGrain) {
            case 'F':
                return 'L';
            case 'L':
                return 'W';
            case 'W':
                return 'C';
            default:
                return 'F';
        }
    }
}
