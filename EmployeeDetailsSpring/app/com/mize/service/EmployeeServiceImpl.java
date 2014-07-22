package com.mize.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mize.dto.Employee;
import com.mize.jpa.repository.EmployeeJPARepository;

@Service
public  class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeJPARepository employeeJPARepository;
	
	@Override
	public List<Employee> getAllRecords(){
		
		return employeeJPARepository.findAll();
	}
	@Override
	public Employee getEmployeeById(long id){
		return employeeJPARepository.findOne(id);
	}
	
	@Override
	public boolean insertEmployee(Employee employee){
		
		employeeJPARepository.save(employee);
		return false;
	}
	@Override
	public int updateEmployee(Employee employee){
		employeeJPARepository.save(employee);
		return 0;
	}
	@Override
	public boolean deleteEmployeeById(long id){

		employeeJPARepository.delete(id);
		return false;
	}
	
	@Override
	public boolean deleteAllEmployees(){

		employeeJPARepository.deleteAll();
		return true;
	}
}
