package com.example.fireincome.service.impl;

import com.example.fireincome.model.Organization;
import com.example.fireincome.model.Role;
import com.example.fireincome.model.User;
import com.example.fireincome.repos.OrganizationRepos;
import com.example.fireincome.repos.UserRepos;
import com.example.fireincome.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.fireincome.model.Role.CHIEF;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final OrganizationRepos organizationRepos;
    private final UserRepos userRepos;

    @Override
    public boolean createOrg(Organization organization) {
        try {
            organization.getDirector().setRole(CHIEF);
            organization.getDirector().setActive(true);
            User director = userRepos.save(organization.getDirector());
            organization.setDirector(director);
            organizationRepos.save(organization);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Organization> allOrgs() {
        return organizationRepos.findAll();
    }
}
