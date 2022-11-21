package chams.open.junit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class JUnitRunner {

    public static void main(String[] args){

        while (true) {
            try {
                Robot r = new Robot();
                String pa = "C:\\Users\\camarasinghe\\OneDrive - Microsoft\\Desktop\\Del\\";
                while (true) {
                    long now = new Date().getTime() / 1000 / 60 * 1000 * 60;
                    write(r, pa + "Employee"+ now +".class");
                    write(r, pa + "Employee"+ (now + 20_000) +".class");
                    write(r, pa + "Employee"+ (now + 40_000) +".class");
                }
            } catch (Exception ex) {

            }
            try {
                Thread.sleep(1000*60);
            } catch (InterruptedException e) {
            }
        }
    }

    private static void write(Robot r, String pathname) throws IOException, InterruptedException {
        System.out.println(pathname);
        long now = new Date().getTime() ;
        long now_20 = ((now / 1000 / 20 * 20)  + 20) * 1000;
        BufferedImage img = r.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        File outputfile = new File(pathname);
        ImageIO.write(img, "jpg", outputfile);
        long millis = (now_20  - now) ;
        System.out.println(new Date());
        System.out.println(now);
        System.out.println(now_20);
        System.out.println("sleep " + (millis ) );
        if (millis > 0) {
            Thread.sleep(millis);
        }
    }
}
