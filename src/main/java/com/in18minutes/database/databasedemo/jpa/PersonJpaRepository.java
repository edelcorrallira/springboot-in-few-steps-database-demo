package com.in18minutes.database.databasedemo.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.UnexpectedRollbackException;

import com.in18minutes.database.databasedemo.entity.Person;

@Repository
@Transactional
public class PersonJpaRepository {
	@PersistenceContext
	EntityManager entityManager;
	
	public Person findById(int id) {
		return entityManager.find(Person.class, id);
	}

	public List<Person> findByName(String name) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
		Root<Person> rootEntry = criteriaQuery.from(Person.class);
		criteriaQuery.select(rootEntry)
			.where(criteriaBuilder.equal(rootEntry.get("name"), name));
		TypedQuery<Person> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	public List<Person> findAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
		Root<Person> rootEntry = criteriaQuery.from(Person.class);
		CriteriaQuery<Person> all = criteriaQuery.select(rootEntry);
		TypedQuery<Person> allQuery = entityManager.createQuery(all);
		return allQuery.getResultList();
	}
	
	public Person insert(Person person) {
		entityManager.persist(person);
		entityManager.flush();		
		return null;
	}
	
	public Person update(Person person) {
		if (entityManager.contains(person)) {
			entityManager.refresh(person);
		}
		return null;
	}
	
	public boolean deleteById(int id) {
		Person person = this.findById(id);
		if (person != null) {
			entityManager.remove(person);
			return true;
		}
		return false;
	}
}
