package com.in18minutes.database.databasedemo.springdata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in18minutes.database.databasedemo.entity.Person;

public interface PersonSpringDataRepository extends JpaRepository <Person, Integer> {
	List<Person> findByName(String name);
}
