package com.example.fireincome.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String city;
	private String street;
	private String house;
	private String kpp;
	@OneToMany
	private List<User> sellers = new ArrayList<>();
}
