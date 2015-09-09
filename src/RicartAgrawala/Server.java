package RicartAgrawala;

/**
 * Created on 03/09/15.
 * by
 * João Pedro M. G. Dias 511455
 * Andre Luiz Beltrami 489611
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private ArrayList<Socket> socketList;
    private ArrayList<ManageRequisition> streams;
    private Process process;

    public Server(int port, Process process){
        try {
            this.serverSocket = new ServerSocket(port);
            this.socketList = new ArrayList<Socket>();
            this.streams = new ArrayList<ManageRequisition>();
            this.process = process;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveMessage() {
        try {
            while (true) {
                Socket client = serverSocket.accept();
                socketList.add(client);
                ManageRequisition stream = new ManageRequisition(client,this);
                streams.add(stream);
                stream.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMessage(Message message){
        process.addMessage(message);
    }

    public void updateClock(int clockFromMessage){
        process.updateClock(clockFromMessage);
    }

    @Override
    public void run() {
        receiveMessage();
    }

    public void closeConnection(){
        try {
            for(ManageRequisition stream : streams)
                stream.closeConnection();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean askPermission(int resourceName, int messagePid){
        return process.askPermission(resourceName, messagePid);
    }

}
