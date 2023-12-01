package com.example.fireincome.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Users")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String username;
	private String surname;
	private String firstName;
	private String lastName;
	private String password;
	private String passport;
	private boolean active;
	@Enumerated(value = EnumType.STRING)
	private Role role;
}
