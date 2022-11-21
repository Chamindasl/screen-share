package chams.open.junit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;

public class Test extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel imagePane;
    private JLabel l1;
    private JButton departmentButton;
    private JCheckBox lock;
    private JButton closeButton;

    public Test() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        departmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onDep();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        long now = (new Date().getTime() / 1000 / 20 * 1000 * 20) - 20_000;
        show("Employee"+ now, now);


//        dispose();
    }

    private void show(String pathname, long now) {
        Date d = new Date(now);
        closeButton.setText(d.toString());
        ImagePanel imp = new ImagePanel();
        BufferedImage img = null;
//        String src = "C:\\Users\\camarasinghe\\OneDrive - Athlone Institute Of Technology\\del\\";
        String src = "C:\\Users\\camarasinghe\\OneDrive - Microsoft\\Desktop\\Del\\";
        String dstP = "C:\\repo\\t\\";
        try {
            if (lock.isSelected()) {

                File input = new File(src + pathname + ".class");
                File dst = new File( dstP +pathname + ".jpg");
                Files.copy(input.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
                img = ImageIO.read(input);
//                lock.setSelected(false);
            } else {
                File dst = new File(dstP + pathname + ".jpg");
                img = ImageIO.read(dst);
            }
            l1.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        long now = (new Date().getTime() / 1000 / 20 * 1000 * 20) - 40_000;
        show("Employee"+ now, now);

    }

    private void onDep() {
        // add your code here if necessary
        // add your code here if necessary
        long now = (new Date().getTime() / 1000 / 20 * 1000 * 20) - 60_000;
        show("Employee"+ now, now);
    }
    public static void main(String[] args) {
        Test dialog = new Test();
        ImagePanel imp = new ImagePanel();
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("C:\\Users\\camarasinghe\\OneDrive - Microsoft\\Desktop\\Del\\Employee.class"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        imp.setImg(img);
        dialog.imagePane = imp;

        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
