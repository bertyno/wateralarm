package mvc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import repository.plant.Plant;
import repository.record.Record;
import repository.record.RecordRepository;

public class StubRecordRepository implements RecordRepository {
	
	private Map<Long, Record> recordsById = new HashMap<Long, Record>();

	@Override
	public List<Record> findBetweenDate(Date beg, Date end, Plant plant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> findLastRecordForEachPlant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Record record) {
		recordsById.put(1L, record);

	}

}
