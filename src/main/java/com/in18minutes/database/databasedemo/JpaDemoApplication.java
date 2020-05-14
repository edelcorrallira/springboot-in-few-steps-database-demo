package com.in18minutes.database.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in18minutes.database.databasedemo.entity.Person;
import com.in18minutes.database.databasedemo.jpa.PersonJpaRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(JpaDemoApplication.class);

	@Autowired
	PersonJpaRepository personJpaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", personJpaRepository.findAll());
		logger.info("User 10002-> {}", personJpaRepository.findById(10002));
		logger.info("User James-> {}", personJpaRepository.findByName("James"));
		logger.info("User Joe -> {}", personJpaRepository.findByName("Joe"));
		logger.info("Deleting 10001 -> {}", personJpaRepository.deleteById(10001));
		logger.info("Deleting 10004 -> {}", personJpaRepository.deleteById(10004));
		logger.info("Inserted 10004 -> {}", personJpaRepository.insert(new Person("Tara", "Berlin", new Date())));
		logger.info("Updated 10002 -> {}", personJpaRepository.update(new Person(10004, "James", "New Orleans", new Date())));
	}

}


