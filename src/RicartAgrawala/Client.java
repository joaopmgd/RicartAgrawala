package RicartAgrawala;

/**
 * Created by joao on 03/09/15.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Thread{

    private ArrayList<Socket> socketList;
    private Process process;

    public Client(Process process){
        this.process = process;
        socketList = new ArrayList<Socket>();
    }

    public void connect(String ip, int port){
        try{
            Socket socket = new Socket(ip,port);
            socketList.add(socket);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void askPermission(){
        try {
            Message askPermission = new Message(process.getResourceName(),process.getPid());
            for(Socket s:socketList){
                boolean reply = false;
                while(!reply){
                    ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                    out.writeObject(askPermission);
                    ObjectInputStream input = new ObjectInputStream(s.getInputStream());
                    Message permission = (Message) input.readObject();
                    reply = permission.isReply();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(){
        try {
            askPermission();
            process.addClock();
            System.out.println("Process " + process.getPid() + " got permission to send messages.\nType the message that process " + process.getPid() + " will send:");
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            Message message = new Message(text,process.getClock(),process.getPid());
            for(Socket s:socketList){
                ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(message);
            }
            process.setSharedResource(process.getResourceName(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            for(Socket s : socketList)
                s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        sendMessage();
    }
}