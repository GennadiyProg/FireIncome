package com.example.fireincome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Users")
@Data
public class User {
	@Id
	private long id;
	private String surname;
	private String firstName;
	private String lastName;
	private Role role;
}
