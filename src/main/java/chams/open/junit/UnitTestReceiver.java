package chams.open.junit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UnitTestReceiver {
    public static void main(String[] args) {
//        client("localhost", 8090);
        int port = 80;

        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception ex) {

        }

        client("4.227.147.186", port);

    }

    private static void client(String serverAddr, int port) {
        JFrame frame = new JFrame();
        ImagePanel panel = new ImagePanel();
        frame.setResizable(true);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        while (true) {
            try {
                Socket socket = new Socket(serverAddr, port);

                ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());

                TDO tdo1 = new TDO();
                tdo1.message = "receiver";
                outstream.writeObject(tdo1);
                outstream.flush();

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                int tCon = 0;
                while (true) {
                    try {
                        TDO tdo = (TDO) inputStream.readObject();
                        ByteArrayInputStream bai = new ByteArrayInputStream(tdo.bytes);
                        BufferedImage image = ImageIO.read(bai);
                        panel.setImg(image);
                        panel.repaint();
                        tCon = 0;
                    } catch (Exception ex) {
                        tCon++;
                        if (tCon > 5) {
                            System.out.println("ex " + ex.getMessage());
                            break;
                        }
                        Thread.sleep(5 * 1000);
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
