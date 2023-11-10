package com.example.fireincome.api;

import com.example.fireincome.model.Organization;
import com.example.fireincome.model.view.AdminOrgPrewView;
import com.example.fireincome.repos.OrganizationRepos;
import com.example.fireincome.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/createOrg")
    public boolean createOrg(@RequestBody Organization organization) {
        return adminService.createOrg(organization);
    }

    @GetMapping("/orgs")
    public List<AdminOrgPrewView> allOrgs() {
        List<Organization> orgs = adminService.allOrgs();
        List<AdminOrgPrewView> adminOrgs = orgs.stream().map(AdminOrgPrewView::new).toList();
        return adminOrgs;
    }
}
