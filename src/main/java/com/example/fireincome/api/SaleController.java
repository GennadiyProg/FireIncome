package com.example.fireincome.api;

import com.example.fireincome.model.Sale;
import com.example.fireincome.model.User;
import com.example.fireincome.service.SaleService;
import com.example.fireincome.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sale")
@PreAuthorize("hasAnyRole('SELLER')")
public class SaleController {
    private final SaleService saleService;
    private final UserService userService;

    @PostMapping("/create")
    public Sale createSale(@RequestBody Sale sale, Principal principal) {
        User seller = userService.findByUsername(principal.getName());
        sale.setSeller(seller);
        return saleService.createSale(sale);
    }
}
