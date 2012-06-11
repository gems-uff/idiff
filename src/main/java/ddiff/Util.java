package ddiff;

import java.io.File;
import java.util.Arrays;

/**
 * Classe com metodos utilitarios.
 * 
 * @author Eraldo
 *
 */
public class Util {

    /**
     * Construtor padr�o
     */
    private Util() {
    }

    /**
     * Atribui o valor padrao a cada elemento da matriz.
     * 
     * @param matriz Matriz
     * @param padrao Valor padrao
     */
    public static void atribuir(int[][] matriz, int padrao) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = padrao;
            }
        }
    }

    /**
     * Cria um array de booleanos e atribui um valor padr�o para cada posicao.
     * @param tamanho Tamanho do array
     * @param padrao Valor padrao
     * @return array criado.
     */
    public static boolean[] criarArray(int tamanho, boolean padrao) {
        boolean[] array = new boolean[tamanho];
        Arrays.fill(array, padrao);
        return array;
    }

    /**
     * @param directory
     * @return
     */
    public static boolean isValidDirectory(File directory) {
        if ((directory == null) || (directory.isHidden()) || (!directory.isDirectory())) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param directory 
     * @return
     */
    public static boolean isNotValidDirectory(File directory) {
        return !isValidDirectory(directory);
    }
}
