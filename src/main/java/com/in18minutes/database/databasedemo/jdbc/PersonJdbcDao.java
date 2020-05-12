package com.in18minutes.database.databasedemo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.in18minutes.database.databasedemo.entity.Person;

@Repository
public class PersonJdbcDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//Creating a custom RowMapper, for situations where the bean and model have discrepancies
	class PersonRowMapper implements RowMapper<Person> {
		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
			return person;
		}
	}
	
	public List<Person> findAll() {
		return jdbcTemplate.query("select * from person", 
				//new BeanPropertyRowMapper<Person>(Person.class));
				new PersonRowMapper());
	}
	
	public Person findById(int id) {
		String SELECT_BY_ID = "select * from person where id=?";
		return jdbcTemplate.queryForObject(SELECT_BY_ID, new Object[] {id}, 
				new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public List<Person> findByName(String name) {
		String SELECT_BY_NAME = "select * from person where name=?";
		return jdbcTemplate.query(SELECT_BY_NAME,  new Object[] {name},
				new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	/** 
	 * Returns the number of rows deleted; given these id's should be unique it should
	 * never delete more than one record.
	 * @param id Id of record to delete
	 * @return Number of rows deleted
	 */
	public int deleteById(int id) {
		String DELETE_BY_ID = "delete from person where id=?";
		return jdbcTemplate.update(DELETE_BY_ID,  new Object[] {id});
	}
	
	public boolean insert(Person person) {
		String INSERT_PERSON = "insert into person (id, name, location, birth_date)"
				+" values(?, ?, ?, ?)";
		return jdbcTemplate.update(INSERT_PERSON, new Object[] {
				person.getId(),	person.getName(), person.getLocation(), 
				new Timestamp(person.getBirthDate().getTime())})>=0;
	}
	
	public boolean update (Person person) {
		String UPDATE_PERSON = "update person set name =?, location =?, birth_date =?"
				+" where id =?";
		return jdbcTemplate.update(UPDATE_PERSON, new Object[] {
				person.getName(), person.getLocation(), 
				new Timestamp(person.getBirthDate().getTime()), person.getId()})>=0;
	}
}
