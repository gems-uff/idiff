package diretorioDiff;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import diretorioDiff.resultados.Resultado;


import algorithms.Diff;
import algorithms.DiffException;
import algorithms.FileGrain;
import algorithms.Grain;
import algorithms.ILCSBean;
import algorithms.IResultDiff;

/**
 * Realiza diff de diretórios.
 * 
 * @author Eraldo
 * 
 */
public class DiretorioDiff {

	/**
	 * Diretório serializado 1
	 */
	private DiretorioSerializado dirSerializado1 = null;

	/**
	 * Diretório serializado 2
	 */
	private DiretorioSerializado dirSerializado2 = null;

	private Resultado resultado;

	private int iteracao;


	/**
	 * Realiza a comparação entre dois diretórios. <br>
	 * Segue os seguintes passos: <br>
	 * <ol>
	 * 	<li>Organiza os arquivos contidos nos dois diretórios como uma lista
	 * 		(incluindo os filhos recursivamente).
	 * 	</li>
	 * 	<li>
	 * 		Gera um código hash MD5 com o conteúdo de cada arquivo.</li>
	 * 	<li>
	 * 		Compara os hashs de cada um dos arquivos de cada diretório e marca os
	 * 		que são identicos com o valor 100 em uma matriz de equivalência
	 * 	</li>
	 * 	<li>
	 * 		Monta uma matriz com o percentual de igualdade calculado pelo LCS
	 * 		entre os arquivos que não se mostraram idênticos no passo anterior.
	 * 	</li>
	 * 	<li>
	 * 		Calcula através do algoritmo Húngaro quais são os matches mais
	 * 		próximos entre os itens da matriz anterior
	 * 	</li>
	 * 	<li>
	 * 		Devolve os valores para a matriz de equivalência
	 * 	</li>
	 * 	<li>
	 * 		Analisa a matriz de equivalência e de acordo com ela popula a classe Delta
	 * 	</li>
	 * </ol>
	 * 
	 * @param nomeDiretorio1 Nome do diretório 1
	 * @param nomeDiretorio2 Nome do diretótio 2
	 * 
	 * @return Resultado da comparação.
	 */
	public Resultado compararDiretorios(String nomeDiretorio1,
			String nomeDiretorio2) {
		return compararDiretorios(new File(nomeDiretorio1), new File(nomeDiretorio2));
	}
	
	/**
	 * Realiza a comparação entre dois diretórios. <br>
	 * Segue os seguintes passos: <br>
	 * <ol>
	 * 	<li>Organiza os arquivos contidos nos dois diretórios como uma lista
	 * 		(incluindo os filhos recursivamente).
	 * 	</li>
	 * 	<li>
	 * 		Gera um código hash MD5 com o conteúdo de cada arquivo.</li>
	 * 	<li>
	 * 		Compara os hashs de cada um dos arquivos de cada diretório e marca os
	 * 		que são identicos com o valor 100 em uma matriz de equivalência
	 * 	</li>
	 * 	<li>
	 * 		Monta uma matriz com o percentual de igualdade calculado pelo LCS
	 * 		entre os arquivos que não se mostraram idênticos no passo anterior.
	 * 	</li>
	 * 	<li>
	 * 		Calcula através do algoritmo Húngaro quais são os matches mais
	 * 		próximos entre os itens da matriz anterior
	 * 	</li>
	 * 	<li>
	 * 		Devolve os valores para a matriz de equivalência
	 * 	</li>
	 * 	<li>
	 * 		Analisa a matriz de equivalência e de acordo com ela popula a classe Delta
	 * 	</li>
	 * </ol>
	 * 
	 * @param diretorio1
	 *            - Diretório 1
	 * @param diretorio2
	 *            - Diretório 2
	 * 
	 * @return Resultado da comparação.
	 */
	public Resultado compararDiretorios(File diretorio1, File diretorio2) {

		resultado = new Resultado();
		iteracao = 0;

		try {
			serializarDiretorios(diretorio1, diretorio2);

			buscaArquivosIdenticos();
			
			removerArquivosInclassificaveis();

			while (canProcurarMatch()) {
				iteracao++;
				iteracaoHungaroILCS();
				break;
			}
		} catch (DiretorioDiffException e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}

		return resultado;
	}

	private void removerArquivosInclassificaveis() {
		for (Arquivo arquivo : dirSerializado1.getArquivosSemMatch()) {
			if (!arquivo.isArquivo() || !arquivo.isText()) {
				resultado.addDeletado(arquivo);
				arquivo.setMatch(true);
			}
		}
		
		for (Arquivo arquivo : dirSerializado2.getArquivosSemMatch()) {
			if (!arquivo.isArquivo() || !arquivo.isText()) {
				resultado.addAdicionado(arquivo);
				arquivo.setMatch(true);
			}
		}		
	}

	/**
	 * Calcula a similaridade entre todos os arquivos fazendo uso do ILCS em
	 * granularidade <code>LINHA</code>.<br>
	 * Após isso entrega o resultado para o Hungáro que escolhe o melhor
	 * conjunto de pares.
	 */
	private void iteracaoHungaroILCS() {
		List<Arquivo> arquivosSemMatch1 = dirSerializado1.getArquivosSemMatch();
		List<Arquivo> arquivosSemMatch2 = dirSerializado2.getArquivosSemMatch();

		int tamanhoHungaro = Math.max(arquivosSemMatch1.size(),
				arquivosSemMatch2.size());

		boolean[] adicionados = Util.criarArray(tamanhoHungaro, true);

		int[][] matrizILCS = new int[tamanhoHungaro][tamanhoHungaro];
		Util.atribuir(matrizILCS, 0);

		for (int i = 0; i < arquivosSemMatch1.size(); i++) {
			Arquivo base = arquivosSemMatch1.get(i);

			boolean baseExcluido = true;

			for (int j = 0; j < arquivosSemMatch2.size(); j++) {
				Arquivo comparado = arquivosSemMatch2.get(j);

				int similaridade = calculaSimilaridade(base, comparado);

				matrizILCS[i][j] = similaridade;

				if (similaridade > 0) {
					adicionados[j] = false;
					baseExcluido = false;
					resultado.add(base, comparado, similaridade);
				}
			}

			if (baseExcluido) {
				base.setMatch(true);
				resultado.addDeletado(base);
			}
		}

		// Verifica quais arquivos foram adicionados
		if (isPrimeiraIteracao()) {
			for (int i = 0; i < adicionados.length; i++) {
				if (adicionados[i]) {
					Arquivo arquivo2 = arquivosSemMatch2.get(i);
					arquivo2.setMatch(true);
					resultado.addAdicionado(arquivo2);
				}
			}
		}

		System.out.println("MATRIZ[matrizILCS] antes");
		escreverMatriz(matrizILCS);
		System.out.println();

		int[][] resultadoHungaro = executaHungaro(matrizILCS);

		System.out.println("MATRIZ[resultadoHungaro]");
		escreverMatriz(resultadoHungaro);
		System.out.println();

		
		for (int i = 0; i < arquivosSemMatch1.size(); i++) {
			int[] linhaHungaro = resultadoHungaro[i];
			Arquivo base = arquivosSemMatch1.get(i);

			for (int j = 0; j < arquivosSemMatch2.size(); j++) {
				Arquivo comparado = arquivosSemMatch2.get(j);
				
				if (linhaHungaro[j] == 1 && matrizILCS[i][j] > 0) {
					resultado.setEscolhaHungaro(base, comparado);
					break;
				}
			}
		}
	}

	/**
	 * Calcula a similaridade entre dois arquivos através do ILCS
	 * 
	 * @param base
	 *            Arquivo base da comparação
	 * @param comparado
	 *            Arquivo a ser comparado com o arquivo base.
	 * 
	 * @return O percentual de similaridade valor entre 0 e 100.
	 */
	private int calculaSimilaridade(Arquivo base, Arquivo comparado) {
		File fileBase = base.getArquivo();
		File fileComparado = comparado.getArquivo();

		List<Grain> grainsTo = new ArrayList<Grain>();

		Grain baseGrain = new FileGrain();
		Diff diff = new Diff(fileBase, fileComparado);
		try {
			ILCSBean iLCSBean = new ILCSBean(fileBase, fileComparado);
			iLCSBean.setGranularity("LINE");
			IResultDiff compare = diff.compare(baseGrain, iLCSBean);

			grainsTo.addAll(compare.getGrainsTo());

			compare.cleanResult();
		} catch (DiffException e) {
			e.printStackTrace();
		}

		int qtdeCharsIguais = 0;
		for (Grain grain : grainsTo) {
			String line = grain.getGrain();

			for (int i1 = 0; i1 < line.length(); i1++) {
				if (line.charAt(i1) != ' ') {
					qtdeCharsIguais++;
				}
			}
		}

		int similaridade = (int) (qtdeCharsIguais * 100 / base
				.getTamanhoAtual());

		if (similaridade == 100) {
			similaridade = (int) (qtdeCharsIguais * 100 / comparado
					.getTamanhoAtual());
		}

		return similaridade;
	}

	private boolean isPrimeiraIteracao() {
		return iteracao == 1;
	}

	/**
	 * Calcula as melhores escolhas de match entre os arquivo através do
	 * Algoritmo Húngaro.<br>
	 * 
	 * @param matrizILCS
	 *            Matriz base com os percentuais de similaridade entre os
	 *            arquivos.
	 * 
	 * @return Matriz com o resultado do Algoritmo Húngaro
	 */
	private int[][] executaHungaro(int[][] matrizILCS) {
		AlgoritmoHungaro alHungaro = new AlgoritmoHungaro(matrizILCS);
		alHungaro.executar();
		int[][] resultadoHungaro = alHungaro.getAtribuicoes();
		return resultadoHungaro;
	}

	/**
	 * Verifica a possibilidade de continuar procurando matches entre os
	 * arquivos.
	 * 
	 * @return <code>true</code> ou <code>false</code>.
	 */
	private boolean canProcurarMatch() {
		if (dirSerializado1.getArquivosSemMatch().isEmpty()) {
			return false;
		}

		if (dirSerializado2.getArquivosSemMatch().isEmpty()) {
			return false;
		}

		return true;
	}

	/**
	 * Procura por arquivos idénticos através da comparação de HASH MD5.<br>
	 * Se os arquivos tiverem o mesmo path marca como "INALTERADO" caso
	 * contrário marca como movimentado.<br>
	 * Além disso, marca os arquivos idénticos com match verdadeiro para que
	 * sejam excluidos da próxima execução.
	 */
	private void buscaArquivosIdenticos() {
		List<Arquivo> arquivosSemMatch1 = dirSerializado1.getArquivosSemMatch();
		for (int i = 0; i < arquivosSemMatch1.size(); i++) {
			Arquivo arquivo1 = arquivosSemMatch1.get(i);

			List<Arquivo> arquivosSemMatch2 = dirSerializado2
					.getArquivosSemMatch();

			for (int j = 0; j < arquivosSemMatch2.size(); j++) {
				Arquivo arquivo2 = arquivosSemMatch2.get(j);

				if (comparaArquivos(arquivo1, arquivo2)) {
					arquivo1.setMatch(true);
					arquivo2.setMatch(true);

					resultado.add(arquivo1, arquivo2, Resultado.PERCENTUAL_IDENTICO);
				}
			}
		}
	}

	/**
	 * Serializa os diretórios que serão comparados.
	 * 
	 * @param diretorio1
	 *            Diretório da esquerda
	 * @param diretorio2
	 *            Diretório da direita
	 * @throws DiretorioDiffException
	 *             Erro na serialização de algum diretório
	 */
	private void serializarDiretorios(File diretorio1,
			File diretorio2) throws DiretorioDiffException {
		dirSerializado1 = new DiretorioSerializado(diretorio1, 1);
		dirSerializado2 = new DiretorioSerializado(diretorio2, 2);
	}

	/**
	 * @param matriz
	 */
	private void escreverMatriz(int[][] matriz) {
		for (int[] linha : matriz) {
			for (int valor : linha) {

				String valorStr = Integer.toString(valor);
				while (valorStr.length() < 3) {
					valorStr = "_" + valorStr;
				}

				System.out.print(" | " + valorStr);
			}
			System.out.println();
		}
	}

	/**
	 * Compara duas entidades do tipo <code>br.com.diff.Arquivo</code>
	 * considerando o Hash MD5 de seu conteúdo.<br>
	 * 
	 * @param arquivo1
	 *            Primeiro arquivo.
	 * @param arquivo2
	 *            Segundo arquivo.
	 * 
	 * @return Verdadeiro caso sejam iguais.
	 */
	private boolean comparaArquivos(Arquivo arquivo1, Arquivo arquivo2) {
		return arquivo1 != null && arquivo1.equals(arquivo2);
	}
}
