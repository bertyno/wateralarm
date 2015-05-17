/**
 * 
 */
package rabbit;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 *
 */
@Component
public class HumidityMeasureConsumer {
	
	
	private RabbitTemplate rabbitTemplate;
	
	public HumidityMeasureConsumer() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public HumidityMeasureConsumer(RabbitTemplate rabbitTemplate)
	{
		this.rabbitTemplate = rabbitTemplate;
	}
	

	
	public void consume() throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setVirtualHost("/");
		factory.setHost("localhost");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		
		Channel channel = connection.createChannel();
		
		channel.basicConsume(HumidityMeasureSender.QUEUE_NAME, true,new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					BasicProperties properties, byte[] body) throws IOException {
				System.out.println("receiving quotation: "+new String(body));				
			}
		});		
		
	}
	
}
