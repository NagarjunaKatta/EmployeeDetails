package com.mize.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mize.dto.Organization;
import com.mize.jpa.repository.OrganizationJPARepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	OrganizationJPARepository organizationRepository;

	@Override
	public Organization getOrganizationByCode(long code) {
		return organizationRepository.findOrganizationByCode(code);
	}

	@Override
	public List<Organization> getAllOrganizations() {
		return organizationRepository.findAll();
	}

	@Override
	public Organization insertOrganization(Organization organization) {
		return organizationRepository.save(organization);
	}

	@Override
	public Organization updateOrganization(Organization organization) {
		return organizationRepository.save(organization);
	}

	@Override
	public boolean deleteOrganizationById(long id) {
		boolean status=false;
		organizationRepository.delete(id);
		status=true;
		return status;
	}

	@Override
	public boolean deleteAllOrganizations() {
		boolean status=false;
		organizationRepository.deleteAll();
		status=true;
		return status;
	}
}