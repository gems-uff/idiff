package components;

import algorithms.DiffException;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdesktop.application.Action;

/**
 * FileSelection
 * @author Fernanda Floriano Silva
 */
public class FileSelection extends javax.swing.JFrame {

    private JFileChooser fileChooser;

    /**
     * Constructor 
     */
    public FileSelection() {
        setlaf();
        setIconImage(new ImageIcon("src/main/resources/components/icons/logoIDiff.png").getImage());
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * Set Look and Feel
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
     * Show About Team
     */
    @Action
    public void showAboutTeam() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new AboutTeam().setVisible(true);
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

        buttonGroup = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        aboutTeam = new javax.swing.JToggleButton();
        okButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        compareWith1 = new javax.swing.JLabel();
        fileTextField = new javax.swing.JTextField();
        file3 = new javax.swing.JLabel();
        file4 = new javax.swing.JLabel();
        fileTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        granularityComboBox = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        checkBoxDiff = new javax.swing.JCheckBox();
        checkBoxMove = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        dot = new javax.swing.JCheckBox();
        semicolon = new javax.swing.JCheckBox();
        comma = new javax.swing.JCheckBox();
        key = new javax.swing.JCheckBox();
        brackets = new javax.swing.JCheckBox();
        parenthesis = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Comparable Artifacts Selection");
        setResizable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setName("jPanel3"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FileSelection.class, this);
        aboutTeam.setAction(actionMap.get("showAboutTeam")); // NOI18N
        buttonGroup.add(aboutTeam);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FileSelection.class);
        aboutTeam.setIcon(resourceMap.getIcon("aboutTeam.icon")); // NOI18N
        aboutTeam.setText(resourceMap.getString("aboutTeam.text")); // NOI18N
        aboutTeam.setToolTipText(resourceMap.getString("aboutTeam.toolTipText")); // NOI18N
        aboutTeam.setBorderPainted(false);
        aboutTeam.setContentAreaFilled(false);
        aboutTeam.setName("aboutTeam"); // NOI18N

        okButton.setAction(actionMap.get("loadFiles")); // NOI18N
        okButton.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        okButton.setIcon(resourceMap.getIcon("okButton.icon")); // NOI18N
        okButton.setToolTipText(resourceMap.getString("okButton.toolTipText")); // NOI18N
        okButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        okButton.setBorderPainted(false);
        buttonGroup.add(okButton);
        okButton.setContentAreaFilled(false);
        okButton.setName("okButton"); // NOI18N

        closeButton.setAction(actionMap.get("close")); // NOI18N
        closeButton.setFont(new java.awt.Font("sansserif", 1, 12));
        closeButton.setIcon(resourceMap.getIcon("closeButton.icon")); // NOI18N
        closeButton.setToolTipText(resourceMap.getString("closeButton.toolTipText")); // NOI18N
        closeButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        closeButton.setBorderPainted(false);
        buttonGroup.add(closeButton);
        closeButton.setContentAreaFilled(false);
        closeButton.setName("closeButton"); // NOI18N

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel6.setName("jPanel6"); // NOI18N

        compareWith1.setFont(new java.awt.Font("sansserif", 1, 12));
        compareWith1.setText("Compare with:");
        compareWith1.setName("compareWith1"); // NOI18N

        fileTextField.setText("Select directory or file...");
        fileTextField.setMaximumSize(new java.awt.Dimension(137, 28));
        fileTextField.setName("fileTextField"); // NOI18N
        fileTextField.setRequestFocusEnabled(false);
        fileTextField.setSelectionColor(new java.awt.Color(0, 0, 51));

        file3.setText("Artifact : ");
        file3.setName("file3"); // NOI18N

        file4.setText("Artifact : ");
        file4.setName("file4"); // NOI18N

        fileTextField2.setText("Select directory or file for comparison...");
        fileTextField2.setMaximumSize(new java.awt.Dimension(137, 28));
        fileTextField2.setName("fileTextField2"); // NOI18N
        fileTextField2.setPreferredSize(new java.awt.Dimension(137, 28));

        jButton1.setAction(actionMap.get("findArtifactFrom")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setToolTipText(resourceMap.getString("jButton1.toolTipText")); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setName("jButton1"); // NOI18N

        jButton2.setAction(actionMap.get("findArtifactTo")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setToolTipText(resourceMap.getString("jButton2.toolTipText")); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setName("jButton2"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(compareWith1)
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPanel6Layout.createSequentialGroup()
                                .add(file3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(fileTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 324, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel6Layout.createSequentialGroup()
                                .add(file4)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(fileTextField2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jButton2, 0, 0, Short.MAX_VALUE)
                            .add(jButton1))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(fileTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(file3))
                    .add(jButton1))
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(12, 12, 12)
                        .add(compareWith1)
                        .add(29, 29, 29)
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(file4)
                            .add(fileTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(49, 49, 49)
                        .add(jButton2)))
                .add(75, 75, 75))
        );

        jTabbedPane1.addTab("Artifacts Selection", jPanel6);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setName("jPanel2"); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel7.setName("jPanel7"); // NOI18N

        jLabel2.setText("Select granularity:");
        jLabel2.setName("jLabel2"); // NOI18N

        granularityComboBox.setBackground(new java.awt.Color(255, 255, 255));
        granularityComboBox.setMaximumRowCount(4);
        granularityComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Word (Default)", "File", "Line", "Character" }));
        granularityComboBox.setName("granularityComboBox"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel2)
                    .add(granularityComboBox, 0, 164, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(granularityComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel8.setName("jPanel8"); // NOI18N

        checkBoxDiff.setSelected(true);
        checkBoxDiff.setText("Show Differences");
        checkBoxDiff.setName("checkBoxDiff"); // NOI18N

        checkBoxMove.setSelected(true);
        checkBoxMove.setText("Show Moves");
        checkBoxMove.setName("checkBoxMove"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(checkBoxMove)
                    .add(checkBoxDiff))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .add(checkBoxDiff)
                .add(12, 12, 12)
                .add(checkBoxMove))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Tabs"));
        jPanel9.setName("jPanel9"); // NOI18N

        dot.setFont(new java.awt.Font("sansserif", 1, 12));
        dot.setSelected(true);
        dot.setText(".");
        dot.setName("dot"); // NOI18N
        dot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dotActionPerformed(evt);
            }
        });

        semicolon.setFont(new java.awt.Font("sansserif", 1, 12));
        semicolon.setSelected(true);
        semicolon.setText(";");
        semicolon.setName("semicolon"); // NOI18N

        comma.setFont(new java.awt.Font("sansserif", 1, 12));
        comma.setSelected(true);
        comma.setText(",");
        comma.setName("comma"); // NOI18N

        key.setFont(new java.awt.Font("sansserif", 1, 12));
        key.setSelected(true);
        key.setText("{ }");
        key.setName("key"); // NOI18N

        brackets.setFont(new java.awt.Font("sansserif", 1, 12));
        brackets.setSelected(true);
        brackets.setText("[ ]");
        brackets.setName("brackets"); // NOI18N

        parenthesis.setFont(new java.awt.Font("sansserif", 1, 12));
        parenthesis.setSelected(true);
        parenthesis.setText("( )");
        parenthesis.setName("parenthesis"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(dot)
                .add(39, 39, 39)
                .add(semicolon)
                .add(37, 37, 37)
                .add(comma)
                .add(32, 32, 32)
                .add(key, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(31, 31, 31)
                .add(brackets)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 45, Short.MAX_VALUE)
                .add(parenthesis)
                .add(15, 15, 15))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dot)
                    .add(parenthesis)
                    .add(semicolon)
                    .add(brackets)
                    .add(key)
                    .add(comma))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createSequentialGroup()
                        .add(jPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel8, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Parameters", jPanel2);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(aboutTeam, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 211, Short.MAX_VALUE)
                        .add(closeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 92, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 450, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(closeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(aboutTeam, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void dotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dotActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_dotActionPerformed

    /**
     * Find Artifact From
     */
    @Action
    public void findArtifactFrom() {
        findFile(fileTextField);
    }

    /**
     * Find Artifact To
     */
    @Action
    public void findArtifactTo() {
        findFile(fileTextField2);
    }

    /**
     * Find File
     * @param tField 
     */
    private void findFile(JTextField tField) {
        translateFileChooser();
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/main/resources/components/icons/logoIDiff.png").getImage());

        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("File Chooser");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.CANCEL_OPTION) {
            fileChooser.setVisible(false);
        } else {
            tField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            tField.setForeground(Color.BLACK);
        }
    }

    /**
     * Load Files
     * @throws DiffException
     * @throws FileNotFoundException
     * @throws IOException 
     */
    @Action
    public void loadFiles() throws DiffException, FileNotFoundException, IOException {
        System.out.println("Load Files...");
        if (filesOk()) {
            File artifact1 = new File(fileTextField.getText());
            File artifact2 = new File(fileTextField2.getText());

            if ((artifact1.isDirectory()) || (artifact2.isDirectory())) {
                showDDiff(artifact1, artifact2, (String) granularityComboBox.getSelectedItem(), checkBoxDiff.isSelected(), checkBoxMove.isSelected(), setTags());

            } else {
                showILCS(artifact1, artifact2, (String) granularityComboBox.getSelectedItem(), checkBoxDiff.isSelected(), checkBoxMove.isSelected(), setTags());
            }
            this.setVisible(false);

        } else {
            showError();
        }
    }

    /**
     * Set Tags
     * @return String
     */
    private String setTags() {
        String tag = "[\\s";
        tag = setTag(tag, dot.isSelected(), "\\.");
        tag = setTag(tag, semicolon.isSelected(), "\\;");
        tag = setTag(tag, comma.isSelected(), "\\,");
        tag = setTag(tag, parenthesis.isSelected(), "\\(\\)");
        tag = setTag(tag, brackets.isSelected(), "\\[\\]");
        tag = setTag(tag, key.isSelected(), "\\{\\}");
        return tag + "]";
    }

    private String setTag(String tag, boolean selected, String separator) {
        if (selected) {
            return tag + separator;
        }
        return tag;
    }

    /**
     * Show error frame
     */
    private void showError() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Error dialog = new Error(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            }
        });
    }

    /**
     * Verify if files ok
     * @return 
     */
    private boolean filesOk() {

        return (new File(fileTextField.getText()).exists()) && (new File(fileTextField2.getText()).exists());
    }

    /**
     * Create and Show Gui
     */
    private static void createAndShowGUI() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new FileSelection().setVisible(true);
            }
        });
    }

    /**
     * Main
     * @param args 
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    /**
     * Show ILCS
     * @param fileFrom
     * @param fileTo
     * @param granularity
     * @throws DiffException
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void showILCS(File fileFrom, File fileTo, String granularity, boolean showDiff, boolean showMove, String tags) throws DiffException, FileNotFoundException, IOException {
        MainILCS ilcs = new MainILCS(fileFrom, fileTo, granularity, showDiff, showMove,tags);
        ilcs.setVisible(true);
    }

    /**
     * Show Diff
     */
    private void showDDiff(File directoryFrom, File directoryTo, String granularity, boolean showDiff, boolean showMove, String tags) throws DiffException, FileNotFoundException, IOException {
        MainDDiff ddiff = new MainDDiff(directoryFrom, directoryTo, granularity, showDiff, showMove,tags);
        ddiff.setVisible(true);
    }

    /**
     * Close
     */
    @Action
    public void close() {
        this.setVisible(false);
    }

    /**
     * Translate File Chooser
     */
    private void translateFileChooser() {
        UIManager.put("FileChooser.lookInLabelMnemonic", "L");
        UIManager.put("FileChooser.lookInLabelText", "Look in");
        UIManager.put("FileChooser.saveInLabelMnemonic", "I");
        UIManager.put("FileChooser.saveInLabelText", "Save in");
        UIManager.put("FileChooser.upFolderToolTipText", "Up Folder");
        UIManager.put("FileChooser.upFolderAccessibleName", "Up Folder");
        UIManager.put("FileChooser.homeFolderToolTipText", "Home Folder");
        UIManager.put("FileChooser.homeFolderAccessibleName", "Home Folder");
        UIManager.put("FileChooser.newFolderToolTipText", "New Folder");
        UIManager.put("FileChooser.newFolderAccessibleName", "New Folder");
        UIManager.put("FileChooser.listViewButtonToolTipText", "List View");
        UIManager.put("FileChooser.listViewButtonAccessibleName", "List View");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Details");
        UIManager.put("FileChooser.detailsViewButtonAccessibleName", "Details");
        UIManager.put("FileChooser.fileNameLabelMnemonic", "N");
        UIManager.put("FileChooser.fileNameLabelText", "File Name");
        UIManager.put("FileChooser.filesOfTypeLabelMnemonic", "A");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Files of Type");
        UIManager.put("FileChooser.fileNameHeaderText", "Name");
        UIManager.put("FileChooser.fileSizeHeaderText", "File Size");
        UIManager.put("FileChooser.fileTypeHeaderText", "File Type");
        UIManager.put("FileChooser.fileDateHeaderText", "File Date");
        UIManager.put("FileChooser.fileAttrHeaderText", "File Attr");
        UIManager.put("FileChooser.cancelButtonText", "Cancel");
        UIManager.put("FileChooser.cancelButtonMnemonic", "C");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Cancel");
        UIManager.put("FileChooser.openButtonText", "Open");
        UIManager.put("FileChooser.openButtonMnemonic", "O");
        UIManager.put("FileChooser.openButtonToolTipText", "Open");
        UIManager.put("FileChooser.saveButtonText", "Save");
        UIManager.put("FileChooser.saveButtonToolTipText", "S");
        UIManager.put("FileChooser.saveButtonToolTipText", "Save");
        UIManager.put("FileChooser.updateButtonText", "Update");
        UIManager.put("FileChooser.updateButtonToolTipText", "U");
        UIManager.put("FileChooser.updateButtonToolTipText", "Update");
        UIManager.put("FileChooser.helpButtonText", "Help");
        UIManager.put("FileChooser.helpButtonToolTipText", "H");
        UIManager.put("FileChooser.helpButtonToolTipText", "Help");
        UIManager.put("FileChooser.acceptAllFileFilterText", "All files");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton aboutTeam;
    private javax.swing.JCheckBox brackets;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JCheckBox checkBoxDiff;
    private javax.swing.JCheckBox checkBoxMove;
    private javax.swing.JButton closeButton;
    private javax.swing.JCheckBox comma;
    private javax.swing.JLabel compareWith1;
    private javax.swing.JCheckBox dot;
    private javax.swing.JLabel file3;
    private javax.swing.JLabel file4;
    private javax.swing.JTextField fileTextField;
    private javax.swing.JTextField fileTextField2;
    private javax.swing.JComboBox granularityComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JCheckBox key;
    private javax.swing.JButton okButton;
    private javax.swing.JCheckBox parenthesis;
    private javax.swing.JCheckBox semicolon;
    // End of variables declaration//GEN-END:variables


}
