package RicartAgrawala;

/**
 * Created by joao on 03/09/15.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ManageRequisition extends Thread{

    private Socket socket;
    private Server server;

    public ManageRequisition(Socket socket, Server server){
        this.socket=socket;
        this.server=server;
    }

    public void readMessage(){
        try {
            while(true) {
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) input.readObject();
                server.updateClock(message.getClockFromProcess());
                server.addMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        readMessage();
    }
}
