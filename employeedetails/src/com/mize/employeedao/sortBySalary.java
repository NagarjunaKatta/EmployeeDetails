package com.mize.employeedao;

import java.util.Comparator;

import com.mize.employee.Employee;

public class sortBySalary implements Comparator<Employee> {

	@Override
	public int compare(Employee emp0, Employee emp1) {
		if(emp0.getEmpsalary()>emp1.getEmpsalary())
		return 1;
		else if(emp0.getEmpsalary()<emp1.getEmpsalary())
		return -1;
		else return 0;
	}



	
}
