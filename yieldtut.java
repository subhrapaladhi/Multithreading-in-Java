class yieldThread implements Runnable{
    @Override
    public void run(){
        System.out.println("starting "+Thread.currentThread().getName());
        int stoppoint = Thread.currentThread().getPriority();
        for(int i=0; i<9; i++){
            System.out.println(Thread.currentThread().getName() + ":" + i);
            if(i==stoppoint){
                Thread.yield();
            }
        }
        System.out.println("ending "+Thread.currentThread().getName());
    }   
}

public class yieldtut {
    public static void main(String[] args){
        Thread t1 = new Thread(new yieldThread(), "Thread 1");
        Thread t2 = new Thread(new yieldThread(), "Thread 2");
        t1.setPriority(6);
        t2.setPriority(2);
        t1.start();
        t2.start();
    }
}
