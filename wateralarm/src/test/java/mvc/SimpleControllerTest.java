package mvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.support.SimpleSessionStatus;

import repository.plant.Plant;
import repository.plant.PlantRepository;
import repository.record.RecordRepository;
import repository.user.User;
import repository.user.UserRepository;

public class SimpleControllerTest {
	
	private static final long VALID_ACCOUNT_ID = 1L;
	private static final long ILLEGAL_ACCOUNT_ID = 10101;

	@Autowired
	protected UserRepository userRepository;
	
	@Autowired
	protected PlantRepository plantRepository;

	@Autowired
	protected SimpleController controller;
	
	@Autowired
	protected RecordRepository recordRepository;
	
	@Before
	public void setUp() throws Exception {
		userRepository = new StubUserRepository();
		plantRepository = new StubPlantRepository();
		recordRepository = new StubRecordRepository();
		
		controller = new SimpleController(userRepository, plantRepository, recordRepository);
	}

	@Test
	public void getUserDetails() {
		ExtendedModelMap model = new ExtendedModelMap();
		controller.getUserDetails(VALID_ACCOUNT_ID, model);
		User user = (User) model.get("user");
		assertNotNull(user);
		assertEquals(Long.valueOf(1), user.getId());
	}
	
	@Test
	public void editUser() throws Exception {
		ExtendedModelMap model = new ExtendedModelMap();
		controller.getEditUser(VALID_ACCOUNT_ID, model);
		User user = (User) model.get("user");
		assertNotNull(user);
		assertEquals(Long.valueOf(1), user.getId());
	}

	@Test
	public void successfulEditionPost() throws Exception {
		ExtendedModelMap model = new ExtendedModelMap();
		controller.getEditUser(VALID_ACCOUNT_ID, model);
		User user = (User) model.get("user");
		user.setName("Ben");
		
		BindingResult br = new MapBindingResult(model, "user");
		String viewName = controller.postEditUser(user, br, new SimpleSessionStatus());
		User modifiedUser = userRepository.findById(VALID_ACCOUNT_ID);
		assertEquals("Object name has not been updated by post", "Ben",
				modifiedUser.getName());
		
		assertEquals("Post has not redirected to the correct URL",
				"redirect:/userDetails?entityId=1", viewName);
		
	}
	
	@Test
	public void newUser() throws Exception {
		ExtendedModelMap model = new ExtendedModelMap();
		controller.getNewUser(model);
		User user = (User) model.get("user");
		assertNull("User name not empty", user.getName());
		assertNull("User pass not empty", user.getPassword());
		
		
	}
	
	@Test
	public void successfulNewUserPost() throws Exception {
		ExtendedModelMap model = new ExtendedModelMap();
		controller.getEditUser(VALID_ACCOUNT_ID, model);
		User user = new User();
		user.setPassword("Ben");
		user.setName("Ben");
		
		BindingResult br = new MapBindingResult(model, "user");
		String viewName = controller.postNewUser(user, br, new SimpleSessionStatus(), null);
		User modifiedUser = userRepository.findByUserName("Ben");
		assertEquals("Object name has not been updated by post", "Ben",
				modifiedUser.getName());
		
		assertEquals("Post has not redirected to the correct URL",
				"redirect:/userDetails?entityId="+modifiedUser.getId(), viewName);
		
		controller.getUserDetails(user.getId(), model);
	}
	
	@Test
	public void successfulNewPlantPost() throws Exception {
		ExtendedModelMap model = new ExtendedModelMap();
		controller.getEditUser(VALID_ACCOUNT_ID, model);
		User user = userRepository.findById(VALID_ACCOUNT_ID);
		Plant plant = new Plant("Plantita");
		plant.setUser(user);
		
		
		BindingResult br = new MapBindingResult(model, "plant");
		String viewName = controller.postNewPlant(plant, br, new SimpleSessionStatus(),model);
		Plant newPlant  = plantRepository.findByUserAndPlantName(user, plant.getName());
		assertEquals("Plant not created correctly", "Plantita",
				plant.getName());
		
		assertEquals("Post has not redirected to the correct URL",
				"redirect:/userDetails?entityId="+user.getId(), viewName);
		
		controller.getUserDetails(user.getId(), model);
	}

}
