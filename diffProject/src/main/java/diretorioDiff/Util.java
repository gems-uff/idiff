package diretorioDiff;

import java.util.Arrays;

/**
 * Classe com metódos utilitarios.
 * 
 * @author Eraldo
 *
 */
public class Util {
	
	/**
	 * Construtor padrão
	 */
	private Util() {

	}

	/**
	 * Cria um array de inteiros e atribui um valor padrão para cada posição.
	 * @param tamanho Tamanho do array
	 * @param padrao Valor padrão
	 * @return array criado.
	 */
	public static int[] criarArray(int tamanho, int padrao) {
		int[] array = new int[tamanho];
		Arrays.fill(array, padrao);
		return array;
	}
	
	/**
	 * Atribui o valor padrão a cada elemento da matriz.
	 * 
	 * @param matriz Matriz
	 * @param padrao Valor padrão
	 */
	public static void atribuir(int[][] matriz, int padrao) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {				
				matriz[i][j] = padrao;
			}
		}
	}

	/**
	 * Cria um array de booleanos e atribui um valor padrão para cada posição.
	 * @param tamanho Tamanho do array
	 * @param padrao Valor padrão
	 * @return array criado.
	 */
	public static boolean[] criarArray(int tamanho, boolean padrao) {
		boolean[] array = new boolean[tamanho];
		Arrays.fill(array, padrao);
		return array;
	}
}
