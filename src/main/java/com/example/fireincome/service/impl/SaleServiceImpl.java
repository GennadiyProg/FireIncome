package com.example.fireincome.service.impl;

import com.example.fireincome.model.Sale;
import com.example.fireincome.repos.SaleRepo;
import com.example.fireincome.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepo saleRepo;

    @Override
    public Sale createSale(Sale sale) {
        return saleRepo.save(sale);
    }
}
