package som.make.origin.lock;

public class ProducerThread extends Thread {

    private final ConditionLockQueue<String> queue;

    public ProducerThread(ConditionLockQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                queue.put("chengcheng");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
