package com.example.fireincome.model.dto;

import com.example.fireincome.model.MeasureUnit;
import lombok.Data;

@Data
public class ProductDto {
	private long id;
	private String name;
	private long categoryId;
	private MeasureUnit measureUnit;
}
