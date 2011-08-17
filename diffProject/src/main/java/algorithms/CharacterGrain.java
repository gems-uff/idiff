package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * CharacterGrain
 * @author Fernanda Floriano Silva
 */
public class CharacterGrain extends Grain {

    /**
     * Constructor
     * @param character
     * @param idReference
     */
    public CharacterGrain(String character, int idReference, GrainBean grainBean) {
        super(LevelGranularity.CHARACTER, character, idReference, grainBean);
    }

    /**
     * Constructor
     */
    public CharacterGrain() {
        super(LevelGranularity.CHARACTER);
    }

    /**
     * Start Character Grain
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
                char[] letras = grain.getGrain().toCharArray();
                int startPosition = grain.getOriginalReference().get(1) ;
                for (int i = 0; i < letras.length; i++) {
                    CharacterGrain charGrain = setData(letras, i, grain, startPosition); 
                    finalList.add(charGrain);
                    startPosition++;
                }
            }
        }
        return finalList;
    }

    /**
     * Set Character Data
     * @param letras
     * @param i
     * @param grain
     * @param idReference
     * @return CharacterGrain
     */
    private CharacterGrain setData(char[] letras, int i, Grain grain, int startPosition) { 
        GrainBean grainBean = new GrainBean(grain.getOriginalReference().get(0), startPosition, 1);
        CharacterGrain charGrain = new CharacterGrain(Character.toString(letras[i]), grain.getOriginalReference().get(0), grainBean);
        //charGrain.setOriginalReference(grain.getOriginalReference().get(1));
        charGrain.setOriginalReference(i++);
        return charGrain;
    }

    /**
     * Start Character Granularity
     * @param listBaseFile
     * @param listComparedFile
     * @throws DiffException
     */
    public void startCharacterGranularity(List<Grain> listBaseFile, List<Grain> listComparedFile) throws DiffException {
        try {
            Algorithm.getComparator().setLinesFileOne(this.start(listBaseFile));
            Algorithm.getComparator().setColumnFileTwo(this.start(listComparedFile));
        } catch (IOException ex) {
            throw new DiffException(ex, DiffException.MSG_INVALID_START_CHARACTER_GRANULARITY);
        }
    }
}
