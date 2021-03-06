package com.mize.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.mize.dto.EmployeeDTO;
//import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 
 * @author nagarjunakatta
 * 
 */
public class EmployeeDAO implements EmployeeDAOService, RowMapper {

	DataSource dataSource;
	JdbcTemplate jdbcTemplate;
	Properties queries;

	public Properties getQueries() {
		return queries;
	}

	public void setQueries(Properties queries) {
		this.queries = queries;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	@Override
	public List<EmployeeDTO> getAll() {

		// String sql="select * from employee_k";
		String sql = queries.getProperty("getAllRecords");
		List<EmployeeDTO> employeeList = (List<EmployeeDTO>) jdbcTemplate
				.query(sql, this);
		return employeeList;
	}

	@Override
	public int deleteAll() {
		// String sql="delete from employee_k";
		String sql = queries.getProperty("delete All Records");
		int count = jdbcTemplate.update(sql);
		;
		return count;
	}

	@Override
	public Object getEmployeeByID(String empid) {
		// String sql="select * from employee_k where empid=?";
		String sql = queries.getProperty("getEmployeeById");
		EmployeeDTO empdto = jdbcTemplate.queryForObject(sql,
				new Object[] { empid }, this);
		return empdto;
	}

	@Override
	public List<EmployeeDTO> getEmployeeByDepartment(String dept) {
		// String sql="select * from employee_k where deptid=?";
		String sql = queries.getProperty("getEmpbyDept");
		List<EmployeeDTO> empdto = jdbcTemplate.query(sql,
				new Object[] { dept }, this);
		return empdto;
	}

	@Override
	public int updateEmployee(EmployeeDTO empdto) {
		// String
		// sql="update employee_k set empname=?,deptid=?,salary=?,joindate=? where empid=?";
		String sql = queries.getProperty("updateEmployee");
		int count = jdbcTemplate.update(
				sql,
				new Object[] { empdto.getEmployeeName(),
						empdto.getDepartment(), empdto.getSalary(),
						empdto.getJoinDate(), empdto.getEmployeeID() });
		return count;
	}

	@Override
	public int insertEmployee(EmployeeDTO empdto) {
		// String sql="insert into employee_k values(?,?,?,?,?)";
		String sql = queries.getProperty("insertEmp");
		int count = jdbcTemplate.update(sql,
				new Object[] { empdto.getEmployeeID(),
						empdto.getEmployeeName(), empdto.getDepartment(),
						empdto.getSalary(), empdto.getJoinDate() });
		return count;
	}

	@Override
	public int deleteEmployeeById(int id) {
		// String sql="delete from employee_k where empid=?";
		String sql = queries.getProperty("deleteEmpbyId");
		int count = jdbcTemplate.update(sql, new Object[] { id });
		return count;
	}

	@Override
	public int avarageSalary(List<EmployeeDTO> emp) {
		int avgSal, temp = 0;
		for (EmployeeDTO emplist : emp) {
			temp += emplist.getSalary();
		}
		avgSal = temp / emp.size();
		return avgSal;
	}

	@Override
	public int avarageSalary(String Departname) {
		String sql = "select avg(salary) from employee_k where deptid=?";
		Integer avgSal = jdbcTemplate.queryForObject(sql,
				new Object[] { Departname }, Integer.class);
		return avgSal;
	}

	@Override
	public List<Map<String, Object>> getAvarageSalaryByDepartment() {
		String sql = "select deptid, avg(salary) from employee_k group by deptid";

		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public EmployeeDTO mapRow(ResultSet arg0, int arg1) throws SQLException {
		EmployeeDTO empdto = new EmployeeDTO();
		empdto.setEmployeeID(arg0.getString(1));
		empdto.setEmployeeName(arg0.getString(2));
		empdto.setDepartment(arg0.getString(3));
		empdto.setSalary(Integer.parseInt(arg0.getString(4)));
		empdto.setJoinDate(arg0.getString(5).toString());
		return empdto;
	}

}
