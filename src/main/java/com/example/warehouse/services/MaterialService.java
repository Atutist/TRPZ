package com.example.warehouse.services;

import com.example.warehouse.entity.Material;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Recipe;
import com.example.warehouse.repositories.MaterialRepository;
import com.example.warehouse.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MaterialService {
    private final MaterialRepository materialRepository;

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public Material getMaterialById(Integer id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found"));
    }
}
