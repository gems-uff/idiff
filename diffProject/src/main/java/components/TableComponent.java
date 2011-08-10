package components;

import algorithms.Grain;
import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * TableComponent
 * @author Fernanda Floriano Silva
 */
public class TableComponent {

    /**
     * Constructor
     */
    public TableComponent() {
    }

    /**
     * Clean Model
     * @param tableDetails 
     */
    public void cleanTabelModel(JTable tableDetails) {
        if ((DefaultTableModel) tableDetails.getModel() != null) {
            deleteTableRows(tableDetails);
        }
        ((DefaultTableModel) tableDetails.getModel()).removeTableModelListener(tableDetails);
        tableDetails.revalidate();
        tableDetails.repaint();
    }

    /**
     * Print Lines
     * @param list1
     * @param list2
     * @param diferences 
     * @param tableDetails 
     */
    public void printTableLines(List<Grain> list1, List<Grain> list2, List<Grain> diferences, JTable tableDetails) {
        Iterator<Grain> it1 = list1.iterator();
        Iterator<Grain> it2 = list2.iterator();
        while (it1.hasNext() || it2.hasNext()) {
            Grain grain1 = it1.next();
            Grain grain2 = it2.next();

            if (((grain1 != null) || (grain2 != null)) && (!grain1.getOriginalReference().equals(grain2.getOriginalReference()))) {
                ((DefaultTableModel) tableDetails.getModel()).addRow(new String[]{grain1.getGrain(), "MOVED", printTableReference(grain1.getOriginalReference()), printTableReference(grain2.getOriginalReference())});
            }
        }

        for (Iterator<Grain> it3 = diferences.iterator(); it3.hasNext();) {
            Grain grain = it3.next();
            if (grain != null) {
                if ((grain.getSituation()).equals(Grain.Situation.REMOVED)) {
                    ((DefaultTableModel) tableDetails.getModel()).addRow(new Object[]{grain.getGrain(), grain.getSituation(), printTableReference(grain.getOriginalReference()), "---"});
                } else {
                    ((DefaultTableModel) tableDetails.getModel()).addRow(new Object[]{grain.getGrain(), grain.getSituation(), "---", printTableReference(grain.getOriginalReference())});
                }
            }
        }
        if (tableDetails.getRowCount() == 0) {
            ((DefaultTableModel) tableDetails.getModel()).addRow(new Object[]{" Differences not found ", "---", "---", "---"});
            tableDetails.setBackground(Color.BLUE);
            tableDetails.setForeground(Color.YELLOW);
        }
        tableDetails.setCellSelectionEnabled(false);
        tableDetails.setRowSelectionAllowed(true);
    }
    //TODO Verificar porque está imprimindo duas vezes!

    /**
     * Table Listener
     * @param tableDetails 
     */
    public void tableListener(final JTable tableDetails) {

        tableDetails.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    System.out.println(tableDetails.getValueAt(tableDetails.getSelectedRow(), 0));
                    System.out.println(tableDetails.getValueAt(tableDetails.getSelectedRow(), 1));
                    System.out.println(tableDetails.getValueAt(tableDetails.getSelectedRow(), 2));
                    System.out.println(tableDetails.getValueAt(tableDetails.getSelectedRow(), 3));
                }
            }
        });
    }

    /**
     * Print Reference
     * @param originalReference
     * @return String
     */
    public String printTableReference(List<Integer> originalReference) {
        char level = 'F';
        String stringResult = "";
        for (Iterator<Integer> it = originalReference.iterator(); it.hasNext();) {
            int id = it.next();
            level = getGrainLevel(level);
            stringResult = stringResult + " - " + getNameGrainLevel(level) + " " + id;
        }
        return stringResult.substring(3);
    }

    /**
     * Get Name Grain Level
     * @param levelGrain
     * @return String
     */
    private String getNameGrainLevel(char levelGrain) {
        switch (levelGrain) {
            case 'F':
                return "File";
            case 'L':
                return "Line";
            case 'W':
                return "Word";
            case 'C':
                return "Character";
            default:
                return "File";
        }
    }

    /**
     * Get Grain Level
     * @param levelGrain
     * @return char
     */
    private char getGrainLevel(char levelGrain) {
        switch (levelGrain) {
            case 'F':
                return 'L';
            case 'L':
                return 'W';
            case 'W':
                return 'C';
            default:
                return 'F';
        }
    }

    /**
     * Delete Rows
     * @param tableDetails 
     */
    public void deleteTableRows(JTable tableDetails) {
        int rowCount = ((DefaultTableModel) tableDetails.getModel()).getRowCount();
        for (int i = 0; i < rowCount; i++) {
            ((DefaultTableModel) tableDetails.getModel()).removeRow(0);
        }
    }
}