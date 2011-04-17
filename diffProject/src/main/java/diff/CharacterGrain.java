package diff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class CharacterGrain extends Grain {

    /**
     *
     */
    public CharacterGrain() {
        super(LevelGranularity.CHARACTER);
    }

    /**
     *
     * @param list
     * @return
     * @throws IOException
     */
    public List<Grain> start(List<Grain> list) throws IOException {

        List<Grain> finalList = new ArrayList<Grain>();
        finalList.add(null);
        for (Iterator it = list.iterator(); it.hasNext();) {
            Grain grain = (Grain) it.next();
            if (grain != null) {
                char[] letras = grain.getGrain().toCharArray();
                for (int i = 0; i < letras.length; i++) {
                    finalList.add(new Grain(LevelGranularity.CHARACTER, Character.toString(letras[i])));
                }
            }
        }
        return finalList;
    }
}
