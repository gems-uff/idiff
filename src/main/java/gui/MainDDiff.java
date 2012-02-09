package gui;

import gui.components.resources.Processo;
import ilcs.DiffException;
import idiff.resources.Icon;
import idiff.resources.Laf;
import ddiff.Archive;
import ddiff.Ddiff;
import ddiff.DDiffException;
import ddiff.tree.Tree;
import ddiff.tree.Node;
import ddiff.results.Result;
import ddiff.results.ResultArchive;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jdesktop.application.Action;

/**
 * MainDDiff
 * @author Fernanda Floriano Silva
 */
public class MainDDiff extends JFrame {

    public static final int LEFT_DIRECTORY = 1;
    public static final int RIGHT_DIRECTORY = 2;
    private Tree fromTree;
    private Tree toTree;
    private String granularity;
    private File from;
    private File to;
    private String tags;
    private static MainDDiff instance;
    //private SplashScreen splash;

    /**
     * getInstance
     * @return instance
     */
    public synchronized static MainDDiff getInstance() {
        return instance;
    }

    /**
     * setInstance
     * @param directoryFrom
     * @param directoryTo
     * @param granularity
     * @param tags 
     */
    public synchronized static void setInstance(File directoryFrom, File directoryTo, String granularity, String tags) {
        if (instance != null) {
            instance.dispose();
        }
        instance = new MainDDiff(directoryFrom, directoryTo, granularity, tags);
    }

    /**
     *  Creates new form MainDDiff 
     */
    public MainDDiff() {
        super();
    }

    /**
     * resetInstance
     */
    public static synchronized void resetInstance() {
        instance = null;
    }

    /**
     * MainDDiff
     * @param directoryFrom
     * @param directoryTo
     * @param granularity
     * @param tags 
     */
    public MainDDiff(File directoryFrom, File directoryTo, String granularity, String tags) {
        super();
        initComponents();
        init();
        from = directoryFrom;
        to = directoryTo;
        this.granularity = granularity;
        this.tags = tags;
    }

    /**
     * isQuiteSimilar
     * @param from
     * @param to
     * @return 
     */
    private boolean isQuiteSimilar(Node from, Node to) {
        return (from.getSimilaridade() == 0 && to.getSimilaridade() > 50);
    }

    /**
     * isSimilar
     * @param noFrom
     * @param noTo
     * @return 
     */
    public boolean isSimilar(Node noFrom, Node noTo) {
        return (isQuiteSimilar(noFrom, noTo) || (isQuiteSimilar(noTo, noFrom)));
    }

    DDiffProgress progressMessager = new DDiffProgress(this, true);
    
    Processo p = new Processo(progressMessager, this);
    /**
     * start
     */
    public void start() {   
        this.setVisible(true);
        p.start();
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                
                progressMessager.setVisible(true);
            }
        });
    }

    /**
     * 
     */
    @Action
    public void resize() {
        int largura = (int) splitPanel.getSize().getWidth() / 2;

        splitPanel.setDividerLocation(largura);
    }

    /**
     * 
     * @param noFrom
     * @param noTo
     * @return
     * @throws HeadlessException 
     */
    public boolean verifyError(Node noFrom, Node noTo) throws HeadlessException {
        if ((noFrom == null) && (noTo == null)) {
            showError("Select one file");
            return true;
        }
        if (((noFrom != null) && (noTo != null)) && (noFrom.isSelected() && noTo.isSelected())) {
            showError("Select only one file");
            return true;
        }
        return false;
    }

    /**
     * 
     * @param no
     * @param idDirectory 
     */
    private void initOverView(Node no, int idDirectory) {
        ResultArchive resultado = no.getResultados().get(0);
        switch (idDirectory) {
            case LEFT_DIRECTORY:
                showFileOverView(resultado.getBase().getArquivo(), no.getResultados(), LEFT_DIRECTORY);
                break;
            case RIGHT_DIRECTORY:
                showFileOverView(resultado.getPara().getArquivo(), no.getResultados(), RIGHT_DIRECTORY);
                break;
        }
    }

    /**
     * 
     * @param msg
     * @throws HeadlessException 
     */
    private void showError(String msg) throws HeadlessException {
        Error dialog = new Error(new javax.swing.JFrame(), true);
        dialog.setErrorLabel(msg);
        dialog.setVisible(true);
    }

    /**
     * 
     */
    public void execute() {

        Result resultado = Ddiff.compararDiretorios(from, to, progressMessager,granularity,tags);
        fromTree.setResultado(resultado);
        toTree.setResultado(resultado);
    }

    /**
     * 
     * @throws DDiffException 
     */
    public void loadTree() throws DDiffException {
        fromTree = Tree.getBaseTree(from);
        toTree = Tree.getComparedTree(to, fromTree);

        scrollTreeFrom.setViewportView(fromTree);
        scrollTreeTo.setViewportView(toTree);

    }

    /**
     * 
     */
    @Action
    public void drill() {
        if (toTree != null && fromTree != null) {
            Node noTo = toTree.getSelectedNode();
            Node noFrom = fromTree.getSelectedNode();

            if ((noFrom == null) || (noTo == null)) {
                showError("Select two files");
                return;
            }

            try {
                Archive fileFrom = noFrom.getResultados().get(0).getBase();
                Archive fileTo = noTo.getResultados().get(0).getPara();

                showILCS(fileFrom.getArquivo(), fileTo.getArquivo(), granularity, tags, isSimilar(noFrom, noTo));

            } catch (DiffException ex) {
                Logger.getLogger(MainDDiff.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainDDiff.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainDDiff.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * 
     * @param fileFrom
     * @param fileTo
     * @param granularity
     * @param tags
     * @param isQuiteSimilar
     * @throws DiffException
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void showILCS(File fileFrom, File fileTo, String granularity, String tags, boolean isQuiteSimilar) throws DiffException, FileNotFoundException, IOException {
        MainILCS.setInstance(fileFrom, fileTo, granularity, tags, isQuiteSimilar);
        MainILCS.getInstance().setVisible(true);

    }

    /**
     * Init 
     */
    private void init() {
        Laf.setlaf();
        setLocationRelativeTo(null);
        setIconImage(new Icon().getIcon());
        this.setExtendedState(MAXIMIZED_BOTH);  
    }

    /**
     * 
     */
    @Action
    public void overView() {
        if (toTree != null || fromTree != null) {
            Node noFrom = fromTree.getSelectedNode();
            Node noTo = toTree.getSelectedNode();

            if (verifyError(noFrom, noTo)) {
                return;
            }

            if (noFrom != null) {
                initOverView(noFrom, LEFT_DIRECTORY);
            } else {
                initOverView(noTo, RIGHT_DIRECTORY);
            }
        }
    }

    /**
     * 
     * @param file
     * @param result
     * @param idDirectory 
     */
    private void showFileOverView(File file, List<ResultArchive> result, int idDirectory) {
        MainFDiff.setInstance(file, result, idDirectory);
        MainFDiff.getInstance().setVisible(true);
    }

    /**
     * 
     * @param tree 
     */
    private void expandNodesDiff(Tree tree) {
        if (tree != null) {
            tree.expandNodesWithDiff();
        }
    }

    /**
     * 
     */
    private void cleanResults() {
        MainFDiff.resetInstance();
        MainILCS.resetInstance();
        MainDDiff.resetInstance();
    }

    /**
     * File Selection
     */
    @Action
    public void back() {
        this.dispose();
        cleanResults();
        FileSelection.getInstance();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPanel = new javax.swing.JSplitPane();
        directoryPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        scrollTreeFrom = new javax.swing.JScrollPane();
        directoryPanel2 = new javax.swing.JPanel();
        scrollTreeTo = new javax.swing.JScrollPane();
        jRadioButton3 = new javax.swing.JRadioButton();
        toolBar = new javax.swing.JToolBar();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jTextField5 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Directory Diff");
        setMinimumSize(new java.awt.Dimension(800, 500));

        splitPanel.setDividerLocation(650);
        splitPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        splitPanel.setMinimumSize(new java.awt.Dimension(120, 300));
        splitPanel.setName("splitPanel"); // NOI18N
        splitPanel.setPreferredSize(new java.awt.Dimension(120, 300));
        splitPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                splitPanelComponentResized(evt);
            }
        });

        directoryPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Left Directory"));
        directoryPanel1.setName("directoryPanel1"); // NOI18N
        directoryPanel1.setPreferredSize(new java.awt.Dimension(300, 646));

        jRadioButton1.setText("Expand Folders With Differences");
        jRadioButton1.setFocusable(false);
        jRadioButton1.setName("jRadioButton1"); // NOI18N
        jRadioButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });

        scrollTreeFrom.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        scrollTreeFrom.setName("scrollTreeFrom"); // NOI18N

        org.jdesktop.layout.GroupLayout directoryPanel1Layout = new org.jdesktop.layout.GroupLayout(directoryPanel1);
        directoryPanel1.setLayout(directoryPanel1Layout);
        directoryPanel1Layout.setHorizontalGroup(
            directoryPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(directoryPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(scrollTreeFrom, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
            .add(directoryPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(directoryPanel1Layout.createSequentialGroup()
                    .add(jRadioButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        directoryPanel1Layout.setVerticalGroup(
            directoryPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(directoryPanel1Layout.createSequentialGroup()
                .add(28, 28, 28)
                .add(scrollTreeFrom, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                .addContainerGap())
            .add(directoryPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(directoryPanel1Layout.createSequentialGroup()
                    .add(jRadioButton1)
                    .addContainerGap(559, Short.MAX_VALUE)))
        );

        splitPanel.setLeftComponent(directoryPanel1);

        directoryPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Right Directory"));
        directoryPanel2.setName("directoryPanel2"); // NOI18N

        scrollTreeTo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        scrollTreeTo.setName("scrollTreeTo"); // NOI18N

        jRadioButton3.setText("Expand Folders With Differences");
        jRadioButton3.setFocusable(false);
        jRadioButton3.setName("jRadioButton3"); // NOI18N
        jRadioButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jRadioButton3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton3ItemStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout directoryPanel2Layout = new org.jdesktop.layout.GroupLayout(directoryPanel2);
        directoryPanel2.setLayout(directoryPanel2Layout);
        directoryPanel2Layout.setHorizontalGroup(
            directoryPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(directoryPanel2Layout.createSequentialGroup()
                .add(directoryPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jRadioButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, directoryPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(scrollTreeTo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)))
                .addContainerGap())
        );
        directoryPanel2Layout.setVerticalGroup(
            directoryPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(directoryPanel2Layout.createSequentialGroup()
                .add(jRadioButton3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(scrollTreeTo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addContainerGap())
        );

        splitPanel.setRightComponent(directoryPanel2);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.setEnabled(false);
        toolBar.setName("toolBar"); // NOI18N
        toolBar.setPreferredSize(new java.awt.Dimension(41, 41));

        jSeparator2.setName("jSeparator2"); // NOI18N
        toolBar.add(jSeparator2);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(MainDDiff.class, this);
        jButton1.setAction(actionMap.get("back")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(MainDDiff.class);
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setToolTipText(resourceMap.getString("jButton1.toolTipText")); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(jButton1);

        jSeparator3.setName("jSeparator3"); // NOI18N
        toolBar.add(jSeparator3);

        jButton3.setAction(actionMap.get("drill")); // NOI18N
        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setToolTipText(resourceMap.getString("jButton3.toolTipText")); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setMaximumSize(new java.awt.Dimension(57, 57));
        jButton3.setMinimumSize(new java.awt.Dimension(57, 57));
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(57, 57));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(jButton3);

        jSeparator4.setName("jSeparator4"); // NOI18N
        toolBar.add(jSeparator4);

        jButton2.setAction(actionMap.get("overView")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setToolTipText(resourceMap.getString("jButton2.toolTipText")); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setMaximumSize(new java.awt.Dimension(57, 57));
        jButton2.setMinimumSize(new java.awt.Dimension(57, 57));
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(57, 57));
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(jButton2);

        jSeparator1.setName("jSeparator1"); // NOI18N
        toolBar.add(jSeparator1);

        jTextField5.setEditable(false);
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.setEnabled(false);
        jTextField5.setMaximumSize(new java.awt.Dimension(632, 28));
        jTextField5.setMinimumSize(new java.awt.Dimension(632, 28));
        jTextField5.setName("jTextField5"); // NOI18N
        jTextField5.setPreferredSize(new java.awt.Dimension(632, 28));
        toolBar.add(jTextField5);

        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("gui/Bundle"); // NOI18N
        jTextField1.setText(bundle.getString("MainILCS.jTextField1.text")); // NOI18N
        jTextField1.setMaximumSize(new java.awt.Dimension(82, 28));
        jTextField1.setMinimumSize(new java.awt.Dimension(82, 28));
        jTextField1.setName("jTextField1"); // NOI18N
        toolBar.add(jTextField1);

        jTextField2.setBackground(new java.awt.Color(255, 174, 185));
        jTextField2.setEditable(false);
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setText(bundle.getString("MainILCS.jTextField2.text")); // NOI18N
        jTextField2.setMaximumSize(new java.awt.Dimension(71, 28));
        jTextField2.setMinimumSize(new java.awt.Dimension(71, 28));
        jTextField2.setName("jTextField2"); // NOI18N
        toolBar.add(jTextField2);

        jTextField3.setBackground(new java.awt.Color(193, 255, 193));
        jTextField3.setEditable(false);
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField3.setText(bundle.getString("MainILCS.jTextField3.text")); // NOI18N
        jTextField3.setMaximumSize(new java.awt.Dimension(47, 28));
        jTextField3.setMinimumSize(new java.awt.Dimension(47, 28));
        jTextField3.setName("jTextField3"); // NOI18N
        toolBar.add(jTextField3);

        jTextField4.setBackground(new java.awt.Color(126, 192, 238));
        jTextField4.setEditable(false);
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setText(bundle.getString("MainILCS.jTextField4.text")); // NOI18N
        jTextField4.setMaximumSize(new java.awt.Dimension(50, 28));
        jTextField4.setMinimumSize(new java.awt.Dimension(50, 28));
        jTextField4.setName("jTextField4"); // NOI18N
        toolBar.add(jTextField4);

        jTextField6.setBackground(new java.awt.Color(53, 94, 121));
        jTextField6.setEditable(false);
        jTextField6.setForeground(new java.awt.Color(255, 255, 255));
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setText(bundle.getString("MainILCS.jTextField6.text")); // NOI18N
        jTextField6.setMaximumSize(new java.awt.Dimension(99, 28));
        jTextField6.setMinimumSize(new java.awt.Dimension(99, 28));
        jTextField6.setName("jTextField6"); // NOI18N
        toolBar.add(jTextField6);

        jTextField9.setBackground(new java.awt.Color(255, 255, 153));
        jTextField9.setText("Similarity");
        jTextField9.setMaximumSize(new java.awt.Dimension(62, 28));
        jTextField9.setMinimumSize(new java.awt.Dimension(62, 28));
        jTextField9.setName("jTextField9"); // NOI18N
        toolBar.add(jTextField9);

        jTextField10.setBackground(new java.awt.Color(255, 204, 102));
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField10.setText("Similarity HighLight");
        jTextField10.setMaximumSize(new java.awt.Dimension(118, 28));
        jTextField10.setMinimumSize(new java.awt.Dimension(118, 28));
        jTextField10.setName("jTextField10"); // NOI18N
        toolBar.add(jTextField10);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(toolBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1398, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(splitPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1392, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(toolBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(splitPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void splitPanelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_splitPanelComponentResized
        resize();
    }//GEN-LAST:event_splitPanelComponentResized

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
        if (jRadioButton1.isSelected()) {
            expandNodesDiff(fromTree);
        }
    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    private void jRadioButton3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton3ItemStateChanged
        if (jRadioButton3.isSelected()) {
            expandNodesDiff(toTree);
        }
    }//GEN-LAST:event_jRadioButton3ItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel directoryPanel1;
    private javax.swing.JPanel directoryPanel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JScrollPane scrollTreeFrom;
    private javax.swing.JScrollPane scrollTreeTo;
    private javax.swing.JSplitPane splitPanel;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
