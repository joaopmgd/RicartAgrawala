package RicartAgrawala;

/**
 * Created by joao on 03/09/15.
 */

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client{

    private ArrayList<Socket> socketList;

    public Client(){
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

    public void sendMessage(int clock, int pid){
        try {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            Message message = new Message(text,clock,pid);
            for(Socket s:socketList){
                ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
