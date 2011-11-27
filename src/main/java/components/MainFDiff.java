package components;

import algorithms.Grain;
import algorithms.GrainBean;
import details.Icon;
import details.Laf;
import diretorioDiff.resultados.ResultadoArquivo;
import diretorioDiff.resultados.TipoResultado;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import wrap.Wrap;
import org.jdesktop.application.Action;

/**
 * MainFDiff
 * @author Fernanda Floriano Silva
 */
@SuppressWarnings("serial")
public class MainFDiff extends javax.swing.JFrame {

    private FileComponent fileComponent = new FileComponent();
    private static MainFDiff instance;

    public synchronized static MainFDiff getInstance() {
        return instance;
    }

    public synchronized static void setInstance(File file, List<ResultadoArquivo> result, int idDirectory) {
        if (instance != null) {
            instance.dispose();
        }
        instance = new MainFDiff(file, result, idDirectory);
    }

    public static synchronized void resetInstance() {
        instance = null;
    }

    /** Creates new form FileOverView
     * @param file
     * @param result
     * @param idDirectory  
     */
    public MainFDiff(File file, List<ResultadoArquivo> result, int idDirectory) {
        initComponents();
        Laf.setlaf();
        setLocationRelativeTo(null);
        setIconImage(Icon.getIcon());

        try {
            init(file);
            GranularityComponent.setCleanGranularity(new GrainBean(0, pane.getText().length()), pane,msgRefatory);
            showResult(result, idDirectory);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainFDiff.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFDiff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getIdColor(Random random) {
        return random.nextInt(100) + 156;
    }

    private void init(File file) throws MalformedURLException, IOException {
        new Wrap().setWrapPane(pane);
        fileComponent.submitFile(file, pane);
        panel.setBorder(BorderFactory.createTitledBorder(file.getName()));
    }

    /**
     * Show Diff
     */
    @Action
    public void back() {
        if (Warning.isVisible()) {
            Warning.dispose();
        }
        this.dispose();
    }

    private void showResult(List<ResultadoArquivo> listResult, int idDirectory) {
        List<ResultadoArquivo> orderResult = sort(listResult);
        for (ResultadoArquivo result : orderResult) {
            if (!result.isEscolhaHungaro()) {
                printResult(result, idDirectory);
            }
        }
        printResult(getHungarianChoice(listResult), idDirectory);
    }

    private ResultadoArquivo getHungarianChoice(List<ResultadoArquivo> listResult) {
        for (ResultadoArquivo result : listResult) {
            if (result.isEscolhaHungaro()) {
                return result;
            }
        }
        return listResult.get(0);
    }

    private void printResult(ResultadoArquivo result, int idDirectory) {

        if (result.getTipo() == TipoResultado.CHANGED) {
            showRefactory(result, idDirectory);
        } else {
            showWarning();
        }
    }

    public List<ResultadoArquivo> sort(List<ResultadoArquivo> list) {
        Collections.sort(list, new Comparator<ResultadoArquivo>() {

            @Override
            public int compare(ResultadoArquivo o1, ResultadoArquivo o2) {
                ResultadoArquivo p1 = o1;
                ResultadoArquivo p2 = o2;
                return p1.getSimilaridade() < p2.getSimilaridade() ? -1 : (p1.getSimilaridade() > p2.getSimilaridade() ? +1 : 0);
            }
        });

        return list;
    }

    private void showWarning() {
        Laf.setlaf();
        Warning.setLocationRelativeTo(null);
        Warning.setIconImage(Icon.getIcon());
        Warning.setVisible(true);
    }

    private void showRefactory(ResultadoArquivo result, int idDirectory) {
        switch (idDirectory) {
            case 1:
                setRefactory(result.getGrainsTo(), result.getPara().getArquivo().getName());
                break;
            case 2:
                setRefactory(result.getGrainsFrom(), result.getBase().getArquivo().getName());
                break;
        }
    }

    private void setRefactory(List<Grain> grains, String fileName) {
        Color color = getColor();
        String message = getMsgRefactory(fileName);

        for (Grain grain : grains) {
            GranularityComponent.setRefactoryGranularity(grain.getGrainBean(), pane, scrollPane, color, msgRefatory, message);
        }
    }

    private String getMsgRefactory(String fileName) {
        return "Similarity found in " + fileName;
    }

    private Color getColor() {
        Random random = new Random();
        Color color = new Color(getIdColor(random), getIdColor(random), getIdColor(random));
        return color;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Warning = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        warningMsg = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        pane = new javax.swing.JTextPane();
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        msgRefatory = new javax.swing.JTextField();

        Warning.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Warning.setTitle("Warning");
        Warning.setAlwaysOnTop(true);
        Warning.setFocusTraversalPolicyProvider(true);
        Warning.setMinimumSize(new java.awt.Dimension(234, 91));
        Warning.setName("Warning"); // NOI18N
        Warning.setResizable(false);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(MainFDiff.class);
        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setName("jLabel1"); // NOI18N

        warningMsg.setFont(resourceMap.getFont("warningMsg.font")); // NOI18N
        warningMsg.setForeground(new java.awt.Color(0, 0, 102));
        warningMsg.setText("Refactoring Not Found");
        warningMsg.setName("warningMsg"); // NOI18N

        javax.swing.GroupLayout WarningLayout = new javax.swing.GroupLayout(Warning.getContentPane());
        Warning.getContentPane().setLayout(WarningLayout);
        WarningLayout.setHorizontalGroup(
            WarningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WarningLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warningMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );
        WarningLayout.setVerticalGroup(
            WarningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(warningMsg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("File Overview");
        setResizable(false);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("File Name"));
        panel.setMaximumSize(new java.awt.Dimension(717, 619));
        panel.setMinimumSize(new java.awt.Dimension(717, 619));
        panel.setName("panel"); // NOI18N

        scrollPane.setName("scrollPane"); // NOI18N

        pane.setEditable(false);
        pane.setName("pane"); // NOI18N
        scrollPane.setViewportView(pane);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                .addContainerGap())
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jSeparator2.setName("jSeparator2"); // NOI18N
        jToolBar1.add(jSeparator2);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(MainFDiff.class, this);
        jButton1.setAction(actionMap.get("back")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jToolBar1.add(jSeparator1);

        msgRefatory.setEditable(false);
        msgRefatory.setToolTipText("");
        msgRefatory.setEnabled(false);
        msgRefatory.setFocusable(false);
        msgRefatory.setMaximumSize(new java.awt.Dimension(690, 28));
        msgRefatory.setMinimumSize(new java.awt.Dimension(690, 28));
        msgRefatory.setName("msgRefatory"); // NOI18N
        msgRefatory.setPreferredSize(new java.awt.Dimension(690, 28));
        jToolBar1.add(msgRefatory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 760, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Warning;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField msgRefatory;
    private javax.swing.JTextPane pane;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel warningMsg;
    // End of variables declaration//GEN-END:variables
    //  private JPanel panelLegend;
}
