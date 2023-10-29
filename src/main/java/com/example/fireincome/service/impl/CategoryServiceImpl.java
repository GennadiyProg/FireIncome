package com.example.fireincome.service.impl;

import com.example.fireincome.model.Category;
import com.example.fireincome.repos.CategoryRepos;
import com.example.fireincome.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepos categoryRepos;

	@Override
	public Category add(Category category) {
		return categoryRepos.save(category);
	}
}
