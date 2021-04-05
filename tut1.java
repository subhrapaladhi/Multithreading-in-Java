import java.util.*;
import java.lang.*;

class myThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread());
        System.out.println("Hello");
    }
}

public class tut1 {
    public static void main(String[] args) {
        System.out.println("Start");
        
        myThread mt1 = new myThread();
        Thread t1 = new Thread(mt1);
        t1.start();
        System.out.println("thread 1 "+t1.getId());

        myThread mt2 = new myThread();
        Thread t2 = new Thread(mt2);
        t2.start();
        
        System.out.println("thread 2 "+t2.getId());

        System.out.println("End");
    }
}