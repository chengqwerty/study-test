package som.make.origin.lock;

public class VolatileThread2 {

    private static boolean ready;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (!ready) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("结束");
        }).start();
        Thread.sleep(1000);
        ready = true;
    }
}
