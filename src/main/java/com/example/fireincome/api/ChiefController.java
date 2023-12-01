package com.example.fireincome.api;

import com.example.fireincome.model.Branch;
import com.example.fireincome.model.Organization;
import com.example.fireincome.model.User;
import com.example.fireincome.model.view.AdminOrgPrewView;
import com.example.fireincome.service.AdminService;
import com.example.fireincome.service.BranchService;
import com.example.fireincome.service.OrganizationService;
import com.example.fireincome.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chief")
@PreAuthorize("hasRole('CHIEF')")
public class ChiefController {
    private final OrganizationService organizationService;
    private final UserService userService;
    private final BranchService branchService;

    @GetMapping("/supervisors")
    public List<User> allSupervisors(Principal principal) {
        List<User> users;
        Optional<Organization> org = organizationService.loadByDirectoryUsername(principal.getName());
        if (org.isPresent()) {
            users = org.get().getSupervisors();
        } else {
            users = new ArrayList<>();
        }
        return users;
    }

    @PostMapping("/supervisors/create")
    public String createSupervisor(@RequestBody User user, Principal principal) {
        return userService.createSupervisor(user, principal.getName());
    }

    @DeleteMapping("/supervisors/{username}")
    public void deleteSupervisor(@PathVariable String username, Principal principal) {
        userService.deleteSupervisor(username, principal.getName());
    }

    @PostMapping("/branches/create")
    public Branch createBranch(@RequestBody Branch branch, Principal principal) {
        return branchService.createBranch(branch, principal.getName());
    }

    @DeleteMapping("/branches/{kpp}")
    public void deleteBranch(@PathVariable String kpp, Principal principal) {
        branchService.deleteBranch(kpp, principal.getName());
    }
}
