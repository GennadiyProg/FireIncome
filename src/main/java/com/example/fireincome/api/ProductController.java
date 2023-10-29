package com.example.fireincome.api;

import com.example.fireincome.model.Product;
import com.example.fireincome.model.dto.ProductDto;
import com.example.fireincome.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
	private final ProductService productService;

	@PostMapping("/")
	public Product add(@RequestBody ProductDto productDto) {
		return productService.add(new Product(productDto));
	}
}
