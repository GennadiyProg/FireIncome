package com.example.fireincome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Organization {
	@Id
	private long id;
	private String name;
	@OneToOne
	private User director;
	@OneToMany
	private List<User> supervisors;
	private String inn;
	@OneToMany
	private List<Branch> branches;
}
