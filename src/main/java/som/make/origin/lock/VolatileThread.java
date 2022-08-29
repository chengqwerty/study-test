package som.make.origin.lock;

public class VolatileThread {

    private static boolean ready;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
           while (!ready) {
           }
           System.out.println("I am ready");
        });
        thread.start();
        Thread.sleep(1000);
        ready = true;
    }

}
