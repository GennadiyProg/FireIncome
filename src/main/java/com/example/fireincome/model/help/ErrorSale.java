package com.example.fireincome.model.help;

import com.example.fireincome.model.ClientSale;
import com.example.fireincome.model.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorSale {
    private ClientSale sale;
    private String reason;
}
