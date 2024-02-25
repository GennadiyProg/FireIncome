package com.example.fireincome.repos;

import com.example.fireincome.model.Category;
import com.example.fireincome.model.Organization;
import com.example.fireincome.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
    List<Product> findAllByCategory(Category category);
    @Query("SELECT p FROM Product p WHERE p.name = :name AND p.category.organization = :organization")
    Optional<Product> findByOrganizationAndName(@Param("organization") Organization organization, @Param("name") String name);
}
