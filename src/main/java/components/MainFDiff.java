package components;

import algorithms.Grain;
import details.Icon;
import details.Laf;
import diretorioDiff.resultados.ResultadoArquivo;
import diretorioDiff.resultados.TipoResultado;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import wrap.Wrap;
import org.jdesktop.application.Action;

/**
 * MainFDiff
 * @author Fernanda Floriano Silva
 */
public class MainFDiff extends javax.swing.JFrame {

    private FileComponent fileComponent = new FileComponent();
    private static MainFDiff instance;

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

    /** Creates new form FileOverView
     * @param file
     * @param result
     * @param idDirectory  
     */
    public MainFDiff(File file, List<ResultadoArquivo> result, int idDirectory) {
        initComponents();
        Laf.setlaf();
        setLocationRelativeTo(null);
        setIconImage(Icon.getIcon());

        try {
            init(file);
            showResult(result, idDirectory);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainFDiff.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFDiff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * getIdColor
     * @param random
     * @return int
     */
    private int getIdColor(Random random) {
        return random.nextInt(100) + 156;
    }

    /**
     * init
     * @param file
     * @throws MalformedURLException
     * @throws IOException 
     */
    private void init(File file) throws MalformedURLException, IOException {
        new Wrap().setWrapPane(pane);
        fileComponent.submitFile(file, pane);
        panel.setBorder(BorderFactory.createTitledBorder(file.getName()));
    }

    /**
     * Show Diff
     */
    @Action
    public void back() {
        if (Warning.isVisible()) {
            Warning.dispose();
        }
        this.dispose();
    }

    private void showResult(List<ResultadoArquivo> listResult, int idDirectory) {
        listModel = new DefaultListModel();

        for (ResultadoArquivo result : listResult) {
            printResult(result, idDirectory);
        }
        setList(listResult);
    }

    private void setList(List<ResultadoArquivo> listResult) {
        listSimilarity.setModel(listModel);
        listSimilarity.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listSimilarity.setVisibleRowCount(listResult.size());

        scrollLegend.setViewportView(listSimilarity);
        scrollLegend.setVisible(true);
    }

    /**
     * printResult
     * @param result
     * @param idDirectory 
     */
    private void printResult(ResultadoArquivo result, int idDirectory) {

        if (result.getTipo() == TipoResultado.CHANGED) {
            showRefactory(result, idDirectory);
        } else {
            showWarning();
        }
    }

    /**
     * showWarning
     */
    private void showWarning() {
        Laf.setlaf();
        Warning.setLocationRelativeTo(null);
        Warning.setIconImage(Icon.getIcon());
        Warning.setVisible(true);
    }

    /**
     * showRefactory
     * @param result
     * @param idDirectory 
     */
    private void showRefactory(ResultadoArquivo result, int idDirectory) {
        File file;
        switch (idDirectory) {
            case 1:
                file = result.getPara().getArquivo();
                setRefactory(result.getGrainsTo(), file.getAbsolutePath(), file.getName());
                break;
            case 2:
                file = result.getBase().getArquivo();
                setRefactory(result.getGrainsFrom(), file.getAbsolutePath(), file.getName());
                break;
        }
    }

    private void setRefactory(List<Grain> grains, String path, String fileName) {
        Color color = getColor();
        for (Grain grain : grains) {
            GranularityComponent.setRefactoryGranularity(grain.getGrainBean(), pane, scrollPane, color);
        }
        
        
        listModel.addElement(fileName);
    }

    /**
     * getColor
     * @return Color
     */
    private Color getColor() {
        Random random = new Random();
        Color color = new Color(getIdColor(random), getIdColor(random), getIdColor(random));
        return color;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Warning = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        warningMsg = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        msgRefatory = new javax.swing.JTextField();
        jSplitPane1 = new javax.swing.JSplitPane();
        panel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        pane = new javax.swing.JTextPane();
        scrollLegend = new javax.swing.JScrollPane();
        listSimilarity = new javax.swing.JList();

        Warning.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Warning.setTitle("Warning");
        Warning.setAlwaysOnTop(true);
        Warning.setFocusTraversalPolicyProvider(true);
        Warning.setMinimumSize(new java.awt.Dimension(234, 91));
        Warning.setName("Warning"); // NOI18N
        Warning.setResizable(false);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(MainFDiff.class);
        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setName("jLabel1"); // NOI18N

        warningMsg.setFont(resourceMap.getFont("warningMsg.font")); // NOI18N
        warningMsg.setForeground(new java.awt.Color(0, 0, 102));
        warningMsg.setText("Refactoring Not Found");
        warningMsg.setName("warningMsg"); // NOI18N

        javax.swing.GroupLayout WarningLayout = new javax.swing.GroupLayout(Warning.getContentPane());
        Warning.getContentPane().setLayout(WarningLayout);
        WarningLayout.setHorizontalGroup(
            WarningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WarningLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warningMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );
        WarningLayout.setVerticalGroup(
            WarningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        msgRefatory.setEditable(false);
        msgRefatory.setFont(new java.awt.Font("sansserif", 1, 12));
        msgRefatory.setToolTipText("");
        msgRefatory.setAutoscrolls(true);
        msgRefatory.setEnabled(false);
        msgRefatory.setFocusable(false);
        msgRefatory.setMaximumSize(new java.awt.Dimension(690, 28));
        msgRefatory.setMinimumSize(new java.awt.Dimension(690, 28));
        msgRefatory.setName("msgRefatory"); // NOI18N
        msgRefatory.setPreferredSize(new java.awt.Dimension(890, 28));
        jToolBar1.add(msgRefatory);

        jSplitPane1.setName("jSplitPane1"); // NOI18N
        jSplitPane1.setOneTouchExpandable(true);

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
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(panel);

        scrollLegend.setBorder(javax.swing.BorderFactory.createTitledBorder("Similarity found in:"));
        scrollLegend.setName("scrollLegend"); // NOI18N

        listSimilarity.setName("listSimilarity"); // NOI18N
        scrollLegend.setViewportView(listSimilarity);

        jSplitPane1.setRightComponent(scrollLegend);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Warning;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JList listSimilarity;
    private javax.swing.JTextField msgRefatory;
    private javax.swing.JTextPane pane;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scrollLegend;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel warningMsg;
    // End of variables declaration//GEN-END:variables
    private DefaultListModel listModel;
}
