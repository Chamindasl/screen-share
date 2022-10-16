package chams.open.screenshare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ScreenShareSender {

    public static void main(String[] args) {
        client("192.168.1.5", 8090);
    }

    private static void client(String serverAddr, int port) {

        try {
            Robot r = new Robot();
            Socket socket = new Socket(serverAddr, port);

            ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
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
                }catch (Exception ex) {
                    System.out.println("ex" + ex.getMessage());
                    ex.printStackTrace();
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
