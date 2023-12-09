package com.example.fireincome.service.impl;

import com.example.fireincome.model.Branch;
import com.example.fireincome.model.Organization;
import com.example.fireincome.model.User;
import com.example.fireincome.repos.BranchRepo;
import com.example.fireincome.repos.OrganizationRepo;
import com.example.fireincome.repos.UserRepo;
import com.example.fireincome.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepo branchRepo;
    private final OrganizationRepo organizationRepo;
    private final UserRepo userRepo;

    @Override
    public Branch createBranch(Branch branch, String directorUsername) {
        branch = branchRepo.save(branch);
        Organization org = organizationRepo.findByDirector_Username(directorUsername).orElseGet(Organization::new);
        org.getBranches().add(branch);
        organizationRepo.save(org);
        return branch;
    }

    @Override
    public void deleteBranch(String kpp, String directorUsername) {
        Organization org = organizationRepo.findByDirector_Username(directorUsername).orElseGet(Organization::new);
        org.getBranches().removeIf(branch -> branch.getKpp().equals(kpp));
        branchRepo.deleteByKpp(kpp);
    }

    @Override
    public Branch getBranch(String kpp) {
        return branchRepo.findByKpp(kpp).orElseGet(Branch::new);
    }

    @Override
    public Branch attachSeller(String kpp, User seller) {
        Branch branch = branchRepo.findByKpp(kpp).orElseGet(Branch::new);
        branch.getSellers().add(seller);
        return branchRepo.save(branch);
    }

    @Override
    public void detachSeller(String kpp, User seller) {
        Branch branch = branchRepo.findByKpp(kpp).orElseGet(Branch::new);
        branch.getSellers().removeIf(user -> user.getUsername().equals(seller.getUsername()));
        branchRepo.save(branch);
        List<Branch> branchesWithCurrentSeller = branchRepo.findAllBySellersIsContaining(seller);
        if (branchesWithCurrentSeller.isEmpty()) {
            seller.setActive(false);
            userRepo.save(seller);
        }
    }
}
