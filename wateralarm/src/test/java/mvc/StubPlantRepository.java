package mvc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

import repository.plant.Plant;
import repository.plant.PlantRepository;
import repository.user.User;

public class StubPlantRepository implements PlantRepository {
	
	private Map<Long, Plant> plantById = new HashMap<Long, Plant>();

	private AtomicLong nextEntityId = new AtomicLong(3);

	public StubPlantRepository() {
		/*User User = new User(1l, "keith");
		Plant plant 
		
		
		plantById.put(1L, plant);
		Logger.getLogger(StubUserRepository.class).info("Created StubUserManager");*/
	}

	@Override
	public Plant findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plant findByUserAndPlantName(User user, String plantName) {
		//User user = null;
		Plant plant = null;
		for(Plant aux : plantById.values())
		{
			if(aux.getName().equals(plantName) && aux.getUser().getId().equals(user.getId()))
			{
				plant=aux;
				break;
			}
		}
		return plant;
	}

	@Override
	public Plant createPlant(Plant plant) {
		plant.setId(1L);
		 plantById.put(1L, plant);
		return plant;

	}

}
