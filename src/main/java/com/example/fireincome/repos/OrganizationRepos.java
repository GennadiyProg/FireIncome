package com.example.fireincome.repos;

import com.example.fireincome.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepos extends JpaRepository<Organization, String> {
}
