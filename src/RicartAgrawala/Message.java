package RicartAgrawala;

import java.io.Serializable;

/**
 * Created on 03/09/15.
 * by
 * João Pedro M. G. Dias 511455
 * Andre Luiz Beltrami 489611
 */

public class Message implements Serializable,Comparable<Message>{

    private int clockFromProcess;
    private int pid;
    private String message;
    private boolean askingPermission;
    private int resourceName;
    private boolean reply;

    public Message(String message, int clockFromProcess, int pid){
        this.clockFromProcess = clockFromProcess;
        this.pid = pid;
        this.message = message;
        this.askingPermission = false;
    }

    public Message(int resourceName, int pid){
        this.resourceName = resourceName;
        this.pid = pid;
        this.askingPermission = true;
    }

    public Message (boolean answer){
        this.reply = answer;
    }

    public boolean isReply() {
        return reply;
    }

    public boolean isAskingPermission() {
        return askingPermission;
    }

    public int getResourceName() {
        return resourceName;
    }

    public int getPid() {
        return pid;
    }

    public int getClockFromProcess() {
        return clockFromProcess;
    }

    public void addResourceName(){ resourceName++; }

    @Override
    public int compareTo(Message message) {
        if (this.clockFromProcess < message.getClockFromProcess()){
            return -1;
        }else if (this.clockFromProcess == message.getClockFromProcess()){
            return 0;
        }else{
            return 1;
        }
    }

    public void printMessage(){
        System.out.println("Message\n- Pid: "+this.pid+"\n- Clock: "+this.clockFromProcess+"\n- Message: "+this.message);
    }
}