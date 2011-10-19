package components;

import algorithms.DiffException;
import details.Icon;
import diretorioDiff.DiretorioDiff;
import diretorioDiff.DiretorioDiffException;
import diretorioDiff.arvore.Arvore;
import diretorioDiff.arvore.No;
import diretorioDiff.resultados.Resultado;
import diretorioDiff.resultados.ResultadoArquivo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.jdesktop.application.Action;
import overView.FileOverView;

/**
 * MainDDiff
 * @author Fernanda Floriano Silva
 */
public class MainDDiff extends JFrame {

    private Arvore fromTree;
    private Arvore toTree;
    private File from;
    private File to;

    /**
     *  Creates new form MainDDiff 
     */
    public MainDDiff() {
        super();
    }

    public MainDDiff(File directoryFrom, File directoryTo, String granularity, boolean showDiff, boolean showMove, String tags) {
        super();

        initComponents();

        init();

        from = directoryFrom;
        to = directoryTo;

        try {
            loadTree();
        } catch (DiretorioDiffException ex) {
            Logger.getLogger(MainDDiff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Action
    public void resize() {
        int largura = (int) splitPanel.getSize().getWidth() / 2;

        splitPanel.setDividerLocation(largura);
    }

    @Action
    public void execute() {
        labelProcess.setText("Processing...");

        Resultado resultado = DiretorioDiff.compararDiretorios(from, to);

        fromTree.setResultado(resultado);
        toTree.setResultado(resultado);

        labelProcess.setText("");
    }

    private void loadTree() throws DiretorioDiffException {
        fromTree = Arvore.getBaseTree(from);
        toTree = Arvore.getComparedTree(to, fromTree);

        scrollTreeFrom.setViewportView(fromTree);
        scrollTreeTo.setViewportView(toTree);
    }

    @Action
    public void drill() {
        if (toTree != null) {
            No no = toTree.getNoSelecionado();

            if (no != null) {
                for (ResultadoArquivo resultado : no.getResultados()) {
                    if (resultado.haveFrom() && resultado.haveTo() && resultado.getBase().isText()) {
                        try {
                            showILCS(resultado.getBase().getArquivo(), resultado.getPara().getArquivo(), "CHARACTER", true, true, setTags());
                        } catch (DiffException ex) {
                            Logger.getLogger(MainDDiff.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(MainDDiff.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(MainDDiff.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                }
            }
        }
    }

    private void showILCS(File fileFrom, File fileTo, String granularity, boolean showDiff, boolean showMove, String tags) throws DiffException, FileNotFoundException, IOException {
        MainILCS ilcs = MainILCS.setInstance(fileFrom, fileTo, granularity, showDiff, showMove, tags);
        ilcs.setVisible(true);
    }

    /**
     * Set Tags
     * @return String
     */
    private String setTags() {
        String tag = "[\\s";
        tag = setTag(tag, true, "\\.");
        tag = setTag(tag, true, "\\;");
        tag = setTag(tag, true, "\\,");
        tag = setTag(tag, true, "\\(\\)");
        tag = setTag(tag, true, "\\[\\]");
        tag = setTag(tag, true, "\\{\\}");
        return tag + "]";
    }

    private String setTag(String tag, boolean selected, String separator) {
        if (selected) {
            return tag + separator;
        }
        return tag;
    }

    /**
     * Init 
     */
    private void init() {
        //Laf.setlaf();
        setLocationRelativeTo(null);
        btnExecute.setIcon(new ImageIcon("src/main/resources/components/icons/execute.png"));
        btnDrill.setIcon(new ImageIcon("src/main/resources/components/icons/zoom.png"));
        setIconImage(Icon.getIcon());
    }

    /**
     * Show Diff
     * @param file1
     * @param file2 
     */
    @Action
    public void showDDiff(File file1, File file2) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                System.exit(0);
            }
        });
    }
    
        @Action
    public void showFileOverView(final File file) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new FileOverView(file).setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(MainDDiff.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainDDiff.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPanel = new javax.swing.JSplitPane();
        directoryPanel1 = new javax.swing.JPanel();
        scrollTreeFrom = new javax.swing.JScrollPane();
        directoryPanel2 = new javax.swing.JPanel();
        scrollTreeTo = new javax.swing.JScrollPane();
        toolBar = new javax.swing.JToolBar();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnExecute = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnDrill = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        labelProcess = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Directory Diff");
        setMinimumSize(new java.awt.Dimension(450, 400));

        splitPanel.setDividerLocation(200);
        splitPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        splitPanel.setMinimumSize(new java.awt.Dimension(120, 300));
        splitPanel.setName("splitPanel"); // NOI18N
        splitPanel.setPreferredSize(new java.awt.Dimension(120, 300));
        splitPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                splitPanelComponentResized(evt);
            }
        });

        directoryPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        directoryPanel1.setName("directoryPanel1"); // NOI18N
        directoryPanel1.setPreferredSize(new java.awt.Dimension(300, 646));

        scrollTreeFrom.setName("scrollTreeFrom"); // NOI18N

        org.jdesktop.layout.GroupLayout directoryPanel1Layout = new org.jdesktop.layout.GroupLayout(directoryPanel1);
        directoryPanel1.setLayout(directoryPanel1Layout);
        directoryPanel1Layout.setHorizontalGroup(
            directoryPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(directoryPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(scrollTreeFrom, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );
        directoryPanel1Layout.setVerticalGroup(
            directoryPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(directoryPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(scrollTreeFrom, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );

        splitPanel.setLeftComponent(directoryPanel1);

        directoryPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        directoryPanel2.setName("directoryPanel2"); // NOI18N

        scrollTreeTo.setName("scrollTreeTo"); // NOI18N

        org.jdesktop.layout.GroupLayout directoryPanel2Layout = new org.jdesktop.layout.GroupLayout(directoryPanel2);
        directoryPanel2.setLayout(directoryPanel2Layout);
        directoryPanel2Layout.setHorizontalGroup(
            directoryPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(directoryPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(scrollTreeTo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addContainerGap())
        );
        directoryPanel2Layout.setVerticalGroup(
            directoryPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(directoryPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(scrollTreeTo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );

        splitPanel.setRightComponent(directoryPanel2);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.setName("toolBar"); // NOI18N
        toolBar.setPreferredSize(new java.awt.Dimension(41, 41));

        jSeparator2.setName("jSeparator2"); // NOI18N
        toolBar.add(jSeparator2);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(MainDDiff.class, this);
        btnExecute.setAction(actionMap.get("execute")); // NOI18N
        btnExecute.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnExecute.setContentAreaFilled(false);
        btnExecute.setFocusable(false);
        btnExecute.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExecute.setIconTextGap(200);
        btnExecute.setMaximumSize(new java.awt.Dimension(35, 35));
        btnExecute.setMinimumSize(new java.awt.Dimension(35, 35));
        btnExecute.setName("btnExecute"); // NOI18N
        btnExecute.setPreferredSize(new java.awt.Dimension(35, 35));
        btnExecute.setRequestFocusEnabled(false);
        btnExecute.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(btnExecute);

        jSeparator3.setName("jSeparator3"); // NOI18N
        toolBar.add(jSeparator3);

        btnDrill.setAction(actionMap.get("drill")); // NOI18N
        btnDrill.setFont(new java.awt.Font("sansserif", 1, 12));
        btnDrill.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDrill.setContentAreaFilled(false);
        btnDrill.setFocusable(false);
        btnDrill.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDrill.setIconTextGap(200);
        btnDrill.setName("btnDrill"); // NOI18N
        btnDrill.setOpaque(true);
        btnDrill.setRequestFocusEnabled(false);
        btnDrill.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(btnDrill);
        btnDrill.getAccessibleContext().setAccessibleName("");

        jSeparator4.setName("jSeparator4"); // NOI18N
        toolBar.add(jSeparator4);

        jTextField1.setEditable(false);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("components/Bundle"); // NOI18N
        jTextField1.setText(bundle.getString("MainILCS.jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        toolBar.add(jTextField1);

        jTextField2.setBackground(new java.awt.Color(255, 174, 185));
        jTextField2.setEditable(false);
        jTextField2.setText(bundle.getString("MainILCS.jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N
        toolBar.add(jTextField2);

        jTextField3.setBackground(new java.awt.Color(193, 255, 193));
        jTextField3.setEditable(false);
        jTextField3.setText(bundle.getString("MainILCS.jTextField3.text")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N
        toolBar.add(jTextField3);

        jTextField4.setBackground(new java.awt.Color(126, 192, 238));
        jTextField4.setEditable(false);
        jTextField4.setText(bundle.getString("MainILCS.jTextField4.text")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N
        toolBar.add(jTextField4);

        jTextField6.setBackground(new java.awt.Color(53, 94, 121));
        jTextField6.setEditable(false);
        jTextField6.setForeground(new java.awt.Color(255, 255, 255));
        jTextField6.setText(bundle.getString("MainILCS.jTextField6.text")); // NOI18N
        jTextField6.setName("jTextField6"); // NOI18N
        toolBar.add(jTextField6);

        jTextField7.setBackground(new java.awt.Color(255, 255, 0));
        jTextField7.setEditable(false);
        jTextField7.setText(bundle.getString("MainILCS.jTextField6.text")); // NOI18N
        jTextField7.setName("jTextField7"); // NOI18N
        toolBar.add(jTextField7);

        jTextField8.setBackground(new java.awt.Color(255, 174, 66));
        jTextField8.setEditable(false);
        jTextField8.setText(bundle.getString("MainILCS.jTextField6.text")); // NOI18N
        jTextField8.setName("jTextField8"); // NOI18N
        toolBar.add(jTextField8);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        labelProcess.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelProcess.setName("labelProcess"); // NOI18N

        jButton1.setAction(actionMap.get("showFileOverView")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(MainDDiff.class);
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setToolTipText(resourceMap.getString("jButton1.toolTipText")); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setName("jButton1"); // NOI18N

        jButton2.setAction(actionMap.get("showFileOverView")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setName("jButton2"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(28, 28, 28)
                .add(jButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 522, Short.MAX_VALUE)
                .add(labelProcess, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(labelProcess, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButton2)
                    .add(jButton1))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(splitPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                    .add(toolBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(toolBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(splitPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void splitPanelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_splitPanelComponentResized
        // TODO add your handling code here:
        resize();
    }//GEN-LAST:event_splitPanelComponentResized
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDrill;
    private javax.swing.JButton btnExecute;
    private javax.swing.JPanel directoryPanel1;
    private javax.swing.JPanel directoryPanel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel labelProcess;
    private javax.swing.JScrollPane scrollTreeFrom;
    private javax.swing.JScrollPane scrollTreeTo;
    private javax.swing.JSplitPane splitPanel;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
