package com.example.fireincome.repos;

import com.example.fireincome.model.Category;
import com.example.fireincome.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, String> {
    List<Category> findAllByOrganization(Organization organization);
}
