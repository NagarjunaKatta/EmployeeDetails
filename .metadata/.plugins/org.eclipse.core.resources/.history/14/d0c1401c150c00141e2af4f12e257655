package com.mize.service;

import java.util.List;
import java.util.Map;

import com.mize.dao.EmployeeDAO;
import com.mize.dao.EmployeeDAOService;
import com.mize.dto.EmployeeDTO;

/**
 * 
 * @author nagarjunakatta
 * 
 */
public class EmployeeService implements EmployeeDAOService {
	EmployeeDAO employeedao;

	public void setEmployeedao(EmployeeDAO employeedao) {
		this.employeedao = employeedao;
	}

	@Override
	public List<EmployeeDTO> getAll() {

		return employeedao.getAll();
	}

	@Override
	public int deleteAll() {

		return employeedao.deleteAll();
	}

	@Override
	public Object getEmployeeByID(String empid) {

		return employeedao.getEmployeeByID(empid);
	}

	@Override
	public Object getEmployeeByDepartment(String Dept) {

		return employeedao.getEmployeeByDepartment(Dept);
	}

	@Override
	public int insertEmployee(EmployeeDTO empdto) {

		return employeedao.insertEmployee(empdto);
	}

	@Override
	public int updateEmployee(EmployeeDTO empdto) {

		return employeedao.updateEmployee(empdto);
	}

	@Override
	public int deleteEmployeeById(int id) {

		return employeedao.deleteEmployeeById(id);
	}

	@Override
	public int avarageSalary(List<EmployeeDTO> emp) {

		return employeedao.avarageSalary(emp);
	}

	@Override
	public int avarageSalary(String Departname) {

		return employeedao.avarageSalary(Departname);
	}

	@Override
	public List<Map<String, Object>> getAvarageSalaryByDepartment() {
		return employeedao.getAvarageSalaryByDepartment();
	}

}
