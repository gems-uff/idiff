package components;

import diretorioDiff.DiretorioDiff;
import diretorioDiff.DiretorioDiffException;
import diretorioDiff.arvore.Arvore;
import diretorioDiff.resultados.Resultado;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdesktop.application.Action;

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

    /**
     * Init 
     */
    private void init() {
        //setlaf();
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/main/resources/components/icons/logoIDiff.png").getImage());
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

    /**
     * Set Laf
     */
    private void setlaf() {
        try {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception ex) {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }
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
        jButton1 = new javax.swing.JButton();
        drillDownButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelProcess = new javax.swing.JLabel();

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
                .add(scrollTreeFrom, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addContainerGap())
        );
        directoryPanel1Layout.setVerticalGroup(
            directoryPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(directoryPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(scrollTreeFrom, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
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
                .add(scrollTreeTo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );
        directoryPanel2Layout.setVerticalGroup(
            directoryPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(directoryPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(scrollTreeTo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
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
        jButton1.setAction(actionMap.get("execute")); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/components/icons/execute.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setIconTextGap(200);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(jButton1);

        drillDownButton1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        drillDownButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/components/icons/zoom.png"))); // NOI18N
        drillDownButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        drillDownButton1.setBorderPainted(false);
        drillDownButton1.setContentAreaFilled(false);
        drillDownButton1.setFocusable(false);
        drillDownButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        drillDownButton1.setIconTextGap(200);
        drillDownButton1.setName("drillDownButton1"); // NOI18N
        drillDownButton1.setRequestFocusEnabled(false);
        drillDownButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(drillDownButton1);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        labelProcess.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelProcess.setName("labelProcess"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(351, Short.MAX_VALUE)
                .add(labelProcess, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(labelProcess, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(splitPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                    .add(toolBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
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
    private javax.swing.JPanel directoryPanel1;
    private javax.swing.JPanel directoryPanel2;
    private javax.swing.JButton drillDownButton1;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JLabel labelProcess;
    private javax.swing.JScrollPane scrollTreeFrom;
    private javax.swing.JScrollPane scrollTreeTo;
    private javax.swing.JSplitPane splitPanel;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
