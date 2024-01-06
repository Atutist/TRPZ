package com.example.warehouse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @NotNull
    @Column(name ="name")
    private String name;

    @NotNull
    @Column(name ="amount")
    private Double amount; // Changed to BigDecimal

    @OneToMany(mappedBy = "product")
    private Set<Recipe> recipes;
}
