package RicartAgrawala;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by joao on 03/09/15.
 */

public class Process extends Thread{

    private String ip;
    private int port;
    private int pid;
    private int clock;
    private ArrayList<Message> messages;
    private Client client;
    private Server server;


    public Process(String ip ,int port, int pid){
        this.ip = ip;
        this.port = port;
        this.pid = pid;
        this.clock = 0;
        this.messages = new ArrayList<Message>();
        this.client = new Client();
    }

    public void startListening(){
        this.server = new Server(port,this);
        server.start();
        this.client.connect(ip, port);
    }

    public void connect(String ip, int port) {
        this.client.connect(ip, port);
    }

    public void sendMessage(){
        this.clock++;
        client.sendMessage(this.clock, this.pid);
    }

    public void addMessage(Message message){
        messages.add(message);
        Collections.sort(messages);
        for (Message m: messages){
            System.out.print("Process "+pid+" received ");
            m.printMessage();
        }
    }

    public void updateClock(int clockFromMessage){
        this.clock = Math.max(this.clock, clockFromMessage)+1;
    }

    @Override
    public void run(){
        startListening();
    }
}
