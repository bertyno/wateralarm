package repository.plant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import repository.user.JdbcUserRepository;
import repository.user.User;

public class JdbcPlantRepositoryTest {
	
	
	private JdbcPlantRepository plantRepository;
	
	private JdbcUserRepository userRepository;
	

	
	private DataSource dataSource;
	
	
	@Before
	public void setUp() throws Exception {
		dataSource = createTestDataSource();
		plantRepository = new JdbcPlantRepository(dataSource);
		userRepository = new JdbcUserRepository(dataSource);
	}
	
	@Test
	public void testFinPlantById() {
				
		Plant plant = plantRepository.findById(0L);
		assertNotNull("user should never be null", plant);
		assertEquals("wrong entity id", Long.valueOf(0), plant.getId());
		assertEquals("wrong name", "My plant", plant.getName());
		assertEquals("bjalonmontanes@pivotal.io", plant.getUser().getEmail());
		
		//dassertEquals("wrong plant name", "My other plant", user.getPlants().);
		
	}
	
	
	private DataSource createTestDataSource() {
		return new EmbeddedDatabaseBuilder()
			.setName("wateralarm")
			.addScript("/testdb/schema.sql")
			.addScript("/testdb/test-data.sql")
			.build();
	}
	
	@Test
	public void testCreatePlant() {
		
		User user = userRepository.findByUserName("Berti");
		
		Plant plant = new Plant( "Berti Plant");
		
		plant.setUser(user);
		
		plantRepository.createPlant(plant);
		
		Plant plantAux = plantRepository.findByUserAndPlantName(user, plant.getName());
		
		assertEquals("wrong name", "Berti Plant", plant.getName());
		
	}


}
