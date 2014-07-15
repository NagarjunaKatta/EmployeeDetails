package com.mize.employeedao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.mize.empdb.DBConnection;
import com.mize.employee.Employee;

public class EmployeeService {
	private static String selectquery;
	private static String groupbyquery;
	private Connection connection;
	private Statement statement;
	private ResultSet resultset;
	private static Properties properties;
	private static FileInputStream input;
	private static boolean flag;
	public static boolean loadProperties(){


		flag=false;
		try {
			input=new FileInputStream("E:/Assesment/quries1.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		properties=new Properties();
		try {
			properties.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectquery=properties.getProperty("selectquery");
		groupbyquery=properties.getProperty("groupbyquery");
		flag=true;
		
		return flag;
	}
	public List<Employee> getAllRecords(){
		ArrayList<Employee> Employeelist = null;
		if(loadProperties()){
		connection=DBConnection.getdbConnection();
		try {
			statement=connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			resultset=statement.executeQuery(groupbyquery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			Employeelist=new ArrayList<Employee>();
			Employee emp;
			while(resultset.next()){
				emp=new Employee();
				emp.setEmpid(resultset.getString(1));
				emp.setEmpname(resultset.getString(2));
				emp.setEmpdepartment(resultset.getString(3));
				emp.setEmpsalary(resultset.getInt(4));
				Employeelist.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		else{System.out.println("Error in loading Qurey Properties");}
		return Employeelist;
	}
	public List<Employee> getRecordsbygroup(){

		ArrayList<Employee> Employeelist = null;
		if(loadProperties()){
		connection=DBConnection.getdbConnection();
		try {
			statement=connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			resultset=statement.executeQuery(groupbyquery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			Employeelist=new ArrayList<Employee>();
			Employee emp;
			while(resultset.next()){
				emp=new Employee();
				emp.setEmpid(resultset.getString(1));
				emp.setEmpname(resultset.getString(2));
				emp.setEmpdepartment(resultset.getString(3));
				emp.setEmpsalary(resultset.getInt(4));
				Employeelist.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		else{System.out.println("Error in loading Qurey Properties");}
		return Employeelist;
	}
	public Employee getEmployeebyid(String id){
		ArrayList<Employee> emplist=(ArrayList<Employee>) getAllRecords();
		Iterator<Employee> iterator=emplist.iterator();
		Employee emp;
		while(iterator.hasNext()){
			emp=iterator.next();
			if(emp.getEmpid().equalsIgnoreCase(id)){
				return emp;
			}
			}
		
		return null;
	}
	public List<Employee> getEmployeebyDepartment(String department){

		ArrayList<Employee> emplist=(ArrayList<Employee>) getAllRecords();
		Iterator<Employee> iterator=emplist.iterator();
		Employee emp=null;
		ArrayList<Employee> list=new ArrayList<Employee>();
		while(iterator.hasNext()){
			emp=iterator.next();
			if(emp.getEmpdepartment().equalsIgnoreCase(department)){
				list.add(emp);
				
				}
			}
		Collections.sort(list,new sortBySalary());
		return list;
	}

}
