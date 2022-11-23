package chams.open.junit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
        long now = (new Date().getTime() / 1000 / 20 * 1000 * 20);
        show("Employee" + now, now);


//        dispose();
    }

    private void show(String pathname, long now) {
        ImagePanel imp = new ImagePanel();
        BufferedImage img = null;
        String src = "C:\\Users\\camarasinghe\\OneDrive - Athlone Institute Of Technology\\del\\";
//        String src = "C:\\Users\\camarasinghe\\OneDrive - Microsoft\\Desktop\\Del\\";
        String dstP = "C:\\repo\\t\\";
        while (true) {
            pathname = "Employee" + now;
            try {
                if (lock.isSelected()) {

                    File input = new File(src + pathname + ".class");
                    File dst = new File(dstP + pathname + ".jpg");
                    Files.copy(input.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    img = ImageIO.read(input);
                    Date d = new Date(now);
                    closeButton.setText(d.toString());
//                lock.setSelected(false);
                } else {
                    File dst = new File(dstP + pathname + ".jpg");
                    img = ImageIO.read(dst);
                }
                break;
            } catch (Exception e) {
                now = now - 20_000;
            }
        }
//            l1.setIcon(new ImageIcon(scale(img)));
        l1.setIcon(new ImageIcon(resize(img, 1600)));
    }

    public static BufferedImage scale(BufferedImage src) {
        int w = (int) (src.getWidth() * .8);
        int h = (int) (src.getHeight() * .8);
        BufferedImage img =
                new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int x, y;
        int ww = src.getWidth();
        int hh = src.getHeight();
        int[] ys = new int[h];
        for (y = 0; y < h; y++)
            ys[y] = y * hh / h;
        for (x = 0; x < w; x++) {
            int newX = x * ww / w;
            for (y = 0; y < h; y++) {
                int col = src.getRGB(newX, ys[y]);
                img.setRGB(x, y, col);
            }
        }
        return img;
    }

    private static BufferedImage resize(BufferedImage src, int targetSize) {
        if (targetSize <= 0) {
            return src; //this can't be resized
        }
        int targetWidth = targetSize;
        int targetHeight = targetSize;
        float ratio = ((float) src.getHeight() / (float) src.getWidth());
        if (ratio <= 1) { //square or landscape-oriented image
            targetHeight = (int) Math.ceil((float) targetWidth * ratio);
        } else { //portrait image
            targetWidth = Math.round((float) targetHeight / ratio);
        }
        BufferedImage bi = new BufferedImage(targetWidth, targetHeight, src.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); //produces a balanced resizing (fast and decent quality)
        g2d.drawImage(src, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();
        return bi;
    }

    private void onCancel() {
        // add your code here if necessary
        long now = (new Date().getTime() / 1000 / 20 * 1000 * 20) - 20_000;
        show("Employee" + now, now);

    }

    private void onDep() {
        // add your code here if necessary
        // add your code here if necessary
        long now = (new Date().getTime() / 1000 / 20 * 1000 * 20) - 40_000;
        show("Employee" + now, now);
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
