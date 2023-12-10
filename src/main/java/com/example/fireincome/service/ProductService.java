package com.example.fireincome.service;

import com.example.fireincome.model.Category;
import com.example.fireincome.model.Product;

import java.util.List;

public interface ProductService {
	List<Product> findAllByCategory(Category category);

	Product createProduct(Product product);
}
