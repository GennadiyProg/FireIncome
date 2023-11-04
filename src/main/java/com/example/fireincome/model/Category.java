package com.example.fireincome.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String name;
	@ManyToOne
	private Organization organization;

	public Category(String id) {
		this.id = id;
	}
}
