package com.example.fireincome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ClientSale {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String productName;
	private String sellerFio;
	private String amount;
	private String cost;
	private String time;
}
