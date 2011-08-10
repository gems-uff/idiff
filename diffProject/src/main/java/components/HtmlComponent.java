package components;

import javax.swing.JEditorPane;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class HtmlComponent {

    public void setTextDecoration(StringBuilder sb) {
        sb.append("<head><title>text-decoration</title>	<style rel='stylesheet' type='text/css'>");
        sb.append("span.excluded {font: 9px sans-serif;color: red;background: white;text-decoration: line-through;}");
        sb.append("span.added {font: 9px sans-serif;color:green;background: white;text-decoration: underline;}");
        sb.append("span.moved {font: 9px sans-serif;background: #D3CDC5;border: 2px solid black;}");
        sb.append("span.unchanged {font: 9px sans-serif;background: white;}");
        sb.append("</style></head><body>");
    }

    public void setAddedLine(StringBuilder sb, String linha, int idLine) {
        sb.append("<span class='added'>").append(idLine).append(":").append(linha).append("</span><br>");
    }

    public void setRemovedLine(StringBuilder sb, String linha, int idLine) {
        sb.append("<span class='excluded'>").append(linha).append("</span><br>");
    }

    public void setMovedLine(StringBuilder sb, String linha, int idLine) {
        sb.append("<span class='moved'>").append(linha).append("</span><br>");
    }

    public void setUnchangedLine(StringBuilder sb, String linha, int idLine) {
        sb.append("<span class='unchanged'>").append(linha).append("</span><br>");
    }

    public void setToolTipText(JEditorPane editorPane, String msg) {
        editorPane.setToolTipText("<html>" + msg + "</html>");
    }
}
