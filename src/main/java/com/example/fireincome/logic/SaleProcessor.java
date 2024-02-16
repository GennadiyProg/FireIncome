package com.example.fireincome.logic;

import com.example.fireincome.model.Sale;
import com.example.fireincome.model.view.ProcessingResult;

import java.util.List;

public interface SaleProcessor {
    ProcessingResult processSaleTime(List<Sale> sales);
    ProcessingResult processSaleAmount(List<Sale> sales);
    ProcessingResult processSaleProduct(List<Sale> sales);
    ProcessingResult processSaleSeller(List<Sale> sales);
    ProcessingResult processSalePrice(List<Sale> sales);
}
