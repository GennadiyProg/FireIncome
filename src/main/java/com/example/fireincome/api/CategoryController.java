package com.example.fireincome.api;

import com.example.fireincome.model.Category;
import com.example.fireincome.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
	private final CategoryService categoryService;

	@PostMapping("/")
	public Category add(@RequestBody Category category) {
		return categoryService.add(category);
	}
}
