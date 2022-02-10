package som.make.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock锁的测试
 */
public class MonReentrantLock implements Runnable {

    public static Integer i = 0;
    private final ReentrantLock lock;
    private final boolean isTry;

    public MonReentrantLock(ReentrantLock lock, boolean isTry) {
        this.lock = lock;
        this.isTry = isTry;
    }

    @Override
    public void run() {
        if (isTry) {
            tryLockRun();
        } else {
            lockRun();
        }
    }

    private void lockRun() {
        for (int j = 0; j < 1000; j++) {
            System.out.println(Thread.currentThread().getName() +"开始尝试获取锁。");
            lock.lock();
            System.out.println(Thread.currentThread().getName() +"获取到锁。");
            try {
                i++;
            } finally {
                System.out.println(Thread.currentThread().getName() +"释放锁。");
                lock.unlock();
            }
        }
    }

    private void tryLockRun() {
        for (int j = 0; j < 1000; j++) {
            try {
                System.out.println(Thread.currentThread().getName() +"开始尝试获取锁，5s。");
                if (lock.tryLock(5, TimeUnit.SECONDS)) {
                    System.out.println(Thread.currentThread().getName() +"已经获取到锁。");
                    i++;
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                System.out.println("tryLock中断或者sleep中断。");
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    System.out.println(Thread.currentThread().getName() +"开始释放锁。");
                    lock.unlock();
                }
            }
        }
    }

}


