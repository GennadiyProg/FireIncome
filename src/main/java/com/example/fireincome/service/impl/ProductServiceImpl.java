package com.example.fireincome.service.impl;

import com.example.fireincome.model.Product;
import com.example.fireincome.repos.ProductRepos;
import com.example.fireincome.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepos productRepos;

	@Override
	public Product add(Product product) {
		return productRepos.save(product);
	}
}
