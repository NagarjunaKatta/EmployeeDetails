package controllers;

import java.util.List;

import com.mize.dto.EmployeeDTO;
import com.mize.service.EmployeeService;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

	static EmployeeService employeeservice =new EmployeeService();
    public static Result index() {
//    	getAll();
        return ok(index.render("Your new application is ready."));
        
    }
    public static List<EmployeeDTO> getAll() {
    	return employeeservice.getAll();
    }
    public Object getEmployeeByID(String empid) {
    
    	return employeeservice.getEmployeeByID(empid);
    }

    
}