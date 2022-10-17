package chams.open.screenshare;

import java.io.*;
import java.net.Socket;

public class SenderThread implements Runnable{

    private ReceiverThread receiverThread;
    private Socket socket;

    private ObjectInputStream  in;
    private ObjectOutputStream objectOutputStream;

    public ReceiverThread getReceiverThread() {
        return receiverThread;
    }

    public void setReceiverThread(ReceiverThread receiverThread) throws IOException {
        this.receiverThread = receiverThread;
        objectOutputStream = new ObjectOutputStream(receiverThread.getSocket().getOutputStream());
    }

    public SenderThread(Socket cSoc, ObjectInputStream in) throws IOException {
        socket = cSoc;
        this.in = in;

    }


    @Override
    public void run() {

        int tCount = 0;
        while (true) {
            try {

                TDO tdo = (TDO) in.readObject();
                objectOutputStream.writeObject(tdo);
                objectOutputStream.flush();
                tCount = 0;
            } catch (Exception ex) {
                tCount++;
                System.out.println(tCount + "c " + ex.getMessage());
                if(tCount>5) {
                    break;
                }
            }

        }
        System.out.println("closing server thread");
        throw new RuntimeException("lets crash");
    }

}
