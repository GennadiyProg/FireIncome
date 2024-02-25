package com.example.fireincome.service;

import com.example.fireincome.model.Organization;
import com.example.fireincome.model.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUser(Principal principal);

    String createSupervisor(User user, String directorUsername);

    void deleteSupervisor(String supervisorUsername, String directorUsername);

    Optional<User> findSellerByFioAndPassport(User user);

    User createSeller(User user);

    User findByUsername(String username);

    List<User> findSellerByFioAndOrganization(String[] fio, Organization organization)
}
