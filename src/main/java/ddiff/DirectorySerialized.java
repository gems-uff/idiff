package ddiff;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Guarda uma representacao do diretorio com os arquivos armazenados em uma lista.
 * 
 * @author Eraldo
 *
 */
public class DirectorySerialized {

    /**
     * Lista com os arquivos contidos no diretorio.
     * 
     */
    private List<Archive> arquivos = new ArrayList<Archive>();
    /**
     * Id do diretorio.
     */
    private int id;
    /**
     * Diretario base
     */
    private File diretorio;

    /**
     * Construtor padrao.
     * 
     * @param diretorio Diretorio a ser representado.
     * @param id Id do diretorio.
     * @throws DDiffException caso o arquivo nao exista ou nao seja um diretorio.
     */
    public DirectorySerialized(File diretorio, int id) throws DDiffException {
        this.diretorio = diretorio;
        if (Util.isNotValidDirectory(diretorio)) {
            throw new DDiffException("Invalid directory '" + diretorio.getAbsolutePath() + "'.");
        }

        carregarArquivos(diretorio);
        this.id = id;
    }

    /**
     * Carrega os arquivos do diretorio para a lista de arquivos.
     * 
     * @param diretorio Diretorio do qual serao lidos os arquivos.
     */
    private void carregarArquivos(File diretorio) {
        if (!diretorio.isHidden()) {
            File[] filhos = diretorio.listFiles();

            if (filhos != null && filhos.length > 0) {
                for (File filho : filhos) {
                    if (filho.isFile()) {
                        adicionarArquivo(filho);
                    } else {
                        carregarArquivos(filho);
                    }
                }
            } else {
                adicionarArquivo(diretorio);
            }
        }
    }

    /**
     * Adiciona um novo arquivo a lista do diretorio
     * 
     * @param filho Archive
     */
    private void adicionarArquivo(File filho) {
        if (filho.isHidden()) {
            return;
        }

        arquivos.add(new Archive(filho, arquivos.size() + 1, diretorio.getAbsolutePath()));
    }

    /**
     * Getter para a lista de arquivos ainda nao verificados.
     * 
     * @return A lista de arquivos.
     */
    public List<Archive> getArquivosSemMatch() {
        List<Archive> arquivosSemMatch = new ArrayList<Archive>();

        for (Archive arquivo : arquivos) {
            if (!arquivo.isMatch()) {
                arquivosSemMatch.add(arquivo);
            }
        }

        return arquivosSemMatch;
    }
}
