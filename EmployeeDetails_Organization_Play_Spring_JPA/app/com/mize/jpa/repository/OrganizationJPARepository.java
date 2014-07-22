package com.mize.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mize.dto.Organization;

@Repository
public interface OrganizationJPARepository extends JpaRepository<Organization, Long>
{
	Organization findOrganizationByCode(long code);
}
