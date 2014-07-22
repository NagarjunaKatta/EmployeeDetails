package com.mize.service;

import java.sql.SQLException;
import java.util.List;

import com.mize.dto.Employee;


public interface EmployeeService{

	List<Employee> getAllRecords() throws SQLException;

	Employee getEmployeeById(long id) throws SQLException;

	boolean insertEmployee(Employee employee) throws SQLException;

	boolean deleteEmployeeById(long id) throws SQLException;

	boolean deleteAllEmployees() throws SQLException;
	
	int updateEmployee(Employee employee) throws SQLException;

}