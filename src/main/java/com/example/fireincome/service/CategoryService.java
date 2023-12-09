package com.example.fireincome.service;

import com.example.fireincome.model.Category;
import com.example.fireincome.model.Organization;

import java.util.List;

public interface CategoryService {
	Category createCategory(Category category);

	List<Category> findAllByOrganization(Organization organization);
}
