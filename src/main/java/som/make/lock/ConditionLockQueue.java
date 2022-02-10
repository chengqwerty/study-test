package som.make.lock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionLockQueue<T> {

    private final LinkedList<T> queue = new LinkedList<>();

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition producer = lock.newCondition();
    private final Condition consumer = lock.newCondition();

    public int max = 10;

    public void put(T resource) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() > max) {
                System.out.println(Thread.currentThread() + "：生产者：队列已满，停止生产。");
                producer.await();
            }
            System.out.println(Thread.currentThread() + "：生产者：插入" + resource + "!!!");
            queue.add(resource);
            // 此时可以唤醒消费者了
            consumer.signal();
        } finally {
            lock.unlock();
        }
    }

    public void take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() <= 0) {
                System.out.println(Thread.currentThread() + "：消费者：队列为空，停止消费。");
                consumer.await();
            }
            T resource = queue.remove();
            System.out.println(Thread.currentThread() + "：消费者：取出消息" + resource + "!!!");
            // 此时可以唤醒生产者
            producer.signal();
        } finally {
            lock.unlock();
        }
    }

}
