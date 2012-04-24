package ilcs.grain;

import ilcs.algorithms.Algorithm;
import ilcs.DiffException;
import ilcs.ILCSBean;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * LineGrain
 * @author Fernanda Floriano Silva
 */
public class LineGrain extends Grain {

    /**
     * Constructor
     * @param line
     * @param idReference
     * @param start 
     */
    public LineGrain(String line, int idReference, int start) {
        super(LevelGranularity.LINE, line, idReference, new GrainBean(start, line.length()));
    }

    /**
     * Constructor
     */
    public LineGrain() {
        super(LevelGranularity.LINE);
    }

    /**
     * Start Line Grain
     * @param file
     * @param ilcsb
     * @param idFile 
     * @return
     * @throws IOException 
     */
    public List<Grain> start(File file, ILCSBean ilcsb, int idFile) throws IOException {
        List<Grain> finalList = new ArrayList<Grain>();
        finalList.add(null);
        String line = null;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int id = 0;
        int idStart = 0;
        while (((line = reader.readLine()) != null) && (!islineTag(line, ilcsb.getTags()))) {
            id++;
            LineGrain lineGrain = new LineGrain(line, id, idStart);
            lineGrain.setIdFile(idFile);
            finalList.add(lineGrain);
            idStart = idStart + line.length() + 1;
        }
        return finalList;
    }

    /**
     * Start Line Granularity
     * @param fileFrom
     * @param fileTo
     * @param ilcsb
     * @throws IOException
     * @throws DiffException 
     */
    public void startLineGranularity(File fileFrom, File fileTo, ILCSBean ilcsb) throws IOException, DiffException {
        try {
            Algorithm.getComparator().setLinesFileOne(this.start(fileFrom, ilcsb, 1));
            Algorithm.getComparator().setColumnFileTwo(this.start(fileTo, ilcsb, 2));
        } catch (IOException ex) {
            throw new DiffException(ex, DiffException.MSG_INVALID_START_LINE_GRANULARITY);
        }
    }

    private boolean islineTag(String line, String tags) {
        if (!line.equals("")) { //Existe conteúdo a ser comparado
            Pattern p = Pattern.compile(tags);
            String[] token = p.split(line);
            return ((allTokensEmpty(token)) ? true : false);
        }
        return false;
    }

    private boolean allTokensEmpty(String[] token) {
        for (int i = 0; i < token.length; i++) {
            if (!token[i].equals("")) {
                return false;
            }
        }
        return true; 
    }
}
