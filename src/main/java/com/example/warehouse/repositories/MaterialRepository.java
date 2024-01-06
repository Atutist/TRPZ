package com.example.warehouse.repositories;

import com.example.warehouse.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository <Material, Long> {
    Optional<Material> findById(Integer id);
}
