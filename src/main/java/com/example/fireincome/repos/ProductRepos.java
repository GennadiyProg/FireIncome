package com.example.fireincome.repos;

import com.example.fireincome.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepos extends JpaRepository<Product, Long> {
}
