package com.example.fireincome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Users")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String surname;
	private String firstName;
	private String lastName;
	@JsonIgnore
	private String password;
	private String passport;
	private boolean active;
	@Enumerated(value = EnumType.STRING)
	private Role role;
}
