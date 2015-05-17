/**
 * 
 */
package rabbit;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import repository.record.Record;

import com.rabbitmq.client.AMQP.Exchange;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 *
 */
@Component
public class HumidityMeasureSender {
	
	
	private MeasureService measureService;
	
	
	private RabbitTemplate rabbitTemplate;
	
	public static int MaxHumidity = 30;
	public static int PLANT_HUMIDITY_THRESHOLD = 10; //Level of humidity when the plant must be watered so it doesn't start withering. 

	public static String EXCHANGE_NAME = "plantsFanout";
	public static String QUEUE_NAME = "plantsToWaterQueue";
	
	public HumidityMeasureSender() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public HumidityMeasureSender(MeasureService measureService, RabbitTemplate rabbitTemplate)
	{
		this.measureService = measureService;
		this.rabbitTemplate = rabbitTemplate;
	}
	
/*	public static void main(String [] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setVirtualHost("/");
		factory.setHost("localhost");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		
		Channel channel = connection.createChannel();
		
		
		
		
		while(true) {
			letsWait();
			String quotation = measureService.next();
			channel.basicPublish("quotations", "nasq", null, quotation.getBytes());
		}
	}*/

	private static void letsWait() throws Exception {
		Thread.sleep(1000);		
	}
	
	
	
	public void checkAndSend() throws Exception {
		
		/*ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setVirtualHost("/");
		factory.setHost("localhost");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		
		
		
		Channel channel = connection.createChannel();
		channel.exchangeDeclare("plantFanout","fanout",true);
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		channel.queueBind(QUEUE_NAME, "plantFanout", "");*/
		List<Record> records =  measureService.getLastHumidityRecords();
		
		
		
		for(Record record : records)
		{
			if (record.getHumidity() <= PLANT_HUMIDITY_THRESHOLD)
			{
				rabbitTemplate.convertAndSend(EXCHANGE_NAME, "",record.getPlant().getId().toString());
				//channel.basicPublish(EXCHANGE_NAME, "", null, record.getPlant().getId().toString().getBytes());
			}
			
		}
	}
	
}
