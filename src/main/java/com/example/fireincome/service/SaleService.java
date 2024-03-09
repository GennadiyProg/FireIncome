package com.example.fireincome.service;

import com.example.fireincome.model.*;
import com.example.fireincome.model.view.ProcessingResult;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleService {

    int countSalesByCategory(Category category);

    int countSalesByBranch(Branch branch);

    LocalDateTime loadTimeFirstSaleInOrganization(Organization organization);

    LocalDateTime loadTimeLastSaleInOrganization(Organization organization);

    int countSalesInPeriod(LocalDateTime start, LocalDateTime end);

    ProcessingResult process(List<ClientSale> sales, Organization organization);
}
