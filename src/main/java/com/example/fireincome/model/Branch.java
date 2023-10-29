package com.example.fireincome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Branch {
	@Id
	private long id;
	private String city;
	private String street;
	private String house;
	private String kpp;
	@OneToMany
	private List<User> sellers;
}
