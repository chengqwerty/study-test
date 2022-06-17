package som.make.middleware.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BaseRabbitmqConnect {

    protected Connection connection;
    protected Channel channel;

    protected void createConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.6.149");
        factory.setPort(5672);
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

}
