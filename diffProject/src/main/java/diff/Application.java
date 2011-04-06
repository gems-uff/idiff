package diff;

import java.io.File;

/**
 * Test Application
 * @author Fernanda Floriano Silva
 *
 */
public class Application {

    /**
     *
     * @param args
     * @throws DiffException
     */
    public static void main(String[] args) throws DiffException {
        String dir = "C:/Users/Sisi/Desktop/Testes LCS/";
        //Diff diff = new Diff(new File(dir + "Linha1.txt"), new File(dir + "Linha2.txt"));
       // Diff diff = new Diff(new File(dir + "Palavra1.txt"), new File(dir + "Palavra2.txt"));
       //Diff diff = new Diff(new File(dir + "Caracter1.txt"), new File(dir + "Caracter2.txt"));
       Diff diff = new Diff(new File(dir + "Caracter1.txt"), new File(dir + "Caracter3.txt"));

        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }
}
