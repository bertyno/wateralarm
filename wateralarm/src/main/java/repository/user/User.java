package repository.user;

import java.util.HashSet;
import java.util.Set;

import repository.plant.Plant;

public class User  {
	
	private Long id;
	private String name;
	private String password;
	private String email;
	private Boolean enabled;
	private Set<Plant> plants = new HashSet<Plant>();
	
	public User(Long id, String name) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.name=name;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public void addPlant(Plant plant) {
		plants.add(plant);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Plant> getPlants() {
		return plants;
	}
	public void setPlants(Set<Plant> plants) {
		this.plants = plants;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
