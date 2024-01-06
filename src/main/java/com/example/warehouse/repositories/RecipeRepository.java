package com.example.warehouse.repositories;


import com.example.warehouse.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByNameIgnoreCase(String name);
    Optional<Recipe> findById(Integer id);

    Optional<Recipe> findByProductId(Integer id);
}
