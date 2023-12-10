package com.example.fireincome.service.impl;

import com.example.fireincome.model.Branch;
import com.example.fireincome.model.Organization;
import com.example.fireincome.model.User;
import com.example.fireincome.repos.BranchRepo;
import com.example.fireincome.repos.OrganizationRepo;
import com.example.fireincome.repos.UserRepo;
import com.example.fireincome.service.BranchService;
import com.example.fireincome.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepo organizationRepo;
    private final UserRepo userRepo;
    private final BranchRepo branchRepo;

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
