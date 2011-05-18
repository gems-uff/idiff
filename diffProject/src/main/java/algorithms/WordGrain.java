package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * WordGrain
 * @author Fernanda Floriano Silva
 */
public class WordGrain extends Grain {

    /**
     * Constructor
     * @param word
     * @param idReference
     */
    public WordGrain(String word, int idReference) {
        super(LevelGranularity.WORD, word, idReference);
    }

    /**
     * Constructor
     */
    public WordGrain() {
        super(LevelGranularity.WORD);
    }

    /**
     * Start Word Granularity
     * @param list
     * @return List<Grain>
     * @throws IOException
     */
    public List<Grain> start(List<Grain> list) throws IOException {
        List<Grain> finalList = new ArrayList<Grain>();
        finalList.add(null);

        for (Iterator it = list.iterator(); it.hasNext();) {
            Grain grain = (Grain) it.next();
            if (grain != null) {
                StringTokenizer token = new StringTokenizer(grain.getGrain());
                int idReference = 0;
                while (token.hasMoreTokens()) {
                    idReference++;
                    WordGrain wordGrain = setData(token, grain, idReference);
                    finalList.add(wordGrain);
                }
            }

        }
        return finalList;
    }

    /**
     * Set WordGrain Data
     * @param token
     * @param grain
     * @param idReference
     * @return WordGrain
     */
    private WordGrain setData(StringTokenizer token, Grain grain, int idReference) {
        WordGrain wordGrain = new WordGrain(token.nextToken(), grain.getOriginalReference().get(0));
        wordGrain.setOriginalReference(idReference);
        return wordGrain;
    }

    /**
     * Start Word Granularity
     * @param listBaseFile
     * @param listComparedFile
     * @throws DiffException
     */
    public void startWordGranularity(List<Grain> listBaseFile, List<Grain> listComparedFile) throws DiffException {
        try {
            Algorithm.getComparator().setLinesFileOne(this.start(listBaseFile));
            Algorithm.getComparator().setColumnFileTwo(this.start(listComparedFile));
        } catch (IOException ex) {
            throw new DiffException(ex, DiffException.MSG_INVALID_START_WORD_GRANULARITY);
        }
    }
}
