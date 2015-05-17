package rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import repository.plant.Plant;
import repository.plant.PlantRepository;

@Component
public class HumidityMsgHandler implements MessageListener {

	private MailSender mailSender;

	private PlantRepository plantRepository;

	private SimpleMailMessage templateMessage;

	public HumidityMsgHandler() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public HumidityMsgHandler(PlantRepository plantRepository,
			MailSender mailSender, SimpleMailMessage templateMessage) {
		this.plantRepository = plantRepository;
		this.mailSender = mailSender;
		this.templateMessage = templateMessage;
	}

	@Override
	public void onMessage(Message msg) {

		System.out.println("receiving quotationnnn: "
				+ new String(msg.getBody()));
		
		Plant plant = plantRepository.findById(new Long(new String(msg.getBody())));

		SimpleMailMessage mailMsg = new SimpleMailMessage(this.templateMessage);
		mailMsg.setTo(plant.getUser().getEmail());
		mailMsg.setText("Dear " + plant.getUser().getName()

		+ ", Please, water you plant " + plant.getName());
		try {
			this.mailSender.send(mailMsg);
		} catch (MailException ex) {
			// simply log it and go on...
			System.err.println(ex.getMessage());
		}
	}

}
