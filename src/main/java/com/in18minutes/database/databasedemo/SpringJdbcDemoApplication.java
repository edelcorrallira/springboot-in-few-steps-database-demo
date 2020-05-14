package com.in18minutes.database.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in18minutes.database.databasedemo.entity.Person;
import com.in18minutes.database.databasedemo.jdbc.PersonJdbcDao;

//@SpringBootApplication
public class SpringJdbcDemoApplication implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(SpringJdbcDemoApplication.class);

	@Autowired
	PersonJdbcDao personJdbcDao;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", personJdbcDao.findAll());
		logger.info("User 10002-> {}", personJdbcDao.findById(10002));
		logger.info("User James-> {}", personJdbcDao.findByName("James"));
		logger.info("User Joe -> {}", personJdbcDao.findByName("Joe"));
		logger.info("Deleting 10001 -> {}", personJdbcDao.deleteById(10001));
		logger.info("Deleting 10004 -> {}", personJdbcDao.deleteById(10004));
		logger.info("Inserted 10004 -> {}", personJdbcDao.insert(new Person(10004, "Tara", "Berlin", new Date())));
		logger.info("Updated 10002 -> {}", personJdbcDao.update(new Person(10004, "James", "New Orleans", new Date())));
	}

}
