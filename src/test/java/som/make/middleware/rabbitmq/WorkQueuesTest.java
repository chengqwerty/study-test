package som.make.middleware.rabbitmq;

import com.rabbitmq.client.DeliverCallback;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WorkQueuesTest {

    private final static String QUEUE_NAME = "hello";

    @Test
    public void test1() throws InterruptedException {
        Thread publisher = new Thread(new WorkPublisher());
        Thread consumer1 = new Thread(new WorkConsumer());
        Thread consumer2 = new Thread(new WorkConsumer());

        publisher.start();
        consumer1.start();
        consumer2.start();

        publisher.join();
        consumer1.join();
        consumer2.join();
    }

    static class WorkPublisher extends BaseRabbitmqConnect implements Runnable {

        @Override
        public void run() {
            createConnection();
            try {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                for (int i = 1; i < 11; i++) {
                    String message = "Hello " + i + " .";
                    System.out.println("--------------publish " + message);
                    channel.basicPublish("", "hello", null, message.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class WorkConsumer extends BaseRabbitmqConnect implements Runnable {

        private String name = "consumer";

        public WorkConsumer() {
        }

        private final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
            try {
                doWork(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" [x] Done");
            }
        };

        private static void doWork(String task) throws InterruptedException {
            for (char ch: task.toCharArray()) {
                if (ch == '.') Thread.sleep(1000);
            }
        }

        @Override
        public void run() {
            createConnection();
            boolean autoAck = true; // acknowledgment is covered below
            try {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> { });
                System.out.println("================consume");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
