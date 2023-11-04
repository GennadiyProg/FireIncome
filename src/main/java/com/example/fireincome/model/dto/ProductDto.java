package com.example.fireincome.model.dto;

import com.example.fireincome.model.MeasureUnit;
import lombok.Data;

@Data
public class ProductDto {
	private String id;
	private String name;
	private String categoryId;
	private MeasureUnit measureUnit;
}
