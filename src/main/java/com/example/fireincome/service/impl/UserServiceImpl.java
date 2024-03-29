package com.example.fireincome.service.impl;

import com.example.fireincome.model.Organization;
import com.example.fireincome.model.Role;
import com.example.fireincome.model.User;
import com.example.fireincome.repos.OrganizationRepo;
import com.example.fireincome.repos.UserRepo;
import com.example.fireincome.service.UserService;
import com.example.fireincome.util.ModelUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static com.example.fireincome.model.Role.SELLER;
import static com.example.fireincome.model.Role.SUPERVISOR;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final OrganizationRepo organizationRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUser(Principal principal) {
        return userRepo.findByUsername(principal.getName()).orElseGet(User::new);
    }

    @Override
    public String createSupervisor(User user, String directorUsername) {
        user = createUser(user, SUPERVISOR);
        Organization org = organizationRepo.findByDirector_Username(directorUsername).orElseGet(Organization::new);
        org.getSupervisors().add(user);
        organizationRepo.save(org);
        return user.getUsername();
    }

    @Override
    public void deleteSupervisor(String supervisorUsername, String directorUsername) {
        Optional<User> user = userRepo.findByUsername(supervisorUsername);
        if (user.isPresent()) {
            user.get().setActive(false);
            userRepo.save(user.get());
            Organization org = organizationRepo.findByDirector_Username(directorUsername).orElseGet(Organization::new);
            org.getSupervisors().removeIf(supervisor -> supervisor.getUsername().equals(supervisorUsername));
            organizationRepo.save(org);
        }
    }

    @Override
    public Optional<User> findSellerByFioAndPassport(User user) {
        return userRepo.findBySurnameAndFirstNameAndLastNameAndPassportAndRole(
                user.getSurname(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassport(),
                user.getRole()
        );
    }

    @Override
    public User createSeller(User user) {
        return createUser(user, SELLER);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElseGet(User::new);
    }

    @Override
    public List<User> findSellerByFioAndOrganization(String[] fio, Organization organization) {
        if (fio.length > 2)
            return userRepo.findBySurnameIgnoreCaseAndFirstNameIgnoreCaseAndLastNameIgnoreCaseAndRoleAndOrganization(fio[0], fio[1], fio[2], SELLER, organization);
        return userRepo.findBySurnameIgnoreCaseAndFirstNameIgnoreCaseAndRoleAndOrganization(fio[0], fio[1], SELLER, organization);
    }

    private User createUser(User user, Role role) {
        user.setUsername(ModelUtils.generateUsername(user));
        user.setRole(role);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }


}
