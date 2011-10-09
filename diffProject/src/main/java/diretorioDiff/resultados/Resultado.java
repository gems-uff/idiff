package diretorioDiff.resultados;

import java.util.ArrayList;
import java.util.List;

import diretorioDiff.Arquivo;


/**
 * Representa o resultado de comparação entre dois diretórios
 * 
 * @author Eraldo
 *
 */
public class Resultado {

	/**
	 * Valor correspondente ao percentual idéntico.
	 */
	public static final int PERCENTUAL_IDENTICO = 100;
	
	/**
	 * Resultados para arquivos.
	 */
	private List<ResultadoArquivo> resultadosArquivo =  new ArrayList<ResultadoArquivo>();

	/**
	 * Adiciona um resultado na lista de resultados para Arquivos.
	 * 
	 * @param resultadoArquivo Resultado
	 */
	private void add(ResultadoArquivo resultadoArquivo) {
		resultadosArquivo.add(resultadoArquivo);
	}	
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		for (ResultadoArquivo resultadoArquivo : resultadosArquivo) {
			sb.append(resultadoArquivo);
			sb.append("\n");
			sb.append("\n");
		}
		
		return sb.toString();
	}

	/**
	 * Adiciona um resultado do tipo DELETADO.
	 * 
	 * @param deletado Arquivo que foi removido do diretório base.
	 */
	public void addDeletado(Arquivo deletado) {
		add(new ResultadoArquivo(deletado, TipoResultado.DELETADO));
		
	}

	/**
	 * Adiciona um resultado do tipo ADICIONADO.
	 * 
	 * @param adicionado Arquivo que foi adicionado no diretório comparado.
	 */
	public void addAdicionado(Arquivo adicionado) {
		add(new ResultadoArquivo(adicionado, TipoResultado.ADICIONADO));
	}

	/**
	 * Adiciona um resultado que pode ser do tipo INALTERADO, EDITADO ou MOVIDO.
	 * @param base Arquivo base da comparação.
	 * @param comparado Arquivo comparado.
	 * @param similaridade Grau de similaridade entre os arquivos.
	 */
	public void add(Arquivo base, Arquivo comparado, int similaridade) {
		add(new ResultadoArquivo(base, comparado, similaridade));		
	}

	/**
	 * Marca um resultado como escolha do Algoritmo Húngaro.
	 *  
	 * @param base Arquivo base da comparação.
	 * @param comparado Arquivo comparado.
	 */
	public void setEscolhaHungaro(Arquivo base, Arquivo comparado) {
		for (ResultadoArquivo resultado : resultadosArquivo) {
			if (resultado.getBase().getId() == base.getId() 
					&& resultado.getPara().getId() == comparado.getId()) {
				resultado.setEscolhaHungaro(true);
				break;
			}
		}
	}

	/**
	 * @return the resultadosArquivo
	 */
	public List<ResultadoArquivo> getResultadosArquivo() {
		return resultadosArquivo;
	}
}
