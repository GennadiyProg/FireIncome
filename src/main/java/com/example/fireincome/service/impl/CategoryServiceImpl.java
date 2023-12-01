package com.example.fireincome.service.impl;

import com.example.fireincome.model.Category;
import com.example.fireincome.repos.CategoryRepo;
import com.example.fireincome.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepo categoryRepo;

	@Override
	public Category add(Category category) {
		return categoryRepo.save(category);
	}
}
