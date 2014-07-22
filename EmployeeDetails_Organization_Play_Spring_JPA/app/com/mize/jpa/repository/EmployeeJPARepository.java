package com.mize.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mize.dto.Employee;

@Repository
public interface EmployeeJPARepository extends JpaRepository<Employee, Long>
{

}
