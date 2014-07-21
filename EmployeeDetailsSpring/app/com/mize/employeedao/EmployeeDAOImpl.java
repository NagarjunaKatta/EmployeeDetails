package com.mize.employeedao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mize.employee.Employee;

@Component
public class EmployeeDAOImpl implements EmployeeDAO , RowMapper<Employee> {
	
	

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static void main(String ar[]){
		ApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		System.out.println(context.getBean("dataSource"));
	}
	
	public void setDataSource(DataSource datasource) {
		this.dataSource = datasource;
		
	}
	@Override
	public List<Employee> getAllRecords() {
		play.Logger.info("Entered getAllRecords()");
		String sql="select * from employee";
		List<Employee> employee=(List<Employee>) jdbcTemplate.query(sql,this);
//		List<StudentDTO> student=jdbcTemplate.query(sql, new BeanPropertyRowMapper(StudentDTO.class));
		return employee;
	}
	@Override
	public Employee getEmployeebyid(String id) {
		String sql="select * from employee where empid=?";
		Employee emp=jdbcTemplate.queryForObject(sql, new Object[]{id},this);
		return emp;
	}
	@Override
	public boolean insertEmployee(Employee emp){
		boolean status=false;
		String sql="insert into employee values(?,?,?,?)";
		int count=jdbcTemplate.update(sql, new Object[]{ emp.getEmpid(),emp.getEmpname(),emp.getEmpdepartment(),emp.getEmpsalary()});
		status=true;
		return status;
	}
	@Override
	public boolean deleteEmployeeById(String id){
		boolean status=false;
		String sql="delete from employee where empid=?";
		jdbcTemplate.update(sql,new Object[]{id});
		status=true;
		return status;
	}
	@Override
	public boolean deleteAllEmployees(){
		String sql="delete from employee";
		jdbcTemplate.update(sql);
		return true;
	}
	@Override
	public int updateEmployee(Employee emp){
		String sql="update employee set empname=?,empdepart=?,salary=? where empid=?";
		int count=jdbcTemplate.update(sql, new Object[]{ emp.getEmpname(),emp.getEmpdepartment(),emp.getEmpsalary(),emp.getEmpid()});
		return count;
	}
	
	@Override
	public Employee mapRow(ResultSet arg, int arg1) throws SQLException{
		Employee employee=new Employee();
		employee.setEmpid(arg.getString("empid"));
		employee.setEmpname(arg.getString("empname"));
		employee.setEmpdepartment(arg.getString("empdepart"));
		employee.setEmpsalary(arg.getInt("salary"));
		return employee;
	}
}
