package com.springtest.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtest.demo.entity.Employee;
import com.springtest.demo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeservice;
	
	//quick and dirty : inject employee dao
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		employeeservice= employeeService;
	}
	
	//expose " /employees" and return list of employees
  @GetMapping("/employees")
  public List<Employee> findAll(){
	  return employeeservice.findAll();
  }
  
  //add mapping for GET /employees/{employeeId}
  
  @GetMapping("/employees/{employeeId}")
  public Employee getEmployee(@PathVariable int employeeId) {
	  
	Employee theemployee = employeeservice.findById(employeeId);  
	
	if(theemployee==null) {
		throw new RuntimeException("Employee id not found");
	}
 return theemployee;
	
  }
  
  
  //add mapping for POST /employees - add new employee
  
  @PostMapping("/employees")
  public Employee addEmployee(@RequestBody Employee theemployee) {
	
	//also just in case they pass an id in json ... set id to 0
	//this is to force a save of new item ... instead of update
	  
	  
	  theemployee.setId(0);
	  
	  employeeservice.save(theemployee);
	  
	return theemployee;
	  
  }
  
  //add mapping for PUT /employee -update existing employee
  
  @PutMapping("/employees")
  public Employee updateEmployee(@RequestBody Employee theemployee) {
	  
	 employeeservice.save(theemployee); 
	 
	 return theemployee;
  }
  
  
  //add mapping for DELETE /employees/{employeeId} - delete employee
  
  @DeleteMapping("/employees/{employeeId}")
  public String deleteEmployee(@PathVariable int employeeId) {
	
	  Employee tempemployee = employeeservice.findById(employeeId);
 
	  if(tempemployee == null) {
		  throw new RuntimeException("Employee id not found -" + employeeId); 
	  }
	  
	  employeeservice.deletebyId(employeeId); 
	  
	return "Deleted employee id - " + employeeId ;
  
  }
  

}
