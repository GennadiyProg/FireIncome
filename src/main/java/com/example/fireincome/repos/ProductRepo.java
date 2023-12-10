package com.example.fireincome.repos;

import com.example.fireincome.model.Category;
import com.example.fireincome.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
    List<Product> findAllByCategory(Category category);
}
