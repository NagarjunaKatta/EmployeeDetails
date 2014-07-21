package com.mize.employee;

import org.springframework.stereotype.Component;

@Component
public class Employee {
private String empid;
private String empname;
private String empdepartment;
private int empsalary;
public String getEmpid() {
	return empid;
}
public void setEmpid(String empid) {
	this.empid = empid;
}
public String getEmpname() {
	return empname;
}
public void setEmpname(String empname) {
	this.empname = empname;
}
public String getEmpdepartment() {
	return empdepartment;
}
public void setEmpdepartment(String empdepartment) {
	this.empdepartment = empdepartment;
}
public int getEmpsalary() {
	return empsalary;
}
public void setEmpsalary(int empsalary) {
	this.empsalary = empsalary;
}

}
