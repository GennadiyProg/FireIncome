package com.example.fireincome.logic.impl;

import com.example.fireincome.logic.SaleProcessor;
import com.example.fireincome.model.*;
import com.example.fireincome.model.help.ErrorSale;
import com.example.fireincome.model.view.ProcessingResult;
import com.example.fireincome.service.ProductService;
import com.example.fireincome.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleProcessorImpl implements SaleProcessor {
	private final ProductService productService;
	private final UserService userService;
	
	@Override
	public ProcessingResult processSaleTime(ClientSale sale) {
		String time = sale.getTime();
		if (time == null || time.isEmpty()) {
			return new ProcessingResult(new ErrorSale(sale, "Time is undefined"));
		}
		try {
			LocalDateTime parsedTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			if (parsedTime.isAfter(LocalDateTime.now())) {
				return new ProcessingResult(new ErrorSale(sale, "Time from the future"));
			} else {
				return new ProcessingResult(sale);
			}
		} catch (DateTimeParseException e) {
			return new ProcessingResult(new ErrorSale(sale, "Time is not recognized"));
		}
	}

	@Override
	public ProcessingResult processSaleAmount(ClientSale sale) {
		String amount = sale.getAmount();
		if (amount == null || amount.isEmpty()) {
			sale.setAmount("1");
			return new ProcessingResult(sale);
		}
		try {
			int parsedAmount = Integer.parseInt(amount);
			if (parsedAmount == 0) {
				return new ProcessingResult(new ErrorSale(sale, "Amount is 0"));
			} else if (parsedAmount < 0) {
				return new ProcessingResult(new ErrorSale(sale, "Amount is negative"));
				//amount > 1B
			} else if (parsedAmount > 1000000000) {
				return new ProcessingResult(new ErrorSale(sale, "Amount is huge, exceeds the 1.000.000.000"));
			} else {
				return new ProcessingResult(sale);
			}
		} catch (NumberFormatException e) {
			return new ProcessingResult(new ErrorSale(sale, "Amount is not recognized"));
		}
	}

	@Override
	public ProcessingResult processSaleProduct(ClientSale sale, Organization organization) {
		String productName = sale.getProductName();
		if (productName == null || productName.isEmpty()) {
			return new ProcessingResult(new ErrorSale(sale, "Product name is undefined"));
		}
		Optional<Product> foundedProduct = productService.findByOrganizationAndName(organization, productName);
		if (foundedProduct.isEmpty()) {
			return new ProcessingResult(new ErrorSale(sale, "Specified product doesn't sales in yours organization"));
		}
		return new ProcessingResult(sale);
	}

	@Override
	public ProcessingResult processSaleSeller(ClientSale sale, Organization organization) {
		String fio = sale.getSellerFio();
		if (fio == null || fio.isEmpty()) {
			return new ProcessingResult(new ErrorSale(sale, "Seller fio is undefined"));
		}
		String[] separatedFio = fio.split(" ", 2);
		if (separatedFio.length < 2) {
			return new ProcessingResult(new ErrorSale(sale, "Seller fio is incorrect"));
		}
		List<User> sellers = userService.findSellerByFioAndOrganization(separatedFio, organization);
		if (sellers.isEmpty()) {
			return new ProcessingResult(new ErrorSale(sale, "Seller with specified fio not founded"));
		} else if (sellers.size() > 1) {
			return new ProcessingResult(new ErrorSale(sale, "Was founded several seller with specified fio"));
		} else if (!sellers.get(0).isActive()) {
			return new ProcessingResult(new ErrorSale(sale, "Specified seller was founded, but now he does not work in your organization"));
		}
		return new ProcessingResult(sale);
	}

	@Override
	public ProcessingResult processSalePrice(ClientSale sale, Organization organization) {
		String cost = sale.getCost();
		if (cost == null || cost.isEmpty()) {
			ClientSale saleWithCorrectProduct = processSaleProduct(sale, organization).getSavedSale();
			ClientSale saleWithCorrectAmount = processSaleAmount(sale).getSavedSale();
			if (saleWithCorrectProduct == null || saleWithCorrectAmount == null) {
				return new ProcessingResult(new ErrorSale(sale, "Cost, product and amount is undefined"));
			}
			Product foundedProduct = productService.findByOrganizationAndName(organization, sale.getProductName()).orElseGet(Product::new);
			int amount = Integer.parseInt(sale.getAmount());
			sale.setCost(String.valueOf(foundedProduct.getPrice() * amount));
			return new ProcessingResult(sale);
		}
		try {
			int parsedCost = Integer.parseInt(cost);
			if (parsedCost == 0) {
				return new ProcessingResult(new ErrorSale(sale, "Cost is 0"));
			} else if (parsedCost < 0) {
				return new ProcessingResult(new ErrorSale(sale, "Cost is negative"));
			} else {
				return new ProcessingResult(sale);
			}
		} catch (NumberFormatException e) {
			return new ProcessingResult(new ErrorSale(sale, "Cost is not recognized"));
		}
	}
}
