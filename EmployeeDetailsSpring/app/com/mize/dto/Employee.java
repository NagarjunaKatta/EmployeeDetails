package com.mize.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "employee", uniqueConstraints = {@UniqueConstraint (columnNames = {"id"})})
public class Employee {
	
private long id;
private String name;
private String department;
private double salary;


@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name = "id", nullable = false, unique = true)
public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

@Column(name = "name", length = 30, nullable = false)
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
@Column(name = "department", length = 30, nullable = false)
public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}
@Column(name = "salary", length = 30, nullable =false)
public double getSalary() {
	return salary;
}

public void setSalary(double salary) {
	this.salary = salary;
}
}
