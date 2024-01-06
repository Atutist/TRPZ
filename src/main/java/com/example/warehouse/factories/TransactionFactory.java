package com.example.warehouse.factories;

import com.example.warehouse.entity.Material;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Transaction;

public interface TransactionFactory {
    Transaction createTransaction(String type, Double amount, String username, Material material, Product product);
}
