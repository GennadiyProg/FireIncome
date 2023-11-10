package com.example.fireincome.service;

import com.example.fireincome.model.Organization;

import java.util.List;

public interface AdminService {
    boolean createOrg(Organization organization);

    List<Organization> allOrgs();
}
