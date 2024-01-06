package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recipes_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @Column(name = "material_amount")
    private Double materialAmount;
}
