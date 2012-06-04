package ddiff.results;

import java.util.ArrayList;
import java.util.List;

import ilcs.grain.Grain;

import ddiff.Archive;

/**
 * Representa o resultado de comparacao entre dois diretorios
 * 
 * @author Eraldo
 *
 */
public class Result {

    /**
     * Resultados para arquivos.
     */
    private List<ResultArchive> resultadosArquivo = new ArrayList<ResultArchive>();

    /**
     * 
     */
    //private List<Grain> paramGrainsTo = new ArrayList<Grain>();
    //private List<Grain> paramGrainsFrom = new ArrayList<Grain>();
    /**
     * Adiciona um resultado na lista de resultados para Arquivos.
     * 
     * @param resultadoArquivo Result
     */
    private void add(ResultArchive resultadoArquivo) {
        resultadosArquivo.add(resultadoArquivo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (ResultArchive resultadoArquivo : resultadosArquivo) {
            sb.append(resultadoArquivo);
            sb.append("\n");
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Adiciona um resultado do tipo DELETADO.
     * 
     * @param deletado Archive que foi removido do diret�rio base.
     */
    public void addDeletado(Archive deletado) {
        add(new ResultArchive(deletado, TypeResult.REMOVED));

    }

    /**
     * Adiciona um resultado do tipo ADICIONADO.
     * 
     * @param adicionado Archive que foi adicionado no diretorio comparado.
     */
    public void addAdicionado(Archive adicionado) {
        add(new ResultArchive(adicionado, TypeResult.ADDED));
    }

    /**
     * Adiciona um resultado que pode ser do tipo INALTERADO, EDITADO ou MOVIDO.
     * @param base Archive base da comparação.
     * @param comparado Archive comparado.
     * @param similaridade Grau de similaridade entre os arquivos.
     */
    public void add(Archive base, Archive comparado, int similaridade) {
        add(new ResultArchive(base, comparado, similaridade));
    }

    /**
     * Adiciona um resultado que pode ser do tipo INALTERADO, EDITADO ou MOVIDO.
     * @param base Archive base da comparação.
     * @param comparado Archive comparado.
     * @param similaridade Grau de similaridade entre os arquivos.
     * @param grainsFrom Result ILCS from.
     * @param grainsTo Result ILCS to.
     */
    public void add(Archive base, Archive comparado, int similaridade, List<Grain> grainsFrom, List<Grain> grainsTo) {
        add(new ResultArchive(base, comparado, similaridade, grainsFrom, grainsTo));
    }

    /**
     * Marca um resultado como escolha do Algoritmo Húngaro.
     *  
     * @param base Archive base da comparação.
     * @param comparado Archive comparado.
     */
    public void setEscolhaHungaro(Archive base, Archive comparado) {

        for (ResultArchive resultado : resultadosArquivo) {

            if (resultado.getBase() != null && resultado.getBase().getId() == base.getId()
                    && resultado.getPara() != null && resultado.getPara().getId() == comparado.getId()) {
                resultado.setEscolhaHungaro(true);
                break;
            }
        }
    }

    /**
     * @return the resultadosArquivo
     */
    public List<ResultArchive> getResultadosArquivo() {
        return resultadosArquivo;
    }

    public List<Integer> getToFilesIds(int idArquivo) {
        ArrayList<Integer> lista = new ArrayList<Integer>();

        List<ResultArchive> resultados = getResultadosByFrom(idArquivo);
        for (ResultArchive resultadoArquivo : resultados) {
            if (resultadoArquivo.haveTo()) {
                lista.add(resultadoArquivo.getPara().getId());
            }
        }


        return lista;
    }

    public List<ResultArchive> getResultadosByFrom(int idArquivo) {
        ArrayList<ResultArchive> lista = new ArrayList<ResultArchive>();

        for (ResultArchive resultado : resultadosArquivo) {
            if (resultado.haveFrom() && resultado.getBase().getId() == idArquivo) {
                lista.add(resultado);
            }
        }

        return lista;
    }

    public List<ResultArchive> getResultadosByTo(int idArquivo) {
        ArrayList<ResultArchive> lista = new ArrayList<ResultArchive>();

        for (ResultArchive resultado : resultadosArquivo) {
            if (resultado.haveTo() && resultado.getPara().getId() == idArquivo) {
                lista.add(resultado);
            }
        }

        return lista;
    }
}