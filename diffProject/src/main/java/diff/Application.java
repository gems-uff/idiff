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
        System.out.println(" 1 ) Iniciando teste para verificação de LCS com granularidade LINHA");
        Diff diff = new Diff(new File("Linha1.txt"), new File("Linha2.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);

    }
}
