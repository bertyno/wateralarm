package repository.plant;

import repository.user.User;

public class Plant {
	
	private Long id;
	private String name;
	private User user;
	
	public Plant() {
		
	}
	
	
	
	public Plant(Long id, String name) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.name=name;
	}
	
	public Plant(String name) {
		// TODO Auto-generated constructor stub
		
		this.name=name;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
