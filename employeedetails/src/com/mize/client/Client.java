package com.mize.client;

import java.util.Iterator;
import java.util.List;

import com.mize.employee.Employee;
import com.mize.employeedao.EmployeeService;

public class Client {
	static EmployeeService empservice=new EmployeeService();
	static List<Employee> list;
	
	private void displaylist(List<Employee> list){
		Iterator<Employee> iterator=list.iterator();
		Employee emp;
		while(iterator.hasNext()){
			emp=iterator.next();
			displayemployee(emp);
		}
	}

	private void displayemployee(Employee emp) {
		System.out.println(emp.getEmpid()+"  "+emp.getEmpname()+"  "+emp.getEmpdepartment()+"  "+emp.getEmpsalary());
	}
	
	public static void main(String[] args) {
		
		Client client=new Client();
		System.out.println("____Display All Records_____");
		list=empservice.getAllRecords();
		client.displaylist(list);
		list=empservice.getEmployeebyDepartment("eee");
		System.out.println("____Department wise order by salary_____");
		client.displaylist(list);
		Employee employee=empservice.getEmployeebyid("3");
		System.out.println("____Get Employee by Employee id_____");
		client.displayemployee(employee);
		System.out.println("____Get Employee group by Department_____");
		list=empservice.getRecordsbygroup();
		client.displaylist(list);
		
	}

}
