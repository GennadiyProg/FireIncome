package com.example.fireincome.service.impl;

import com.example.fireincome.model.*;
import com.example.fireincome.model.view.ProcessingResult;
import com.example.fireincome.repos.SaleRepo;
import com.example.fireincome.service.CategoryService;
import com.example.fireincome.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepo saleRepo;
    private final CategoryService categoryService;

    @Override
    public Sale createSale(Sale sale) {
        return saleRepo.save(sale);
    }

    @Override
    public int countSalesByCategory(Category category) {
        return saleRepo.countByProduct_Category(category);
    }

    @Override
    public int countSalesByBranch(Branch branch) {
        return saleRepo.countBySellerIn(branch.getSellers());
    }

    @Override
    public LocalDateTime loadTimeFirstSaleInOrganization(Organization organization) {
        return saleRepo.findFirstByProduct_CategoryInOrderByTime(categoryService.findAllByOrganization(organization)).getTime();
    }

    @Override
    public LocalDateTime loadTimeLastSaleInOrganization(Organization organization) {
        return saleRepo.findLastByProduct_CategoryInOrderByTime(categoryService.findAllByOrganization(organization)).getTime();
    }

    @Override
    public int countSalesInPeriod(LocalDateTime start, LocalDateTime end) {
        return saleRepo.countByTimeIsBetween(start, end);
    }

    public ProcessingResult process(List<Sale> sales) {
        return
    }
}
