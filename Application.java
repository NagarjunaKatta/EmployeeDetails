package controllers;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mize.training.domain.Employee;

import play.*;
import play.mvc.*;
import play.mvc.Http.RequestBody;

import views.html.*;

public class Application extends Controller {

	public static ObjectMapper objectMapper = new ObjectMapper();
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result testEmployee() {
    	
    	Employee emp = new Employee();
    	emp.setId(1L);
    	emp.setName("Suresh");
    	return ok(getJsonString(emp));
    	
    }
    
	public static String getJsonString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getObjectFromJsonRequest(Class<?> valueType) {
		RequestBody body = request().body();
		JsonNode node = body.asJson();
		
		Object readValue = null;
		try {
			if(node != null){
				readValue = objectMapper.readValue(node.traverse(), valueType);
			}
			else if(body.asFormUrlEncoded() != null && body.asFormUrlEncoded().get("json") != null && body.asFormUrlEncoded().get("json").length > 0){
				readValue = objectMapper.readValue(body.asFormUrlEncoded().get("json")[0], valueType);
			}
			return (T) readValue;
		} catch (IOException e) {
			return null;
		}
	}
	
}
