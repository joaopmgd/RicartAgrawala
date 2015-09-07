package RicartAgrawala;

/**
 * Created by joao on 03/09/15.
 */

public class Main {

    public static void main(String args[]){

        Process p1 = new Process("localhost",3001,1);
        Process p2 = new Process("localhost",3002,2);
        Process p3 = new Process("localhost",3003,3);

        p1.start();
        p2.start();
        p3.start();

        p1.connect("localhost",3002);
        p1.connect("localhost",3003);

        p2.connect("localhost",3001);
        p2.connect("localhost",3003);

        p3.connect("localhost",3001);
        p3.connect("localhost",3002);

        p1.sendMessage();
        p2.sendMessage();
        p2.sendMessage();


    }
}
