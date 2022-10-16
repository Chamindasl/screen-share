package chams.open;

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

    public SenderThread(Socket cSoc) throws IOException {
        socket = cSoc;
        in = new ObjectInputStream(socket.getInputStream());

    }


    @Override
    public void run() {

        while (true) {
            try {

                TDO tdo = (TDO) in.readObject();
                objectOutputStream.writeObject(tdo);
                objectOutputStream.flush();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

}
