package com.example.fireincome.repos;

import com.example.fireincome.model.Organization;
import com.example.fireincome.model.Role;
import com.example.fireincome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    List<User> findAllByRole(Role role);

    Optional<User> findBySurnameAndFirstNameAndLastNameAndPassportAndRole(String surname, String firstName, String LastName, String passport, Role role);
    List<User> findBySurnameIgnoreCaseAndFirstNameIgnoreCaseAndRoleAndOrganization(String surname, String firstName, Role role, Organization organization);
    List<User> findBySurnameIgnoreCaseAndFirstNameIgnoreCaseAndLastNameIgnoreCaseAndRoleAndOrganization(String surname, String firstName, String lastName, Role role, Organization organization);
}
