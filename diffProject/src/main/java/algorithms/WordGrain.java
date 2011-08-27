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
    public WordGrain(String word, int idReference, GrainBean grainBean) {
        super(LevelGranularity.WORD, word, idReference, grainBean);
    }

    /**
     * Constructor
     */
    public WordGrain() {
        super(LevelGranularity.WORD);
    }

    /**
     * Start word grain
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
                //int startPosition = 0;
                int startPosition = grain.getGrainBean().getStartPosition();
                while (token.hasMoreTokens()) {
                    idReference++;
                    WordGrain wordGrain = setData(token, grain, idReference, startPosition);
                    finalList.add(wordGrain);
                    startPosition = setStartPosition(startPosition, wordGrain.getGrain());
                }
            }

        }
        return finalList;
    }

    private int setStartPosition(int startPosition, String word) {
        return (startPosition + word.length() + 1);
    }

    /**
     * Set Data
     * @param token
     * @param grain
     * @param idReference
     * @return WordGrain
     */
    private WordGrain setData(StringTokenizer token, Grain grain, int idReference, int startPosition) {
        String nextToken = token.nextToken();
        GrainBean grainBean = new GrainBean(startPosition, nextToken.length());
        WordGrain wordGrain = new WordGrain(nextToken, grain.getOriginalReference().get(0), grainBean);
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
