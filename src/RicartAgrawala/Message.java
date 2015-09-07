package RicartAgrawala;

import java.io.Serializable;

/**
 * Created by joao on 03/09/15.
 */
public class Message implements Serializable,Comparable<Message>{

    private int clockFromProcess;
    private int pid;
    private String message;

    public Message(String message, int clockFromProcess, int pid){
        this.clockFromProcess = clockFromProcess;
        this.pid = pid;
        this.message = message;
    }

    public int getClockFromProcess() {
        return clockFromProcess;
    }

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
