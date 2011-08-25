package components;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdesktop.application.Action;

/**
 * MainDDiff
 * @author Fernanda Floriano Silva
 */
public class MainDDiff extends javax.swing.JFrame {

    /**
     *  Creates new form MainDDiff 
     */
    public MainDDiff() {
        initComponents();
        setlaf();
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

    /**
     * Drill Down 
     */
    @Action
    public void drillDown() {
        System.out.println("DRILL DOWN...");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        directoryPanel1 = new javax.swing.JPanel();
        directoryPanel2 = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        drillDownButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        backButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main DDiff");
        setResizable(false);

        jSplitPane1.setDividerLocation(650);
        jSplitPane1.setMinimumSize(new java.awt.Dimension(120, 300));
        jSplitPane1.setName("jSplitPane1"); // NOI18N

        directoryPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        directoryPanel1.setName("directoryPanel1"); // NOI18N
        directoryPanel1.setPreferredSize(new java.awt.Dimension(555, 646));

        org.jdesktop.layout.GroupLayout directoryPanel1Layout = new org.jdesktop.layout.GroupLayout(directoryPanel1);
        directoryPanel1.setLayout(directoryPanel1Layout);
        directoryPanel1Layout.setHorizontalGroup(
            directoryPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 645, Short.MAX_VALUE)
        );
        directoryPanel1Layout.setVerticalGroup(
            directoryPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 642, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(directoryPanel1);

        directoryPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        directoryPanel2.setName("directoryPanel2"); // NOI18N

        org.jdesktop.layout.GroupLayout directoryPanel2Layout = new org.jdesktop.layout.GroupLayout(directoryPanel2);
        directoryPanel2.setLayout(directoryPanel2Layout);
        directoryPanel2Layout.setHorizontalGroup(
            directoryPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 700, Short.MAX_VALUE)
        );
        directoryPanel2Layout.setVerticalGroup(
            directoryPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 642, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(directoryPanel2);

        toolBar.setRollover(true);
        toolBar.setName("toolBar"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(MainDDiff.class, this);
        drillDownButton.setAction(actionMap.get("drillDown")); // NOI18N
        drillDownButton.setFont(new java.awt.Font("sansserif", 1, 12));
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(MainDDiff.class);
        drillDownButton.setIcon(resourceMap.getIcon("drillDownButton.icon")); // NOI18N
        drillDownButton.setBorderPainted(false);
        drillDownButton.setContentAreaFilled(false);
        drillDownButton.setName("drillDownButton"); // NOI18N
        toolBar.add(drillDownButton);

        jSeparator2.setName("jSeparator2"); // NOI18N
        toolBar.add(jSeparator2);

        backButton.setAction(actionMap.get("showDDiff")); // NOI18N
        backButton.setFont(new java.awt.Font("sansserif", 1, 12));
        backButton.setIcon(resourceMap.getIcon("backButton.icon")); // NOI18N
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setName("backButton"); // NOI18N
        toolBar.add(backButton);

        jSeparator3.setName("jSeparator3"); // NOI18N
        toolBar.add(jSeparator3);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(toolBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1377, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1365, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(toolBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel directoryPanel1;
    private javax.swing.JPanel directoryPanel2;
    private javax.swing.JButton drillDownButton;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
