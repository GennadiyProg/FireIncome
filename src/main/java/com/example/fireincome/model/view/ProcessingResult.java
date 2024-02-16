package com.example.fireincome.model.view;

import com.example.fireincome.model.Sale;
import com.example.fireincome.model.help.ErrorSale;
import lombok.Data;

import java.util.List;

@Data
public class ProcessingResult {
    private List<Sale> savedSales;
    private List<ErrorSale> errorSales;
}
