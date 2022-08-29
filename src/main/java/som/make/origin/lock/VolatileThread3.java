package som.make.origin.lock;

import java.util.HashMap;
import java.util.Map;

public class VolatileThread3 {

    private static Mock mock = new Mock();
    private static volatile boolean ready;

    private static class Mock {
        public int a = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (!ready) {
                System.out.println("---------------------");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                mock.a = 10;
            }
        }).start();
        Thread.sleep(1000);
        System.out.println("================");
        mock = new Mock();
        Thread.sleep(1000);
        ready = true;

        System.out.println("*************" + mock.a);
    }
}
