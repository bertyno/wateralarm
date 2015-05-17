package repository.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repository.plant.Plant;
import repository.user.JdbcUserRepository;
import repository.user.User;

@ContextConfiguration(locations = {"classpath:/system-test-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcUserRepositoryTest {
	
	private JdbcUserRepository repository;

	@Autowired
	private DataSource dataSource;

	@Before
	public void setUp() throws Exception {
		//dataSource = createTestDataSource();
		repository = new JdbcUserRepository(dataSource);
	}

	@Test
	public void testFindUserById() {
		
		
		User user = repository.findById(1l);
		assertNotNull("user should never be null", user);
		assertEquals("wrong entity id", Long.valueOf(1), user.getId());
		assertEquals("wrong name", "keith", user.getName());
		assertEquals("wrong beneficiary collection size", 2, user.getPlants().size());
		assertEquals("wrong email", "bjalonmontanes@pivotal.io", user.getEmail());
		//dassertEquals("wrong plant name", "My other plant", user.getPlants().);
		
	}
	
	@Test
	public void testFindUserByUserName() {
		
		
		User user = repository.findByUserName("keith");
		assertNotNull("user should never be null", user);
		assertEquals("wrong entity id", Long.valueOf(1), user.getId());
		assertEquals("wrong name", "keith", user.getName());
		assertEquals("wrong email", "bjalonmontanes@pivotal.io", user.getEmail());
		assertEquals("wrong beneficiary collection size", 2, user.getPlants().size());
		//dassertEquals("wrong plant name", "My other plant", user.getPlants().);
		
	}
	
	
	public void testgetAllUsers() {
		
		
		List<User> users = repository.getAllUsers();
		assertEquals("wrong beneficiary collection size", 2, users.size());
		
		assertEquals("wrong name", "Keith and Keri Donald", users.get(0).getName());
		
		//dassertEquals("wrong plant name", "My other plant", user.getPlants().);
		
	}
	
	@Test
	public void testUpdateUser() {
		
		User user = repository.findById(1l);
		user.setName("keithe");
		repository.update(user);
		
		user = repository.findById(1l);
		assertEquals("wrong name", "keithe", user.getName());
		
		user.setName("keith");
		repository.update(user);
		
		user = repository.findById(1l);
		assertEquals("wrong name", "keith", user.getName());
		
	
	}
	
	@Test
	public void testCreateUser() {
		
		User user = new User();
		user.setName("Fulanito");
		user.setPassword("Supass");
		user.setEmail("bertyno@yahoo.es");
		repository.create(user);
		
		
		assertEquals("wrong name", "Fulanito", user.getName());
		assertEquals("wrong pass", "Supass", user.getPassword());
		assertEquals("wrong name", "bertyno@yahoo.es", user.getEmail());
		
		
	
	}
	
	
	
}
