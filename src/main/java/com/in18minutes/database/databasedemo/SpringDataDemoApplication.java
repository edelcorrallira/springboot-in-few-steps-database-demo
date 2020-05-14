package com.in18minutes.database.databasedemo;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in18minutes.database.databasedemo.entity.Person;
import com.in18minutes.database.databasedemo.jpa.PersonJpaRepository;
import com.in18minutes.database.databasedemo.springdata.PersonSpringDataRepository;

@SpringBootApplication
public class SpringDataDemoApplication implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(SpringDataDemoApplication.class);

	@Autowired
	PersonSpringDataRepository personSpringDataRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", personSpringDataRepository.findAll());
		logger.info("User 10002-> {}", personSpringDataRepository.findById(10002));
		List<Person> persons = personSpringDataRepository.findByName("James");
		logger.info("User James-> {}", persons);
		logger.info("User Joe -> {}", personSpringDataRepository.findByName("Joe"));
		if(persons.size()>0) {
			Person person = persons.get(0);
			logger.info("Deleting {} ", person); 
			personSpringDataRepository.deleteById(person.getId());
		}
		logger.info("Updated 10002 -> {}", personSpringDataRepository.save(new Person(10002, "James", "New Orleans", new Date())));			
	}
}


