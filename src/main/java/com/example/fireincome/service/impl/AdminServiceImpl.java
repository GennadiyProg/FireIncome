package com.example.fireincome.service.impl;

import com.example.fireincome.model.Organization;
import com.example.fireincome.model.User;
import com.example.fireincome.repos.OrganizationRepo;
import com.example.fireincome.repos.UserRepo;
import com.example.fireincome.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.fireincome.model.Role.CHIEF;
import static com.example.fireincome.util.ModelUtils.generateUsername;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final OrganizationRepo organizationRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String createOrg(Organization organization) {
        try {
            String username = generateUsername(organization.getDirector());
            organization.getDirector().setUsername(username);
            organization.getDirector().setRole(CHIEF);
            organization.getDirector().setActive(true);
            organization.getDirector().setPassword(passwordEncoder.encode(organization.getDirector().getPassword()));
            User director = userRepo.save(organization.getDirector());
            organization.setDirector(director);
            organizationRepo.save(organization);
            return username;
        } catch (Exception e) {
            log.error(e.getMessage());
            return "";
        }
    }

    @Override
    public List<Organization> allOrgs() {
        return organizationRepo.findAll();
    }
}
