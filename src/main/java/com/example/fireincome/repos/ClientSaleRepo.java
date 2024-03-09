package com.example.fireincome.repos;

import com.example.fireincome.model.ClientSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientSaleRepo extends JpaRepository<ClientSale, String> {
}
