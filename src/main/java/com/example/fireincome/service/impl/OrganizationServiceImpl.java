package com.example.fireincome.service.impl;

import com.example.fireincome.model.Branch;
import com.example.fireincome.model.Organization;
import com.example.fireincome.model.User;
import com.example.fireincome.repos.BranchRepo;
import com.example.fireincome.repos.OrganizationRepo;
import com.example.fireincome.repos.UserRepo;
import com.example.fireincome.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.fireincome.model.Role.CHIEF;
import static com.example.fireincome.util.ModelUtils.generateUsername;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepo organizationRepo;
    private final UserRepo userRepo;
    private final BranchRepo branchRepo;
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

    @Override
    public Optional<Organization> loadByDirectoryUsername(String director) {
        return organizationRepo.findByDirector_Username(director);
    }

    @Override
    public Optional<Organization> loadBySupervisor(String supervisorUsername) {
        User supervisor = userRepo.findByUsername(supervisorUsername).orElseGet(User::new);
        return organizationRepo.findBySupervisorsContaining(supervisor);
    }

    @Override
    public Optional<Organization> loadBySeller(String sellerUsername) {
        User seller = userRepo.findByUsername(sellerUsername).orElseGet(User::new);
        List<Branch> branchesWithCurrentSeller = branchRepo.findAllBySellersIsContaining(seller);
        if (!branchesWithCurrentSeller.isEmpty()) {
            return organizationRepo.findByBranchesContaining(branchesWithCurrentSeller.get(0));
        }
        return Optional.empty();
    }
}
