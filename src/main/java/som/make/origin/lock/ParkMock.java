package som.make.origin.lock;

import java.util.concurrent.locks.LockSupport;

public class ParkMock {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            LockSupport.park();
            // System.out.println(Thread.currentThread().isInterrupted());
            // System.out.println(Thread.interrupted());
            System.out.println("===========1");
        });
        Thread thread2 = new Thread(() -> {
            thread1.interrupt();
            // LockSupport.unpark(thread1);
        });
        thread1.start();
        Thread.sleep(5000);
        thread2.start();

        thread1.join();
        thread2.join();
    }

}
