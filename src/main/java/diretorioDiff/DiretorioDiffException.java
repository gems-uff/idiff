package diretorioDiff;

/**
 * Exceções lançadas pelo processso de Diff em diretórios
 * 
 * @author Eraldo
 *
 */
public class DiretorioDiffException extends Exception {

	public DiretorioDiffException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Serialização
	 */
	private static final long serialVersionUID = -115586961436156258L;

}
