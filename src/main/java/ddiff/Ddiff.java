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
import idiff.resources.Constants;
import java.util.Collection;
import java.util.Map;

/**
 * Realiza diff de diretorios.
 * 
 * @author Eraldo
 * 
 */
public class Ddiff {

    /**
     * Diretorio serializado 1
     */
    private DirectorySerialized dirSerializado1 = null;
    /**
     * Diretorio serializado 2
     */
    private DirectorySerialized dirSerializado2 = null;
    private Result resultado = null;
    private int iteracao;
    private String granularity;
    private String tags;
    private final ProgressMessager progressMessager;

    public Ddiff(ProgressMessager progressMessager, String granularity, String tags) {
        this.progressMessager = progressMessager;
        this.granularity = granularity;
        this.tags = tags;
    }

    /**
     * Realiza a comparacao entre dois diretorios. <br>
     * Segue os seguintes passos: <br>
     * <ol>
     * 	<li>Organiza os arquivos contidos nos dois diretorios como uma lista
     * 		(incluindo os filhos recursivamente).
     * 	</li>
     * 	<li>
     * 		Gera um codigo hash MD5 com o conteudo de cada arquivo.</li>
     * 	<li>
     * 		Compara os hashs de cada um dos arquivos de cada diretorio e marca os
     * 		que sao identicos com o valor 100 em uma matriz de equivalencia
     * 	</li>
     * 	<li>
     * 		Monta uma matriz com o percentual de igualdade calculado pelo LCS
     * 		entre os arquivos que nao se mostraram identicos no passo anterior.
     * 	</li>
     * 	<li>
     * 		Calcula atraves do algoritmo Hungaro quais sao os matches mais
     * 		proximos entre os itens da matriz anterior
     * 	</li>
     * 	<li>
     * 		Devolve os valores para a matriz de equivalencia
     * 	</li>
     * 	<li>
     * 		Analisa a matriz de equivalencia e de acordo com ela popula a classe Delta
     * 	</li>
     * </ol>
     * 
     * @param diretorio1
     *            - Diretorio 1
     * @param diretorio2
     *            - Diretorio 2
     * @param progressMessager 
     * @param granularity 
     * @param tags 
     * @param threshold 
     * @return Result da comparacao.
     */
    public static Result compararDiretorios(File diretorio1, File diretorio2, ProgressMessager progressMessager, String granularity, String tags, int threshold) {
        return new Ddiff(progressMessager, granularity, tags).compararDiretoriosInterno(diretorio1, diretorio2);
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
        for (Archive arquivo : dirSerializado1.getArquivosSemMatch().values()) {
            if (!arquivo.isArquivo() || !arquivo.isText()) {
                resultado.addDeletado(arquivo);
                arquivo.setMatch(true);
            }
        }

        for (Archive arquivo : dirSerializado2.getArquivosSemMatch().values()) {
            if (!arquivo.isArquivo() || !arquivo.isText()) {
                resultado.addAdicionado(arquivo);
                arquivo.setMatch(true);
            }
        }
    }

    /**
     * Calcula a similaridade entre todos os arquivos fazendo uso do ILCS em
     * granularidade <code>LINHA</code>.<br>
     * Apos isso entrega o resultado para o Hungaro que escolhe o melhor
     * conjunto de pares.
     */
    private void iteracaoHungaroILCS() {
        List<Archive> arquivosSemMatch1 = dirSerializado1.getArquivosSemMatchToList();
        List<Archive> arquivosSemMatch2 = dirSerializado2.getArquivosSemMatchToList();

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
                    iLCSBean.setGranularity(setGrainName(this.granularity));
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
                int tamanhoTotal = base.getTamanhoAtual() + comparado.getTamanhoAtual();
                if (tamanhoTotal > 0) {
                    similaridade = qtdeCharsIguais * 200 / tamanhoTotal;
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
     * Calcula as melhores escolhas de match entre os arquivo atraves do
     * Algoritmo Hungaro.<br>
     * 
     * @param matrizILCS
     *            Matriz base com os percentuais de similaridade entre os
     *            arquivos.
     * 
     * @return Matriz com o resultado do Algoritmo Hungaro
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
    return (hasFilesForMatch(dirSerializado1)||hasFilesForMatch(dirSerializado2));
    }

    private boolean hasFilesForMatch(DirectorySerialized dir) {
        return !dir.getArquivosSemMatch().isEmpty();
    }
    private String setGrainName(String granularity) {
        if ("Word (Default)".equals(granularity)) {
            return "Word";
        }
        return granularity;
    }

    /**
     * Procura por arquivos identicos atraves da comparacao de HASH MD5.<br>
     * Se os arquivos tiverem o mesmo path marca como "INALTERADO" caso
     * contrario marca como movimentado.<br>
     * Alem disso, marca os arquivos identicos com match verdadeiro para que
     * sejam excluidos da proxima execucao.
     */
    private void buscaArquivosIdenticos() {
        Collection<Archive> arquivosSemMatch1 = dirSerializado1.getArquivosSemMatch().values();
        
        for (Archive arquivo1 : arquivosSemMatch1) {
            Map<String, Archive> arquivosSemMatch2 = dirSerializado2.getArquivosSemMatch();
            
            if (arquivosSemMatch2.containsKey(arquivo1.getHash())) {
                Archive arquivo2 = arquivosSemMatch2.get(arquivo1.getHash());
                
                arquivo1.setMatch(true);
                arquivo2.setMatch(true);

                resultado.add(arquivo1, arquivo2, Constants.PERCENTUAL_IDENTICO);
            }
        }
    }
    /**
     * Serializa os diretorios que serao comparados.
     * 
     * @param diretorio1
     *            Diretorio da esquerda
     * @param diretorio2
     *            Diretorio da direita
     * @throws DDiffException
     *             Erro na serializacao de algum diretorio
     */
    private void serializarDiretorios(File diretorio1,
            File diretorio2) throws DDiffException {
        dirSerializado1 = new DirectorySerialized(diretorio1, 1);
        dirSerializado2 = new DirectorySerialized(diretorio2, 2);
    }
}
