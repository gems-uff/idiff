package gui;

import idiff.resources.Icon;
import idiff.resources.Laf;
import org.jdesktop.application.Action;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class Error extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;

    /** Creates new form Error
     * @param parent
     * @param modal  
     */
    public Error(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        Laf.setlaf();
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(Icon.getIcon());
    }

    /**
     * 
     * @param errorLabel 
     */
    public void setErrorLabel(String errorLabel) {
        this.errorLabel.setText(errorLabel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        errorLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Error");

        errorLabel.setFont(new java.awt.Font("sansserif", 1, 12));
        errorLabel.setText("  Please enter a valid file ");
        errorLabel.setName("errorLabel"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(Error.class, this);
        closeButton.setAction(actionMap.get("closeError")); // NOI18N
        closeButton.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(Error.class);
        closeButton.setText(resourceMap.getString("closeButton.text")); // NOI18N
        closeButton.setName("closeButton"); // NOI18N

        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(errorLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, closeButton))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(errorLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 64, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(closeButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Close Error Frame
     */
    @Action
    public void closeError() {
        this.dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
