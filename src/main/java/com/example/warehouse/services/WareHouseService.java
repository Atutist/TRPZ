package com.example.warehouse.services;

import com.example.warehouse.dto.UserResponse;
import com.example.warehouse.entity.Material;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Transaction;
import com.example.warehouse.entity.User;
import com.example.warehouse.repositories.MaterialRepository;
import com.example.warehouse.repositories.ProductRepository;
import com.example.warehouse.repositories.TransactionRepository;
import com.example.warehouse.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jdk.jfr.TransitionTo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class WareHouseService {
    private final MaterialRepository materialRepository;
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    //==Receiving products==
    public void receiveMaterial(Integer materialId, Double amount, UserResponse currentUser){
        Optional<Material> optionalMaterial = materialRepository.findById(materialId);

        if (optionalMaterial.isPresent()) {
            //==Checking and adding material===
            Material material = optionalMaterial.get();
            Double currentAmount = material.getAmount();
            Double newAmount = currentAmount + amount;
            material.setAmount(newAmount);

            materialRepository.save(material);
            //===Creating transaction===
            Transaction transaction = new Transaction.Builder()
                    .setType("RECEIVE")
                    .setMaterial(material)
                    .setAmount(amount)
                    .setDateTime(LocalDateTime.now())
                    .setUsername(String.valueOf(currentUser))
                    .build();


            transactionRepository.save(transaction);
        } else {

            throw new RuntimeException("Material with ID " + materialId + " not found");
        }
    }

    public void issueProduct(Integer productId, Double amount, UserResponse currentUser) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Product with ID " + productId + " not found");
        }

        Product product = optionalProduct.get();

        //=== Do we have enough product ?===
        if (product.getAmount() < amount) {
            throw new RuntimeException("Not enough product in stock");
        }

        //===Logic of taking products out of warehouse==
        Double newAmount = product.getAmount() - amount;
        product.setAmount(newAmount);

        productRepository.save(product);


        Transaction transaction = new Transaction.Builder()
                .setType("RECEIVE")
                .setProduct(product)
                .setAmount(amount)
                .setDateTime(LocalDateTime.now())
                .setUsername(String.valueOf(currentUser))
                .build();

        transactionRepository.save(transaction);
    }

}
