package com.example.fireincome.repos;

import com.example.fireincome.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepos extends JpaRepository<Category, Long> {
}
