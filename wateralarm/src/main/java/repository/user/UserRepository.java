package repository.user;

import java.util.List;

public interface UserRepository {
	
	

		/**
		 * Load an user by id
		 * @param id the user id
		 * @return user object
		 */
		public User findById(Long id);

		public List <User> getAllUsers();

		public void update(User user);

		public void create(User user);

		User findByUserName(String userName);



}
