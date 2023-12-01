package com.example.fireincome.service;

import com.example.fireincome.model.User;

import java.security.Principal;

public interface UserService {
    User getUser(Principal principal);

    String createSupervisor(User user, String directorUsername);

    void deleteSupervisor(String supervisorUsername, String directorUsername);
}
