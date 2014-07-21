package com.mize.dao;

import java.util.List;

import com.mize.dto.EmployeeDTO;

/**
 * 
 * @author nagarjunakatta This is Genral interface
 * 
 *         Defines Common methods for all DAO Classes
 */
public interface DAOService {

	/**
	 * Returns all records of implimented class
	 * 
	 * @return
	 */
	List<EmployeeDTO> getAll();

	/**
	 * Delete's All Records
	 * 
	 * @return
	 */
	int deleteAll();

}
