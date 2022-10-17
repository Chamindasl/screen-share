package chams.open.screenshare;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ScreenShareServer {

    public static void main(String[] args) throws IOException {
        while (true) {
            ServerSocket socket = new ServerSocket(8090);
            SenderThread senderThread = null;
            ReceiverThread receiverThread = null;
            int tCon = 0;
            try {
                while (true) {
                    Socket client = socket.accept();
                    ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                    TDO tdo = (TDO) in.readObject();
                    System.out.println(tdo.message);

                    String type = tdo.message;
                    if (type.equals("sender")) {
                        System.out.println("sender connected");
                        senderThread = new SenderThread(client, in);
                    } else if (type.equals("receiver")) {
                        System.out.println("receiver connected");
                        receiverThread = new ReceiverThread(client);
                    }
                    if (senderThread != null && receiverThread != null) {
                        senderThread.setReceiverThread(receiverThread);
                        new Thread(senderThread).start();
                    }
                }
            } catch (Exception ex) {
                try {
                    Thread.sleep(5 * 1000);
                    tCon++;
                    if (tCon > 5) {
                        if (socket != null) {
                            socket.close();
                            System.out.println("starting new server");
                        }
                    }
                }catch (Exception ex1) {

                }
                System.out.println("ex " + ex.getMessage());
            }
        }

    }

}
