package ddiff;

import ilcs.grain.LineGrain;

/**
 * Representa o grao linha para diff de diret�rios
 * 
 * @author Eraldo
 *
 */
public class Linha extends LineGrain {

    /**
     * Guarda se a linha tem algum match.
     */
    private boolean match = false;

    public Linha(String line, int idReference, int start) {
        super(line, idReference, start);
    }

    /**
     * Setter para o campo match
     * @param match Novo valor para o campo match
     */
    public void setMatch(boolean match) {
        this.match = match;
    }

    /**
     * Getter para o campo match
     * @return valor do campo match
     */
    public boolean isMatch() {
        return match;
    }
}
