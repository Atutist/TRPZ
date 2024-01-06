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
@Table(name= "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name ="name")
    private String name;

    @NotNull
    @Column(name ="amount")
    private Double amount;

    @Column(name = "minimum_stock")
    private Double minimumStock;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    private Set<RecipeList> recipeLists;


}
