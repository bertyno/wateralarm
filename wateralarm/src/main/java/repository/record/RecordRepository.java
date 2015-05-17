package repository.record;

import java.util.Date;
import java.util.List;

import repository.plant.Plant;

public interface RecordRepository {
	
	public List<Record> findBetweenDate(Date beg, Date end, Plant plant);
	
	public List<Record> findLastRecordForEachPlant();
	


	void create(Record record);
	
	

}
