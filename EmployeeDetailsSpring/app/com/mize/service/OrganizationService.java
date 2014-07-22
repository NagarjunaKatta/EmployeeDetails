package com.mize.service;

import java.util.List;

import com.mize.dto.Organization;

public interface OrganizationService{

	Organization getOrganizationByCode(long code);

	List<Organization> getAllOrganizations();

	Organization insertOrganization(Organization organization);

	Organization updateOrganization(Organization organization);

	boolean deleteOrganizationById(long code);

	boolean deleteAllOrganizations();
	
}
