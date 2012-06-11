package ddiff;

import ilcs.grain.LineGrain;

/**
 * Representa o grao linha para diff de diretorios
 * 
 * @author Eraldo
 *
 */
public class Line extends LineGrain {

    /**
     * Guarda se a linha tem algum match.
     */
    private boolean match = false;

    public Line(String line, int idReference, int start) {
        super(line, idReference, start);
    }

    /**
     * Getter para o campo match
     * @return valor do campo match
     */
    public boolean isMatch() {
        return match;
    }
}
