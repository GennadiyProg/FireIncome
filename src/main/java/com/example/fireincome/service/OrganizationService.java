package com.example.fireincome.service;

import com.example.fireincome.model.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {
    String createOrg(Organization organization);

    List<Organization> allOrgs();

    Optional<Organization> loadByDirectoryUsername(String director);

    Optional<Organization> loadBySupervisor(String supervisorUsername);

    Optional<Organization> loadBySeller(String sellerUsername);
}
