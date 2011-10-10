package diretorioDiff;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Guarda uma representa��o do diret�rio com os arquivos armazenados em uma lista.
 * 
 * @author Eraldo
 *
 */
public class DiretorioSerializado {

	/**
	 * Lista com os arquivos contidos no diret�rio.
	 * 
	 */
	private List<Arquivo> arquivos = new ArrayList<Arquivo>();
	
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
	 * @throws DiretorioDiffException caso o arquivo n�o exista ou n�o seja um diret�rio.
	 */
	public DiretorioSerializado(File diretorio, int id) throws DiretorioDiffException {
		this.diretorio = diretorio;
		if (!diretorio.exists() || !diretorio.isDirectory()) {
			throw new DiretorioDiffException("Path '" + diretorio.getAbsolutePath() + "' n�o indica um diret�rio v�lido.");
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
		File[] filhos = diretorio.listFiles();
		for (File filho : filhos) {
                    if (!filho.isHidden()) {
			if(filho.isFile()) {
				adicionarArquivo(filho);
			} else {
				carregarArquivos(filho);
			}
                    }
		}
	}

	/**
	 * Adiciona um novo arquivo a lista do diret�rio
	 * 
	 * @param filho Arquivo
	 */
	private void adicionarArquivo(File filho) {
		Arquivo arquivo = new Arquivo(filho, arquivos.size() + 1, diretorio.getAbsolutePath());
		arquivos.add(arquivo);
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
	public Arquivo get(int indice) {
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
