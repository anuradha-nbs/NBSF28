package Sources;

import com.sun.javafx.application.PlatformImpl;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * KeyBoard
 */
public class KeyBoard extends JDialog {

    private Stage stage1;
    private Stage stage2;
    private WebView browser1;
    private WebView browser2;
    private JFXPanel jfxPanel1;
    private JFXPanel jfxPanel2;
    private JButton swingButton;
    private WebEngine webEngine1;
    private WebEngine webEngine2;
    private JTextField textField;
//    final JDialog frame;
    private boolean isUnicode;
    private JTabbedPane tabbedPane;

    public KeyBoard() {
        initComponents();
//        frame = new JDialog();

//        frame.getContentPane().add(this);
//        frame.setMinimumSize(new Dimension(800, 550));
//        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//        frame.setResizable(false);
//        frame.setAlwaysOnTop(true);
//        frame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
//
//        frame.setVisible(true);
        setMinimumSize(new Dimension(800, 550));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
    }

    public KeyBoard(boolean isUnicode, JTextField textField) {
        this();
        this.textField = textField;
        this.isUnicode = isUnicode;
        if (isUnicode) {
            tabbedPane.setSelectedIndex(0);
        } else {
            tabbedPane.setSelectedIndex(1);
        }

    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();

        jfxPanel1 = new JFXPanel();
        jfxPanel2 = new JFXPanel();
        createScene();

        setLayout(new BorderLayout());
        tabbedPane.add(jfxPanel1, "Unicode");
        tabbedPane.add(jfxPanel2, "Amali");
        add(tabbedPane);
        swingButton = new JButton();
        swingButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        String textContent = "";
                        if (isUnicode) {
                            textContent = (String) webEngine1.executeScript("document.getElementById('box2').value;");
                        } else {
                            textContent = (String) webEngine2.executeScript("document.getElementById('box2').value;");
                        }
                        textField.setText(textContent);
                        JOptionPane.showMessageDialog(rootPane, textContent);
                        KeyBoard.this.dispose();
                    }
                });
            }
        });
        swingButton.setText("Set");

        add(swingButton, BorderLayout.SOUTH);
    }

    /**
     * createScene
     *
     * Note: Key is that Scene needs to be created and run on "FX user thread"
     * NOT on the AWT-EventQueue Thread
     *
     */
    private void createScene() {
        PlatformImpl.startup(new Runnable() {
            @Override
            public void run() {

                stage1 = new Stage();

                stage1.setTitle("");
                stage1.setResizable(true);

                stage2 = new Stage();

                stage2.setTitle("");
                stage2.setResizable(true);

                Group root1 = new Group();
                Group root2 = new Group();
                Scene scene1 = new Scene(root1, 100, 100);
                Scene scene2 = new Scene(root2, 100, 100);
                stage1.setScene(scene1);
                stage2.setScene(scene2);

                // Set up the embedded browser:
                browser1 = new WebView();
                webEngine1 = browser1.getEngine();
                File f1 = new File("Singlish.html");
                webEngine1.load("file:///" + f1.getAbsolutePath());

                browser2 = new WebView();
                webEngine2 = browser2.getEngine();
                File f2 = new File("UnicodeToAmali.html");
                webEngine2.load("file:///" + f2.getAbsolutePath());

                ObservableList<Node> children1 = root1.getChildren();
                ObservableList<Node> children2 = root2.getChildren();
                children1.add(browser1);
                children2.add(browser2);

                jfxPanel1.setScene(scene1);
                jfxPanel2.setScene(scene2);
            }
        });
    }
}
