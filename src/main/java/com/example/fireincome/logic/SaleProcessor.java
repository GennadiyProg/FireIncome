package com.example.fireincome.logic;

import com.example.fireincome.model.ClientSale;
import com.example.fireincome.model.Organization;
import com.example.fireincome.model.Sale;
import com.example.fireincome.model.view.ProcessingResult;

import java.util.List;

public interface SaleProcessor {
    ProcessingResult processSaleTime(ClientSale sales);
    ProcessingResult processSaleAmount(ClientSale sales);
    ProcessingResult processSaleProduct(ClientSale sales, Organization organization);
    ProcessingResult processSaleSeller(ClientSale sales, Organization organization);
    ProcessingResult processSalePrice(ClientSale sales, Organization organization);
}
