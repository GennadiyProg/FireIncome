package com.example.fireincome.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User seller;
    private int amount;
    private LocalDateTime time;
}
