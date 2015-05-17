package mvc;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import repository.plant.Plant;
import repository.plant.PlantRepository;
import repository.record.Record;
import repository.record.RecordRepository;
import repository.user.User;
import repository.user.UserRepository;


@Controller
@SessionAttributes("user")
public class SimpleController {

	private UserRepository userRepository;
	private PlantRepository plantRepository;
	private RecordRepository recordRepository;
	
	@Autowired
	public SimpleController(UserRepository repository, PlantRepository plantRepository, RecordRepository recordRepository) {
		this.userRepository = repository;
		this.plantRepository = plantRepository;
		this.recordRepository = recordRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/newUser")
	public String getNewUser( Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("plant", new Plant());
		return "editUser";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/newUser")
	public String postNewUser(User user, BindingResult bindingResult,
			SessionStatus status, HttpSession session) {
		validateUser(user, bindingResult);
		if (bindingResult.hasErrors()) {
			return "editUser";
		}
		userRepository.create(user);
		user=userRepository.findByUserName(user.getName());
		
		status.setComplete();
		return "redirect:/userDetails?entityId="
				+ user.getId();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/newPlant")
	public String postNewPlant(Plant plant, BindingResult bindingResult,
			SessionStatus status, Model model) {
		
		User user = (User)model.asMap().get("user");
		plant.setUser(user);
		plant = plantRepository.createPlant(plant);
		
		Record record = new Record(new Date(), 10, plant);
		recordRepository.create(record);
		
		/*user=userRepository.findByUserName(user.getName());*/
		status.setComplete();
		return "redirect:/userDetails?entityId="
				+ user.getId();
	}

	@RequestMapping("/userList")
	public void getUserList(Model model) {
		model.addAttribute("users", userRepository.getAllUsers());
	}

	@RequestMapping("/userDetails")
	public void getUserDetails(Long entityId, Model model) {
		model.addAttribute("user", userRepository.findById(entityId)) ;
		
	}
	
	@RequestMapping("/userDetailsByName")
	public void getUserDetailsByName(String userName, Model model) {
		model.addAttribute("user", userRepository.findByUserName(userName)) ;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/editUser")
	public void getEditUser(Long entityId, Model model) {
		model.addAttribute("user", userRepository.findById(entityId));
		model.addAttribute("plant", new Plant(null)) ;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/editUserByName")
	public String getEditUserByName(String userName, Model model) {
		model.addAttribute("user", userRepository.findByUserName(userName));
		model.addAttribute("plant", new Plant(null)) ;
		return "editUser";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/editUser")
	public String postEditUser(User user, BindingResult bindingResult,
			SessionStatus status) {
		validateUser(user, bindingResult);
		if (bindingResult.hasErrors()) {
			return "editUser";
		}
		userRepository.update(user);
		
		status.setComplete();
		return "redirect:/userDetails?entityId="
				+ user.getId();
	}
	
	void validateUser(User user, Errors errors) {
		/*if (!StringUtils.hasText(user.getNumber())) {
			errors.rejectValue("number", "empty.value");
		}*/
		if (!StringUtils.hasText(user.getName())) {
			errors.rejectValue("name", "empty.value");
		}
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// The next 4 methods enable form handling so that beneficiaries can
	// be edited. This, and much more, is covered by the Spring-Web course.
	//
	// You do NOT need to understand the following to do this lab.
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
/*
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setRequiredFields(new String[] { "number", "name" });
	}

	

	@RequestMapping(method = RequestMethod.POST, value = "/editAccount")
	public String postEditAccount(Account account, BindingResult bindingResult,
			SessionStatus status) {
		validateAccount(account, bindingResult);
		if (bindingResult.hasErrors()) {
			return "editAccount";
		}
		accountManager.update(account);
		status.setComplete();
		return "redirect:/accounts/accountDetails?entityId="
				+ account.getEntityId();
	}

	void validateAccount(Account account, Errors errors) {
		if (!StringUtils.hasText(account.getNumber())) {
			errors.rejectValue("number", "empty.value");
		}
		if (!StringUtils.hasText(account.getName())) {
			errors.rejectValue("name", "empty.value");
		}
	}*/

	@RequestMapping("/simple")
	public @ResponseBody String simple() {
		return "Hello world!";
	}

}