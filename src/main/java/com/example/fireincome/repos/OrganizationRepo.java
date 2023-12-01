package com.example.fireincome.repos;

import com.example.fireincome.model.Organization;
import com.example.fireincome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepo extends JpaRepository<Organization, String> {
    Optional<Organization> findByDirector_Username(String username);

    Optional<Organization> findBySupervisorsContaining(User supervisor);
}
