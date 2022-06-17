package som.make.origin.lock;

public class SyncLock implements Runnable {

    public static int i = 0;

    @Override
    public void run() {
        for(int j = 0; j < 100000; j++) {
            synchronized (SyncLock.class) {
                i = i + 1;
            }
        }
    }

}
