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

    /**
     *
     */
    public LCS() {
    }

    /**
     *
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
     *
     * @param fileVersionOne
     * @param fileVersionTwo
     * @param grain
     * @return
     * @throws DiffException
     */
    @Override
    public IResultDiff diff(File fileVersionOne, File fileVersionTwo, Grain grain) throws DiffException {
        IResultDiff result = null;
        while (grain.canReduceGranularity()) {
            grain.reduceGranularity();
            try {
                init(fileVersionOne, fileVersionTwo, grain.getLevelGrain(), result);
            } catch (IOException ex) {
                throw new DiffException(DiffException.MSG_INVALID_SITUATION);
            }
            do {

                result = mainLcs();
                removeLcs(result);
            } while (!lcsIsEmpty(result));
        }
        return result;
    }

    /**
     *
     * @param result
     * @return
     */
    private boolean lcsIsEmpty(IResultDiff result) {
        return (result.getLcs().isEmpty());
    }

    /**
     *
     * @return
     * @throws DiffException
     */
    public IResultDiff mainLcs() throws DiffException {
        lcs();
        IResultDiff result = printLCS();
        return result;
    }

    /**
     *
     */
    public void lcs() {

        int m = this.linesFileOne.size();
        int n = this.columnFileTwo.size();
        //debug
        arrayLcs = null;

        arrayLcs = new LCSBean[this.linesFileOne.size()][this.columnFileTwo.size()];
        for (int i = 0; i < m; i++) {
            arrayLcs[i][0] = new LCSBean(0, Arrow.UP);
        }
        for (int j = 0; j < n; j++) {
            arrayLcs[0][j] = new LCSBean(0, Arrow.UP);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (compare(this.linesFileOne.get(i), this.columnFileTwo.get(j))) {
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
     *
     * @return
     * @throws DiffException
     */
    public IResultDiff printLCS() throws DiffException {
        List<Grain> lcs = new ArrayList();
        lcs.clear();
        printLCS(lcs, this.linesFileOne.size() - 1, this.columnFileTwo.size() - 1);
        //Para debug
        print(lcs);
        return new ResultLCS(this.linesFileOne, this.columnFileTwo, lcs);
    }

    /**
     *
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
            lcs.add(this.linesFileOne.get(i));
        } else {
            if (arrayLcs[i][j].getArrow() == Arrow.UP) {
                printLCS(lcs, i - 1, j);
            } else {
                printLCS(lcs, i, j - 1);
            }
        }
    }

    /**
     *
     * @param result
     */
    private void removeLcs(IResultDiff result) {
        if (result.getFileVersionOne().size() > 1) {
            this.linesFileOne = remove(result.getFileVersionOne());
        }
        if (result.getFileVersionTwo().size() > 1) {
            this.columnFileTwo = remove(result.getFileVersionTwo());
        }
    }

    /**
     *
     * @param grainOne
     * @param grainTwo
     * @return
     */
    private boolean compare(Grain grainOne, Grain grainTwo) {
        return grainOne.getGrain().compareTo(grainTwo.getGrain()) == 0;
    }

    /**
     *
     * @param arrayFile
     * @return
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
     *
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
     *
     * @param fileVersionOne
     * @param fileVersionTwo
     * @param levelGrain
     * @param result
     * @throws DiffException
     * @throws IOException
     */
    public void init(File fileVersionOne, File fileVersionTwo, Grain.LevelGranularity levelGrain, IResultDiff result) throws DiffException, IOException {
        System.out.println();
        arrayLcs = null;
        switch (levelGrain) {
            case LINE:
                System.out.println("...Start Algorithm LCS with Line Grain...");
                startLineGranularity(fileVersionOne, fileVersionTwo);
                break;
            case WORD:
                System.out.println("...Start Algorithm LCS with Word Grain...");
                startWordGranularity(result.getFileVersionOne(), result.getFileVersionTwo());
                break;
            case CHARACTER:
                System.out.println("...Start Algorithm LCS with Character Grain...");
                startCharacterGranularity(result.getFileVersionOne(), result.getFileVersionTwo());
                break;
            default:
                throw new DiffException(DiffException.MSG_INVALID_SITUATION);
        }
    }

    /**
     *
     * @param listBaseFile
     * @param listComparedFile
     * @throws DiffException
     */
    public void startCharacterGranularity(List<Grain> listBaseFile, List<Grain> listComparedFile) throws DiffException {
        try {
            CharacterGrain characterGrain = new CharacterGrain();
            this.linesFileOne = characterGrain.start(listBaseFile);
            this.columnFileTwo = characterGrain.start(listComparedFile);
            arrayLcs = new LCSBean[this.linesFileOne.size()][this.columnFileTwo.size()];
        } catch (IOException ex) {
            throw new DiffException(ex, DiffException.MSG_INVALID_START_CHARACTER_GRANULARITY);
        }
    }

    /**
     *
     * @param baseFile
     * @param comparedFile
     * @throws IOException
     * @throws DiffException
     */
    public void startLineGranularity(File baseFile, File comparedFile) throws IOException, DiffException {
        try {
            LineGrain lineGrain = new LineGrain();
            this.linesFileOne = lineGrain.start(baseFile);
            this.columnFileTwo = lineGrain.start(comparedFile);
            arrayLcs = new LCSBean[this.linesFileOne.size()][this.columnFileTwo.size()];
        } catch (IOException ex) {
            throw new DiffException(ex, DiffException.MSG_INVALID_START_LINE_GRANULARITY);
        }
    }

    /**
     *
     * @param listBaseFile
     * @param listComparedFile
     * @throws DiffException
     */
    public void startWordGranularity(List<Grain> listBaseFile, List<Grain> listComparedFile) throws DiffException {
        try {
            WordGrain wordGrain = new WordGrain();
            this.linesFileOne = wordGrain.start(listBaseFile);
            this.columnFileTwo = wordGrain.start(listComparedFile);
            arrayLcs = new LCSBean[this.linesFileOne.size()][this.columnFileTwo.size()];
        } catch (IOException ex) {
            throw new DiffException(ex, DiffException.MSG_INVALID_START_WORD_GRANULARITY);
        }
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
                System.out.print(grain.getGrain() + " , ");
            }
        }
    }

    /**
     *
     * @param linesFile
     * @return
     */
    private boolean isfileEmpty(List<Grain> linesFile) {
        return (linesFile.size() <= 1);
    }
}
