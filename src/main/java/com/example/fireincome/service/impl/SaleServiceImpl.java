package com.example.fireincome.service.impl;

import com.example.fireincome.logic.SaleProcessor;
import com.example.fireincome.model.*;
import com.example.fireincome.model.help.ErrorSale;
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
    private final SaleProcessor saleProcessor;

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

    public ProcessingResult process(List<ClientSale> sales, Organization organization) {
        List<ClientSale> successfulSales = new ArrayList<>();
        List<ErrorSale> errorSales = new ArrayList<>();
        for (ClientSale sale: sales) {
            ProcessingResult result = saleProcessor.processSaleTime(sale);
            recognizeProcessingResult(result, successfulSales, errorSales);
            result = saleProcessor.processSaleAmount(sale);
            recognizeProcessingResult(result, successfulSales, errorSales);
            result = saleProcessor.processSaleProduct(sale, organization);
            recognizeProcessingResult(result, successfulSales, errorSales);
            result = saleProcessor.processSaleSeller(sale, organization);
            recognizeProcessingResult(result, successfulSales, errorSales);
            result = saleProcessor.processSalePrice(sale, organization);
            recognizeProcessingResult(result, successfulSales, errorSales);
        }
        //Todo нормальный возврат сделать
        return null;
    }

    private void recognizeProcessingResult(ProcessingResult result, List<ClientSale> successfulSales, List<ErrorSale> errorSales) {
        if (result.getSavedSale() != null) {
            successfulSales.add(result.getSavedSale());
        } else {
            errorSales.add(result.getErrorSale());
        }
    }
}
