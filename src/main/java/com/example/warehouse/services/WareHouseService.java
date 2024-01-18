package com.example.warehouse.services;

import com.example.warehouse.dto.UserResponse;
import com.example.warehouse.entity.Material;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Transaction;
import com.example.warehouse.entity.User;
import com.example.warehouse.factories.TransactionFactory;
import com.example.warehouse.factories.transaction.IssueProductTransactionFactory;
import com.example.warehouse.factories.transaction.ReceiveMaterialTransactionFactory;
import com.example.warehouse.process.IssueProductProcess;
import com.example.warehouse.process.ReceiveMaterialProcess;
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
    private final TransactionService transactionService;
    //==Receiving products==

    public void receiveMaterial(Integer materialId, Double amount, UserResponse currentUser){
        Optional<Material> optionalMaterial = materialRepository.findById(materialId);
        if (optionalMaterial.isPresent()) {
            Material material = optionalMaterial.get();
            Double currentAmount = material.getAmount();
            Double newAmount = currentAmount + amount;
            material.setAmount(newAmount);
            materialRepository.save(material);

            ReceiveMaterialProcess process = new ReceiveMaterialProcess(
                    amount, currentUser.getName(), material,
                    new ReceiveMaterialTransactionFactory(), transactionService);
            process.executeTransaction();
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
        if (product.getAmount() < amount) {
            throw new RuntimeException("Not enough product in stock");
        }

        product.setAmount(product.getAmount() - amount);
        productRepository.save(product);

        IssueProductProcess process = new IssueProductProcess(
                amount, currentUser.getName(), product,
                new IssueProductTransactionFactory(), transactionService); // Use transactionService
        process.executeTransaction();
    }


}
