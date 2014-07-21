package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mize.employee.Employee;
import com.mize.service.EmployeeService;
import com.mize.service.EmployeeServiceImpl;

@Component
public class Application extends Controller {
	
	@Autowired
	private EmployeeService empser;
	private ObjectMapper objectMapper=new ObjectMapper();
	
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setEmpser(EmployeeService empser) {
		this.empser = empser;
	}
	public Result getEmployeebyid(String id) {
		
		if (id != null) {
			Employee emp = null;
			try {
				emp = empser.getEmployeebyid(id);
			} catch (SQLException e) {
				play.Logger.error(e.getMessage(), e);
				return internalServerError(e.getMessage());
			}
			if (emp != null) {
				play.Logger.info("Employee object by id is fetched");
				return ok(getJsonString(emp));
			} else {
				play.Logger.info("Record Not Found");
				return notFound(Json.toJson("Record Not Found"));
			}
		} else {
			play.Logger.warn("Invalid EmployeeId");
			return badRequest(Json.toJson("Invalid EmployeeId"));
		}
	}

	public Result getAllEmployees() {
		List<Employee> emplist = null;
		try {
			emplist = empser.getAllRecords();
		} catch (SQLException e) {
			play.Logger.error(e.getMessage(), e);
			return internalServerError(e.getMessage());
		}
		if (emplist != null) {
			play.Logger.info("Employee List is fetched");
			return ok(getJsonString(emplist));
		} else {
			play.Logger.info("Record Not Found");
			return notFound(Json.toJson("Record Not Found"));
		}
	}

	public Result insertEmployee() {

		Employee emp = getObjectFromJsonRequest(Employee.class);
		try {
			empser.insertEmployee(emp);
		} catch (SQLException e) {
			play.Logger.error(e.getMessage(), e);
			return internalServerError(e.getMessage());
		}
		play.Logger.info("Employee Record is Inserted Successfully");
		return ok(Json.toJson(emp));
	}

	public Result updateEmployee() {
		Employee emp = getObjectFromJsonRequest(Employee.class);
		try {
//			empser=getEmployeeServiceImplObject();
			empser.updateEmployee(emp);
		} catch (SQLException e) {
			play.Logger.error(e.getMessage(), e);
			return internalServerError(e.getMessage());

		}
		play.Logger.info("Employee Record is Updated Successfully");
		return ok(Json.toJson(emp));
	}

	public Result deleteEmployeeById(String id) {
		if (id != null) {
			try {
//				empser=getEmployeeServiceImplObject();
				empser.deleteEmployeeById(id);
			} catch (SQLException e) {
				play.Logger.error(e.getMessage(), e);
				return internalServerError(e.getMessage());
			}
			play.Logger.info("Employee Record is Deleted");
			return ok(Json.toJson("Record Deleted"));
		} else {
			play.Logger.warn("Invalid Id");
			return badRequest(Json.toJson("Invalid Id"));
		}

	}

	public Result deleteAllEmployees() {
		boolean status = false;
		try {
			status = empser.deleteAllEmployees();
		} catch (SQLException e) {
			play.Logger.error(e.getMessage(), e);
			return internalServerError(e.getMessage());
		}
		play.Logger.error("All Records Deleted");
		return ok(Json.toJson(" All Records Deleted " + status));
	}

	public String getJsonString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			play.Logger.error(e.getMessage(), e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getObjectFromJsonRequest(Class<?> valueType) {
		RequestBody body = request().body();
		JsonNode node = body.asJson();

		Object readValue = null;
		try {
			if (node != null) {
				readValue = objectMapper.readValue(node.traverse(), valueType);
			} else if (body.asFormUrlEncoded() != null
					&& body.asFormUrlEncoded().get("json") != null
					&& body.asFormUrlEncoded().get("json").length > 0) {
				readValue = objectMapper.readValue(
						body.asFormUrlEncoded().get("json")[0], valueType);
			}
			return (T) readValue;
		} catch (IOException e) {
			play.Logger.error(e.getMessage(), e);
			return null;
		}
	}
}
