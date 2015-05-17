package rabbit;

import java.util.List;

import repository.record.Record;

public interface MeasureService {
	
	//public void checkHumidity(); 
	
	public List <Record> getLastHumidityRecords(); 

}
