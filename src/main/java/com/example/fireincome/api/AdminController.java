package com.example.fireincome.api;

import com.example.fireincome.model.Organization;
import com.example.fireincome.repos.OrganizationRepos;
import com.example.fireincome.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/createOrg")
    public boolean createOrg(@RequestBody Organization organization) {
        return adminService.createOrg(organization);
    }
}
