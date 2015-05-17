package rabbit;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repository.record.JdbcRecordRepository;
import repository.record.Record;


@ContextConfiguration(locations = {"classpath:/system-test-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MeasureServiceTest {

	
	private JdbcRecordRepository recordRepository;

	@Autowired
	private DataSource dataSource;
	
	
	private MeasureService measureService;
	
	private HumidityMeasureSender sender;

	@Before
	public void setUp() throws Exception {
		//dataSource = createTestDataSource();
		recordRepository = new JdbcRecordRepository(dataSource);
		measureService = new MeasureServiceImpl(recordRepository);
		//sender = new HumidityMeasureSender(measureService);
	}
	
	@Test
	public void testEmailService()
	{
		List<Record> lastRecords = measureService.getLastHumidityRecords();
		
	}
	
	
	@Test
	public void testSender()
	{
		/*List<Record> lastRecords = measureService.getLastHumidityRecords();
		
		for(Record record : lastRecords)
			System.out.println("Plant " + record.getPlant().getName() + " has humidity " + record.getHumidity());
		
		try {
			sender.checkAndSend();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(3600000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	

}
