package com.springtest.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springtest.demo.dao.EmployeeDAO;
import com.springtest.demo.entity.Employee;


@Service

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeedao;
	
	@Autowired
	public EmployeeServiceImpl(@Qualifier("employeeDAOJpaImpl") EmployeeDAO employeeDAO) {
		employeedao=employeeDAO;
	}
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		
		return employeedao.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		
		return employeedao.findById(theId);
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		
		employeedao.save(theEmployee);
	}

	@Override
	@Transactional
	public void deletebyId(int theID) {
		
		employeedao.deletebyId(theID);
	}
	
	
}
