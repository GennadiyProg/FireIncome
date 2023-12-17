package com.example.fireincome.service;

import com.example.fireincome.model.Branch;
import com.example.fireincome.model.Category;
import com.example.fireincome.model.Organization;
import com.example.fireincome.model.Sale;

import java.time.LocalDateTime;

public interface SaleService {
    Sale createSale(Sale sale);

    int countSalesByCategory(Category category);

    int countSalesByBranch(Branch branch);

    LocalDateTime loadTimeFirstSaleInOrganization(Organization organization);

    LocalDateTime loadTimeLastSaleInOrganization(Organization organization);

    int countSalesInPeriod(LocalDateTime start, LocalDateTime end);
}
