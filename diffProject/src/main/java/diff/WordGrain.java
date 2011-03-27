package diff;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class WordGrain extends Grain {

    public WordGrain() {
        super(2);
    }
// Em construção

    public List<Grain> start(List<Grain> file) throws IOException {
        List<Grain> listFile = new ArrayList<Grain>();

        /*   listFile.add(null);
        String line = null;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
        listFile.add(new Grain(line));
        }
        return listFile;
        }*/
        return listFile;
    }
}
