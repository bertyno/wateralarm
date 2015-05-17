package repository.plant;

import repository.user.User;


public interface PlantRepository {
	
	
			/**
			 * Load an plant by id
			 * @param id the user id
			 * @return user object
			 */
			public Plant findById(Long id);

			Plant findByUserAndPlantName(User user, String plantName);

			Plant createPlant(Plant plant);


	


}
