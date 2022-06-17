package som.make.middleware.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * publish和consume一对一直接操作queue
 */
public class HelloWorldTest {

    private final static String QUEUE_NAME = "hello";

    @Test
    public void test1() throws InterruptedException {
        Thread publisher = new Thread(new HelloPublisher());
        publisher.start();
        Thread consumer = new Thread(new HelloConsumer());
        consumer.start();

        publisher.join();
        consumer.join();
    }

    static class HelloPublisher extends BaseRabbitmqConnect implements Runnable {

        @Override
        public void run() {
            createConnection();
            try {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = "Hello World!";
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
                channel.close();
                connection.close();
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }

    }

    static class HelloConsumer extends BaseRabbitmqConnect implements Runnable {

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };

        @Override
        public void run() {
            createConnection();
            try {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
