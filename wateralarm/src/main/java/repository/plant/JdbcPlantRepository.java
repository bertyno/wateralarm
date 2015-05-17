package repository.plant;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import repository.user.User;

@Component
public class JdbcPlantRepository implements PlantRepository {

	private JdbcTemplate jdbcTemplate;

	public JdbcPlantRepository() {

	}

	@Autowired
	public JdbcPlantRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Plant findById(Long id) {
		// TODO Auto-generated method stub

		// String sql = "select ID, NAME from T_PLANT  where ID = ?";

		String sql = "select U.ID as USER_ID, U.NAME as USER_NAME, U.EMAIL as USER_EMAIL, "
				+ "P.ID as PLANT_ID, P.NAME as PLANT_NAME "
				+ "from T_USER U INNER JOIN T_PLANT P ON (U.ID = P.USER_ID)  where P.ID = ?";

		return jdbcTemplate.queryForObject(sql, new RowMapper<Plant>() {

			@Override
			public Plant mapRow(ResultSet rs, int i) throws SQLException {
				// TODO Auto-generated method stub
				Plant plant = new Plant(rs.getLong("PLANT_ID"), rs
						.getString("PLANT_NAME"));
				User user = new User();
				user.setId(rs.getLong("USER_ID"));
				user.setName(rs.getString("USER_NAME"));
				user.setEmail(rs.getString("USER_EMAIL"));
				
				plant.setUser(user);

				return plant;
			}
		}, id);
	}

	@Override
	public Plant findByUserAndPlantName(User user, String plantName) {
		// TODO Auto-generated method stub

		String sql = "select U.ID as USER_ID, U.NAME as USER_NAME, P.ID as PLANT_ID, P.NAME as PLANT_NAME from T_USER U INNER JOIN T_PLANT P ON (U.ID = P.USER_ID)  where U.ID = ? AND P.NAME= ?";
		return jdbcTemplate.queryForObject(sql, new RowMapper<Plant>() {

			@Override
			public Plant mapRow(ResultSet rs, int i) throws SQLException {
				// TODO Auto-generated method stub
				Plant plant = new Plant(rs.getLong("PLANT_ID"), rs
						.getString("PLANT_NAME"));
				User user = new User();
				user.setId(rs.getLong("USER_ID"));
				user.setName(rs.getString("USER_NAME"));

				return plant;
			}
		}, user.getId(), plantName);
	}

	@Override
	public Plant createPlant(Plant plant) {
		//String sql = "insert into T_PLANT (ID, USER_ID, NAME) values (NEXT VALUE FOR S_PLANT_ID, ?, ?)";
		String sql = "insert into T_PLANT (USER_ID, NAME) values (?, ?)";

		jdbcTemplate.update(sql, plant.getUser().getId(), plant.getName());
		
		Long plantId = jdbcTemplate.queryForObject("SELECT id FROM T_PLANT ORDER BY id DESC LIMIT 1",Long.class);
		
		plant.setId(plantId);

		return plant;
	}
}
