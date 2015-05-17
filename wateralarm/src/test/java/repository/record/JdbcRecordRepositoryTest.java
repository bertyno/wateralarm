package repository.record;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repository.plant.Plant;
import repository.record.JdbcRecordRepository;
import repository.record.Record;




@ContextConfiguration(locations = {"classpath:/system-test-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcRecordRepositoryTest {

	
	private JdbcRecordRepository repository;

	@Autowired
	private DataSource dataSource;

	@Before
	public void setUp() throws Exception {
		//dataSource = createTestDataSource();
		repository = new JdbcRecordRepository(dataSource);
	}

	@Test
	public void testFinBetweenDates() {
		
		SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
		Date beg=null;
		Date end=null;
		try {
			beg = ft.parse("31-12-2014");
			end = ft.parse("02-01-2015");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		Plant plant = new Plant(1l,"whatever");
				
				
		List<Record> records = repository.findBetweenDate(beg, end, plant);
		assertNotNull("user should never be null", records);
		Record record = records.get(0);
		System.out.println("Humidity: " + record.getHumidity());
		assertEquals("wrong humidity", Integer.valueOf(20), record.getHumidity());
		
		
		
	}
	
	@Test
	public void findLastRecordForEachPlant()
	{
		List<Record> records = repository.findLastRecordForEachPlant();
		for(Record record: records)
			System.out.println(record.getPlant().getName() + " : " + record.getDate());
		assertEquals("List length should be 2", Integer.valueOf(2), Integer.valueOf(records.size()));
		assertEquals("User is not keith", "keith", records.get(0).getPlant().getUser().getName() );
		assertEquals("Email is not bjalonmontanes@pivotal.io", "bjalonmontanes@pivotal.io", records.get(0).getPlant().getUser().getEmail() );
		
	}
	
	
	
}
