package com.example.fireincome.repos;

import com.example.fireincome.model.Category;
import com.example.fireincome.model.Sale;
import com.example.fireincome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleRepo extends JpaRepository<Sale, String> {
    int countByProduct_Category(Category category);

    int countBySellerIn(List<User> sellers);

    @Query("select s from Sale s where s.product.category in (?1) order by s.time limit 1")
    Sale findFirstByProduct_CategoryInOrderByTime(List<Category> categories);

    @Query("select s from Sale s where s.product.category in (?1) order by s.time desc limit 1")
    Sale findLastByProduct_CategoryInOrderByTime(List<Category> categories);

    int countByTimeIsBetween(LocalDateTime start, LocalDateTime end);
}
