package chams.open.screenshare;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ScreenShareServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8090);
        SenderThread senderThread = null;
        ReceiverThread receiverThread = null;
        while (true) {
            Socket client = socket.accept();
            String type = "server";
            if (senderThread == null) {
                System.out.println("sender connected");
                senderThread = new SenderThread(client);
//                new Thread(senderThread).start();
            } else {
                System.out.println("receiver connected");
                receiverThread = new ReceiverThread(client);
            }
            if (senderThread != null && receiverThread != null) {
                senderThread.setReceiverThread(receiverThread);
                new Thread(senderThread).start();
            }
        }

    }

}
