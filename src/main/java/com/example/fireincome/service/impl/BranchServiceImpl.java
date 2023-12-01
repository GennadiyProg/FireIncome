package com.example.fireincome.service.impl;

import com.example.fireincome.model.Branch;
import com.example.fireincome.model.Organization;
import com.example.fireincome.repos.BranchRepo;
import com.example.fireincome.repos.OrganizationRepo;
import com.example.fireincome.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepo branchRepo;
    private final OrganizationRepo organizationRepo;

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
}
