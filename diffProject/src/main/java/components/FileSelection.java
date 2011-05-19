package components;

import org.jdesktop.application.Action;

/**
 *
 * @author Sisi
 */
public class FileSelection extends javax.swing.JFrame {

    /** Creates new form FileSelection */
    public FileSelection() {
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
        fileTextField2 = new javax.swing.JTextField();
        file1 = new javax.swing.JLabel();
        file2 = new javax.swing.JLabel();
        fileTextField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        closeButton = new javax.swing.JButton();
        configurationsButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        fileButton1 = new javax.swing.JButton();
        fileButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("File Selection");

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FileSelection.class);
        fileTextField2.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        fileTextField2.setText("Select directory or file for comparison...");
        fileTextField2.setName("fileTextField2"); // NOI18N

        file1.setText("File 2: ");
        file1.setName("file1"); // NOI18N

        file2.setText("File 1: ");
        file2.setName("file2"); // NOI18N

        fileTextField.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        fileTextField.setText("Select directory or file...");
        fileTextField.setName("fileTextField"); // NOI18N
        fileTextField.setRequestFocusEnabled(false);
        fileTextField.setSelectionColor(new java.awt.Color(0, 0, 51));

        jSeparator1.setName("jSeparator1"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FileSelection.class, this);
        closeButton.setAction(actionMap.get("close")); // NOI18N
        closeButton.setIcon(resourceMap.getIcon("closeButton.icon")); // NOI18N
        closeButton.setToolTipText(resourceMap.getString("closeButton.toolTipText")); // NOI18N
        closeButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        closeButton.setBorderPainted(false);
        buttonGroup.add(closeButton);
        closeButton.setContentAreaFilled(false);
        closeButton.setName("closeButton"); // NOI18N

        configurationsButton.setAction(actionMap.get("configurations")); // NOI18N
        configurationsButton.setIcon(resourceMap.getIcon("configurationsButton.icon")); // NOI18N
        configurationsButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        configurationsButton.setBorderPainted(false);
        buttonGroup.add(configurationsButton);
        configurationsButton.setContentAreaFilled(false);
        configurationsButton.setName("configurationsButton"); // NOI18N

        okButton.setAction(actionMap.get("loadFiles")); // NOI18N
        okButton.setIcon(resourceMap.getIcon("okButton.icon")); // NOI18N
        okButton.setToolTipText(resourceMap.getString("okButton.toolTipText")); // NOI18N
        okButton.setBorder(null);
        buttonGroup.add(okButton);
        okButton.setContentAreaFilled(false);
        okButton.setName("okButton"); // NOI18N

        fileButton1.setAction(actionMap.get("searchFile")); // NOI18N
        fileButton1.setIcon(resourceMap.getIcon("fileButton1.icon")); // NOI18N
        fileButton1.setToolTipText(resourceMap.getString("fileButton1.toolTipText")); // NOI18N
        fileButton1.setBorder(null);
        fileButton1.setBorderPainted(false);
        buttonGroup.add(fileButton1);
        fileButton1.setContentAreaFilled(false);
        fileButton1.setName("fileButton1"); // NOI18N

        fileButton2.setAction(actionMap.get("searchFile")); // NOI18N
        fileButton2.setIcon(resourceMap.getIcon("fileButton2.icon")); // NOI18N
        fileButton2.setToolTipText(resourceMap.getString("fileButton2.toolTipText")); // NOI18N
        fileButton2.setBorder(null);
        fileButton2.setBorderPainted(false);
        buttonGroup.add(fileButton2);
        fileButton2.setContentAreaFilled(false);
        fileButton2.setName("fileButton2"); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(file2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(fileTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE))
                            .add(layout.createSequentialGroup()
                                .add(file1)
                                .add(12, 12, 12)
                                .add(fileTextField2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(fileButton1)
                            .add(fileButton2)))
                    .add(layout.createSequentialGroup()
                        .add(configurationsButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 356, Short.MAX_VALUE)
                        .add(closeButton)
                        .add(18, 18, 18)
                        .add(okButton)
                        .add(6, 6, 6)))
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(23, 23, 23)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(fileTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(file2))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(fileButton1))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(8, 8, 8)
                        .add(fileButton2))
                    .add(layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(file1)
                            .add(fileTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .add(18, 18, 18)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(okButton)
                    .add(closeButton)
                    .add(configurationsButton))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new FileSelection().setVisible(true);
            }
        });
    }

    @Action
    public void searchFile() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new File().setVisible(true);
            }
        });
    }

    @Action
    public void loadFiles() {
        // TODO implementar
        System.out.println("Load Files...");
    }

    @Action
    public void close() {
        this.setVisible(false);
    }

    @Action
    public void configurations() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Configurations().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton configurationsButton;
    private javax.swing.JLabel file1;
    private javax.swing.JLabel file2;
    private javax.swing.JButton fileButton1;
    private javax.swing.JButton fileButton2;
    private javax.swing.JTextField fileTextField;
    private javax.swing.JTextField fileTextField2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}
