package com.example.fireincome.api;

import com.example.fireincome.model.Branch;
import com.example.fireincome.model.Organization;
import com.example.fireincome.model.User;
import com.example.fireincome.model.view.Statistic;
import com.example.fireincome.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chief")
@PreAuthorize("hasRole('CHIEF')")
public class ChiefController {
    private final OrganizationService organizationService;
    private final UserService userService;
    private final BranchService branchService;
    private final StatisticService statisticService;

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

    @GetMapping("/statistic/category")
    public List<Statistic> getStatisticByCategory(Principal principal) {
        Optional<Organization> org = organizationService.loadByDirectoryUsername(principal.getName());
        if (org.isPresent()) {
            return statisticService.loadStatisticByCategory(org.get());
        } else {
            return new ArrayList<>();
        }
    }

    @GetMapping("/statistic/branch")
    public List<Statistic> getStatisticByBranch(Principal principal) {
        Optional<Organization> org = organizationService.loadByDirectoryUsername(principal.getName());
        if (org.isPresent()) {
            return statisticService.loadStatisticByBranch(org.get());
        } else {
            return new ArrayList<>();
        }
    }

    @GetMapping("/statistic/time")
    public List<Statistic> getStatisticByMonth(Principal principal) {
        Optional<Organization> org = organizationService.loadByDirectoryUsername(principal.getName());
        if (org.isPresent()) {
            return statisticService.loadStatisticByMonth(org.get());
        } else {
            return new ArrayList<>();
        }
    }
}
