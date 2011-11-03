package components;

import algorithms.DiffException;
import details.Icon;
import details.Laf;
import diretorioDiff.DiretorioDiff;
import diretorioDiff.DiretorioDiffException;
import diretorioDiff.arvore.Arvore;
import diretorioDiff.arvore.No;
import diretorioDiff.resultados.Resultado;
import diretorioDiff.resultados.ResultadoArquivo;
import diretorioDiff.resultados.TipoResultado;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        execute();
    }

    @Action
    public void resize() {
        int largura = (int) splitPanel.getSize().getWidth() / 2;

        splitPanel.setDividerLocation(largura);
    }

   
    private void execute() {
        Resultado resultado = DiretorioDiff.compararDiretorios(from, to);

        fromTree.setResultado(resultado);
        toTree.setResultado(resultado);
    }

    private void loadTree() throws DiretorioDiffException {
        fromTree = Arvore.getBaseTree(from);
        toTree = Arvore.getComparedTree(to, fromTree);

        scrollTreeFrom.setViewportView(fromTree);
        scrollTreeTo.setViewportView(toTree);
    }

    @Action
    public void drill() {
        if (toTree != null && fromTree != null) {
            No noTo = toTree.getSelectedNode();
            No noFrom =  fromTree.getSelectedNode();

            if (noFrom == null) {
                return;
            }
            
            if (!noFrom.isSelected()) {
                return;
            }
            
            if (noTo == null) {
                return;
            }
            
            if (!noTo.isSelected()) {
                return;
            }
                
            for (ResultadoArquivo resultado : noFrom.getResultados()) {
                if (!resultado.isIdFrom(noFrom.getId())) {
                    continue;
                }

                if (!resultado.isIdTo(noTo.getId())) {
                    continue;
                }

                if (!resultado.getBase().isText()) {
                    continue;
                }
                
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
        Laf.setlaf();
        setLocationRelativeTo(null);
        setIconImage(Icon.getIcon());
        
        for(TipoResultado tipo: TipoResultado.values()) {
            JLabel label = new JLabel(tipo.getLabel());
            label.setBackground(tipo.getColor());
            label.setOpaque(true);
            
            label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            label.setMaximumSize(new java.awt.Dimension(80, 30));
            label.setMinimumSize(new java.awt.Dimension(80, 30));
            label.setPreferredSize(new java.awt.Dimension(80, 30));
            label.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            legend.add(label);
        }
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
                    new MainFDiff(file).setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(MainDDiff.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainDDiff.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void expandNodesDiff(Arvore tree) {
        if (tree != null) {
            tree.expandNodesWithDiff();
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
        jRadioButton1 = new javax.swing.JRadioButton();
        scrollTreeFrom = new javax.swing.JScrollPane();
        directoryPanel2 = new javax.swing.JPanel();
        scrollTreeTo = new javax.swing.JScrollPane();
        jRadioButton3 = new javax.swing.JRadioButton();
        toolBar = new javax.swing.JToolBar();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        legend = new javax.swing.JToolBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Directory Diff");
        setMinimumSize(new java.awt.Dimension(800, 500));

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

        directoryPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Left Directory"));
        directoryPanel1.setName("directoryPanel1"); // NOI18N
        directoryPanel1.setPreferredSize(new java.awt.Dimension(300, 646));

        jRadioButton1.setText("Expand Folders With Diferences");
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
            .add(org.jdesktop.layout.GroupLayout.TRAILING, scrollTreeFrom, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
            .add(directoryPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(directoryPanel1Layout.createSequentialGroup()
                    .add(jRadioButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        directoryPanel1Layout.setVerticalGroup(
            directoryPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(directoryPanel1Layout.createSequentialGroup()
                .add(24, 24, 24)
                .add(scrollTreeFrom, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
            .add(directoryPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(directoryPanel1Layout.createSequentialGroup()
                    .add(jRadioButton1)
                    .addContainerGap(274, Short.MAX_VALUE)))
        );

        splitPanel.setLeftComponent(directoryPanel1);

        directoryPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Right Directory"));
        directoryPanel2.setName("directoryPanel2"); // NOI18N

        scrollTreeTo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        scrollTreeTo.setName("scrollTreeTo"); // NOI18N

        jRadioButton3.setText("Expand Folders With Diferences");
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
            .add(scrollTreeTo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
            .add(directoryPanel2Layout.createSequentialGroup()
                .add(jRadioButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        directoryPanel2Layout.setVerticalGroup(
            directoryPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(directoryPanel2Layout.createSequentialGroup()
                .add(jRadioButton3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(scrollTreeTo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
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
        jButton3.setAction(actionMap.get("drill")); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/components/icons/zoom.png"))); // NOI18N
        jButton3.setText(null);
        jButton3.setToolTipText(null);
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

        jButton2.setAction(actionMap.get("showFileOverView")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(MainDDiff.class);
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(null);
        jButton2.setToolTipText(null);
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

        legend.setFloatable(false);
        legend.setName("legend"); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, splitPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(toolBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(legend, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 403, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(legend, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(toolBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(splitPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void splitPanelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_splitPanelComponentResized
        // TODO add your handling code here:
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar legend;
    private javax.swing.JScrollPane scrollTreeFrom;
    private javax.swing.JScrollPane scrollTreeTo;
    private javax.swing.JSplitPane splitPanel;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
