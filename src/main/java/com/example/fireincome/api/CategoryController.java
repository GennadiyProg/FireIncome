package com.example.fireincome.api;

import com.example.fireincome.model.Category;
import com.example.fireincome.model.Organization;
import com.example.fireincome.model.Product;
import com.example.fireincome.service.CategoryService;
import com.example.fireincome.service.OrganizationService;
import com.example.fireincome.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@PreAuthorize("hasAnyRole('CHIEF', 'SUPERVISOR', 'SELLER')")
public class CategoryController {
	private final CategoryService categoryService;
	private final OrganizationService organizationService;
	private final ProductService productService;

	@GetMapping("/")
	public List<Category> allCategories(Principal principal) {
		return categoryService.findAllByOrganization(getOrganizationByPrincipal(principal));
	}

	@PostMapping("/create")
	public Category createCategory(@RequestBody Category category, Principal principal) {
		category.setOrganization(getOrganizationByPrincipal(principal));
		return categoryService.createCategory(category);
	}

	@GetMapping("/{categoryId}/product")
	public List<Product> allProductsByCategory(@PathVariable String categoryId) {
		Category category = categoryService.findById(categoryId);
		return productService.findAllByCategory(category);
	}

	@PostMapping("/{categoryId}/product")
	public Product createProduct(@PathVariable String categoryId, @RequestBody Product product) {
		Category category = categoryService.findById(categoryId);
		product.setCategory(category);
		product.setSelling(true);
		return productService.createProduct(product);
	}

	private Organization getOrganizationByPrincipal(Principal principal) {
		Optional<Organization> org = organizationService.loadByDirectoryUsername(principal.getName());
		if (org.isEmpty()) {
			org = organizationService.loadBySupervisor(principal.getName());
			if (org.isEmpty()) {
				org = organizationService.loadBySeller(principal.getName());
			}
		}
		return org.orElseGet(Organization::new);
	}
}
