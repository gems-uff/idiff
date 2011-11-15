package components;

import details.Scroll;
import algorithms.Diff;
import algorithms.DiffException;
import algorithms.FileGrain;
import algorithms.Grain;
import algorithms.ILCSBean;
import algorithms.IResultDiff;
import algorithms.Result;
import details.Icon;
import details.Laf;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JToolBar.Separator;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;
import wrap.Wrap;

/**
 * MainILCS
 * @author Fernanda Floriano Silva
 */
@SuppressWarnings("serial")
public final class MainILCS extends javax.swing.JFrame {

    private static MainILCS instance;

    public synchronized static MainILCS getInstance(File fileFrom, File fileTo, String granularity, String tags, boolean isQuiteSimilar) throws DiffException, FileNotFoundException, IOException {
        if (instance != null) {
            instance.dispose();
        }
        instance = new MainILCS(fileFrom, fileTo, granularity, tags, isQuiteSimilar);
        return instance;
    }

    public static synchronized void resetInstance() {
        instance = null;
    }

    /**
     * Creates new form MainILCS 
     * @param fileFrom
     * @param fileTo
     * @param granularity
     * @param tags 
     * @param isQuiteSimilar 
     * @throws DiffException
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public MainILCS(File fileFrom, File fileTo, String granularity, String tags, boolean isQuiteSimilar) throws DiffException, FileNotFoundException, IOException {
        Splash splash = new Splash();
        splash.setVisible(true);
        splash.setMessage("Starting components...");

        initComponents();

        new Wrap().setWrap(leftPane, rightPane);
        ilcsBean = new ILCSBean(fileFrom, fileTo);

        splash.setMessage("Initial Steps...");

        initialSteps(fileFrom, fileTo, granularity, tags, getPerspective(isQuiteSimilar));
        Scroll.adjustmentScroll(leftScrollPane, rightScrollPane);

        setListenerRadioButton();

        splash.setMessage("Loading Files...");
        splash.setMessage("Runing ...");
        splash.dispose();

        runProject();

    }

    /**
     * Set Listener for checkBox
     */
    private void setListenerRadioButton() throws FileNotFoundException, IOException {
        setListenerRadioButton(diffRadioButton, 1);
        setListenerRadioButton(similarRadioButton, 2);
    }

    public int getPerspective(boolean isQuiteSimilar) {
        if (isQuiteSimilar) {
            this.diffRadioButton.setSelected(true);
            this.similarRadioButton.setSelected(false);
            return 1;
        } else {
            this.diffRadioButton.setSelected(false);
            this.similarRadioButton.setSelected(true);
            return 2;
        }
    }

    /**
     * Set Listener Diff CheckBox
     */
    private void setListenerRadioButton(JRadioButton radio, final int idPerspective) {

        radio.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                ilcsBean.setPerspective(idPerspective);
                restartComponents();
            }
        });
    }

    /** ReStart Components
     * 
     */
    private void restartComponents() {
        try {
            tableComponent.cleanTabelModel(tableDetails);
            fileComponent.clear(leftPane, rightPane);
            startDiff(ilcsBean.getFileFrom(), ilcsBean.getFileTo());
        } catch (DiffException ex) {
            Logger.getLogger(MainILCS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainILCS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainILCS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initial Steps
     * @param fileFrom
     * @param fileTo
     * @param granularity
     * @param showDiff
     * @param showMove
     * @throws IOException 
     */
    private void initialSteps(File fileFrom, File fileTo, String granularity, String tags, int perspective) throws IOException {
        init();
        initFiles(fileFrom, fileTo);
        initParameters(granularity, tags, perspective);
        setLayoutPane();
    }

    /**
     * Set layout pane
     */
    private void setLayoutPane() {
        leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.PAGE_AXIS));
        rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.PAGE_AXIS));
    }

    /**
     * Init Files
     * @param fileFrom
     * @param fileTo
     * @throws IOException 
     */
    private void initFiles(File fileFrom, File fileTo) throws IOException {
        setFiles(fileFrom, fileTo);
        loadTreeFiles(fileFrom, fileTo);
        fileComponent.showFiles(fileFrom, fileTo, leftPane, rightPane);
    }

    /**
     * Init 
     */
    private void init() {
        Laf.setlaf();
        setLocationRelativeTo(null);
        setIconImage(Icon.getIcon());
    }

    private String setWordGrainName(String granularity) {
        if ("Word (Default)".equals(granularity)) {
            return "Word";
        }
        return granularity;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exitDialog = new JDialog();
        noButton = new JButton();
        yesButton = new JButton();
        exitLabel = new JLabel();
        jLabel1 = new JLabel();
        mainButtonGroup = new ButtonGroup();
        buttonGroup = new ButtonGroup();
        toolBar = new JToolBar();
        jButton1 = new JButton();
        jSeparator4 = new Separator();
        fileSelectionMenuBar = new JButton();
        jSeparator3 = new Separator();
        diffRadioButton = new JRadioButton();
        jSeparator6 = new Separator();
        similarRadioButton = new JRadioButton();
        jSeparator2 = new Separator();
        jTextField5 = new JTextField();
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();
        jTextField6 = new JTextField();
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
        leftScrollPane = new JScrollPane();
        leftPane = new JTextPane();
        rightScrollPane = new JScrollPane();
        rightPane = new JTextPane();
        jScrollPane1 = new JScrollPane();
        tableDetails = new JTable();

        exitDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(bundle.getString("MainILCS.title")); // NOI18N
        setIconImages(null);
        setMinimumSize(new Dimension(1365, 700));
        setModalExclusionType(null);
        setName("frame"); // NOI18N

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.setName("Menu Bar"); // NOI18N

        ActionMap actionMap = Application.getInstance().getContext().getActionMap(MainILCS.class, this);
        jButton1.setAction(actionMap.get("backToDDiff")); // NOI18N
        jButton1.setIcon(new ImageIcon(getClass().getResource("/components/icons/back.png"))); // NOI18N
        jButton1.setText(bundle.getString("MainILCS.jButton1.text_2")); // NOI18N
        jButton1.setToolTipText(bundle.getString("MainILCS.jButton1.toolTipText")); // NOI18N
        jButton1.setBorderPainted(false);
        mainButtonGroup.add(jButton1);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolBar.add(jButton1);
        toolBar.add(jSeparator4);

        fileSelectionMenuBar.setAction(actionMap.get("fileSelection")); // NOI18N
        ResourceMap resourceMap = Application.getInstance().getContext().getResourceMap(MainILCS.class);
        fileSelectionMenuBar.setIcon(resourceMap.getIcon("fileSelectionMenuBar.icon")); // NOI18N
        fileSelectionMenuBar.setToolTipText(resourceMap.getString("fileSelectionMenuBar.toolTipText")); // NOI18N
        mainButtonGroup.add(fileSelectionMenuBar);
        fileSelectionMenuBar.setContentAreaFilled(false);
        fileSelectionMenuBar.setFocusable(false);
        fileSelectionMenuBar.setHorizontalTextPosition(SwingConstants.CENTER);
        fileSelectionMenuBar.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolBar.add(fileSelectionMenuBar);
        toolBar.add(jSeparator3);

        buttonGroup.add(diffRadioButton);
        diffRadioButton.setSelected(true);
        diffRadioButton.setText(bundle.getString("MainILCS.diffRadioButton.text")); // NOI18N
        diffRadioButton.setFocusable(false);
        diffRadioButton.setHorizontalTextPosition(SwingConstants.CENTER);
        diffRadioButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolBar.add(diffRadioButton);
        toolBar.add(jSeparator6);

        buttonGroup.add(similarRadioButton);
        similarRadioButton.setText(bundle.getString("MainILCS.similarRadioButton.text")); // NOI18N
        similarRadioButton.setFocusable(false);
        similarRadioButton.setHorizontalTextPosition(SwingConstants.CENTER);
        similarRadioButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolBar.add(similarRadioButton);
        toolBar.add(jSeparator2);

        jTextField5.setEditable(false);
        jTextField5.setText(bundle.getString("MainILCS.jTextField5.text")); // NOI18N
        jTextField5.setEnabled(false);
        toolBar.add(jTextField5);

        jTextField1.setEditable(false);
        jTextField1.setText(bundle.getString("MainILCS.jTextField1.text")); // NOI18N
        toolBar.add(jTextField1);

        jTextField2.setBackground(new Color(255, 174, 185));
        jTextField2.setEditable(false);
        jTextField2.setText(bundle.getString("MainILCS.jTextField2.text")); // NOI18N
        toolBar.add(jTextField2);

        jTextField3.setBackground(new Color(193, 255, 193));
        jTextField3.setEditable(false);
        jTextField3.setText(bundle.getString("MainILCS.jTextField3.text")); // NOI18N
        toolBar.add(jTextField3);

        jTextField4.setBackground(new Color(126, 192, 238));
        jTextField4.setEditable(false);
        jTextField4.setText(bundle.getString("MainILCS.jTextField4.text")); // NOI18N
        toolBar.add(jTextField4);

        jTextField6.setBackground(new Color(53, 94, 121));
        jTextField6.setEditable(false);
        jTextField6.setForeground(new Color(255, 255, 255));
        jTextField6.setText(bundle.getString("MainILCS.jTextField6.text")); // NOI18N
        toolBar.add(jTextField6);

        mainSplitPane.setDividerLocation(500);
        mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        mainSplitPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        mainSplitPane.setOneTouchExpandable(true);

        detailsScrollPane.setAutoscrolls(true);

        detailsTextPane.setBorder(BorderFactory.createTitledBorder(bundle.getString("MainILCS.detailsTextPane.border.title"))); // NOI18N
        detailsScrollPane.setViewportView(detailsTextPane);

        mainSplitPane.setBottomComponent(detailsScrollPane);

        splitPaneUp.setBorder(BorderFactory.createEtchedBorder());
        splitPaneUp.setDividerLocation(200);
        splitPaneUp.setOneTouchExpandable(true);

        splitPaneLeft.setDividerLocation(230);
        splitPaneLeft.setOrientation(JSplitPane.VERTICAL_SPLIT);

        dirScrollPane1.setBorder(BorderFactory.createTitledBorder(bundle.getString("MainILCS.dirScrollPane1.border.title"))); // NOI18N
        dirScrollPane1.setAutoscrolls(true);

        dirTree1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DefaultMutableTreeNode treeNode1 = new DefaultMutableTreeNode("root");
        dirTree1.setModel(new DefaultTreeModel(treeNode1));
        dirTree1.setAutoscrolls(true);
        dirScrollPane1.setViewportView(dirTree1);

        splitPaneLeft.setTopComponent(dirScrollPane1);

        dirScrollPane2.setBorder(BorderFactory.createTitledBorder(bundle.getString("MainILCS.dirScrollPane2.border.title"))); // NOI18N
        dirScrollPane2.setAutoscrolls(true);

        treeNode1 = new DefaultMutableTreeNode("root");
        dirTree2.setModel(new DefaultTreeModel(treeNode1));
        dirTree2.setAutoscrolls(true);
        dirScrollPane2.setViewportView(dirTree2);

        splitPaneLeft.setRightComponent(dirScrollPane2);

        splitPaneUp.setLeftComponent(splitPaneLeft);

        splitPaneRight.setDividerLocation(560);
        splitPaneRight.setDividerSize(40);
        splitPaneRight.setAutoscrolls(true);

        leftScrollPane.setBorder(BorderFactory.createTitledBorder(bundle.getString("MainILCS.leftScrollPane.border.title"))); // NOI18N

        leftPane.setBorder(null);
        leftPane.setMaximumSize(new Dimension(800, 600));
        leftPane.setMinimumSize(new Dimension(102, 18));
        leftScrollPane.setViewportView(leftPane);

        splitPaneRight.setLeftComponent(leftScrollPane);

        rightScrollPane.setBorder(BorderFactory.createTitledBorder(bundle.getString("MainILCS.rightScrollPane.border.title"))); // NOI18N

        rightPane.setMaximumSize(new Dimension(800, 600));
        rightPane.setMinimumSize(new Dimension(102, 18));
        rightPane.setPreferredSize(new Dimension(131, 65));
        rightScrollPane.setViewportView(rightPane);

        splitPaneRight.setRightComponent(rightScrollPane);

        splitPaneUp.setRightComponent(splitPaneRight);

        mainSplitPane.setLeftComponent(splitPaneUp);

        jScrollPane1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jScrollPane1.setAutoscrolls(true);

        tableDetails.setAutoCreateRowSorter(true);
        tableDetails.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Content", "Situation", "From (Left)", "To (Right)"
            }
        ) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableDetails.setToolTipText(bundle.getString("MainILCS.tableDetails.toolTipText")); // NOI18N
        tableDetails.setColumnSelectionAllowed(true);
        tableDetails.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableDetails);
        tableDetails.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableDetails.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("MainILCS.tableDetails.columnModel.title1")); // NOI18N
        tableDetails.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("MainILCS.tableDetails.columnModel.title0")); // NOI18N
        tableDetails.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("MainILCS.tableDetails.columnModel.title2")); // NOI18N
        tableDetails.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("MainILCS.tableDetails.columnModel.title3")); // NOI18N

        mainSplitPane.setBottomComponent(jScrollPane1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.LEADING)
            .add(toolBar, GroupLayout.DEFAULT_SIZE, 1389, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(mainSplitPane, GroupLayout.DEFAULT_SIZE, 1377, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.RELATED)
                .add(mainSplitPane, GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Start Diff
     * @param fileFrom
     * @param fileTo
     * @throws DiffException 
     */
    private void startDiff(File fileFrom, File fileTo) throws DiffException, FileNotFoundException, IOException {
        Grain grain = new FileGrain();
        tableComponent.cleanTabelModel(tableDetails);
        Diff diff = new Diff(fileFrom, fileTo);
        result.cleanResult();
        result = diff.compare(grain, ilcsBean);
        startTable();
        startComponent();
    }

    /**
     * Start Table
     */
    private void startTable() {
        tableComponent.printTableLines(result.getGrainsFrom(), result.getGrainsTo(), result.getDifferences(), tableDetails, ilcsBean);
    }

    /**
     * Start Component
     * @throws IOException 
     */
    private void startComponent() throws IOException {
        fileComponent.repaint(result, leftPane, leftScrollPane, rightPane, rightScrollPane, ilcsBean);
    }

    /**
     * Run Project
     * @throws DiffException
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void runProject() throws DiffException, FileNotFoundException, IOException {
        startDiff(ilcsBean.getFileFrom(), ilcsBean.getFileTo());
    }

    /**
     * File Selection
     */
    @Action
    public void fileSelection() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                FileSelection.getInstance();
            }
        });
    }

    /**
     * Show Diff
     */
    @Action
    public void backToDDiff() {
        this.dispose();
    }

    /**
     * Load Tree Files
     * @param fileFrom
     * @param fileTo 
     */
    private void loadTreeFiles(File fileFrom, File fileTo) {
        try {
            treeComponent.constructTree(dirTree1, fileFrom, leftScrollPane, "Left ");
            treeComponent.constructTree(dirTree2, fileTo, rightScrollPane, "Right ");
        } catch (SecurityException ex) {
            Logger.getLogger(MainILCS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainILCS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Set Files
     * @param fileFrom
     * @param fileTo 
     */
    private void setFiles(File fileFrom, File fileTo) {
        ilcsBean.setFileFrom(fileFrom);
        ilcsBean.setFileTo(fileTo);
    }

    /**
     * Init Parameters
     * @param granularity
     * @param trimLine
     * @param emptyLine
     * @param whiteSpace 
     */
    private void initParameters(String granularity, String tags, int perspective) {
        ilcsBean.setGranularity(setWordGrainName(granularity));
        ilcsBean.setTags(tags);
        ilcsBean.setPerspective(perspective);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonGroup buttonGroup;
    private JScrollPane detailsScrollPane;
    private JTextPane detailsTextPane;
    private JRadioButton diffRadioButton;
    private JScrollPane dirScrollPane1;
    private JScrollPane dirScrollPane2;
    private JTree dirTree1;
    private JTree dirTree2;
    private JDialog exitDialog;
    private JLabel exitLabel;
    private JButton fileSelectionMenuBar;
    private JButton jButton1;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private Separator jSeparator2;
    private Separator jSeparator3;
    private Separator jSeparator4;
    private Separator jSeparator6;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;
    private JTextField jTextField5;
    private JTextField jTextField6;
    private JTextPane leftPane;
    private JScrollPane leftScrollPane;
    private ButtonGroup mainButtonGroup;
    private JSplitPane mainSplitPane;
    private JButton noButton;
    private JTextPane rightPane;
    private JScrollPane rightScrollPane;
    private JRadioButton similarRadioButton;
    private JSplitPane splitPaneLeft;
    private JSplitPane splitPaneRight;
    private JSplitPane splitPaneUp;
    private JTable tableDetails;
    private JToolBar toolBar;
    private JButton yesButton;
    // End of variables declaration//GEN-END:variables
    private ILCSBean ilcsBean;
    private IResultDiff result = new Result();
    private FileComponent fileComponent = new FileComponent();
    private TableComponent tableComponent = new TableComponent();
    private TreeComponent treeComponent = new TreeComponent();
}
