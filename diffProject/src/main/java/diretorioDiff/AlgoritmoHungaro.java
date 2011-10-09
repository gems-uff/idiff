package diretorioDiff;

import java.util.Arrays;

/**
 * Implementação do algoritmo Húngaro
 * <br>
 * Autores do algoritmo  <b>Dénes Kőnig</b> e <b>Jenő Egerváry</b>
 * 
 * @author Eraldo
 *
 */
public class AlgoritmoHungaro {
	
	/**
	 * Último passo do algoritmo
	 */
	private static final int ULTIMO_PASSO = 4;
	
	/**
	 * Número de linhas da matriz de custos.
	 */
	private int nLinhas = 0;
	
	/**
	 * Número de colunas da matriz de custos.
	 */
	private int nColunas = 0;
	
	/**
	 * Matriz de custos
	 */
	private int[][] custos;
	
	/**
	 * Matriz de custos temporaria
	 */
	private int[][] custosTemporario;
	
	/**
	 * Matriz de atribuições
	 */
	private int[][] atribuicoes;

	/**
	 * Atribuições realizadas em cada linha
	 */
	private int[] atribuicaoLinhas;
	
	/**
	 * Atribuições realizadas em cada coluna
	 */
	private int[] atribuicaoColunas;

	/**
	 * Custo final
	 */
	private int custo = 0;
	
	/**
	 * Construtor padrão.
	 * 
	 * @param pCustos Matriz com os custos.
	 */
	public AlgoritmoHungaro(int[][] pCustos) {
		custos = pCustos;
		
		nLinhas = custos.length;
		nColunas = custos[0].length;
				
		atribuicaoLinhas = Util.criarArray(nLinhas, 0);
		atribuicaoColunas = Util.criarArray(nColunas, 0);
		
		atribuicoes = new int[nLinhas][nColunas];
		
		custosTemporario = new int[nLinhas][nColunas];
		for (int l = 0; l < nLinhas; l++) {
			for (int c = 0; c < nColunas; c++) {
				custosTemporario[l][c] = custos[l][c];
			}
		}
	}	
	

	/**
	 * Executa o algoritmo.
	 */
	public void executar() {
		boolean executar = true;
		int passo = 0;
		while(executar) {
			switch (passo) {
				case 0:
					passo = passoZero();
					break;
				
				case 1:
					passo = passoUm();
					break;
				
				case 2:
					passo = passoDois();
					break;
					
				case 3:
					passo = passoTres();
					break;	
	
				default:
					executar = false;
					break;
			}
		}
		
		for (int l = 0; l < nLinhas; l++) {
			for (int c = 0; c < nColunas; c++) {
				if(atribuicoes[l][c] == 1) {
					custo += custos[l][c];
				}
			}
		}
	}
	
	/**
	 * Realiza o nivelamento da matriz através da operação:
	 * <br> 
	 * <i>c[i][j] = M - c[i][j]</i> onde <i>M</i> é o valor máximo da matriz. 
	 * 
	 * @return próximo passo.
	 */
	private int passoZero() {
		int maximo = 0;
		for (int l = 0; l < nLinhas; l++) {
			for (int c = 0; c < nColunas; c++) {
				if(custosTemporario[l][c] > maximo) {
					maximo = custosTemporario[l][c];
				}
			}
		}
		
		for (int l = 0; l < nLinhas; l++) {
			for (int c = 0; c < nColunas; c++) {
				custosTemporario[l][c] = maximo - custosTemporario[l][c];
			}
		}
		
		
		return 1;
	}

	/**
	 * Realiza a redução de linhas da matriz através da operação:
	 * <br> 
	 * <i>c[i][j] = c[i][j] - m[i]</i> onde <i>m[i]</i> é o menor valor da linha <i>i</i>. 
	 * 
	 * @return próximo passo.
	 */
	private int passoUm() {
		for (int l = 0; l < nLinhas; l++) {
			int menor = Integer.MAX_VALUE;
			
			for (int c = 0; c < nColunas; c++) {
				if(custosTemporario[l][c] < menor) {
					menor = custosTemporario[l][c];
				}
			}
			
			for (int c = 0; c < nColunas; c++) {
				custosTemporario[l][c] = custosTemporario[l][c] - menor;
			}
		}
		
		if(atribuirRelacionamento()) {
			return ULTIMO_PASSO;
		}
		
		return 2;
	}
	


	/**
	 * Realiza a redução de colunas da matriz através da operação:
	 * <br> 
	 * <i>c[i][j] = c[i][j] - m[j]</i> onde <i>m[j]</i> é o menor valor da coluna <i>j</i>. 
	 * 
	 * @return próximo passo.
	 */
	private int passoDois() {
		
		for (int c = 0; c < nColunas; c++) {
			int menor = custosTemporario[0][c];			
			for (int l = 1; l < nLinhas; l++) {
				if(custosTemporario[l][c] < menor) {
					menor = custosTemporario[l][c];
				}
			}
			
			for (int l = 0; l < nLinhas; l++) {
				custosTemporario[l][c] = custosTemporario[l][c] - menor;
			}
		}
		
		if(atribuirRelacionamento()) {
			return ULTIMO_PASSO;
		}
		
		return 3;
	}
	
	/**
	 * Realiza a alteração da matriz de custos com a verificação de linhas e colunas com zeros.
	 * 
	 *  <ol>
	 *  	<li>Tenta realizar as atribuições</li>
	 *  	<li>Calcula o número mínimo de traços para cobrir todos os zeros da matriz de custo</li>
	 * 		<li>Se o número de traços for igual ao número de linhas termina a execução</li>
	 * 		<ol>
	 * 			<li>Caso contrário subtrai o menor valor não coberto de todos os não cobertos</li>
	 * 			<li>Soma o menor valor não coberto em todos que tiverem a linha e coluna marcados</li>
	 * 			<li>Retorna 3 como o próximo passo</li>
	 * 		</ol>
	 *  </ol>
	 * 
	 * @return próximo passo.
	 */
	private int passoTres() {
		int numero = 0;
		
		int[] coberturaLinhas = Util.criarArray(nLinhas, 0);
		int[] coberturaColunas = Util.criarArray(nColunas, 0);
		
		atribuirRelacionamento();
		
		/*
		 * Marcar linhas com atribuição
		 */
		for (int l = 0; l < nLinhas; l++) {			
			coberturaLinhas[l] = 0;
			for (int c = 0; c < nColunas; c++) {
				if(atribuicoes[l][c] == 1) {
					coberturaLinhas[l] = 1;
					break;
				}
			}
		}
		
		//Marcar todas as colunas com zeros nas linhas marcadas
		for (int l = 0; l < nLinhas; l++) {
			if(coberturaLinhas[l] == 0) {
				for (int c = 0; c < nColunas; c++) {
					if(custosTemporario[l][c] == 0) {
						coberturaColunas[c] = 1;
					}
				}
			}
		}
		
		//Desmarcar todas as linhas com atribuições nas colunas marcadas anteriormente
		for (int c = 0; c < nColunas; c++) {
			if(coberturaColunas[c] == 1) {
				for (int l = 0; l < nLinhas; l++) {
					if(atribuicoes[l][c] == 1) {
						coberturaLinhas[l] = 0;
					}
				}
			}
		}
		
		//Sublinhar colunas marcadas
		for (int c = 0; c < nColunas; c++) {
			if(coberturaColunas[c] == 1) {
				numero++;
			}
		}
		
		//Sublinhar linhas marcadas
		for (int l = 0; l < nLinhas; l++) {
			if(coberturaLinhas[l] == 1) {
				numero++;
			}
		}
		
		if(numero == nLinhas) {
			return ULTIMO_PASSO;
		}
		
		
		/*
		 * Descobre o menor valor da matriz de custo não marcado
		 */
		int menorNaoCoberto = Integer.MAX_VALUE;		
		for (int l = 0; l < nLinhas; l++) {
			if(coberturaLinhas[l] == 0) {				
				for (int c = 0; c < nColunas; c++) {
					if(coberturaColunas[c] == 0) {
						if(custosTemporario[l][c] < menorNaoCoberto) {
							menorNaoCoberto = custosTemporario[l][c];
						}
					}
				}
			}			
		}
		
		/*
		 * Subtrair menorNaoCoberto de todos os não cobertos
		 * e adiciona nos que estiverem linha e coluna marcados
		 */
		for (int l = 0; l < nLinhas; l++) {
			for (int c = 0; c < nColunas; c++) {
				if(coberturaLinhas[l] == 0 && coberturaColunas[c] == 0) {
					custosTemporario[l][c] = custosTemporario[l][c] - menorNaoCoberto;
				} else
				if(coberturaLinhas[l] == 1 && coberturaColunas[c] == 1) {
					custosTemporario[l][c] = custosTemporario[l][c] + menorNaoCoberto;
				}
			}
		}
		
		//Zera cobertura de linhas
		Arrays.fill(atribuicaoLinhas, 0);
		
		//Zera cobertura de colunas
		Arrays.fill(atribuicaoColunas, 0);
		
		return 3;
	}

	/**
	 * Realiza as atribuições possíveis
	 * @return retorna verdadeiro se foram realizadas todas as atribuições e falso em caso contrário
	 */
	private boolean atribuirRelacionamento() {
		int qtdeAtribuicoes = 0;
		
		//Zerar mascara
		for (int l = 0; l < nLinhas; l++) {
			for (int c = 0; c < nColunas; c++) {
				atribuicoes[l][c] = 0;
			}
		}
		
		/*
		 * Lista quantos zeros existem em cada linha e coluna
		 */
		for (int l = 0; l < nLinhas; l++) {
			for (int c = 0; c < nColunas; c++) {
				if(custosTemporario[l][c] == 0) {
					atribuicaoLinhas[l]++;
					atribuicaoColunas[c]++;
				}
			}
		}
		
		/*
		 * Atribui nas linhas que possuem apenas 1 valor zero
		 * e zera a quantidade de zeros na linha e coluna referente a atribuição
		 */
		for (int l = 0; l < nLinhas; l++) {
			if(atribuicaoLinhas[l] == 1) {
				for (int c = 0; c < nColunas; c++) {
					if(custosTemporario[l][c] == 0 && atribuicaoColunas[c] >= 1) {
						atribuicoes[l][c] = 1;
						atribuicaoColunas[c] = 0;
						atribuicaoLinhas[l] = 0;
						qtdeAtribuicoes++;
						break;
					}
				}
			}
		}	
		
		/*
		 * Para cada coluna que sobrou é atribuido o primeiro possível
		 * e zera a quantidade de zeros na linha e coluna referente a atribuição
		 */
		for (int c = 0; c < nColunas; c++) {		
			if(atribuicaoColunas[c] >= 1) {
				for (int l = 0; l < nLinhas; l++) {
					if(custosTemporario[l][c] == 0 && atribuicaoLinhas[l] >= 1) {
						atribuicoes[l][c] = 1;
						atribuicaoColunas[c] = 0;
						atribuicaoLinhas[l] = 0;
						qtdeAtribuicoes++;
						break;
					}
				}
			}
		}
		
		return qtdeAtribuicoes == nLinhas;
	}
	
	
	/**
	 * @return the atribuicoes
	 */
	public int[][] getAtribuicoes() {
		return atribuicoes;
	}


	/**
	 * @return the custo
	 */
	public int getCusto() {
		return custo;
	}
}
