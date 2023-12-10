package com.example.fireincome.service.impl;

import com.example.fireincome.model.Category;
import com.example.fireincome.model.Product;
import com.example.fireincome.repos.ProductRepo;
import com.example.fireincome.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepo productRepo;


	@Override
	public List<Product> findAllByCategory(Category category) {
		return productRepo.findAllByCategory(category);
	}

	@Override
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}
}
