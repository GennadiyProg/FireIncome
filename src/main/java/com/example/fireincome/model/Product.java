package com.example.fireincome.model;

import com.example.fireincome.model.dto.ProductDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String name;
	@ManyToOne
	private Category category;
	private MeasureUnit measureUnit;

	public Product(ProductDto dto) {
		this.name = dto.getName();
		this.category = new Category(dto.getCategoryId());
		this.measureUnit = dto.getMeasureUnit();
	}
}
