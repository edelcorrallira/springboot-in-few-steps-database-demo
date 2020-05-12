package com.in18minutes.database.databasedemo.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.in18minutes.database.databasedemo.entity.Person;

@Repository
public class PersonJdbcDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	public List<Person> findAll() {
		return jdbcTemplate.query("select * from person", 
				new BeanPropertyRowMapper<Person>(Person.class));
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
	
	//Returns the number of rows deleted
	public int deleteById(int id) {
		String DELETE_BY_ID = "delete from person where id=?";
		return jdbcTemplate.update(DELETE_BY_ID,  new Object[] {id});
	}
}
