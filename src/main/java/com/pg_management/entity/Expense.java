package com.pg_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // e.g., Grocery, Electricity
    private Double amount;
    private String description;
}
