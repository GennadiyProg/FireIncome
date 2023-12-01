package com.example.fireincome.api;

import com.example.fireincome.model.User;
import com.example.fireincome.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class IndexController {
    private final UserService userService;

    @GetMapping("/loadUser")
    @PreAuthorize("isAuthenticated()")
    public User loadUser(Principal principal) {
        return userService.getUser(principal);
    }
}
