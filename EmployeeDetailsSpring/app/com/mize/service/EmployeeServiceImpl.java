package com.mize.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mize.employee.Employee;
import com.mize.employeedao.EmployeeDAO;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO empdao;
	
	public EmployeeDAO getEmpdao() {
		return empdao;
	}
	public void setEmpdao(EmployeeDAO empdao) {
		this.empdao = empdao;
	}
	@Override
	public List<Employee> getAllRecords() throws SQLException{
		
		return empdao.getAllRecords();
	}
	@Override
	public Employee getEmployeebyid(String id) throws SQLException {
		return empdao.getEmployeebyid(id);
	}
	
	@Override
	public boolean insertEmployee(Employee emp) throws SQLException {
		return empdao.insertEmployee(emp);
	}
	
	@Override
	public boolean deleteEmployeeById(String id) throws SQLException {
		return empdao.deleteEmployeeById(id);
	}
	
	@Override
	public boolean deleteAllEmployees() throws SQLException {
		return empdao.deleteAllEmployees();
	}
	
	@Override
	public int updateEmployee(Employee emp) throws SQLException {
		
		return empdao.updateEmployee(emp);
	}
}
