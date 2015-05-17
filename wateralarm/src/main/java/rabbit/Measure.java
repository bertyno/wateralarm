package rabbit;

import java.util.Date;

import repository.plant.Plant;

public class Measure {
	
	private Plant plant;
	private Integer humidity;
	private Date date;
	
	
	public Plant getPlant() {
		return plant;
	}
	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	public Integer getHumidity() {
		return humidity;
	}
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String toSimpleString() {
		// TODO Auto-generated method stub
		return null;
	}

}
