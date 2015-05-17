package repository.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import repository.plant.Plant;

@Component
public class JdbcUserRepository implements UserRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcUserRepository() {
		
	}
	@Autowired
	public JdbcUserRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		
		String sql = "select u.ID as USER_ID,  u.NAME as USER_NAME, u.PASSWORD, u.ENABLED, u.EMAIL, p.ID as PLANT_ID, p.NAME as PLANT_NAME  from T_USER U LEFT OUTER JOIN T_PLANT p ON (u.ID = p.USER_ID)  where u.ID = ?";
		return jdbcTemplate.query(sql,
				new ResultSetExtractor<User>() {

					@Override
					public User extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						User user = null;
						while (rs.next()) {
							if (user == null) {
								Long id = rs.getLong("USER_ID");
								String name = rs.getString("USER_NAME");
								user = new User(id, name);
								user.setPassword(rs.getString("PASSWORD"));
								user.setEnabled(rs.getBoolean("ENABLED"));
								user.setEmail(rs.getString("EMAIL"));
								
								
							}
							Plant plant = new Plant(rs.getLong("PLANT_ID"), rs.getString("PLANT_NAME"));
							user.addPlant(plant);
						}
						if (user == null) {
							// no rows returned - throw an empty result exception
							throw new EmptyResultDataAccessException(1);
						}
						return user;
						
					}
			
				}
		, id);
	}

	@Override
	public List<User> getAllUsers() {
		String sql = "select *   from T_USER order by ID";
		
		
		return jdbcTemplate.query(sql,
				new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet rs, int i)
							throws SQLException {
						
						return new User(rs.getLong("ID"), rs.getString("NAME"));
					}
				}

					
		);
	}
	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
		String sql = "update T_USER set NAME = ? where ID = ?";
		
		jdbcTemplate.update(sql, user.getName(),user.getId());
		
		
	}
	@Override
	public void create(User user) {
		
		// String sql = "insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (NEXT VALUE FOR S_USER_ID,?, ?, ?, true)";
		
		String sql = "insert into T_USER ( NAME, PASSWORD, EMAIL, ENABLED) values (?, ?, ?, true)";

		
		jdbcTemplate.update(sql, user.getName(),user.getPassword(), user.getEmail());
		
		sql = "insert into T_AUTHORITY (USERNAME, AUTHORITY) values (?, 'ROLE_VIEWER')";
		
		jdbcTemplate.update(sql, user.getName());

		
	}
	
	@Override
	public User findByUserName(String userName ) {
		// TODO Auto-generated method stub
		
		String sql = "select u.ID as USER_ID,  u.NAME as USER_NAME, u.PASSWORD, u.ENABLED, u.EMAIL, p.ID as PLANT_ID, p.NAME as PLANT_NAME  from T_USER U LEFT OUTER JOIN T_PLANT p  ON (u.ID = p.USER_ID ) WHERE u.NAME = ?";
		return jdbcTemplate.query(sql,
				new ResultSetExtractor<User>() {

					@Override
					public User extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						User user = null;
						while (rs.next()) {
							if (user == null) {
								Long id = rs.getLong("USER_ID");
								String name = rs.getString("USER_NAME");
								user = new User(id, name);
								user.setPassword(rs.getString("PASSWORD"));
								user.setEnabled(rs.getBoolean("ENABLED"));
								user.setEmail(rs.getString("EMAIL"));
								
								
							}
							Plant plant = new Plant(rs.getLong("PLANT_ID"), rs.getString("PLANT_NAME"));
							user.addPlant(plant);
						}
						if (user == null) {
							// no rows returned - throw an empty result exception
							throw new EmptyResultDataAccessException(1);
						}
						return user;
						
					}
			
				}
		, userName);
	}
	
	

}
