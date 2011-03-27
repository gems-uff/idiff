package diff;

import diff.LCSBean.Arrow;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * Executes and prints the lcs
     * @param fileVersionOne
     * @param fileVersionTwo
     * @return
     * @throws DiffException
     */
    @Override
    public IResultDiff diff(File fileVersionOne, File fileVersionTwo, Grain grain) throws DiffException {
        IResultDiff result = null;
        while (grain.canReduceGranularity(grain)) {
            grain.reduceGranularity(grain);
            try {
                init(fileVersionOne, fileVersionTwo, result, grain.getIdGrain());
            } catch (IOException ex) {
                Logger.getLogger(LCS.class.getName()).log(Level.SEVERE, null, ex);
            }
            do {
                result = mainLcs();
                removeLcs(result);
            } while (!result.getLcs().isEmpty());

        }
        return result;
    }

    public void init(File fileVersionOne, File fileVersionTwo, IResultDiff result, int idGrain) throws DiffException, IOException {
        Grain grain = null;
        switch (idGrain) {
            case 3:
                System.out.println("Line Grain");
                grain = new LineGrain();
                startLineGranularity(fileVersionOne, fileVersionTwo);
                break;
            case 2:
                System.out.println("Word Grain");
                grain = new WordGrain();
                startWordGranularity(result.getFileVersionOne(), result.getFileVersionTwo());
                break;
            case 1:
                System.out.println("Character Grain");
                grain = new CharacterGrain();
                startCharacterGranularity(result.getFileVersionOne(), result.getFileVersionTwo());
                break;
            default:
                throw new DiffException(DiffException.MSG_INVALID_SITUATION);
        }
    }

    public IResultDiff mainLcs() throws DiffException {
        lcs();
        IResultDiff result = printLCS();
        return result;
    }

    private void removeLcs(IResultDiff result) {
        result.setFileVersionOne(remove(result.getFileVersionOne()));
        result.setFileVersionTwo(remove(result.getFileVersionTwo()));
    }

    public void startLineGranularity(File baseFile, File comparedFile) throws IOException, DiffException {
        try {

            LineGrain lineGrain = new LineGrain();

            linesFileOne = lineGrain.start(baseFile);
            columnFileTwo = lineGrain.start(comparedFile);

            arrayLcs = new LCSBean[linesFileOne.size()][columnFileTwo.size()];
        } catch (IOException ex) {
            throw new DiffException(ex, DiffException.MSG_INVALID_START_LINE_GRANULARITY);
        }
    }

    public void startWordGranularity(List<Grain> listBaseFile, List<Grain> listComparedFile) throws DiffException {
        try {

            WordGrain wordGrain = new WordGrain();

            linesFileOne = wordGrain.start(listBaseFile);
            columnFileTwo = wordGrain.start(listComparedFile);

            arrayLcs = new LCSBean[linesFileOne.size()][columnFileTwo.size()];
        } catch (IOException ex) {
            throw new DiffException(ex, DiffException.MSG_INVALID_START_WORD_GRANULARITY);
        }
    }

    public void startCharacterGranularity(List<Grain> listBaseFile, List<Grain> listComparedFile) throws DiffException {
        try {

            CharacterGrain characterGrain = new CharacterGrain();

            linesFileOne = characterGrain.start(listBaseFile);
            columnFileTwo = characterGrain.start(listComparedFile);

            arrayLcs = new LCSBean[linesFileOne.size()][columnFileTwo.size()];
        } catch (IOException ex) {
            throw new DiffException(ex, DiffException.MSG_INVALID_START_WORD_GRANULARITY);
        }
    }

    /**
     * LCS algorithm
     * @return
     */
    public void lcs() {

        int m = linesFileOne.size();
        int n = columnFileTwo.size();

        for (int i = 0; i < m; i++) {
            arrayLcs[i][0] = new LCSBean(0, Arrow.UP);
        }

        for (int j = 0; j < n; j++) {
            arrayLcs[0][j] = new LCSBean(0, Arrow.UP);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (compare(linesFileOne.get(i), columnFileTwo.get(j))) {
                    arrayLcs[i][j] = new LCSBean(arrayLcs[i - 1][j - 1].getCounter() + 1, Arrow.DIAGONAL);
                } else {
                    if (arrayLcs[i - 1][j].getCounter() >= arrayLcs[i][j - 1].getCounter()) {
                        arrayLcs[i][j] = new LCSBean(arrayLcs[i - 1][j].getCounter(), Arrow.UP);
                    } else {
                        arrayLcs[i][j] = new LCSBean(arrayLcs[i][j - 1].getCounter(), Arrow.LEFT);
                    }
                }
            }
        }
    }

    /**
     * Print LCS
     * @return
     * @throws DiffException
     */
    public IResultDiff printLCS() throws DiffException {
        List<Grain> lcs = new ArrayList();

        printLCS(lcs, linesFileOne.size() - 1, columnFileTwo.size() - 1);

        return new ResultLCS(linesFileOne, columnFileTwo, lcs);
    }

    /**
     * Print LCS list
     * @param lcs
     * @param i
     * @param j
     */
    private void printLCS(List<Grain> lcs, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (arrayLcs[i][j].getArrow() == Arrow.DIAGONAL) {
            printLCS(lcs, i - 1, j - 1);
            lcs.add(linesFileOne.get(i));
        } else {
            if (arrayLcs[i][j].getArrow() == Arrow.UP) {
                printLCS(lcs, i - 1, j);
            } else {
                printLCS(lcs, i, j - 1);
            }
        }
    }

    /**
     * Performs comparison between lines
     * @param grainOne
     * @param grainTwo
     * @return
     */
    private boolean compare(Grain grainOne, Grain grainTwo) {
        return grainOne.getGrain().compareTo(grainTwo.getGrain()) == 0;
    }

    private List<Grain> remove(List<Grain> arrayFile) {
        Grain grain;
        List<Grain> result = new ArrayList<Grain>();
        int id = 1;

        for (Iterator it = arrayFile.iterator(); it.hasNext(); id++) {
            grain = getNext(it);
            if (grain.getSituation() != Grain.Situation.UNCHANGED) {
                result.add(grain);
            }
        }
        return result;
    }

    /**
     * Get next iteration
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
