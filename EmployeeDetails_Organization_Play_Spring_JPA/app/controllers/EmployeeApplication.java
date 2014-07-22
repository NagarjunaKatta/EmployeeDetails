package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mize.dto.Employee;
import com.mize.service.EmployeeService;

@Component
public class EmployeeApplication extends Controller {
	
	@Autowired
	private EmployeeService employeeService;
	private ObjectMapper objectMapper=new ObjectMapper();
	
	public Result getEmployeeById(String id) {
			Employee employee = null;
			if(id!=null){
			try {
				employee = employeeService.getEmployeeById(Long.parseLong(id));
			} catch (SQLException e) {
				play.Logger.error(e.getMessage(), e);
				return internalServerError(e.getMessage());
			}
			if (employee != null) {
				play.Logger.info("Employee object by id is fetched");
				return ok(getJsonString(employee));
			} else {
				play.Logger.info("Record Not Found");
				return notFound(Json.toJson("Record Not Found"));
			}
			}else{
				play.Logger.warn("Invalid Id");
				return badRequest(Json.toJson("Invalid Id"));
			}
	}

	public Result getAllEmployees() {
		List<Employee> employeeList = null;
		try {
			employeeList = employeeService.getAllRecords();
		} catch (SQLException e) {
			play.Logger.error(e.getMessage(), e);
			return internalServerError(e.getMessage());
		}
		if (employeeList != null) {
			play.Logger.info("Employee List is fetched");
			return ok(getJsonString(employeeList));
		} else {
			play.Logger.info("Record Not Found");
			return notFound(Json.toJson("Record Not Found"));
		}
	}

	public Result insertEmployee() {

		Employee employee = getObjectFromJsonRequest(Employee.class);
		try {
			employeeService.insertEmployee(employee);
		} catch (SQLException e) {
			play.Logger.error(e.getMessage(), e);
			return internalServerError(e.getMessage());
		}
		play.Logger.info("Employee Record is Inserted Successfully");
		return ok(Json.toJson(employee));
	}

	public Result updateEmployee() {
		Employee employee = getObjectFromJsonRequest(Employee.class);
		try {
			employeeService.updateEmployee(employee);
		} catch (SQLException e) {
			play.Logger.error(e.getMessage(), e);
			return internalServerError(e.getMessage());
		}
		play.Logger.info("Employee Record is Updated Successfully");
		return ok(Json.toJson(employee));
	}

	public Result deleteEmployeeById(String id) {
			try {
				employeeService.deleteEmployeeById(Long.parseLong(id));
			} catch (SQLException e) {
				play.Logger.error(e.getMessage(), e);
				return internalServerError(e.getMessage());
			}
			play.Logger.info("Employee Record is Deleted");
			return ok(Json.toJson("Record Deleted"));
	}

	public Result deleteAllEmployees() {
		boolean status = false;
		try {
			status = employeeService.deleteAllEmployees();
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
