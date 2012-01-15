package ddiff;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Guarda uma representa��o do diret�rio com os arquivos armazenados em uma lista.
 * 
 * @author Eraldo
 *
 */
public class DirectorySerialized {

	/**
	 * Lista com os arquivos contidos no diret�rio.
	 * 
	 */
	private List<Archive> arquivos = new ArrayList<Archive>();
	
	/**
	 * Id do diret�rio.
	 */
	private int id;

	/**
	 * Diret�rio base
	 */
	private File diretorio;

		
	/**
	 * Construtor padr�o.
	 * 
	 * @param diretorio Diret�rio a ser representado.
	 * @param id Id do diret�rio.
	 * @throws DDiffException caso o arquivo n�o exista ou n�o seja um diret�rio.
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
	 * Carrega os arquivos do diret�rio para a lista de arquivos.
	 * 
	 * @param diretorio Diret�rio do qual ser�o lidos os arquivos.
	 */
	private void carregarArquivos(File diretorio) {
		if (!diretorio.isHidden()) {			
			File[] filhos = diretorio.listFiles();
			
			if (filhos != null && filhos.length > 0) {
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
	 * Adiciona um novo arquivo a lista do diret�rio
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
	 * Getter para a lista de arquivos.
	 * 
	 * @return A lista de arquivos.
	 */
	public List<Archive> getArquivos() {
		return arquivos;
	}

	/**
	 * Retorna o numero de arquivos contidos no diret�rio representado.
	 * 
	 * @return tamanho da lista de arquivos
	 */
	public int tamanho() {
		return arquivos.size();
	}
	
	/**
	 * Rretorna o arquivo pelo indice na lista.
	 * 
	 * @param indice Posi��o do arquivo na lista.
	 * 
	 * @return Retorna o arquivo ou null caso n�o exista arquivo com este �ndice.
	 */
	public Archive get(int indice) {
		if (indice < 0 || arquivos.size() < indice) {
			return null;
		}
		
		return arquivos.get(indice);
	}

	/**
	 * Getter para a lista de arquivos ainda n�o verificados.
	 * 
	 * @return A lista de arquivos.
	 */
	public List<Archive> getArquivosSemMatch() {
		List<Archive> arquivosSemMatch = new ArrayList<Archive>();
		
		for (Archive arquivo : arquivos) {
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
