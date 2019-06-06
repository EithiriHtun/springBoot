package com.springtest.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springtest.demo.entity.Employee;


@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	//define field for entitymanager

	private EntityManager EntityManager;
	
	//set up constructor injection
	@Autowired
	public EmployeeDAOImpl (EntityManager theEntityManager) {
		EntityManager =theEntityManager;
	}
	
	
	@Override
	public List<Employee> findAll() {
	
		
		// get the current hibernate session
		
		Session currentSession = EntityManager.unwrap(Session.class);
		
		//create a query
		Query<Employee> sql= currentSession.createQuery("from Employee",Employee.class);
		
		//ececute query and get result list
		List<Employee> employees= sql.getResultList();
		
		//return  the results
		return employees;
		
	}


	@Override
	public Employee findById(int theId) {

		//get the current hibernate session
		Session currentSession = EntityManager.unwrap(Session.class);
		
		//get the employee 
		Employee employee= 
				currentSession.get(Employee.class, theId);
		
		//return the employee
		return employee;
	}


	@Override
	public void save(Employee theEmployee) {
		
		//get the current hibernate session
				Session currentSession = EntityManager.unwrap(Session.class);
		
		//save employee
	   currentSession.saveOrUpdate(theEmployee);		
				
	}


	@Override
	public void deletebyId(int theID) {
		
		//get the current hibernate session
		Session currentSession = EntityManager.unwrap(Session.class);
		
		//delete object with primary key
		Query sql= currentSession.createQuery("delete from Employee where id=:employeeId");
	
		sql.setParameter("employeeId", theID);
		
		sql.executeUpdate();
		
	}

}
