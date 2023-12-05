package com.example.fireincome.api;

import com.example.fireincome.model.Branch;
import com.example.fireincome.model.Organization;
import com.example.fireincome.model.User;
import com.example.fireincome.service.BranchService;
import com.example.fireincome.service.OrganizationService;
import com.example.fireincome.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/branch")
@PreAuthorize("hasAnyRole('CHIEF', 'SUPERVISOR')")
public class BranchController {
    private final BranchService branchService;
    private final OrganizationService organizationService;
    private final UserService userService;

    @GetMapping("/all")
    public List<Branch> allBranches(Principal principal) {
        List<Branch> branches;
        Optional<Organization> org = organizationService.loadByDirectoryUsername(principal.getName());
        if (org.isEmpty()) {
            org = organizationService.loadBySupervisor(principal.getName());
        }
        branches = org.orElseGet(Organization::new).getBranches();
        return branches;
    }

    @GetMapping("/{kpp}")
    public Branch getBranch(@PathVariable String kpp) {
        return branchService.getBranch(kpp);
    }

    @PostMapping("/{kpp}/sellers/attach")
    public String attachSeller(@RequestBody User seller, @PathVariable String kpp) {
        Branch branch = branchService.getBranch(kpp);
        Optional<User> foundedSeller = userService.findSellerByFioAndPassport(seller);
    }
}
