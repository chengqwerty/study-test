package som.make.origin.lock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import som.make.origin.lock.*;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    @DisplayName("ReentrantLock lock测试")
    @Test
    public void testLock() throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();
        Thread lockThread1 = new Thread(new MonReentrantLock(lock, false));
        Thread lockThread2 = new Thread(new MonReentrantLock(lock, false));
        lockThread1.start();
        lockThread2.start();

        lockThread1.join();
        lockThread2.join();

        System.out.println(MonReentrantLock.i);
    }

    @DisplayName("ReentrantLock tryLock测试")
    @Test
    public void testTryLock() throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();
        Thread lockThread1 = new Thread(new MonReentrantLock(lock, true));
        Thread lockThread2 = new Thread(new MonReentrantLock(lock, true));
        lockThread1.start();
        lockThread2.start();

        lockThread1.join();
        lockThread2.join();

        System.out.println(MonReentrantLock.i);
    }

    @DisplayName("ReentrantLock Condition测试")
    @Test
    public void testQueue() throws InterruptedException {
        ConditionLockQueue<String> queue = new ConditionLockQueue<>();
        Thread p1 = new ProducerThread(queue);
        Thread p2 = new ProducerThread(queue);
        Thread p3 = new ProducerThread(queue);
        Thread p4 = new ProducerThread(queue);

        Thread c1 = new ConsumerThread(queue);
        Thread c2 = new ConsumerThread(queue);
        Thread c3 = new ConsumerThread(queue);

        p1.start();
        p2.start();
        p3.start();
        p4.start();

        c1.start();
        c2.start();
        c3.start();

        p1.join();
        p2.join();
        p3.join();
        p4.join();
        c1.join();
        c2.join();
        c3.join();

        System.out.println("执行完成！");
    }

    @DisplayName("无锁测试")
    @Test
    public void testNoLock() throws InterruptedException {
        Thread thread1 = new Thread(new NoLock());
        Thread thread2 = new Thread(new NoLock());
        Thread thread3 = new Thread(new NoLock());

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(NoLock.i);
    }

    @DisplayName("synchronized锁测试")
    @Test
    public void testSyncLock() throws InterruptedException {
        Thread thread1 = new Thread(new SyncLock());
        Thread thread2 = new Thread(new SyncLock());
        Thread thread3 = new Thread(new SyncLock());

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(SyncLock.i);
    }
}
