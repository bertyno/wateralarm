package repository.record;

import java.util.Date;

import repository.plant.Plant;

public class Record {
	
	private Date date;
	private Integer humidity;
	private Plant plant;
	
	
	public Record(Date date, Integer humidity, Plant plant) {
		this.date=date;
		this.humidity = humidity;
		this.plant=plant;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getHumidity() {
		return humidity;
	}
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}
	public Plant getPlant() {
		return plant;
	}
	public void setPlant(Plant plant) {
		this.plant = plant;
	}

}
