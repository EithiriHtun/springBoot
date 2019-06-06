package com.springtest.demo.service;

import java.util.List;


import com.springtest.demo.entity.Employee;

public interface EmployeeService {
	
	
	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deletebyId(int theID);
	
	

}
