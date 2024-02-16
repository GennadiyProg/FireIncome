package com.example.fireincome.model.help;

import com.example.fireincome.model.Sale;
import lombok.Data;

@Data
public class ErrorSale {
    private Sale sale;
    private String reason;
}
