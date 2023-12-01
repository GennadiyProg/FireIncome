package com.example.fireincome.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String name;
	@OneToOne
	private User director;
	@OneToMany
	private List<User> supervisors = new ArrayList<>();
	private String inn;
	@OneToMany
	private List<Branch> branches = new ArrayList<>();
}
