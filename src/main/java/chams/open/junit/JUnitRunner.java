package chams.open.junit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class JUnitRunner {

    public static void main(String[] args){

        try {
            long now = new Date().getTime() / 1000 / 60 * 1000 * 60;
            long now10 = now + 10_000;
            Thread.sleep(now10 - now);
            Robot r = new Robot();
            String pa = "C:\\Users\\camarasinghe\\OneDrive - Microsoft\\Desktop\\Del\\";
            while (true) {
                write(r, pa + "Employee.class");
                write(r, pa + "Address.class");
                write(r, pa + "Department.class");
            }
        } catch (Exception ex) {

        }
    }

    private static void write(Robot r, String pathname) throws IOException, InterruptedException {
        BufferedImage img = r.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        File outputfile = new File(pathname);
        ImageIO.write(img, "jpg", outputfile);
        Thread.sleep(10_000);
    }
}
