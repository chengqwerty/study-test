package som.make.origin.lock;

public class ConsumerThread extends Thread {

    private final ConditionLockQueue<String> queue;

    public ConsumerThread(ConditionLockQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                queue.take();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
