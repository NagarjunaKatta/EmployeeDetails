package controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mize.dto.Organization;
import com.mize.service.OrganizationService;

@org.springframework.stereotype.Controller
public class OrganizationApplication extends Controller {
	
	@Autowired
	private OrganizationService organizationService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	public Result getOrganizationByCode(String code) {
			Organization organization = null;
			organization = organizationService.getOrganizationByCode(Long.parseLong(code));
			if (organization != null) {
				play.Logger.info("Organization object by id is fetched");
				return ok(getJsonString(organization));
			} else {
				play.Logger.info("Record Not Found");
				return notFound(Json.toJson("Record Not Found"));
			}
	}

	public Result getAllOrganizations() {
		List<Organization> organizationList = null;
		organizationList = organizationService.getAllOrganizations();
		if (organizationList != null) {
			play.Logger.info("Organization List is fetched");
			return ok(getJsonString(organizationList));
		} else {
			play.Logger.info("Record Not Found");
			return notFound(Json.toJson("Record Not Found"));
		}
	}

	public Result insertOrganization() {
		Organization organization = getObjectFromJsonRequest(Organization.class);
		organizationService.insertOrganization(organization);
		play.Logger.info("Organization Record is Inserted Successfully");
		return ok(Json.toJson(organization));
	}

	public Result updateOrganization() {
		Organization organization = getObjectFromJsonRequest(Organization.class);
		organizationService.updateOrganization(organization);
		play.Logger.info("Organization Record is Updated Successfully");
		return ok(Json.toJson(organization));
	}

	public Result deleteOrganizationById(String id) {
		boolean status = false;
		status = organizationService.deleteOrganizationById(Integer.parseInt(id));
			play.Logger.info("Organization Record is Deleted");
			return ok(Json.toJson("Record Deleted"+status));
	}

	public Result deleteAllOrganizations() {
		boolean status = false;
		status = organizationService.deleteAllOrganizations();
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
