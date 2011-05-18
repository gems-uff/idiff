/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Configurations.java
 *
 * Created on 14/05/2011, 22:57:29
 */
package components;

import org.jdesktop.application.Action;

/**
 *
 * @author Sisi
 */
public class Configurations extends javax.swing.JFrame {

    /** Creates new form Configurations */
    public Configurations() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        granularityPanel = new javax.swing.JPanel();
        directoryCheckBox = new javax.swing.JCheckBox();
        fileCheckBox = new javax.swing.JCheckBox();
        lineCheckBox = new javax.swing.JCheckBox();
        wordCheckBox = new javax.swing.JCheckBox();
        characterCheckBox = new javax.swing.JCheckBox();
        image = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Parameters");

        granularityPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Granularity analyzed "));
        granularityPanel.setName("granularityPanel"); // NOI18N

        directoryCheckBox.setText("Directory");
        directoryCheckBox.setName("directoryCheckBox"); // NOI18N

        fileCheckBox.setText("File");
        fileCheckBox.setName("fileCheckBox"); // NOI18N

        lineCheckBox.setText("Line");
        lineCheckBox.setName("lineCheckBox"); // NOI18N

        wordCheckBox.setText("Word");
        wordCheckBox.setName("wordCheckBox"); // NOI18N

        characterCheckBox.setText("Character");
        characterCheckBox.setName("characterCheckBox"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(Configurations.class);
        image.setIcon(resourceMap.getIcon("image.icon")); // NOI18N
        image.setName("image"); // NOI18N

        org.jdesktop.layout.GroupLayout granularityPanelLayout = new org.jdesktop.layout.GroupLayout(granularityPanel);
        granularityPanel.setLayout(granularityPanelLayout);
        granularityPanelLayout.setHorizontalGroup(
            granularityPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(granularityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(granularityPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(granularityPanelLayout.createSequentialGroup()
                        .add(granularityPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(directoryCheckBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(lineCheckBox))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 6, Short.MAX_VALUE)
                        .add(granularityPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(fileCheckBox)
                            .add(granularityPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(image)
                                .add(wordCheckBox)))
                        .add(51, 51, 51))
                    .add(characterCheckBox))
                .addContainerGap())
        );
        granularityPanelLayout.setVerticalGroup(
            granularityPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(granularityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(granularityPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(directoryCheckBox)
                    .add(fileCheckBox))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(granularityPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(wordCheckBox)
                    .add(lineCheckBox))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(granularityPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(characterCheckBox)
                    .add(image))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(Configurations.class, this);
        okButton.setAction(actionMap.get("ok")); // NOI18N
        okButton.setIcon(resourceMap.getIcon("okButton.icon")); // NOI18N
        okButton.setToolTipText("OK");
        okButton.setBorderPainted(false);
        okButton.setContentAreaFilled(false);
        okButton.setName("okButton"); // NOI18N

        closeButton.setAction(actionMap.get("close")); // NOI18N
        closeButton.setIcon(resourceMap.getIcon("closeButton.icon")); // NOI18N
        closeButton.setToolTipText("Close");
        closeButton.setContentAreaFilled(false);
        closeButton.setName("closeButton"); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(closeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(granularityPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 179, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(granularityPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(okButton)
                    .add(closeButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Configurations().setVisible(true);
            }
        });
    }

    @Action
    public void ok() {
        // TODO implementar
        System.out.println("ok click...");
    }

    @Action
    public void close() {
        this.setVisible(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JCheckBox characterCheckBox;
    private javax.swing.JButton closeButton;
    private javax.swing.JCheckBox directoryCheckBox;
    private javax.swing.JCheckBox fileCheckBox;
    private javax.swing.JPanel granularityPanel;
    private javax.swing.JLabel image;
    private javax.swing.JCheckBox lineCheckBox;
    private javax.swing.JButton okButton;
    private javax.swing.JCheckBox wordCheckBox;
    // End of variables declaration//GEN-END:variables
}
