package com.example.fireincome.repos;

import com.example.fireincome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepos extends JpaRepository<User, String> {
}
