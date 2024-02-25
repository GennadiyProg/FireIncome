package com.example.fireincome.service;

import com.example.fireincome.model.Category;
import com.example.fireincome.model.Organization;
import com.example.fireincome.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
	List<Product> findAllByCategory(Category category);

	Product createProduct(Product product);

	Optional<Product> findByOrganizationAndName(Organization organization, String name);
}
