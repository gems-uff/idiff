package algorithms;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Test Application
 * @author Fernanda Floriano Silva
 *
 */
public class Application {

    /**
     * Main - For Initial Test
     * @param args
     * @throws DiffException
     */
    public static void main(String[] args) throws DiffException {
        System.out.println(" 1 ) Iniciando teste para verificacao de LCS com granularidade LINHA");
        Grain grain = new FileGrain();
        Diff diff = new Diff(new File("t1.txt"), new File("t2.txt"));
        IResultDiff compare = diff.compare(grain);

        //TODO - RETIRAR: Apenas para debug
        List<Grain> teste1 = compare.getGrainsFrom();
        List<Grain> teste2 = compare.getGrainsTo();

        System.out.println('\n');
        System.out.println("RESULT : ");
        print(teste1, teste2);
    }
    //TODO - RETIRAR: Apenas para debug

    static void print(List<Grain> teste1, List<Grain> teste2) {
        Iterator<Grain> it = teste1.iterator();
        Iterator<Grain> it2 = teste2.iterator();

        while (it.hasNext() || it2.hasNext()) {
            Grain grain1 = it.next();
            Grain grain2 = it2.next();
            if ((grain1 != null) || (grain2 != null)) {
                System.out.print(grain1.getGrain() + "[FROM: ");
                printReference(grain1);
                System.out.print("][TO: ");
                printReference(grain2);
                System.out.print("]");
            }
        }
    }
    //TODO - RETIRAR: Apenas para debug

    static void printReference(Grain grain) {
        char grainLevel = 'F';
        for (Iterator<Integer> it = grain.getOriginalReference().iterator(); it.hasNext();) {
            int id = it.next();
            grainLevel = getGrainLevel(grainLevel);
            System.out.print(" " + grainLevel + " " + id);
        }
    }
    //TODO - RETIRAR: Apenas para debug

    static char getGrainLevel(char levelGrain) {
        switch (levelGrain) {
            case 'F':
                return 'L';
            case 'L':
                return 'W';
            case 'W':
                return 'C';
            default:
                return 'F';
        }
    }
}
