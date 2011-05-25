package components;

import javax.swing.ImageIcon;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class AboutILCS extends javax.swing.JFrame {

    /** Creates new form AboutILCS */
    public AboutILCS() {
        initComponents();
        setLocationRelativeTo(null);        
        setIconImage(new ImageIcon("src/main/resources/components/icons/icon.png").getImage());

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About Project");
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setLocationByPlatform(true);
        setResizable(false);

        jLabel1.setText("Sobre o Diff...Sobre o Diff...Sobre o Diff");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("Sobre o Diff...Sobre o Diff...Sobre o Diff");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText("Sobre o Diff...Sobre o Diff...Sobre o Diff");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("Sobre o Diff...Sobre o Diff...Sobre o Diff");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/components/icons/AboutMenu.png"))); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText("Sobre o Diff...Sobre o Diff...Sobre o Diff");
        jLabel6.setName("jLabel6"); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 210, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(layout.createSequentialGroup()
                            .add(jLabel6, 0, 0, Short.MAX_VALUE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                            .add(jLabel5))
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel2)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel3)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel4)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(14, 14, 14)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel5)
                    .add(jLabel6))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
