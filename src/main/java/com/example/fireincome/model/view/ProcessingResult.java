package com.example.fireincome.model.view;

import com.example.fireincome.model.ClientSale;
import com.example.fireincome.model.Sale;
import com.example.fireincome.model.help.ErrorSale;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProcessingResult {
    private ClientSale savedSale;
    private ErrorSale errorSale;

    public ProcessingResult(ClientSale savedSale) {
        this.savedSale = savedSale;
    }

    public ProcessingResult(ErrorSale errorSale) {
        this.errorSale = errorSale;
    }
}
