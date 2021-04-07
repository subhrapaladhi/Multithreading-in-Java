class customThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Starting " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " : " + e.getMessage());
        } finally {
            System.out.println("Ending " + Thread.currentThread().getName());
        }
    }
}

public class sleeptut {
    public static void main(String[] args) {
        Thread t1 = new Thread(new customThread(), "Thread 1");
        Thread t2 = new Thread(new customThread(), "Thread 2");
        t1.start();
        t2.start();
    }
}
