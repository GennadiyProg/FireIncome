package com.example.fireincome.service;

import com.example.fireincome.model.Organization;

import java.util.Optional;

public interface OrganizationService {
    Optional<Organization> loadByDirectoryUsername(String director);

    Optional<Organization> loadBySupervisor(String supervisorUsername);
}
