package mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

import repository.user.User;
import repository.user.UserRepository;

public class StubUserRepository implements UserRepository{
	
	private Map<Long, User> usersById = new HashMap<Long, User>();

	private AtomicLong nextEntityId = new AtomicLong(3);

	public StubUserRepository() {
		User User = new User(1l, "Keith and Keri Donald");
		
		usersById.put(1L, User);
		Logger.getLogger(StubUserRepository.class).info("Created StubUserManager");
	}

	@Override
	public User findById(Long id) {
		
		return usersById.get(id);
	}

	@Override
	public List<User> getAllUsers() {
		
		return new ArrayList<User> (usersById.values());
	}

	@Override
	public void update(User user) {
		User aux = usersById.get(user.getId());
		aux.setName(user.getName());
		usersById.put(user.getId(), aux);
		
	}

	@Override
	public void create(User user) {
		usersById.put(user.getId(), user);
		
	}

	@Override
	public User findByUserName(String userName) {
		User user = null;
		for(User aux : usersById.values())
		{
			if(aux.getName().equals(userName))
			{
				user=aux;
				break;
			}
		}
		return user;
	}


}
