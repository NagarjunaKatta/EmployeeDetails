package com.mize.employeedao;

import java.sql.SQLException;
import java.util.List;

import com.mize.employee.Employee;

public interface EmployeeDAO {

	public abstract List<Employee> getAllRecords() throws SQLException;

	public abstract Employee getEmployeebyid(String id) throws SQLException;

	public abstract boolean insertEmployee(Employee emp) throws SQLException;
	
	public abstract boolean deleteEmployeeById(String id) throws SQLException;
	
	public abstract boolean deleteAllEmployees() throws SQLException;

	public abstract int updateEmployee(Employee emp) throws SQLException;
}