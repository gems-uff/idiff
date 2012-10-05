package ddiff;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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
    private Map<String, Archive> arquivos = new HashMap<String, Archive>();
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
        Archive archive = new Archive(filho, arquivos.size() + 1, diretorio.getAbsolutePath());

        arquivos.put(archive.getHash(), archive);
    }

    /**
     * Getter para o mapa de arquivos ainda nao verificados.
     * 
     * @return O mapa de arquivos.
     */
    public Map<String, Archive> getArquivosSemMatch() {
        Map<String, Archive> arquivosSemMatch = new HashMap<String, Archive>();

        for (Archive arquivo : arquivos.values()) {
            if (!arquivo.isMatch()) {
                arquivosSemMatch.put(arquivo.getHash(), arquivo);
            }
        }

        return arquivosSemMatch;
    }
    
    /**
     * Getter para a lista de arquivos ainda nao verificados.
     * 
     * @return A lista de arquivos.
     */
    public List<Archive> getArquivosSemMatchToList() {
        return new ArrayList<Archive>(getArquivosSemMatch().values());
    }
}
