package diretorioDiff;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Guarda uma representação do diretório com os arquivos armazenados em uma lista.
 * 
 * @author Eraldo
 *
 */
public class DiretorioSerializado {

	/**
	 * Lista com os arquivos contidos no diretório.
	 * 
	 */
	private List<Arquivo> arquivos = new ArrayList<Arquivo>();
	
	/**
	 * Id do diretório.
	 */
	private int id;

	/**
	 * Diretório base
	 */
	private File diretorio;

		
	/**
	 * Construtor padrão.
	 * 
	 * @param diretorio Diretório a ser representado.
	 * @param id Id do diretório.
	 * @throws DiretorioDiffException caso o arquivo não exista ou não seja um diretório.
	 */
	public DiretorioSerializado(File diretorio, int id) throws DiretorioDiffException {
		this.diretorio = diretorio;
		if (!diretorio.exists() || !diretorio.isDirectory()) {
			throw new DiretorioDiffException("Path '" + diretorio.getAbsolutePath() + "' não indica um diretório válido.");
		}
		
		carregarArquivos(diretorio);
		this.id = id;
	}

	/**
	 * Carrega os arquivos do diretório para a lista de arquivos.
	 * 
	 * @param diretorio Diretório do qual serão lidos os arquivos.
	 */
	private void carregarArquivos(File diretorio) {
		if (!diretorio.isHidden()) {			
			File[] filhos = diretorio.listFiles();
			
			if (filhos.length > 0) {
				for (File filho : filhos) {
					if(filho.isFile()) {
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
	 * Adiciona um novo arquivo a lista do diretório
	 * 
	 * @param filho Arquivo
	 */
	private void adicionarArquivo(File filho) {
		if (filho.isHidden()) {
			return;
		}
		
		arquivos.add(new Arquivo(filho, arquivos.size() + 1, diretorio.getAbsolutePath()));
	}

	/**
	 * Getter para a lista de arquivos.
	 * 
	 * @return A lista de arquivos.
	 */
	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	/**
	 * Retorna o numero de arquivos contidos no diretório representado.
	 * 
	 * @return tamanho da lista de arquivos
	 */
	public int tamanho() {
		return arquivos.size();
	}
	
	/**
	 * Rretorna o arquivo pelo indice na lista.
	 * 
	 * @param indice Posição do arquivo na lista.
	 * 
	 * @return Retorna o arquivo ou null caso não exista arquivo com este índice.
	 */
	public Arquivo get(int indice) {
		if (indice < 0 || arquivos.size() < indice) {
			return null;
		}
		
		return arquivos.get(indice);
	}

	/**
	 * Getter para a lista de arquivos ainda não verificados.
	 * 
	 * @return A lista de arquivos.
	 */
	public List<Arquivo> getArquivosSemMatch() {
		List<Arquivo> arquivosSemMatch = new ArrayList<Arquivo>();
		
		for (Arquivo arquivo : arquivos) {
			if(!arquivo.isMatch()) {
				arquivosSemMatch.add(arquivo);
			}
		}
		
		return arquivosSemMatch;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}
