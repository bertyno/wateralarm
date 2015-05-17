/**
 * 
 */
package rabbit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import repository.record.Record;
import repository.record.RecordRepository;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 */
@Component
public class MeasureServiceImpl implements MeasureService{
	
	private RecordRepository recordRepository;


	@Autowired
	private MailSender mailSender;
	
	@Autowired
    private SimpleMailMessage templateMessage;
	
	public static final Integer humidityThereshold = 30;
	
	public MeasureServiceImpl()
	{
		
	}
	
	@Autowired
	public MeasureServiceImpl(RecordRepository recordRepository)
	{
		this.recordRepository = recordRepository;
		//this.mailSender = mailSender;
		//this.templateMessage = templateMessage;
		
	}
	
	public String next() {
		return nextDetailed().toSimpleString();
	}
	
	public Measure nextDetailed() {
		// VMware stocks are skyrocketting! BUY!!!
		
		Measure measure = new Measure(); //new Measure(symbol,current,new Date());
		return measure;		
	}
	
/*	public void checkHumidity()
	{
		List<Record> lastRecordForEachPlant = recordRepository.findLastRecordForEachPlant();
		for(Record record: lastRecordForEachPlant)
		{
			if(record.getHumidity() < humidityThereshold)
				sendEmailToUserSoHeWaters(record);
		}
	}
	
	private void sendEmailToUserSoHeWaters(Record record)
	{
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(record.getPlant().getUser().getEmail());
        msg.setText(
            "Dear " + record.getPlant().getUser().getName()
                
                + ", Please, water you plant "
                + record.getPlant().getName());
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
	}*/
	
	

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

	@Override
	public List<Record> getLastHumidityRecords() {
		
		return recordRepository.findLastRecordForEachPlant();
	}

   	
}
