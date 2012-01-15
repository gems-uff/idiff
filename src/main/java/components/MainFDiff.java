package components;

import ilcs.grain.Grain;
import idiff.resources.IDIFFColor;
import idiff.resources.Icon;
import idiff.resources.Laf;
import idiff.resources.Warning;
import ddiff.results.ResultadoArquivo;
import ddiff.results.TipoResultado;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import org.jdesktop.application.Action;

/**
 * MainFDiff
 * @author Fernanda Floriano Silva
 */
public class MainFDiff extends javax.swing.JFrame {

    public static final int LEFT_DIRECTORY = 1;
    public static final int RIGHT_DIRECTORY = 2;
    private FileComponent fileComponent = new FileComponent();
    private static MainFDiff instance;
    private String selectedItem = "Show All Similarities/Refactoring";

    /**
     * getInstance
     * @return instance
     */
    public synchronized static MainFDiff getInstance() {
        return instance;
    }

    /**
     * setInstance
     * @param file
     * @param result
     * @param idDirectory 
     */
    public synchronized static void setInstance(File file, List<ResultadoArquivo> result, int idDirectory) {
        if (instance != null) {
            instance.dispose();
        }
        instance = new MainFDiff(file, result, idDirectory);
    }

    /**
     * resetInstance
     */
    public static synchronized void resetInstance() {
        instance = null;
    }

    private void init() {
        initComponents();
        Laf.setlaf();
        setLocationRelativeTo(null);
        setIconImage(Icon.getIcon());
    }

    /** Creates new form FileOverView
     * @param file
     * @param result
     * @param idDirectory  
     */
    public MainFDiff(File file, List<ResultadoArquivo> result, int idDirectory) {
        init();
        try {
            start(file, result, idDirectory, false);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainFDiff.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFDiff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean isFileSimilarity(String fileName) {
        return selectedItem.equals("Show Similarity with " + fileName);
    }

    private boolean showAllSimilarity() {
        return selectedItem.equals("Show All Similarities/Refactoring");
    }

    private void start(File file, List<ResultadoArquivo> result, int idDirectory, boolean isRepaint) throws IOException {
        fileComponent.setFile(pane, panel, file);
        showSimilarity(result, idDirectory, isRepaint);
        addComboListener(file, refactoringCombo, result, idDirectory);
    }

    private boolean isSelectedItem(String fileName) {
        return isFileSimilarity(fileName) || showAllSimilarity();
    }

    private void showSimilarity(List<ResultadoArquivo> listResult, int idDirectory, boolean isRepaint) {
        for (ResultadoArquivo result : listResult) {
            printResult(result, idDirectory, isRepaint);
        }
    }

    private void addComboListener(final File file, JComboBox combo, final List<ResultadoArquivo> listResult, final int idDirectory) {
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                selectedItem = (String) cb.getSelectedItem();
                restartComponents(file, listResult, idDirectory);
            }
        });
    }

    private void restartComponents(File file, List<ResultadoArquivo> result, int idDirectory) {
        fileComponent.clear(pane);
        try {
            start(file, result, idDirectory, true);
        } catch (IOException ex) {
            Logger.getLogger(MainFDiff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Show Diff
     */
    @Action
    public void back() {
        Warning.dispose(WarningDialog);
        this.dispose();
    }

    /**
     * printResult
     * @param result
     * @param idDirectory 
     */
    private void printResult(ResultadoArquivo result, int idDirectory, boolean isRepaint) {
        if (result.getTipo() == TipoResultado.CHANGED) {
            showRefactory(result, idDirectory, isRepaint);
        } else {
            Warning.show(WarningDialog);
        }
    }

    /**
     * showRefactory
     * @param result
     * @param idDirectory 
     */
    private void showRefactory(ResultadoArquivo result, int idDirectory, boolean isRepaint) {
        File file = null;
        switch (idDirectory) {
            case MainDDiff.LEFT_DIRECTORY:
                file = result.getPara().getArquivo();
                setRefactory(result.getGrainsTo(), file.getAbsolutePath());
                break;
            case MainDDiff.RIGHT_DIRECTORY:
                file = result.getBase().getArquivo();
                setRefactory(result.getGrainsFrom(), file.getAbsolutePath());
                break;
        }
        if (!isRepaint) {
            setCombo(file);
        }
    }

    private void setCombo(File file) {
        refactoringCombo.addItem("Show Similarity with " + file.getAbsolutePath());
    }

    private void setRefactory(List<Grain> grains, String fileName) {
        Color color = IDIFFColor.getRandomColor();
        for (Grain grain : grains) {
            if (isSelectedItem(fileName)) {
                GranularityComponent.setRefactoryGranularity(grain.getGrainBean(), pane, scrollPane, color, getMsgRefactory(fileName));
            }
        }
    }

    private String getMsgRefactory(String fileName) {
        return "Similarity found with " + fileName;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WarningDialog = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        warningMsg = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        refactoringCombo = new javax.swing.JComboBox();
        panel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        pane = new javax.swing.JTextPane();

        WarningDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WarningDialog.setTitle("Warning");
        WarningDialog.setAlwaysOnTop(true);
        WarningDialog.setFocusTraversalPolicyProvider(true);
        WarningDialog.setMinimumSize(new java.awt.Dimension(234, 91));
        WarningDialog.setName("WarningDialog"); // NOI18N
        WarningDialog.setResizable(false);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(MainFDiff.class);
        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setName("jLabel1"); // NOI18N

        warningMsg.setFont(resourceMap.getFont("warningMsg.font")); // NOI18N
        warningMsg.setForeground(new java.awt.Color(0, 0, 102));
        warningMsg.setText("Refactoring Not Found");
        warningMsg.setName("warningMsg"); // NOI18N

        javax.swing.GroupLayout WarningDialogLayout = new javax.swing.GroupLayout(WarningDialog.getContentPane());
        WarningDialog.getContentPane().setLayout(WarningDialogLayout);
        WarningDialogLayout.setHorizontalGroup(
            WarningDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WarningDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warningMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );
        WarningDialogLayout.setVerticalGroup(
            WarningDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(warningMsg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("File Overview");
        setResizable(false);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jSeparator2.setName("jSeparator2"); // NOI18N
        jToolBar1.add(jSeparator2);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(MainFDiff.class, this);
        jButton1.setAction(actionMap.get("back")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jToolBar1.add(jSeparator1);

        refactoringCombo.setMaximumRowCount(5);
        refactoringCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Show All Similarities/Refactoring" }));
        refactoringCombo.setToolTipText("Similarities/Refactoring List Found");
        refactoringCombo.setAutoscrolls(true);
        refactoringCombo.setDoubleBuffered(true);
        refactoringCombo.setFocusCycleRoot(true);
        refactoringCombo.setFocusTraversalPolicyProvider(true);
        refactoringCombo.setMaximumSize(new java.awt.Dimension(900, 28));
        refactoringCombo.setMinimumSize(new java.awt.Dimension(900, 28));
        refactoringCombo.setName("refactoringCombo"); // NOI18N
        refactoringCombo.setOpaque(true);
        refactoringCombo.setPreferredSize(new java.awt.Dimension(900, 28));
        jToolBar1.add(refactoringCombo);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("File Name"));
        panel.setMaximumSize(new java.awt.Dimension(717, 619));
        panel.setMinimumSize(new java.awt.Dimension(717, 619));
        panel.setName("panel"); // NOI18N

        scrollPane.setName("scrollPane"); // NOI18N

        pane.setEditable(false);
        pane.setName("pane"); // NOI18N
        scrollPane.setViewportView(pane);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 963, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog WarningDialog;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextPane pane;
    private javax.swing.JPanel panel;
    private javax.swing.JComboBox refactoringCombo;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel warningMsg;
    // End of variables declaration//GEN-END:variables
}