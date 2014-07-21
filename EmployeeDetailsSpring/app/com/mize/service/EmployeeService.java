package com.mize.service;

import java.sql.SQLException;
import java.util.List;

import com.mize.employee.Employee;


public interface EmployeeService {

	List<Employee> getAllRecords() throws SQLException;

	Employee getEmployeebyid(String id) throws SQLException;

	boolean insertEmployee(Employee emp) throws SQLException;

	boolean deleteEmployeeById(String id) throws SQLException;

	boolean deleteAllEmployees() throws SQLException;
	
	int updateEmployee(Employee emp) throws SQLException;

}