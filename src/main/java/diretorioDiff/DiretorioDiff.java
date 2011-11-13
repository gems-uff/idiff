package diretorioDiff;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import algorithms.Diff;
import algorithms.DiffException;
import algorithms.FileGrain;
import algorithms.Grain;
import algorithms.ILCSBean;
import algorithms.IResultDiff;
import diretorioDiff.resultados.Resultado;

/**
 * Realiza diff de diret�rios.
 * 
 * @author Eraldo
 * 
 */
public class DiretorioDiff {

	/**
	 * Diret�rio serializado 1
	 */
	private DiretorioSerializado dirSerializado1 = null;

	/**
	 * Diret�rio serializado 2
	 */
	private DiretorioSerializado dirSerializado2 = null;

	private Resultado resultado;

	private int iteracao;

	private final ProgressMessager progressMessager;


	public DiretorioDiff(ProgressMessager progressMessager) {
		this.progressMessager = progressMessager;
	}

	/**
	 * Realiza a compara��o entre dois diret�rios. <br>
	 * Segue os seguintes passos: <br>
	 * <ol>
	 * 	<li>Organiza os arquivos contidos nos dois diret�rios como uma lista
	 * 		(incluindo os filhos recursivamente).
	 * 	</li>
	 * 	<li>
	 * 		Gera um c�digo hash MD5 com o conte�do de cada arquivo.</li>
	 * 	<li>
	 * 		Compara os hashs de cada um dos arquivos de cada diret�rio e marca os
	 * 		que s�o identicos com o valor 100 em uma matriz de equival�ncia
	 * 	</li>
	 * 	<li>
	 * 		Monta uma matriz com o percentual de igualdade calculado pelo LCS
	 * 		entre os arquivos que n�o se mostraram id�nticos no passo anterior.
	 * 	</li>
	 * 	<li>
	 * 		Calcula atrav�s do algoritmo H�ngaro quais s�o os matches mais
	 * 		pr�ximos entre os itens da matriz anterior
	 * 	</li>
	 * 	<li>
	 * 		Devolve os valores para a matriz de equival�ncia
	 * 	</li>
	 * 	<li>
	 * 		Analisa a matriz de equival�ncia e de acordo com ela popula a classe Delta
	 * 	</li>
	 * </ol>
	 * 
	 * @param nomeDiretorio1 Nome do diret�rio 1
	 * @param nomeDiretorio2 Nome do diret�tio 2
	 * 
	 * @return Resultado da compara��o.
	 */
	public static Resultado compararDiretorios(String nomeDiretorio1,
			String nomeDiretorio2) {
		ProgressMessager defaultProgressMessager = new ProgressMessager() {
			
			@Override
			public void setMessage(String message) {
				System.out.println(message);
			}
		};
		
		return compararDiretorios(new File(nomeDiretorio1), new File(nomeDiretorio2), defaultProgressMessager);
	}
	
	/**
	 * Realiza a compara��o entre dois diret�rios. <br>
	 * Segue os seguintes passos: <br>
	 * <ol>
	 * 	<li>Organiza os arquivos contidos nos dois diret�rios como uma lista
	 * 		(incluindo os filhos recursivamente).
	 * 	</li>
	 * 	<li>
	 * 		Gera um c�digo hash MD5 com o conte�do de cada arquivo.</li>
	 * 	<li>
	 * 		Compara os hashs de cada um dos arquivos de cada diret�rio e marca os
	 * 		que s�o identicos com o valor 100 em uma matriz de equival�ncia
	 * 	</li>
	 * 	<li>
	 * 		Monta uma matriz com o percentual de igualdade calculado pelo LCS
	 * 		entre os arquivos que n�o se mostraram id�nticos no passo anterior.
	 * 	</li>
	 * 	<li>
	 * 		Calcula atrav�s do algoritmo H�ngaro quais s�o os matches mais
	 * 		pr�ximos entre os itens da matriz anterior
	 * 	</li>
	 * 	<li>
	 * 		Devolve os valores para a matriz de equival�ncia
	 * 	</li>
	 * 	<li>
	 * 		Analisa a matriz de equival�ncia e de acordo com ela popula a classe Delta
	 * 	</li>
	 * </ol>
	 * 
	 * @param diretorio1
	 *            - Diret�rio 1
	 * @param diretorio2
	 *            - Diret�rio 2
	 * @param progressMessager Messager of progress
	 * 
	 * @return Resultado da compara��o.
	 */
	public static Resultado compararDiretorios(File diretorio1, File diretorio2, ProgressMessager progressMessager) {
		return new DiretorioDiff(progressMessager).compararDiretoriosInterno(diretorio1, diretorio2);
	}
	
	private Resultado compararDiretoriosInterno(File diretorio1, File diretorio2) {
		resultado = new Resultado();
		iteracao = 0;
		
		try {
			progressMessager.setMessage("Loading directories.");
			
			serializarDiretorios(diretorio1, diretorio2);

			progressMessager.setMessage("Searching same files.");
			
			buscaArquivosIdenticos();
			
			progressMessager.setMessage("Searching deleted and added files.");
			
			marcarArquivosInclassificaveis();

			while (canProcurarMatch()) {
				iteracao++;
				iteracaoHungaroILCS();
				break;
			}
		} catch (DiretorioDiffException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		return resultado;
	}

	private void marcarArquivosInclassificaveis() {
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
	 * Ap�s isso entrega o resultado para o Hung�ro que escolhe o melhor
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

		progressMessager.setMessage("Find similarity of files.");
		
		for (int i = 0; i < arquivosSemMatch1.size(); i++) {
			Arquivo base = arquivosSemMatch1.get(i);

			boolean baseExcluido = true;

			for (int j = 0; j < arquivosSemMatch2.size(); j++) {
				Arquivo comparado = arquivosSemMatch2.get(j);

				progressMessager.setMessage("Comparing '" + base.getArquivo().getName() + "' and '" + comparado.getArquivo().getName() + "'.");
				
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
			for (int i = 0; i < arquivosSemMatch2.size(); i++) {
                                if (adicionados[i]) {
					Arquivo arquivo2 = arquivosSemMatch2.get(i);
					arquivo2.setMatch(true);
					resultado.addAdicionado(arquivo2);
				}
			}
		}

		progressMessager.setMessage("Searching best similarity.");
		
		int[][] resultadoHungaro = executaHungaro(matrizILCS);
		
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
	 * Calcula a similaridade entre dois arquivos atrav�s do ILCS
	 * 
	 * @param base
	 *            Arquivo base da compara��o
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

		int similaridade = qtdeCharsIguais * 100 / base
                         .getTamanhoAtual();

		if (similaridade == 100) {
			similaridade = qtdeCharsIguais * 100 / comparado
                                 .getTamanhoAtual();
		}

		return similaridade;
	}

	private boolean isPrimeiraIteracao() {
		return iteracao == 1;
	}

	/**
	 * Calcula as melhores escolhas de match entre os arquivo atrav�s do
	 * Algoritmo H�ngaro.<br>
	 * 
	 * @param matrizILCS
	 *            Matriz base com os percentuais de similaridade entre os
	 *            arquivos.
	 * 
	 * @return Matriz com o resultado do Algoritmo H�ngaro
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
	 * Procura por arquivos id�nticos atrav�s da compara��o de HASH MD5.<br>
	 * Se os arquivos tiverem o mesmo path marca como "INALTERADO" caso
	 * contr�rio marca como movimentado.<br>
	 * Al�m disso, marca os arquivos id�nticos com match verdadeiro para que
	 * sejam excluidos da pr�xima execu��o.
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
	 * Serializa os diret�rios que ser�o comparados.
	 * 
	 * @param diretorio1
	 *            Diret�rio da esquerda
	 * @param diretorio2
	 *            Diret�rio da direita
	 * @throws DiretorioDiffException
	 *             Erro na serializa��o de algum diret�rio
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
	 * considerando o Hash MD5 de seu conte�do.<br>
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
