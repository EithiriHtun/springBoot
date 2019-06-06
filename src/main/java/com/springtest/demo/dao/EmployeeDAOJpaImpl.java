package com.springtest.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springtest.demo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
	
	
	private EntityManager  entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theentity) {
		entityManager=theentity;
	}
	
	@Override
	public List<Employee> findAll() {
		

		//create a query
		Query  SQL= (Query) entityManager.createQuery("from Employee");
		
		//ececute query and get result list
		List<Employee> employees= SQL.getResultList();
		
		//return  the results
		return employees;
		
	}

	@Override
	public Employee findById(int theId) {

		//get the employee 
	   Employee employee= 
		entityManager.find(Employee.class, theId);
				
	    //return the employee
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		//save employee
		Employee employeedb = entityManager.merge(theEmployee);
		
	//update with id from db ...employee db...	
				
		employeedb.setId(employeedb.getId());
		   
	}

	@Override
	public void deletebyId(int theID) {
		
		//delete object with primary key
		Query  SQL= (Query) entityManager.createQuery("delete from Employee where id=:employeeId");
		
		SQL.setParameter("employeeId", theID);
		
		SQL.executeUpdate();
	
	}

}
