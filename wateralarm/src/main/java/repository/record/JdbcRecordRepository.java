package repository.record;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import repository.plant.Plant;
import repository.user.User;

@Component
public class JdbcRecordRepository implements RecordRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcRecordRepository(){
		
	}
	
	@Autowired
	public JdbcRecordRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Record> findBetweenDate(Date beg, Date end, Plant plant) {
		String sql="SELECT * FROM T_RECORD WHERE R_DATE >= ? AND R_DATE <= ?";
	
		
		 return jdbcTemplate.query(sql, new RowMapper<Record>() {

			@Override
			public Record mapRow(ResultSet rs, int i) throws SQLException {
				// TODO Auto-generated method stub
				return new Record(rs.getDate("R_DATE") , rs.getInt("HUMIDITY"), null);
			}
		}, beg, end);
		 
		/*return jdbcTemplate.query(sql, new ResultSetExtractor<Record>() {

			@Override
			public Record extractData(ResultSet arg0) throws SQLException,
					DataAccessException {
				// TODO Auto-generated method stub
				return null;
			}
		}, beg, end);
		 */
		  
	}
//R_DATE date not null, HUMIDITY integer, PLANT_ID
	@Override
	public List<Record> findLastRecordForEachPlant() {
		/*String sql ="SELECT r1.PLANT_ID as PLANT_ID, r1.HUMIDITY as HUMIDITY, r1.R_DATE as R_DATE  FROM T_RECORD r1 LEFT OUTER JOIN T_RECORD r2 "
				+ "ON (r1.PLANT_ID = R2.PLANT_ID AND r1.R_DATE < r2.R_DATE)"
				+ "WHERE r2.PLANT_ID IS NULL";
		*/
		
		String sql = "select R1.PLANT_ID, R1.HUMIDITY, R1.R_DATE, P.NAME as PLANT_NAME, U.NAME as USER_NAME, U.EMAIL as USER_EMAIL from T_RECORD R1"
				+ " INNER JOIN T_PLANT P ON (R1.PLANT_ID = P.ID ) INNER JOIN T_USER U ON (P.USER_ID = U.ID) "
         + " where R_DATE = (select max(R_DATE) from T_RECORD R2 where R1.PLANT_ID = R2.PLANT_ID)"
         + " order by R_DATE desc";		
		
		
		return jdbcTemplate.query(sql, new RowMapper<Record>() {

			@Override
			public Record mapRow(ResultSet rs, int i) throws SQLException {
				
				User user = new User();
				user.setName(rs.getString("USER_NAME"));
				user.setEmail(rs.getString("USER_EMAIL"));
				
				
				Plant plant = new Plant(rs.getLong("PLANT_ID"), rs.getString("PLANT_NAME"));
				plant.setUser(user);
				
				Record record = new Record(rs.getDate("R_DATE") , rs.getInt("HUMIDITY"),plant);
				
				// TODO Auto-generated method stub
				return  record;
			}
		});
	}

	@Override
	public void create(Record record) {
		

		String sql = "insert into T_RECORD (R_DATE, HUMIDITY, PLANT_ID) values ( ?, ?, ? )";

		
		jdbcTemplate.update(sql, new Date(), record.getHumidity() ,record.getPlant().getId());
		
				
	}
	
	
	

}
