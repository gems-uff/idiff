package diff;

import java.io.File;
import junit.framework.TestCase;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class ApplicationTest extends TestCase {

    public void setUp() throws Exception {
    }

    public void tearDown() throws Exception {
    }

    public void testMainLine() throws Exception {
     System.out.println(" 1 ) Iniciando teste para verificação de LCS com granularidade LINHA");
        Diff diff = new Diff(new File("Linha1.txt"), new File("Linha2.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }

    public void testMainWord() throws Exception {
     System.out.println(" 2 ) Iniciando teste para verificação de LCS com granularidade PALAVRA");
        Diff diff = new Diff(new File("Palavra1.txt"), new File("Palavra2.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);

    }

    public void testMainCharacter() throws Exception {
        System.out.println(" 3 ) Iniciando teste para verificação de LCS com granularidade CARACTER");
        Diff diff = new Diff(new File("Caracter1.txt"), new File("Caracter2.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }
    public void testMainCharacter2() throws Exception {
        System.out.println(" 4 ) Iniciando teste para verificação de LCS com granularidade CARACTER");
        Diff diff = new Diff(new File("Caracter1.txt"), new File("Caracter3.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }

    public void testMainEqualFile() throws Exception {
        System.out.println(" 5 ) Iniciando teste para verificação de LCS com todas as granularidades");
       Diff diff = new Diff(new File("Caracter1.txt"), new File("Caracter1.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }
       public void testAllGrain() throws Exception {
        System.out.println(" 6 ) Iniciando teste para verificação de LCS com todas as granularidades");
        Diff diff = new Diff(new File("teste1.txt"), new File("teste2.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }
}
