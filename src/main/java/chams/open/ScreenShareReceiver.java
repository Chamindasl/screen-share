package chams.open;

import chams.open.old.ImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ScreenShareReceiver {
    public static void main(String[] args) {
        client("192.168.1.5", 8090);
    }

    private static void client(String serverAddr, int port) {
        JFrame frame = new JFrame();
        ImagePanel panel = new ImagePanel();
        frame.setResizable(true);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        try {
            BufferedImage image;
            Robot r = new Robot();
            Socket socket = new Socket(serverAddr, port);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            while (true) {
                try {
                    TDO tdo = (TDO) inputStream.readObject();
                    ByteArrayInputStream bai = new ByteArrayInputStream(tdo.bytes);
                    image = ImageIO.read(bai);
                    panel.setImg(image);
                    panel.repaint();
                } catch (Exception ex) {

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
