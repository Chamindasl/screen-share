package chams.open.junit;

import java.net.Socket;

public class ReceiverThread {

    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ReceiverThread(Socket cSoc){
        socket = cSoc;
    }



}