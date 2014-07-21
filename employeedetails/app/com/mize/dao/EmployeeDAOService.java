package com.mize.dao;

import java.util.List;
import java.util.Map;

import com.mize.dto.EmployeeDTO;

/**
 * 
 * @author nagarjunakatta This is EmployeeDAO Specific Interface Defines methods
 *         Specific to Employee
 */
public interface EmployeeDAOService extends DAOService {

	/**
	 * Returns Employee Record for give Employee ID
	 * 
	 * @param empid
	 * @return
	 */
	Object getEmployeeByID(String empid);

	/**
	 * Returns Employee Record for give Employee Department
	 * 
	 * @param Dept
	 * @return
	 */
	Object getEmployeeByDepartment(String Dept);

	/**
	 * Returns number of Employee Records Inserted
	 * 
	 * @param employeedto
	 * @return
	 */
	int insertEmployee(EmployeeDTO employeedto);

	/**
	 * Returns number of Employee Records Updated
	 * 
	 * @param empdto
	 * @return
	 */
	int updateEmployee(EmployeeDTO empdto);

	/**
	 * Deletes Employee Record for given ID
	 * 
	 * @param id
	 * @return
	 */
	int deleteEmployeeById(int id);

	/**
	 * Returns AvarageSalary of Given Employees
	 * 
	 * @param emp
	 * @return
	 */
	int avarageSalary(List<EmployeeDTO> emp);

	/**
	 * Returns AvarageSalary of given Department
	 * 
	 * @param Departname
	 * @return
	 */
	int avarageSalary(String Departname);

	/**
	 * Returns AvarageSalary of Each Department
	 * 
	 * @return
	 */
	List<Map<String, Object>> getAvarageSalaryByDepartment();

}
