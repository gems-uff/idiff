package ddiff;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ilcs.Diff;
import ilcs.DiffException;
import ilcs.grain.FileGrain;
import ilcs.grain.Grain;
import ilcs.ILCSBean;
import ilcs.result.IResultDiff;
import ddiff.hungarianAlgorithm.HungarianAlgorithm;
import ddiff.results.Result;

/**
 * Realiza diff de diret�rios.
 * 
 * @author Eraldo
 * 
 */
public class Ddiff {

    /**
     * Diret�rio serializado 1
     */
    private DirectorySerialized dirSerializado1 = null;
    /**
     * Diret�rio serializado 2
     */
    private DirectorySerialized dirSerializado2 = null;
    private Result resultado;
    private int iteracao;
    private String granularity;    
    private String tags;

    private final ProgressMessager progressMessager;

    public Ddiff(ProgressMessager progressMessager,String granularity, String tags) {
        this.progressMessager = progressMessager;
        this.granularity = granularity;
        this.tags = tags;
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
     * @return Result da compara��o.
     */
  /*AQUI  public static Result compararDiretorios(String nomeDiretorio1,
            String nomeDiretorio2) {
        ProgressMessager defaultProgressMessager = new ProgressMessager() {

            @Override
            public void setMessage(String message) {
                System.out.println(message);
            }
        };

        return compararDiretorios(new File(nomeDiretorio1), new File(nomeDiretorio2), defaultProgressMessager);
    }*/

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
     * @return Result da compara��o.
     */
   /*AQUIpublic static Result compararDiretorios(File diretorio1, File diretorio2) {
        ProgressMessager defaultProgressMessager = new ProgressMessager() {

            @Override
            public void setMessage(String message) {
                System.out.println(message);
            }
        };
        return compararDiretorios(diretorio1, diretorio2, defaultProgressMessager);
    }*/

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
     * @return Result da compara��o.
     */
    public static Result compararDiretorios(File diretorio1, File diretorio2, ProgressMessager progressMessager,String granularity, String tags) {
        return new Ddiff(progressMessager,granularity,tags).compararDiretoriosInterno(diretorio1, diretorio2);
    }
    private Result compararDiretoriosInterno(File diretorio1, File diretorio2) {
        resultado = new Result();
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
        } catch (DDiffException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return resultado;
    }

    private void marcarArquivosInclassificaveis() {
        for (Archive arquivo : dirSerializado1.getArquivosSemMatch()) {
            if (!arquivo.isArquivo() || !arquivo.isText()) {
                resultado.addDeletado(arquivo);
                arquivo.setMatch(true);
            }
        }

        for (Archive arquivo : dirSerializado2.getArquivosSemMatch()) {
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
        List<Archive> arquivosSemMatch1 = dirSerializado1.getArquivosSemMatch();
        List<Archive> arquivosSemMatch2 = dirSerializado2.getArquivosSemMatch();

        int tamanhoHungaro = Math.max(arquivosSemMatch1.size(),
                arquivosSemMatch2.size());

        boolean[] adicionados = Util.criarArray(tamanhoHungaro, true);

        int[][] matrizILCS = new int[tamanhoHungaro][tamanhoHungaro];
        Util.atribuir(matrizILCS, 0);

        progressMessager.setMessage("Find similarity of files.");

        for (int i = 0; i < arquivosSemMatch1.size(); i++) {
            Archive base = arquivosSemMatch1.get(i);

            boolean baseExcluido = true;

            for (int j = 0; j < arquivosSemMatch2.size(); j++) {
                Archive comparado = arquivosSemMatch2.get(j);
                File fileBase = base.getArquivo();
                File fileComparado = comparado.getArquivo();

                List<Grain> grainsTo = new ArrayList<Grain>();
                List<Grain> grainsFrom = new ArrayList<Grain>();

                Grain baseGrain = new FileGrain();
                Diff diff = new Diff(fileBase, fileComparado);
                try {
                    ILCSBean iLCSBean = new ILCSBean(fileBase, fileComparado);
                    iLCSBean.setGranularity(this.granularity);
                    iLCSBean.setTags(this.tags);
                    IResultDiff compare = diff.compare(baseGrain, iLCSBean);

                    grainsTo.addAll(compare.getGrainsTo());
                    grainsFrom.addAll(compare.getGrainsFrom());

                    compare.cleanResult();
                } catch (DiffException e) {
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

                int similaridade = 0;

                if (base.getTamanhoAtual() > 0) {
                    similaridade = qtdeCharsIguais * 100 / base.getTamanhoAtual();
                }

                if (similaridade == 100 && comparado.getTamanhoAtual() > 0) {
                    similaridade = qtdeCharsIguais * 100 / comparado.getTamanhoAtual();
                }

                progressMessager.setMessage("Comparing '" + base.getArquivo().getName() + "' and '" + comparado.getArquivo().getName() + "'.");

                matrizILCS[i][j] = similaridade;

                if (similaridade > 0) {
                    adicionados[j] = false;
                    baseExcluido = false;
                    resultado.add(base, comparado, similaridade, grainsTo, grainsFrom);
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
                    Archive arquivo2 = arquivosSemMatch2.get(i);
                    arquivo2.setMatch(true);
                    resultado.addAdicionado(arquivo2);
                }
            }
        }

        progressMessager.setMessage("Searching best similarity.");

        int[][] resultadoHungaro = executaHungaro(matrizILCS);

        for (int i = 0; i < arquivosSemMatch1.size(); i++) {
            int[] linhaHungaro = resultadoHungaro[i];

            if (linhaHungaro[0] < arquivosSemMatch1.size() && linhaHungaro[1] < arquivosSemMatch2.size()) {
                Archive base = arquivosSemMatch1.get(linhaHungaro[0]);
                Archive comparado = arquivosSemMatch2.get(linhaHungaro[1]);

                resultado.setEscolhaHungaro(base, comparado);
            }
        }
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
        int novaColuna = matrizILCS[0].length;

        if (matrizILCS.length > matrizILCS[0].length) {
            novaColuna = matrizILCS.length - matrizILCS[0].length;
        }

        double[][] matrizSimilaridade = new double[matrizILCS.length][novaColuna];

        for (int i = 0; i < matrizSimilaridade.length; i++) {
            for (int j = 0; j < matrizSimilaridade[i].length; j++) {
                if (i < matrizILCS.length && j < matrizILCS[0].length) {
                    matrizSimilaridade[i][j] = matrizILCS[i][j];
                } else {
                    matrizSimilaridade[i][j] = 0;
                }
            }
        }

        return HungarianAlgorithm.hgAlgorithm(matrizSimilaridade, "max");
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
        List<Archive> arquivosSemMatch1 = dirSerializado1.getArquivosSemMatch();
        for (int i = 0; i < arquivosSemMatch1.size(); i++) {
            Archive arquivo1 = arquivosSemMatch1.get(i);

            List<Archive> arquivosSemMatch2 = dirSerializado2.getArquivosSemMatch();

            for (int j = 0; j < arquivosSemMatch2.size(); j++) {
                Archive arquivo2 = arquivosSemMatch2.get(j);

                if (comparaArquivos(arquivo1, arquivo2)) {
                    arquivo1.setMatch(true);
                    arquivo2.setMatch(true);

                    resultado.add(arquivo1, arquivo2, Result.PERCENTUAL_IDENTICO);
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
     * @throws DDiffException
     *             Erro na serializa��o de algum diret�rio
     */
    private void serializarDiretorios(File diretorio1,
            File diretorio2) throws DDiffException {
        dirSerializado1 = new DirectorySerialized(diretorio1, 1);
        dirSerializado2 = new DirectorySerialized(diretorio2, 2);
    }

    /**
     * Compara duas entidades do tipo <code>br.com.diff.Archive</code>
     * considerando o Hash MD5 de seu conte�do.<br>
     * 
     * @param arquivo1
     *            Primeiro arquivo.
     * @param arquivo2
     *            Segundo arquivo.
     * 
     * @return Verdadeiro caso sejam iguais.
     */
    private boolean comparaArquivos(Archive arquivo1, Archive arquivo2) {
        return arquivo1 != null && arquivo1.equals(arquivo2);
    }
}
