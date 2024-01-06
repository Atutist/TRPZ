package com.example.warehouse.repositories;

import com.example.warehouse.entity.RecipeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeListRepository extends JpaRepository<RecipeList,Long> {
    Optional<RecipeList> findById(Integer id);
}
