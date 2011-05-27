package components;

import algorithms.Diff;
import algorithms.DiffException;
import algorithms.FileGrain;
import algorithms.Grain;
import algorithms.IResultDiff;
import algorithms.Result;
import java.awt.Cursor;
import java.awt.Dimension;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JToolBar.Separator;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class MainILCS extends javax.swing.JFrame {

    private File basedFile;
    private File comparedFile;
    private IResultDiff result = new Result();
    private DefaultTableModel model;

    /** Creates new form MainILCS */
    public MainILCS(File basedFile, File comparedFile) throws DiffException {
        initComponents();
        setlaf();
        result.cleanResult();
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/main/resources/components/icons/icon.png").getImage());
        setFiles(basedFile, comparedFile);
    }

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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exitDialog = new JDialog();
        noButton = new JButton();
        yesButton = new JButton();
        exitLabel = new JLabel();
        jLabel1 = new JLabel();
        mainButtonGroup = new ButtonGroup();
        toolBar = new JToolBar();
        jSeparator4 = new Separator();
        runMenuBar = new JButton();
        jSeparator1 = new Separator();
        fileSelectionMenuBar = new JButton();
        jSeparator3 = new Separator();
        overviewMenuBar = new JButton();
        jSeparator2 = new Separator();
        mainSplitPane = new JSplitPane();
        detailsScrollPane = new JScrollPane();
        detailsTextPane = new JTextPane();
        splitPaneUp = new JSplitPane();
        splitPaneLeft = new JSplitPane();
        dirScrollPane1 = new JScrollPane();
        dirTree1 = new JTree();
        dirScrollPane2 = new JScrollPane();
        dirTree2 = new JTree();
        splitPaneRight = new JSplitPane();
        baseFileScrollPane = new JScrollPane();
        baseFileEditorPane = new JEditorPane();
        comparedFileScrollPane = new JScrollPane();
        comparedFileEditorPane = new JEditorPane();
        jScrollPane1 = new JScrollPane();
        tableDetails = new JTable();
        menuBar = new JMenuBar();
        mainMenu = new JMenu();
        runSubMenu = new JMenuItem();
        fileSelectionSubMenu = new JMenuItem();
        closeSubMenu = new JMenuItem();
        aboutMenu = new JMenu();
        aboutProjectSubMenu = new JMenuItem();
        aboutTeamSubMenu = new JMenuItem();

        ResourceBundle bundle = ResourceBundle.getBundle("components/Bundle"); // NOI18N
        exitDialog.setTitle(bundle.getString("MainILCS.exitDialog.title")); // NOI18N

        noButton.setText(bundle.getString("MainILCS.noButton.text")); // NOI18N

        yesButton.setText(bundle.getString("MainILCS.yesButton.text")); // NOI18N

        exitLabel.setText(bundle.getString("MainILCS.exitLabel.text")); // NOI18N

        jLabel1.setIcon(new ImageIcon(getClass().getResource("/components/icons/icone_help.png"))); // NOI18N

        GroupLayout exitDialogLayout = new GroupLayout(exitDialog.getContentPane());
        exitDialog.getContentPane().setLayout(exitDialogLayout);
        exitDialogLayout.setHorizontalGroup(
            exitDialogLayout.createParallelGroup(GroupLayout.LEADING)
            .add(exitDialogLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(LayoutStyle.RELATED)
                .add(exitLabel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.RELATED)
                .add(yesButton)
                .addPreferredGap(LayoutStyle.RELATED)
                .add(noButton)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        exitDialogLayout.setVerticalGroup(
            exitDialogLayout.createParallelGroup(GroupLayout.LEADING)
            .add(exitDialogLayout.createSequentialGroup()
                .add(exitDialogLayout.createParallelGroup(GroupLayout.LEADING)
                    .add(exitDialogLayout.createSequentialGroup()
                        .add(31, 31, 31)
                        .add(exitDialogLayout.createParallelGroup(GroupLayout.BASELINE)
                            .add(exitLabel)
                            .add(yesButton)
                            .add(noButton)))
                    .add(jLabel1))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(bundle.getString("MainILCS.title")); // NOI18N
        setIconImages(null);
        setMinimumSize(new Dimension(1365, 700));
        setModalExclusionType(null);
        setName("frame"); // NOI18N
        setResizable(false);

        toolBar.setRollover(true);
        toolBar.setName("Menu Bar"); // NOI18N
        toolBar.add(jSeparator4);

        ActionMap actionMap = Application.getInstance().getContext().getActionMap(MainILCS.class, this);
        runMenuBar.setAction(actionMap.get("runProjectMB")); // NOI18N
        ResourceMap resourceMap = Application.getInstance().getContext().getResourceMap(MainILCS.class);
        runMenuBar.setIcon(resourceMap.getIcon("runMenuBar.icon")); // NOI18N
        runMenuBar.setToolTipText(resourceMap.getString("runMenuBar.toolTipText")); // NOI18N
        runMenuBar.setBorder(null);
        mainButtonGroup.add(runMenuBar);
        runMenuBar.setContentAreaFilled(false);
        runMenuBar.setFocusable(false);
        runMenuBar.setHorizontalTextPosition(SwingConstants.CENTER);
        runMenuBar.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolBar.add(runMenuBar);
        toolBar.add(jSeparator1);

        fileSelectionMenuBar.setAction(actionMap.get("fileSelectionMB")); // NOI18N
        fileSelectionMenuBar.setIcon(resourceMap.getIcon("fileSelectionMenuBar.icon")); // NOI18N
        fileSelectionMenuBar.setToolTipText(resourceMap.getString("fileSelectionMenuBar.toolTipText")); // NOI18N
        fileSelectionMenuBar.setBorderPainted(false);
        mainButtonGroup.add(fileSelectionMenuBar);
        fileSelectionMenuBar.setContentAreaFilled(false);
        fileSelectionMenuBar.setFocusable(false);
        fileSelectionMenuBar.setHorizontalTextPosition(SwingConstants.CENTER);
        fileSelectionMenuBar.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolBar.add(fileSelectionMenuBar);
        toolBar.add(jSeparator3);

        overviewMenuBar.setAction(actionMap.get("showDDiff")); // NOI18N
        overviewMenuBar.setIcon(resourceMap.getIcon("overviewMenuBar.icon")); // NOI18N
        overviewMenuBar.setToolTipText(resourceMap.getString("overviewMenuBar.toolTipText")); // NOI18N
        overviewMenuBar.setBorderPainted(false);
        mainButtonGroup.add(overviewMenuBar);
        overviewMenuBar.setContentAreaFilled(false);
        overviewMenuBar.setFocusable(false);
        overviewMenuBar.setHorizontalTextPosition(SwingConstants.CENTER);
        overviewMenuBar.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolBar.add(overviewMenuBar);
        toolBar.add(jSeparator2);

        mainSplitPane.setDividerLocation(500);
        mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        mainSplitPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        mainSplitPane.setOneTouchExpandable(true);

        detailsTextPane.setBorder(BorderFactory.createTitledBorder(bundle.getString("MainILCS.detailsTextPane.border.title"))); // NOI18N
        detailsScrollPane.setViewportView(detailsTextPane);

        mainSplitPane.setBottomComponent(detailsScrollPane);

        splitPaneUp.setDividerLocation(200);
        splitPaneUp.setOneTouchExpandable(true);

        splitPaneLeft.setDividerLocation(230);
        splitPaneLeft.setOrientation(JSplitPane.VERTICAL_SPLIT);

        dirScrollPane1.setViewportView(dirTree1);

        splitPaneLeft.setTopComponent(dirScrollPane1);

        dirScrollPane2.setViewportView(dirTree2);

        splitPaneLeft.setRightComponent(dirScrollPane2);

        splitPaneUp.setLeftComponent(splitPaneLeft);

        splitPaneRight.setDividerLocation(560);

        baseFileEditorPane.setBorder(BorderFactory.createTitledBorder(bundle.getString("MainILCS.baseFileEditorPane.border.title"))); // NOI18N
        baseFileScrollPane.setViewportView(baseFileEditorPane);

        splitPaneRight.setLeftComponent(baseFileScrollPane);

        comparedFileEditorPane.setBorder(BorderFactory.createTitledBorder(bundle.getString("MainILCS.comparedFileEditorPane.border.title"))); // NOI18N
        comparedFileScrollPane.setViewportView(comparedFileEditorPane);

        splitPaneRight.setRightComponent(comparedFileScrollPane);

        splitPaneUp.setRightComponent(splitPaneRight);

        mainSplitPane.setLeftComponent(splitPaneUp);

        tableDetails.setBorder(null);
        tableDetails.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Content", "From", "To"
            }
        ) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableDetails);
        tableDetails.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("MainILCS.tableDetails.columnModel.title0")); // NOI18N
        tableDetails.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("MainILCS.tableDetails.columnModel.title1")); // NOI18N
        tableDetails.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("MainILCS.tableDetails.columnModel.title2")); // NOI18N
        tableDetails.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("MainILCS.tableDetails.columnModel.title3")); // NOI18N

        mainSplitPane.setBottomComponent(jScrollPane1);

        mainMenu.setIcon(new ImageIcon(getClass().getResource("/components/icons/arquivo.png"))); // NOI18N
        mainMenu.setText(bundle.getString("MainILCS.mainMenu.text")); // NOI18N
        mainButtonGroup.add(mainMenu);
        mainMenu.setContentAreaFilled(false);
        mainMenu.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        mainMenu.setDisabledSelectedIcon(null);

        runSubMenu.setAction(actionMap.get("runProject")); // NOI18N
        runSubMenu.setIcon(resourceMap.getIcon("runSubMenu.icon")); // NOI18N
        mainButtonGroup.add(runSubMenu);
        runSubMenu.setName("Run Project"); // NOI18N
        mainMenu.add(runSubMenu);
        runSubMenu.getAccessibleContext().setAccessibleParent(mainMenu);

        fileSelectionSubMenu.setAction(actionMap.get("fileSelection")); // NOI18N
        fileSelectionSubMenu.setIcon(resourceMap.getIcon("fileSelectionSubMenu.icon")); // NOI18N
        mainButtonGroup.add(fileSelectionSubMenu);
        mainMenu.add(fileSelectionSubMenu);
        fileSelectionSubMenu.getAccessibleContext().setAccessibleParent(mainMenu);

        closeSubMenu.setAction(actionMap.get("close")); // NOI18N
        closeSubMenu.setIcon(resourceMap.getIcon("closeSubMenu.icon")); // NOI18N
        mainButtonGroup.add(closeSubMenu);
        mainMenu.add(closeSubMenu);

        menuBar.add(mainMenu);

        aboutMenu.setIcon(new ImageIcon(getClass().getResource("/components/icons/AboutMenu.png"))); // NOI18N
        aboutMenu.setText(bundle.getString("MainILCS.aboutMenu.text")); // NOI18N

        aboutProjectSubMenu.setAction(actionMap.get("showAboutProject")); // NOI18N
        aboutProjectSubMenu.setIcon(resourceMap.getIcon("aboutProjectSubMenu.icon")); // NOI18N
        mainButtonGroup.add(aboutProjectSubMenu);
        aboutMenu.add(aboutProjectSubMenu);

        aboutTeamSubMenu.setAction(actionMap.get("showAboutTeam")); // NOI18N
        aboutTeamSubMenu.setIcon(resourceMap.getIcon("aboutTeamSubMenu.icon")); // NOI18N
        mainButtonGroup.add(aboutTeamSubMenu);
        aboutMenu.add(aboutTeamSubMenu);

        menuBar.add(aboutMenu);

        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.LEADING)
            .add(toolBar, GroupLayout.DEFAULT_SIZE, 1365, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(mainSplitPane, GroupLayout.DEFAULT_SIZE, 1359, Short.MAX_VALUE)
                .add(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.UNRELATED)
                .add(mainSplitPane, GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startDiff(File basedFile, File comparedFile) throws DiffException {
        Grain grain = new FileGrain();
        Diff diff = new Diff(basedFile, comparedFile);
        result = diff.compare(grain);        
        int lines = printLines(result.getGrainsFrom(), result.getGrainsTo());
       // deleteRows(lines);
    }

    @Action
    public void runProject() throws DiffException {
        System.out.println("Run Project Action Executed");
        startDiff(getBasedFile(), getComparedFile());
    }

    @Action
    public void runProjectMB() {
        System.out.println("Run Project Action Executed");
    }

    @Action
    public void fileSelection() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new FileSelection().setVisible(true);
            }
        });
    }

    @Action
    public void fileSelectionMB() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new FileSelection().setVisible(true);
            }
        });
    }

    @Action
    public void showDDiff() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainDDiff().setVisible(true);
            }
        });
    }

    @Action
    public void close() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                System.exit(0);
            }
        });
    }

    @Action
    public void showAboutProject() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new AboutILCS().setVisible(true);
            }
        });
    }

    @Action
    public void showAboutTeam() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new AboutTeam().setVisible(true);
            }
        });
    }

    private int printLines(List<Grain> list1, List<Grain> list2) {
        Iterator<Grain> it1 = list1.iterator();
        Iterator<Grain> it2 = list2.iterator();
        int rowCount = 0;
        while (it1.hasNext() || it2.hasNext()) {
            Grain grain1 = it1.next();
            Grain grain2 = it2.next();
            if (((grain1 != null) || (grain2 != null)) && (!grain1.getOriginalReference().equals(grain2.getOriginalReference()))) {
                this.model = (DefaultTableModel) tableDetails.getModel();
                this.model.addRow(new String[]{"Move", grain1.getGrain(), printReference(grain1), printReference(grain2)});
                rowCount = this.model.getRowCount();
            }

        }
        return rowCount;
    }

    private String printReference(Grain grain) {
        char level = 'F';
        String stringResult = "";
        for (Iterator<Integer> it = grain.getOriginalReference().iterator(); it.hasNext();) {
            int id = it.next();
            stringResult = stringResult + getNameGrainLevel(getGrainLevel(level)) + " " + id;
        }
        return stringResult;
    }

    private String getNameGrainLevel(char levelGrain) {
        switch (levelGrain) {
            case 'F':
                return "File";
            case 'L':
                return "Line";
            case 'W':
                return "Word";
            case 'C':
                return "Character";
            default:
                return "File";
        }
    }

    private char getGrainLevel(char levelGrain) {
        switch (levelGrain) {
            case 'F':
                return 'L';
            case 'L':
                return 'W';
            case 'W':
                return 'C';
            default:
                return 'F';
        }
    }

    private void setFiles(File basedFile, File comparedFile) {
        setBasedFile(basedFile);
        setComparedFile(comparedFile);
    }

    public File getBasedFile() {
        return basedFile;
    }

    public void setBasedFile(File basedFile) {
        this.basedFile = basedFile;
    }

    public File getComparedFile() {
        return comparedFile;
    }

    public void setComparedFile(File comparedFile) {
        this.comparedFile = comparedFile;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JMenu aboutMenu;
    private JMenuItem aboutProjectSubMenu;
    private JMenuItem aboutTeamSubMenu;
    private JEditorPane baseFileEditorPane;
    private JScrollPane baseFileScrollPane;
    private JMenuItem closeSubMenu;
    private JEditorPane comparedFileEditorPane;
    private JScrollPane comparedFileScrollPane;
    private JScrollPane detailsScrollPane;
    private JTextPane detailsTextPane;
    private JScrollPane dirScrollPane1;
    private JScrollPane dirScrollPane2;
    private JTree dirTree1;
    private JTree dirTree2;
    private JDialog exitDialog;
    private JLabel exitLabel;
    private JButton fileSelectionMenuBar;
    private JMenuItem fileSelectionSubMenu;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private Separator jSeparator1;
    private Separator jSeparator2;
    private Separator jSeparator3;
    private Separator jSeparator4;
    private ButtonGroup mainButtonGroup;
    private JMenu mainMenu;
    private JSplitPane mainSplitPane;
    private JMenuBar menuBar;
    private JButton noButton;
    private JButton overviewMenuBar;
    private JButton runMenuBar;
    private JMenuItem runSubMenu;
    private JSplitPane splitPaneLeft;
    private JSplitPane splitPaneRight;
    private JSplitPane splitPaneUp;
    private JTable tableDetails;
    private JToolBar toolBar;
    private JButton yesButton;
    // End of variables declaration//GEN-END:variables

    private void deleteRows(int lines) {
        for (int i = 0; i < lines; i++) {
            this.model.removeRow(i);
            this.model = (DefaultTableModel) tableDetails.getModel();
        }
    }
}
