package com.example.fireincome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Category {
	@Id
	private long id;
	private String name;
	@ManyToOne
	private Organization organization;

	public Category(long id) {
		this.id = id;
	}
}
