package rabbit;

import java.io.IOException;

import com.rabbitmq.client.AMQP.Exchange;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MainRabbitTest {
	
	public static String EXCHANGE_NAME = "plantsFanout";
	public static String QUEUE_NAME = "plantsToWaterQueue";
	
	public static void main(String[] args) throws IOException {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("bxtwxgom");
		factory.setPassword("G6zTyITDxvk2z-k6wARxgnNHepmmICjw");
		factory.setVirtualHost("bxtwxgom");
		factory.setHost("tiger.cloudamqp.com");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		
		
		
		Channel channel = connection.createChannel();
		channel.exchangeDeclare("plantFanout","fanout",true);
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		channel.queueBind(QUEUE_NAME, "plantFanout", "");
		
		channel.basicPublish(EXCHANGE_NAME, "", null, "quepazaaa".getBytes());

		
	}
	
	

}
