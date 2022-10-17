package chams.open.screenshare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ScreenShareSender {

    public static void main(String[] args) {
        client("localhost", 8090);
//        client("4.227.147.186", 8090);
    }

    private static void client(String serverAddr, int port) {

        while(true) {
            try {
                Robot r = new Robot();
                Socket socket = new Socket(serverAddr, port);
                int tCount = 0;
                ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                TDO tdo1 = new TDO();
                tdo1.message = "sender";
                outstream.writeObject(tdo1);
                outstream.flush();
                while (true) {
                    try {
                        BufferedImage img;
                        img = r.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                        Point mouse = MouseInfo.getPointerInfo().getLocation();
                        Graphics g = img.getGraphics();
                        g.setColor(Color.BLACK);
                        g.fillRect(mouse.x, mouse.y, 30, 30);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(img, "JPEG", baos);
                        TDO tdo = new TDO();
                        tdo.message = new Date().toString();
                        tdo.bytes = baos.toByteArray();
                        outstream.writeObject(tdo);
                        outstream.flush();
                        Thread.sleep(1000);
                        tCount = 0;
                    } catch (Exception ex) {
                        tCount++;
                        if (tCount > 5) {
                            System.out.println(" ex occured");
                            break;
                        }
                    }
                }
                Thread.sleep(10 * 1000);
            } catch (Exception e) {
            }
        }

    }


}
