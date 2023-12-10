package com.example.fireincome.service.impl;

import com.example.fireincome.model.Category;
import com.example.fireincome.model.Organization;
import com.example.fireincome.repos.CategoryRepo;
import com.example.fireincome.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepo categoryRepo;

	@Override
	public Category createCategory(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	public List<Category> findAllByOrganization(Organization organization) {
		return categoryRepo.findAllByOrganization(organization);
	}

	@Override
	public Category findById(String id) {
		return categoryRepo.findById(id).orElseGet(Category::new);
	}
}
