package com.example.fireincome.repos;

import com.example.fireincome.model.Branch;
import com.example.fireincome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepo extends JpaRepository<Branch, String> {
    Optional<Branch> findByKpp(String kpp);
    void deleteByKpp(String kpp);

    List<Branch> findAllBySellersIsContaining(User seller);
}
