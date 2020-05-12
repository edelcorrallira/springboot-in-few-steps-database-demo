package com.in18minutes.database.databasedemo.entity;

import java.util.Date;

public class Person {
	private int id;
	private String name;
	private String location;
	private Date birthDate;

	//Since a constructor with arguments has been provided, a no argument constructor must be provided for
	//the RowMapper (otherwise it throws and exception)
	public Person() {}

	public Person(int id, String name, String location, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	//If we don't provide this, we'll just see the memory addresses of the container objects
	//also consider using the new line argument for readability.
	@Override
	public String toString() {
		return String.format("\nPerson [id=%s, name=%s, location=%s, birthDate=%s]", id, name, location, birthDate);
	}	
}
